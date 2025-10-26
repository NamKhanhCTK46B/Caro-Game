@echo off
REM ========================================
REM INSTALL MAVEN - Smart Installer
REM Tac gia: 2212391 - Nguyen Hoang Nam Khanh
REM ========================================

echo.
echo ==========================================
echo   MAVEN AUTO INSTALLER
echo ==========================================
echo.

REM Check if running as Administrator
net session >nul 2>&1
if %errorLevel% equ 0 (
    set "INSTALL_MODE=ADMIN"
    set "INSTALL_DIR=C:\Program Files\Apache\maven"
    echo [INFO] Che do: Administrator
    echo [INFO] Cai dat toan he thong
) else (
    set "INSTALL_MODE=USER"
    set "INSTALL_DIR=%USERPROFILE%\maven"
    echo [INFO] Che do: User
    echo [INFO] Cai dat cho user hien tai
    echo [INFO] Khong can quyen Admin
)

echo.
echo Cai dat Maven 3.9.6 vao:
echo   %INSTALL_DIR%
echo.
pause

echo.
echo [1/5] Kiem tra Java...
java -version >nul 2>&1
if %errorLevel% neq 0 (
    echo [ERROR] Java chua duoc cai dat!
    echo.
    echo Vui long cai dat JDK 11+ tu:
    echo   https://adoptium.net/
    pause
    exit /b 1
)
java -version
echo [OK] Java da san sang
echo.

echo [2/5] Kiem tra Maven cu...
if exist "%INSTALL_DIR%" (
    echo [WARNING] Maven da ton tai!
    echo Xoa phien ban cu? (Y/N)
    set /p confirm=
    if /i "%confirm%"=="Y" (
        rmdir /s /q "%INSTALL_DIR%"
        echo [OK] Da xoa phien ban cu
    ) else (
        echo [INFO] Giu nguyen, thoat...
        pause
        exit /b 0
    )
)

echo.
echo [3/5] Dang tai Maven 3.9.6...
echo Vui long doi (1-2 phut)...
echo.

REM Create parent directory
if "%INSTALL_MODE%"=="ADMIN" (
    if not exist "C:\Program Files\Apache" mkdir "C:\Program Files\Apache"
)

REM Download using PowerShell with fallback mirrors
powershell -Command "Write-Host 'Downloading...'; [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; $ProgressPreference = 'SilentlyContinue'; $urls = @('https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip', 'https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip'); foreach ($url in $urls) { try { Invoke-WebRequest -Uri $url -OutFile '%TEMP%\maven.zip' -ErrorAction Stop; break } catch { Write-Host \"Mirror failed, trying next...\" } }"

if not exist "%TEMP%\maven.zip" (
    echo [ERROR] Tai that bai!
    echo Kiem tra ket noi internet.
    pause
    exit /b 1
)
echo [OK] Tai thanh cong
echo.

echo [4/5] Giai nen Maven...
if "%INSTALL_MODE%"=="ADMIN" (
    powershell -Command "Expand-Archive -Path '%TEMP%\maven.zip' -DestinationPath 'C:\Program Files\Apache' -Force"
    ren "C:\Program Files\Apache\apache-maven-3.9.6" maven
) else (
    powershell -Command "Expand-Archive -Path '%TEMP%\maven.zip' -DestinationPath '%USERPROFILE%' -Force"
    ren "%USERPROFILE%\apache-maven-3.9.6" maven
)
echo [OK] Giai nen thanh cong
echo.

echo [5/5] Cau hinh bien moi truong...

if "%INSTALL_MODE%"=="ADMIN" (
    REM System-wide installation
    setx MAVEN_HOME "%INSTALL_DIR%" /M >nul 2>&1
    
    for /f "tokens=2*" %%a in ('reg query "HKLM\SYSTEM\CurrentControlSet\Control\Session Manager\Environment" /v Path ^| find "REG_"') do set "SystemPath=%%b"
    echo %SystemPath% | find /i "maven\bin" >nul
    if %errorLevel% neq 0 (
        setx PATH "%SystemPath%;%INSTALL_DIR%\bin" /M >nul 2>&1
    )
) else (
    REM User installation
    setx MAVEN_HOME "%INSTALL_DIR%" >nul 2>&1
    
    for /f "tokens=2*" %%a in ('reg query "HKCU\Environment" /v Path 2^>nul ^| find "REG_"') do set "UserPath=%%b"
    echo %UserPath% | find /i "maven\bin" >nul
    if %errorLevel% neq 0 (
        if defined UserPath (
            setx PATH "%UserPath%;%INSTALL_DIR%\bin" >nul 2>&1
        ) else (
            setx PATH "%INSTALL_DIR%\bin" >nul 2>&1
        )
    )
)

echo [OK] MAVEN_HOME = %INSTALL_DIR%
echo [OK] PATH = ... + %INSTALL_DIR%\bin
echo.

REM Cleanup
del "%TEMP%\maven.zip" >nul 2>&1

echo ==========================================
echo   CAI DAT THANH CONG!
echo ==========================================
echo.
echo Maven da duoc cai dat vao:
echo   %INSTALL_DIR%
echo.
echo ⚠️  CAC BUOC TIEP THEO:
echo ==========================================
echo 1. DONG cua so Command Prompt nay
echo 2. MO MOI Command Prompt
echo 3. Kiem tra: mvn -version
echo.
echo 4. Build du an:
echo      cd %~dp0..
echo      mvn clean package
echo.
echo Neu "mvn -version" khong hoat dong:
echo   - Dam bao da mo terminal MOI
echo   - Hoac khoi dong lai may tinh
echo.

pause
