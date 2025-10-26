@echo off
REM ========================================
REM Script: Tao goi phan phoi don gian
REM Mo ta: Copy cac file can thiet vao release
REM ========================================

echo.
echo ========================================
echo   TAO GOI PHAN PHOI - CARO GAME
echo ========================================
echo.

REM Tao thu muc release
echo [1/3] Tao cau truc thu muc...
if not exist "release" mkdir release
if not exist "release\docs" mkdir release\docs
echo [OK] Thu muc da tao

echo.
echo [2/3] Copy cac file...

REM Copy JAR file (neu co)
if exist "target\caro-game.jar" (
    copy /Y "target\caro-game.jar" "release\caro-game.jar" >nul
    echo [OK] caro-game.jar da copy
) else (
    echo [WARNING] Chua co file JAR!
    echo [INFO] Ban can build JAR truoc:
    echo        - Neu co Maven: mvn clean package
    echo        - Hoac xem HUONG_DAN_CAI_DAT.md
)

REM Copy CHANGELOG
if exist "CHANGELOG.md" (
    copy /Y "CHANGELOG.md" "release\CHANGELOG.md" >nul
    echo [OK] CHANGELOG.md da copy
)

REM Copy tai lieu
if exist "HUONG_DAN_CAI_DAT.md" (
    copy /Y "HUONG_DAN_CAI_DAT.md" "release\docs\HUONG_DAN_CAI_DAT.md" >nul
    echo [OK] HUONG_DAN_CAI_DAT.md da copy
)

REM Tao file HUONG_DAN_SU_DUNG.md
(
echo # 🎮 HUONG DAN SU DUNG CARO GAME
echo.
echo ## ⚡ Cach chay game
echo.
echo ### Cach 1: Double-click
echo.
echo 1. Double-click vao file `caro-game.jar`
echo 2. Game se tu dong khoi dong ^(neu Java da duoc cau hinh^)
echo.
echo ### Cach 2: Command line
echo.
echo ```bash
echo # Windows
echo java -jar caro-game.jar
echo.
echo # macOS/Linux  
echo java -jar caro-game.jar
echo ```
echo.
echo ## 🎯 Cach choi
echo.
echo ### Buoc 1: Chon do kho AI
echo.
echo Khi khoi dong, ban se thay menu voi 3 lua chon:
echo.
echo - **Easy** 🟢 - AI choi ngau nhien ^(de thang^)
echo - **Medium** 🟡 - AI co chien thuat co ban
echo - **Hard** 🔴 - AI su dung Minimax Algorithm ^(kho thang^)
echo.
echo ### Buoc 2: Choi game
echo.
echo 1. **Ban di truoc ^(X^)** - AI di sau ^(O^)
echo 2. **Click vao o trong** de danh dau X
echo 3. **AI tu dong danh** sau nuoc di cua ban
echo 4. **Thang:** 5 quan lien tiep ^(ngang/doc/cheo^)
echo 5. **Dong 5 quan thang** se duoc highlight mau xanh
echo.
echo ### Buoc 3: Su dung cac nut dieu khien
echo.
echo - **Undo** ⏪ - Hoan tac nuoc di
echo - **Redo** ⏩ - Lam lai nuoc di  
echo - **New Game** 🔄 - Bat dau van moi
echo - **Back to Menu** 🏠 - Ve menu chon do kho
echo.
echo ## ✨ Tinh nang dac biet
echo.
echo - ✅ **Undo/Redo khong gioi han** - Co the hoan tac bat ky nuoc di nao
echo - ✅ **Danh dau chien thang** - Dong 5 quan thang duoc highlight
echo - ✅ **Theo doi diem so** - Diem duoc luu giua cac van choi
echo - ✅ **Giao dien muot ma** - Hover effects va animations
echo.
echo ## 🔧 Yeu cau he thong
echo.
echo - **Java:** JRE/JDK 11 hoac cao hon
echo - **OS:** Windows 10/11, macOS 10.14+, Linux
echo - **RAM:** 2 GB toi thieu
echo - **Dung luong:** 50 MB
echo.
echo ## 📚 Cac muc do AI
echo.
echo ### 🟢 Easy - AI De
echo.
echo - **Thuat toan:** Random ^(Ngau nhien^)
echo - **Do kho:** Rat de thang
echo - **Thoi gian suy nghi:** Instant
echo - **Phu hop:** Nguoi moi bat dau
echo.
echo ### 🟡 Medium - AI Trung Binh  
echo.
echo - **Thuat toan:** Heuristic Evaluation
echo - **Chien thuat:**
echo   - Nhan dien pattern ^(2, 3, 4 quan lien tiep^)
echo   - Chan nuoc di cua doi thu
echo   - Tim co hoi tao 5 quan
echo - **Do kho:** Trung binh
echo - **Thoi gian suy nghi:** ^<1 giay
echo.
echo ### 🔴 Hard - AI Kho
echo.
echo - **Thuat toan:** Minimax voi Alpha-Beta Pruning
echo - **Chien thuat:**
echo   - Tim kiem cay tro choi ^(depth 3-4^)
echo   - Toi uu hoa Alpha-Beta
echo   - Ke hoach chien luoc dai han
echo   - Nuoc di gan toi uu
echo - **Do kho:** Rat kho thang
echo - **Thoi gian suy nghi:** 1-3 giay
echo.
echo ## 🔧 Khac phuc su co
echo.
echo ### ❌ Loi: "java: command not found"
echo.
echo **Nguyen nhan:** Java chua duoc cai dat
echo.
echo **Giai phap:**
echo 1. Xem file `docs\HUONG_DAN_CAI_DAT.md`
echo 2. Hoac tai Java tai: https://adoptium.net/
echo.
echo ### ❌ Game khong mo duoc bang double-click
echo.
echo **Windows:**
echo 1. Chuot phai vao `caro-game.jar`
echo 2. Chon "Open with" -^> "Java^(TM^) Platform SE binary"
echo.
echo **macOS:**
echo 1. Chuot phai vao `caro-game.jar`  
echo 2. Chon "Open with" -^> "Jar Launcher"
echo.
echo **Linux:**
echo ```bash
echo chmod +x caro-game.jar
echo ./caro-game.jar
echo ```
echo.
echo ### ❌ Game chay cham hoac lag
echo.
echo **Giai phap:** Tang bo nho heap
echo.
echo ```bash
echo # Windows/macOS/Linux
echo java -Xmx512m -jar caro-game.jar
echo.
echo # Hoac 1GB
echo java -Xmx1g -jar caro-game.jar
echo ```
echo.
echo ## 🔗 Lien he va ho tro
echo.
echo - **GitHub:** https://github.com/NamKhanhCTK46B/Caro-Game
echo - **Bao loi:** https://github.com/NamKhanhCTK46B/Caro-Game/issues
echo - **Email:** 2212391@dlu.edu.vn
echo.
echo ## 📝 Thong tin phien ban
echo.
echo - **Phien ban:** v1.0.0
echo - **Ngay phat hanh:** 27/10/2025
echo - **Kich thuoc JAR:** ~9.5 MB
echo.
echo ---
echo.
echo **Chuc ban choi game vui ve!** 🎉
) > "release\docs\HUONG_DAN_SU_DUNG.md"
echo [OK] HUONG_DAN_SU_DUNG.md da tao

echo.
echo [3/3] Kiem tra ket qua...
echo.
echo ========================================
echo   HOAN THANH!
echo ========================================
echo.
echo Thu muc release\ da duoc tao voi:
echo.
dir /B release
echo.
dir /B release\docs
echo.

if exist "release\caro-game.jar" (
    echo [OK] Goi phan phoi da san sang!
    echo.
    echo Cach su dung:
    echo   1. Vao thu muc: cd release
    echo   2. Chay game: java -jar caro-game.jar
    echo.
) else (
    echo [INFO] Chua co file JAR. De hoan tat goi phan phoi:
    echo.
    echo 1. Cai Maven: .\scripts\install-maven.bat
    echo 2. Build JAR: mvn clean package
    echo 3. Chay lai script nay
    echo.
)

echo De tao file ZIP:
echo   Chuot phai release\ ^> Send to ^> Compressed folder
echo.

pause
