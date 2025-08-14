# --- Self-elevate if not admin ---
if (-not ([Security.Principal.WindowsPrincipal][Security.Principal.WindowsIdentity]::GetCurrent()
    ).IsInRole([Security.Principal.WindowsBuiltinRole]::Administrator)) {
    Start-Process -FilePath "powershell.exe" -ArgumentList "-NoProfile -ExecutionPolicy Bypass -File `"$PSCommandPath`"" -Verb RunAs
    exit
}

# --- Helpers ---
function Convert-FromDmtf([string]$d){
  try{ if([string]::IsNullOrWhiteSpace($d) -or $d.Length -lt 14){$null}else{[Management.ManagementDateTimeConverter]::ToDateTime($d)} }catch{$null}
}
function Get-LocalAdmins {
  try {
    ($([ADSI]"WinNT://$env:COMPUTERNAME/Administrators,group").psbase.Invoke('Members') | ForEach-Object{
      $_.GetType().InvokeMember('Adspath','GetProperty',$null,$_,$null) -replace '^WinNT://','' -replace '/','\'
    }) | Sort-Object
  } catch { @('<unable to query>') }
}
function Try-ParseDate {
    param([object]$Value)
    if ($null -eq $Value) { return $null }
    $out = [datetime]::MinValue
    if ([datetime]::TryParse([string]$Value, [ref]$out)) { $out } else { $null }
}
function Convert-FromDmtf([string]$d){
  try{ if([string]::IsNullOrWhiteSpace($d) -or $d.Length -lt 14){$null}else{[Management.ManagementDateTimeConverter]::ToDateTime($d)} }catch{$null}
}


# --- Gather data (LOCAL PC ONLY – no WinRM needed) ---
$os   = Get-CimInstance Win32_OperatingSystem -ErrorAction SilentlyContinue
$cs   = Get-CimInstance Win32_ComputerSystem -ErrorAction SilentlyContinue
$bios = Get-CimInstance Win32_BIOS -ErrorAction SilentlyContinue
$cpu  = Get-CimInstance Win32_Processor -ErrorAction SilentlyContinue
$dds  = Get-CimInstance Win32_DiskDrive -ErrorAction SilentlyContinue
$lds  = Get-CimInstance Win32_LogicalDisk -Filter "DriveType=3" -ErrorAction SilentlyContinue
$nics = Get-CimInstance Win32_NetworkAdapterConfiguration -Filter "IPEnabled=True" -ErrorAction SilentlyContinue
$qfe  = Get-CimInstance Win32_QuickFixEngineering -ErrorAction SilentlyContinue

$boot = Convert-FromDmtf $os.LastBootUpTime
if (-not $boot) {
  $perf = Get-CimInstance Win32_PerfFormattedData_PerfOS_System -ErrorAction SilentlyContinue
  if ($perf -and $perf.SystemUpTime) { $boot = (Get-Date).AddSeconds(-[double]$perf.SystemUpTime) }
}
$uptime = if ($boot) { (Get-Date) - $boot } else { $null }

$diskDetails = $dds | Select-Object `
  @{n='Index';e={$_.Index}},
  @{n='Model';e={$_.Model}},
  @{n='BusType';e={$_.InterfaceType}},
  @{n='SizeGB';e={ [math]::Round($_.Size/1GB,2) }}

$volDetails = $lds | Select-Object `
  @{n='Drive';e={$_.DeviceID}},
  @{n='Label';e={$_.VolumeName}},
  @{n='FS';e={$_.FileSystem}},
  @{n='SizeGB';e={[math]::Round($_.Size/1GB,2)}},
  @{n='FreeGB';e={[math]::Round($_.FreeSpace/1GB,2)}}

$ipv4 = $nics | ForEach-Object { $_.IPAddress } | Where-Object { $_ -match '^\d{1,3}(\.\d{1,3}){3}$' }
$macs = $nics | ForEach-Object { $_.MACAddress } | Where-Object { $_ }

$recentPatches = foreach ($h in (Get-HotFix -ErrorAction SilentlyContinue)) {
    $dt = Try-ParseDate $h.InstalledOn
    if ($dt -and $dt -ge (Get-Date).AddDays(-30)) {
        [pscustomobject]@{
            HotFixID    = $h.HotFixID
            Description = $h.Description
            InstalledOn = $dt
        }
    }
}


$admins = Get-LocalAdmins

$summary = [pscustomobject]@{
    ComputerName        = $env:COMPUTERNAME
    Manufacturer        = $cs.Manufacturer
    Model               = $cs.Model
    SerialNumber        = $bios.SerialNumber
    OS                  = "$($os.Caption) ($($os.OSArchitecture))"
    OSBuild             = "$($os.Version) / Build $($os.BuildNumber)"
    InstallDate         = (Convert-FromDmtf $os.InstallDate)
    LastBoot            = $boot
    UptimeDays          = if ($uptime) { [math]::Round($uptime.TotalDays,1) } else { $null }
    LoggedOnUser        = $cs.UserName
    CPU                 = $cpu.Name
    Cores               = ($cpu.NumberOfCores | Measure-Object -Sum).Sum
    LogicalProcessors   = ($cpu.NumberOfLogicalProcessors | Measure-Object -Sum).Sum
    RAM_GB              = [math]::Round($cs.TotalPhysicalMemory/1GB,2)
    DiskCount           = ($dds | Measure-Object).Count
    TotalDisk_GB        = [math]::Round(($dds | Measure-Object -Property Size -Sum).Sum/1GB,2)
    SystemDrive         = $os.SystemDrive
    SystemDriveFree_GB  = ($lds | Where-Object DeviceID -eq $os.SystemDrive | ForEach-Object { [math]::Round($_.FreeSpace/1GB,2) })
    IPv4                = ($ipv4 -join ', ')
    MACs                = ($macs -join ', ')
    LocalAdmins         = ($admins -join ', ')
}


# --- Output files (Desktop) ---
$stamp   = (Get-Date).ToString('yyyyMMdd_HHmmss')
$base    = "PCDetails_$($summary.ComputerName)_$stamp"
$desktop = [Environment]::GetFolderPath('Desktop')
$htmlOut = Join-Path $desktop "$base.html"
$csvOut  = Join-Path $desktop "$base.csv"

# CSV (summary only)
$summary | Export-Csv -Path $csvOut -NoTypeInformation -Encoding UTF8

# HTML (summary + tables)
$style = @"
<style>
body{font-family:Segoe UI, Arial, sans-serif; margin:16px;}
h1{font-size:20px;margin-bottom:6px}
table{border-collapse:collapse;width:100%;margin:10px 0}
th,td{border:1px solid #ddd;padding:6px;text-align:left}
th{background:#f3f3f3}
.small{font-size:12px;color:#444}
</style>
"@
$sections = @()
$sections += ($summary | ConvertTo-Html -As List -PreContent "<h1>PC Summary</h1>" -Fragment)
$sections += ($diskDetails | ConvertTo-Html -PreContent "<h1>Physical Disks</h1>" -Fragment)
$sections += ($volDetails  | ConvertTo-Html -PreContent "<h1>Volumes</h1>" -Fragment)
$sections += (($admins | ForEach-Object {[pscustomobject]@{LocalAdministrator=$_}}) |
              ConvertTo-Html -PreContent "<h1>Local Administrators</h1>" -Fragment)
if ($recentPatches) {
  $sections += ($recentPatches | ConvertTo-Html -PreContent "<h1>Recent Patches (30 days)</h1>" -Fragment)
}

$report = ConvertTo-Html -Title "PC Details - $($summary.ComputerName)" -Head $style `
          -PostContent "<div class='small'>Report created: $(Get-Date)</div>" `
          -Body ($sections -join "`n")
$report | Set-Content -Path $htmlOut -Encoding UTF8

# Console summary + open report
$summary | Format-List *
Write-Host "`nSaved:" -ForegroundColor Cyan
Write-Host "  HTML: $htmlOut"
Write-Host "  CSV : $csvOut`n"
Start-Process $htmlOut
