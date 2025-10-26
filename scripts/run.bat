@echo off@echo off

REM ========================================REM ========================================

REM RUN SCRIPT - Caro GameREM Script chay tu dong du an Tro Choi Caro

REM Tac gia: 2212391 - Nguyen Hoang Nam KhanhREM Author: 2212391- Nguyen Hoang Nam Khanh

REM ========================================REM Date: 26/10/2025

REM ========================================

setlocal

echo ========================================

echo.echo    TRO CHOI CARO - AUTO RUN SCRIPT

echo ==========================================echo ========================================

echo   CARO GAME - RUN SCRIPTecho.

echo ==========================================

echo.REM Kiem tra Maven co ton tai khong

echo Chon che do chay:where mvn >nul 2>nul

echo   [1] Run tu source (mvn javafx:run)if %ERRORLEVEL% NEQ 0 (

echo   [2] Run nhanh (khong clean)    echo [ERROR] Maven khong duoc cai dat hoac khong co trong PATH!

echo   [3] Run tu JAR file    echo.

echo.    echo Vui long cai dat Maven tu: https://maven.apache.org/download.cgi

set /p choice="Nhap lua chon (1-3): "    echo Hoac them Maven vao bien moi truong PATH.

    echo.

cd /d "%~dp0\.."    pause

    exit /b 1

if "%choice%"=="1" goto :run_source_clean)

if "%choice%"=="2" goto :run_source_quick

if "%choice%"=="3" goto :run_jarREM Kiem tra Java co ton tai khong

echo [ERROR] Lua chon khong hop le!where java >nul 2>nul

pauseif %ERRORLEVEL% NEQ 0 (

exit /b 1    echo [ERROR] Java khong duoc cai dat hoac khong co trong PATH!

    echo.

:run_source_clean    echo Vui long cai dat JDK 11 hoac cao hon.

echo.    echo.

echo ========================================    pause

echo   BUOC 1: Kiem tra cong cu    exit /b 1

echo ========================================)



where mvn >nul 2>nulecho [INFO] Dang kiem tra phien ban Java...

if %errorlevel% neq 0 (java -version

    echo [ERROR] Maven khong duoc cai dat!echo.

    echo Chay: scripts\install-maven.bat

    pauseecho [INFO] Dang kiem tra phien ban Maven...

    exit /b 1mvn -version

)echo.



where java >nul 2>nulecho ========================================

if %errorlevel% neq 0 (echo   BUOC 1: Clean du an

    echo [ERROR] Java khong duoc cai dat!echo ========================================

    pauseecho [INFO] Dang xoa cac file build cu...

    exit /b 1call mvn clean

)if %ERRORLEVEL% NEQ 0 (

    echo [ERROR] Clean that bai!

echo [OK] Maven va Java da san sang    pause

echo.    exit /b 1

)

echo ========================================echo [SUCCESS] Clean thanh cong!

echo   BUOC 2: Clean du anecho.

echo ========================================

call mvn cleanecho ========================================

if %errorlevel% neq 0 goto :errorecho   BUOC 2: Compile du an

echo [OK] Clean thanh congecho ========================================

echo.echo [INFO] Dang bien dich source code...

call mvn compile

echo ========================================if %ERRORLEVEL% NEQ 0 (

echo   BUOC 3: Compile source    echo [ERROR] Compile that bai!

echo ========================================    pause

call mvn compile    exit /b 1

if %errorlevel% neq 0 goto :error)

echo [OK] Compile thanh congecho [SUCCESS] Compile thanh cong!

echo.echo.



echo ========================================echo ========================================

echo   BUOC 4: Chay ung dungecho   BUOC 3: Chay ung dung

echo ========================================echo ========================================

echo [INFO] Dang khoi dong game...echo [INFO] Dang khoi dong Tro Choi Caro...

echo.echo [INFO] Vui long doi giao dien xuat hien...

call mvn javafx:runecho.

goto :endcall mvn javafx:run

if %ERRORLEVEL% NEQ 0 (

:run_source_quick    echo [ERROR] Khong the chay ung dung!

echo.    pause

echo [INFO] Dang chay game (che do nhanh)...    exit /b 1

echo [INFO] Neu gap loi, chon option [1] de clean + rebuild)

echo.

call mvn javafx:runecho.

goto :endecho ========================================

echo   DA DONG UNG DUNG

:run_jarecho ========================================

echo.echo Cam on ban da su dung Tro Choi Caro!

echo [INFO] Kiem tra JAR file...echo.

pause

if not exist "target\caro-game.jar" (
    echo [ERROR] JAR file khong ton tai!
    echo.
    echo Vui long build truoc:
    echo   scripts\build.bat
    echo   Chon option [2] hoac [3]
    echo.
    pause
    exit /b 1
)

echo [OK] Tim thay JAR file
echo.
echo ==========================================
echo   Dang khoi dong game...
echo ==========================================
echo.

java -jar target\caro-game.jar
if %errorlevel% neq 0 (
    echo.
    echo [ERROR] Khong the chay game!
    echo Kiem tra Java installation.
    pause
    exit /b 1
)
goto :end

:error
echo.
echo ==========================================
echo   LOI KHI CHAY GAME!
echo ==========================================
pause
exit /b 1

:end
echo.
echo ==========================================
echo   Da dong ung dung
echo ==========================================
echo Cam on ban da choi!
echo.
pause
