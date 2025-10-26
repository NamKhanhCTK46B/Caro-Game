@echo off
REM =====================================================
REM CLEANUP & OPTIMIZE PROJECT
REM Xóa các file không cần thiết để giảm dung lượng
REM =====================================================

echo.
echo ==========================================
echo  PROJECT CLEANUP UTILITY
echo ==========================================
echo.
echo This script will:
echo   1. Remove build output (target/)
echo   2. Remove duplicate JAR files
echo   3. Remove temporary files
echo   4. Keep only essential documentation
echo.
echo Press Ctrl+C to cancel, or
pause

cd /d "%~dp0\.."

echo.
echo [Step 1/5] Removing build output...
if exist "target" (
    rmdir /s /q "target"
    echo [OK] target/ removed
) else (
    echo [SKIP] target/ does not exist
)

echo.
echo [Step 2/5] Removing dependency-reduced-pom.xml...
if exist "dependency-reduced-pom.xml" (
    del "dependency-reduced-pom.xml"
    echo [OK] dependency-reduced-pom.xml removed
) else (
    echo [SKIP] dependency-reduced-pom.xml does not exist
)

echo.
echo [Step 3/5] Cleaning Maven local repository cache...
echo.
echo Do you want to clean Maven cache? (Requires re-download)
echo Type YES to confirm, or press Enter to skip:
set /p CLEAN_MAVEN=
if /i "%CLEAN_MAVEN%"=="YES" (
    if exist "%USERPROFILE%\.m2\repository" (
        rmdir /s /q "%USERPROFILE%\.m2\repository"
        echo [OK] Maven cache cleared
    )
) else (
    echo [SKIP] Maven cache preserved
)

echo.
echo [Step 4/5] Removing temporary files...
if exist "*.tmp" del "*.tmp" >nul 2>&1
if exist "*.log" del "*.log" >nul 2>&1
if exist "*.bak" del "*.bak" >nul 2>&1
echo [OK] Temporary files removed

echo.
echo [Step 5/5] Calculating disk space saved...
echo.

REM Calculate current size
powershell -Command "Get-ChildItem -Recurse -File | Measure-Object -Property Length -Sum | Select-Object @{Name='TotalSizeMB';Expression={[math]::Round($_.Sum / 1MB, 2)}}"

echo.
echo ==========================================
echo  CLEANUP COMPLETED!
echo ==========================================
echo.
echo What was removed:
echo   - target/ (build output)
echo   - dependency-reduced-pom.xml
echo   - Temporary files (*.tmp, *.log, *.bak)
echo   - Maven cache (if confirmed)
echo.
echo What was kept:
echo   - Source code (src/)
echo   - Documentation (doc/, *.md)
echo   - Build scripts (scripts/)
echo   - Configuration (pom.xml)
echo.
echo To rebuild:
echo   mvn clean package -DskipTests
echo.

pause
