@echo off
setlocal
set PS=%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe
"%PS%" -NoLogo -NoProfile -ExecutionPolicy Bypass -File "%~dp0PCDetails.ps1"
pause
