@echo off
REM =====================================================
REM OPTIMIZE PROJECT SIZE
REM Xoa cac file khong can thiet, giu lai JAR files
REM =====================================================

echo.
echo ==========================================
echo  TOI UU DUNG LUONG DU AN
echo ==========================================
echo.
echo Script nay se:
echo   1. Xoa compiled classes (target\classes\)
echo   2. Xoa JAR goc (khong co dependencies)
echo   3. Xoa Maven metadata
echo   4. Xoa file ZIP tam
echo   5. GIU LAI: JAR chinh, source code, docs
echo.
echo Nhan Ctrl+C de huy, hoac
pause

cd /d "%~dp0\.."

echo.
echo [1/5] Xoa target\classes (compiled classes)...
if exist "target\classes" (
    rd /s /q "target\classes"
    echo [OK] Da xoa target\classes
) else (
    echo [INFO] Khong co target\classes
)

echo.
echo [2/5] Xoa target\generated-sources...
if exist "target\generated-sources" (
    rd /s /q "target\generated-sources"
    echo [OK] Da xoa target\generated-sources
) else (
    echo [INFO] Khong co target\generated-sources
)

echo.
echo [3/5] Xoa target\maven-status...
if exist "target\maven-status" (
    rd /s /q "target\maven-status"
    echo [OK] Da xoa target\maven-status
) else (
    echo [INFO] Khong co target\maven-status
)

echo.
echo [4/5] Xoa JAR goc (tro_choi_caro-*-SNAPSHOT.jar)...
if exist "target\tro_choi_caro-*.jar" (
    del /q "target\tro_choi_caro-*.jar"
    echo [OK] Da xoa JAR goc
) else (
    echo [INFO] Khong co JAR goc
)

echo.
echo [5/5] Xoa cac file ZIP tam...
if exist "*.zip" (
    del /q "*.zip"
    echo [OK] Da xoa ZIP files
) else (
    echo [INFO] Khong co ZIP files
)

echo.
echo ==========================================
echo  TIET KIEM DUNG LUONG
echo ==========================================
echo.
echo Da xoa:
echo   - Compiled classes (~0.06 MB)
echo   - JAR goc khong dependencies (~0.04 MB)
echo   - Maven metadata
echo   - ZIP files (~8 MB)
echo.
echo Tong tiet kiem: ~8-9 MB
echo.
echo ==========================================
echo  GIU LAI
echo ==========================================
echo.
echo   - target\caro-game.jar (9.5 MB) - JAR chinh
echo   - release\caro-game.jar (9.5 MB) - Phan phoi
echo   - src\ - Source code
echo   - doc\ - Documentation
echo   - scripts\ - Build scripts
echo.
echo Dung luong du an hien tai:
powershell -Command "$size = (Get-ChildItem -Recurse -File | Measure-Object -Property Length -Sum).Sum / 1MB; 'Total: {0:N2} MB' -f $size"
echo.

pause
