@echo off
REM ========================================
REM Script: Tao goi phan phoi (Release Package)
REM Mo ta: Build JAR va tao thu muc release
REM Tac gia: 2212391 - Nguyen Hoang Nam Khanh
REM ========================================

setlocal enabledelayedexpansion

echo.
echo ========================================
echo   TAO GOI PHAN PHOI - CARO GAME
echo ========================================
echo.

REM Tim Maven
set "MAVEN_CMD="
if exist "mvnw.cmd" (
    set "MAVEN_CMD=mvnw.cmd"
    echo [INFO] Su dung Maven Wrapper: mvnw.cmd
) else if exist "mvnw" (
    set "MAVEN_CMD=mvnw"
    echo [INFO] Su dung Maven Wrapper: mvnw
) else (
    where mvn >nul 2>&1
    if !errorlevel! equ 0 (
        set "MAVEN_CMD=mvn"
        echo [INFO] Su dung Maven tu PATH
    ) else (
        echo [ERROR] Khong tim thay Maven!
        echo.
        echo Vui long:
        echo   1. Cai dat Maven
        echo   2. Hoac chay scripts\install-maven.bat
        echo.
        pause
        exit /b 1
    )
)

echo.
echo [1/4] Cleaning old build...
echo ========================================
call !MAVEN_CMD! clean
if errorlevel 1 (
    echo [ERROR] Clean that bai!
    pause
    exit /b 1
)

echo.
echo [2/4] Building JAR file...
echo ========================================
call !MAVEN_CMD! package -DskipTests
if errorlevel 1 (
    echo [ERROR] Build that bai!
    pause
    exit /b 1
)

echo.
echo [3/4] Creating release package...
echo ========================================

REM Tao thu muc release
if not exist "release" mkdir release
if not exist "release\docs" mkdir release\docs

REM Copy JAR file
if exist "target\caro-game.jar" (
    echo [INFO] Copying JAR file...
    copy /Y "target\caro-game.jar" "release\caro-game.jar" >nul
    echo [OK] caro-game.jar
) else (
    echo [ERROR] Khong tim thay file JAR!
    echo [ERROR] File: target\caro-game.jar
    pause
    exit /b 1
)

REM Copy CHANGELOG
if exist "CHANGELOG.md" (
    echo [INFO] Copying CHANGELOG.md...
    copy /Y "CHANGELOG.md" "release\CHANGELOG.md" >nul
    echo [OK] CHANGELOG.md
)

REM Copy tai lieu
echo [INFO] Copying documentation...
if exist "HUONG_DAN_CAI_DAT.md" (
    copy /Y "HUONG_DAN_CAI_DAT.md" "release\docs\HUONG_DAN_CAI_DAT.md" >nul
    echo [OK] docs\HUONG_DAN_CAI_DAT.md
)

REM Tao file HUONG_DAN_SU_DUNG.md
echo [INFO] Creating HUONG_DAN_SU_DUNG.md...
(
echo # HUONG DAN SU DUNG CARO GAME
echo.
echo ## Cach chay game
echo.
echo 1. Mo Terminal/CMD tai thu muc nay
echo 2. Chay lenh: java -jar caro-game.jar
echo 3. Hoac double-click vao file caro-game.jar
echo.
echo ## Dieu khien game
echo.
echo - Click vao o trong de danh dau X
echo - AI se tu dong danh dau O
echo - Thang: 5 quan lien tiep ^(ngang/doc/cheo^)
echo.
echo ### Cac nut dieu khien
echo.
echo - **Undo**: Hoan tac nuoc di
echo - **Redo**: Lam lai nuoc di
echo - **New Game**: Bat dau van moi
echo - **Back to Menu**: Ve menu chon do kho
echo.
echo ## Cac muc do AI
echo.
echo - **Easy**: AI choi ngau nhien ^(de thang^)
echo - **Medium**: AI co chien thuat co ban
echo - **Hard**: AI su dung Minimax Algorithm ^(kho thang^)
echo.
echo ## Yeu cau he thong
echo.
echo - Java 11 hoac cao hon
echo - Windows 10/11, macOS 10.14+, hoac Linux
echo - RAM: 2 GB
echo.
echo ## Lien he
echo.
echo - GitHub: https://github.com/NamKhanhCTK46B/Caro-Game
echo - Email: 2212391@dlu.edu.vn
) > "release\docs\HUONG_DAN_SU_DUNG.md"
echo [OK] docs\HUONG_DAN_SU_DUNG.md

echo.
echo [4/4] Generating package info...
echo ========================================

REM Lay kich thuoc file JAR
for %%A in ("release\caro-game.jar") do set "JAR_SIZE=%%~zA"
set /a "JAR_SIZE_MB=!JAR_SIZE! / 1048576"

echo.
echo ========================================
echo   HOAN THANH!
echo ========================================
echo.
echo Thu muc: release\
echo.
echo Noi dung:
echo   - caro-game.jar         (~!JAR_SIZE_MB! MB)
echo   - README.md             (Huong dan su dung)
echo   - CHANGELOG.md          (Lich su phat trien)
echo   - docs\
echo       - HUONG_DAN_SU_DUNG.md
echo       - HUONG_DAN_CAI_DAT.md
echo.
echo Cach su dung:
echo   1. Vao thu muc release\
echo   2. Chay: java -jar caro-game.jar
echo   3. Hoac double-click vao caro-game.jar
echo.
echo Cach tao ZIP:
echo   - Windows: Chuot phai release\ ^> Send to ^> Compressed folder
echo   - PowerShell: Compress-Archive release\ caro-game-v1.0.0.zip
echo.

pause
