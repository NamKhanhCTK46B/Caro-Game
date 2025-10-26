@echo off@echo off

REM ========================================REM ========================================

REM BUILD SCRIPT - Caro GameREM Script build du an (khong chay)

REM Tac gia: 2212391 - Nguyen Hoang Nam KhanhREM Author: 2212391- Nguyen Hoang Nam Khanh

REM ========================================REM ========================================



setlocalecho ========================================

echo   TRO CHOI CARO - BUILD ONLY

echo.echo ========================================

echo ==========================================echo.

echo   CARO GAME - BUILD SCRIPT

echo ==========================================echo [INFO] Dang build du an...

echo.echo.

echo Chon che do build:

echo   [1] Compile only (nhanh)call mvn clean compile

echo   [2] Package JAR (full build)

echo   [3] Clean + Package JARif %ERRORLEVEL% NEQ 0 (

echo.    echo [ERROR] Build that bai!

set /p choice="Nhap lua chon (1-3): "    pause

    exit /b 1

cd /d "%~dp0\..")



if "%choice%"=="1" goto :compileecho.

if "%choice%"=="2" goto :packageecho ========================================

if "%choice%"=="3" goto :clean_packageecho   BUILD THANH CONG!

echo [ERROR] Lua chon khong hop le!echo ========================================

pauseecho File class da duoc tao trong: target/classes/

exit /b 1echo.

pause

:compile
echo.
echo [INFO] Dang compile source code...
call mvn compile
if %errorlevel% neq 0 goto :error
echo.
echo ==========================================
echo   COMPILE THANH CONG!
echo ==========================================
echo Files: target/classes/
goto :success

:package
echo.
echo [INFO] Dang package JAR file...
call mvn package -DskipTests
if %errorlevel% neq 0 goto :error
goto :show_jar

:clean_package
echo.
echo [INFO] Dang clean du an...
call mvn clean
if %errorlevel% neq 0 goto :error
echo.
echo [INFO] Dang package JAR file...
call mvn package -DskipTests
if %errorlevel% neq 0 goto :error
goto :show_jar

:show_jar
echo.
echo ==========================================
echo   BUILD THANH CONG!
echo ==========================================
if exist "target\caro-game.jar" (
    echo.
    echo JAR file: target\caro-game.jar
    dir target\caro-game.jar | find "caro-game.jar"
    echo.
    echo De chay game:
    echo   java -jar target\caro-game.jar
    echo   scripts\run.bat
) else (
    echo [WARNING] Khong tim thay JAR file!
)
goto :success

:error
echo.
echo ==========================================
echo   BUILD THAT BAI!
echo ==========================================
pause
exit /b 1

:success
echo.
pause
