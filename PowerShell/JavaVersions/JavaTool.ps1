
param(
  [string]$CsvPath = ("C:\Temp\Java-Inventory-{0}.csv" -f $env:COMPUTERNAME)
)
# =========================[ Banner Section ]=========================
$host.UI.RawUI.WindowTitle = "Java Version Manager - Audit & Clean"

Write-Host ""
Write-Host "==============================================================" -ForegroundColor Cyan
Write-Host "              Java Version Manager - Audit & Clean             " -ForegroundColor Yellow
Write-Host "==============================================================" -ForegroundColor Cyan
Write-Host ""
Write-Host " This tool will:" -ForegroundColor Green
Write-Host "   1. Audit all installed Java/JDK/JRE versions on this PC" -ForegroundColor White
Write-Host "   2. Save a report to CSV for review" -ForegroundColor White
Write-Host "   3. Optionally uninstall versions NOT in the approved keep list" -ForegroundColor White
Write-Host ""
Write-Host " Defaults: Keeps versions 17 and 21 (customizable)" -ForegroundColor DarkGray
Write-Host "==============================================================" -ForegroundColor Cyan
Write-Host ""
Start-Sleep -Seconds 2
# ===================================================================
# =========================[ INVENTORY SECTION ]========================


if (-not (Test-Path (Split-Path $CsvPath))) {
  New-Item -ItemType Directory -Force -Path (Split-Path $CsvPath) | Out-Null
}

function Get-UninstallEntries {
  $roots = @(
    'HKLM:\Software\Microsoft\Windows\CurrentVersion\Uninstall\*',
    'HKLM:\Software\WOW6432Node\Microsoft\Windows\CurrentVersion\Uninstall\*'
  )

  $items = foreach ($r in $roots) {
    Get-ItemProperty -Path $r -ErrorAction SilentlyContinue
  }

  $items |
  Where-Object {
    $_.DisplayName -and (
      $_.DisplayName -match '(?i)\b(java|jdk|jre|openjdk|temurin|adoptopenjdk|microsoft build of openjdk)\b'
    )
  } |


    ForEach-Object {
      [pscustomobject]@{
        Source            = 'Registry'
        Name              = $_.DisplayName
        Version           = $_.DisplayVersion
        Vendor            = $_.Publisher
        InstallLocation   = $_.InstallLocation
        UninstallString   = $_.UninstallString
        QuietUninstall    = $_.QuietUninstallString
        RegKey            = $_.PSPath
        MsiGuid           = ([regex]::Match($_.UninstallString, '\{[0-9A-Fa-f\-]{36}\}').Value)
        Architecture      = if ($_.PSPath -match 'WOW6432Node') {'x86'} else {'x64'}
      }
    }
}


function Get-FolderClues {
  $paths = @(
    'C:\Program Files\Java',
    'C:\Program Files (x86)\Java',
    'C:\Program Files\Eclipse Adoptium',
    'C:\Program Files\AdoptOpenJDK',
    'C:\Program Files\Microsoft\jdk',
    'C:\Program Files (x86)\Eclipse Adoptium',
    'C:\Program Files\RedHat\java',
    'C:\Program Files\Zulu'
  ) | Where-Object { Test-Path $_ }

  foreach ($p in $paths) {
    Get-ChildItem -Path $p -Directory -ErrorAction SilentlyContinue | ForEach-Object {
      $binJava = Join-Path $_.FullName 'bin\java.exe'
      $ver = $null
      if (Test-Path $binJava) {
        $raw = & $binJava -version 2>&1
        $match = [regex]::Match(($raw -join ' '), '"([\d\.\-_+a-zA-Z]+)"')
        if ($match.Success) { $ver = $match.Groups[1].Value }
      }
      [pscustomobject]@{
        Source          = 'Folder'
        Name            = $_.Name
        Version         = $ver
        Vendor          = (Split-Path (Split-Path $_.FullName -Parent) -Leaf)
        InstallLocation = $_.FullName
        UninstallString = $null
        QuietUninstall  = $null
        RegKey          = $null
        MsiGuid         = $null
        Architecture    = if ($_.FullName -match '\(x86\)') {'x86'} else {'x64'}
      }
    }
  }
}

function Get-PathVersion {
  try {
    $raw = & java -version 2>&1
    $match = [regex]::Match(($raw -join ' '), '"([\d\.\-_+a-zA-Z]+)"')
    $ver = if ($match.Success) { $match.Groups[1].Value } else { $null }
    [pscustomobject]@{
      Source          = 'PATH'
      Name            = 'java (PATH)'
      Version         = $ver
      Vendor          = $null
      InstallLocation = (Get-Command java -ErrorAction SilentlyContinue | ForEach-Object Source) -as [string]
      UninstallString = $null
      QuietUninstall  = $null
      RegKey          = $null
      MsiGuid         = $null
      Architecture    = $env:PROCESSOR_ARCHITECTURE
    }
  } catch {
    $null
  }
}

Write-Host "Scanning installed Java/JDK/JRE...`n" -ForegroundColor Green

$reg  = @(Get-UninstallEntries)
$fold = @(Get-FolderClues)
$path = @(Get-PathVersion)

$all = @($reg + $fold + $path) | Where-Object { $_ } |
       Sort-Object InstallLocation, Name, Version -Unique


# Save CSV and print a readable table
$all | Export-Csv -Path $CsvPath -NoTypeInformation
Write-Host ("Report written to: {0}" -f $CsvPath) -ForegroundColor Yellow

$all | Select-Object Source, Name, Version, Vendor, Architecture, InstallLocation |
  Format-Table -AutoSize

# If nothing found, say so clearly
if (($all | Measure-Object).Count -eq 0) {
  Write-Host "`nNo Java-like installations were found." -ForegroundColor DarkYellow
}
# ====================================================================

# =========================[ Uninstall Options ]=========================

$response = Read-Host "`nDo you want to uninstall Java versions? (Y/N)"
if ($response -match '^(Y|y)') {

  Write-Host "`nChoose uninstall mode:" -ForegroundColor Yellow
  Write-Host "  1. Default cleanup (keep only approved versions, remove all others)"
  Write-Host "  2. Manual selection (pick versions to uninstall by number)"
  Write-Host "  3. Exit (show installed versions and quit)"   # <-- add this line
  $mode = Read-Host "Enter 1, 2, or 3"
  

    # --- Option 1: Default cleanup ---
    if ($mode -eq '1') {
        $keepVersions = @('17','21')   # <-- adjust this keep list
        Write-Host "`nRunning default cleanup... keeping versions: $($keepVersions -join ', ')" -ForegroundColor Cyan

        $removeList = $all | Where-Object {
            $ver = $_.Version
            -not ($keepVersions | Where-Object { $ver -match $_ })
        }

        if (-not $removeList) {
            Write-Host "All installed Java versions are in the keep list. Nothing to remove." -ForegroundColor Green
        } else {
            foreach ($choice in $removeList) {
                Write-Host "`nUninstalling: $($choice.Name) ($($choice.Version))" -ForegroundColor Red
                if ($choice.UninstallString) {
                    $cmd = $choice.UninstallString
                    if ($cmd -match '\{[0-9A-Fa-f\-]{36}\}') {
                        Start-Process msiexec.exe -ArgumentList "/x $cmd /qn /norestart" -Wait
                    } else {
                        if ($cmd -notmatch '(?i)/S|/quiet|/qn') { $cmd += " /S" }
                        Start-Process cmd.exe -ArgumentList "/c $cmd" -Wait
                    }
                } else {
                    Write-Warning "No uninstall command found for $($choice.Name). Skipping."
                }
            }
        }

    # --- Option 2: Manual selection ---
    } elseif ($mode -eq '2') {
        Write-Host "`nSelect the versions you want to remove:" -ForegroundColor Yellow
        $i = 1
        $all | ForEach-Object {
            Write-Host ("{0}. {1} (Version: {2})" -f $i, $_.Name, $_.Version)
            $i++
        }

        $selection = Read-Host "`nEnter the number(s) to uninstall (comma separated, e.g., 1,3)"
        $indexes = $selection -split ',' | ForEach-Object { $_.Trim() } | Where-Object { $_ -match '^\d+$' }

        foreach ($idx in $indexes) {
            $choice = $all[[int]$idx - 1]
            if ($choice) {
                Write-Host "`nUninstalling: $($choice.Name) ($($choice.Version))" -ForegroundColor Red
                if ($choice.UninstallString) {
                    $cmd = $choice.UninstallString
                    if ($cmd -match '\{[0-9A-Fa-f\-]{36}\}') {
                        Start-Process msiexec.exe -ArgumentList "/x $cmd /qn /norestart" -Wait
                    } else {
                        if ($cmd -notmatch '(?i)/S|/quiet|/qn') { $cmd += " /S" }
                        Start-Process cmd.exe -ArgumentList "/c $cmd" -Wait
                    }
                } else {
                    Write-Warning "No uninstall command found for $($choice.Name). Skipping."
                }
            }
        }
       # --- Option 3: Exit ---
      } elseif ($mode -eq '3') {
        Write-Host "`nInstalled Java versions on this host:" -ForegroundColor Cyan
        $all | Select-Object Name, Version, Vendor, InstallLocation |
          Format-Table -AutoSize
        Write-Host "`nExiting without uninstall." -ForegroundColor DarkGray
        exit

    } else {
        Write-Host "Invalid option. No uninstall actions taken." -ForegroundColor DarkGray
    }

    Write-Host "`nDone. Re-run the scan to confirm removals." -ForegroundColor Green
} else {
    Write-Host "`nNo uninstall actions taken." -ForegroundColor DarkGray
}

