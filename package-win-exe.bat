@echo off
setlocal

REM Always run from the project root (same folder that has dist, src, etc.)
pushd "%~dp0"

REM Prefer jpackage from JAVA_HOME
set "JPACKAGE=%JAVA_HOME%\bin\jpackage.exe"
if not exist "%JPACKAGE%" set "JPACKAGE=jpackage"

echo Using jpackage: %JPACKAGE%

REM App settings
set "APP_NAME=EduHub"
set "APP_VERSION=1.0.2"
set "MAIN_JAR=Information-Management--Project-BSCS-2A.jar"
set "ICON=branding\EduHub.ico"

REM Validate build output
if not exist "dist\%MAIN_JAR%" (
  echo dist\%MAIN_JAR% not found. In NetBeans: Clean and Build, then update MAIN_JAR if the name differs.
  popd & exit /b 1
)

if not exist "resources" (
  echo resources folder not found. Creating...
  mkdir resources
)

mkdir out\installer 2>nul

"%JPACKAGE%" ^
  --type exe ^
  --name "%APP_NAME%" ^
  --app-version "%APP_VERSION%" ^
  --dest out\installer ^
  --input dist ^
  --main-jar "%MAIN_JAR%" ^
  --resource-dir resources ^
  --win-menu ^
  --win-shortcut ^
  --win-per-user-install ^
  --icon "%ICON%"

if errorlevel 1 (
  echo.
  echo jpackage failed.
  popd & exit /b 1
)

echo.
echo Installer created in out\installer
popd
endlocal