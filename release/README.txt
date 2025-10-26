================================================================================
  CARO GAME - TRO CHOI CO CARO v1.0.0
================================================================================

HUONG DAN SU DUNG NHANH
-----------------------

1. YEU CAU HE THONG
   - Java 11 hoac cao hon
   - Windows 10/11, macOS 10.14+, hoac Linux
   - RAM: 2 GB toi thieu

2. KIEM TRA JAVA
   Mo Command Prompt hoac Terminal va chay:
   
   java -version
   
   Neu hien thi "java version 11" hoac cao hon => OK!
   Neu bao loi => Xem huong dan cai dat ben duoi

3. CHAY GAME
   
   Cach 1: Double-click vao file caro-game.jar
   
   Cach 2: Mo Command Prompt tai thu muc nay va chay:
   
   java -jar caro-game.jar

4. CACH CHOI
   - Chon do kho AI: Easy / Medium / Hard
   - Click vao o trong de danh dau X
   - AI tu dong danh dau O
   - Thang: 5 quan lien tiep (ngang/doc/cheo)
   - Dung nut Undo/Redo de hoan tac

================================================================================
CAI DAT JAVA (neu chua co)
================================================================================

WINDOWS:
1. Tai Java tai: https://adoptium.net/
2. Chon: Temurin 21 (LTS) > Windows > x64 > JRE
3. Tai file .msi va cai dat
4. Kiem tra: java -version

macOS:
brew install openjdk@21

Linux (Ubuntu/Debian):
sudo apt update
sudo apt install openjdk-21-jre

================================================================================
KHAC PHUC SU CO
================================================================================

LOI: "java: command not found"
=> Java chua duoc cai dat. Xem huong dan cai dat tren.

LOI: "no main manifest attribute"
=> File JAR bi loi. Tai lai tu GitHub.

LOI: Game khong mo bang double-click
=> Chuot phai vao caro-game.jar > Open with > Java

LOI: UnsupportedClassVersionError
=> Phien ban Java qua cu. Cai Java 21.

================================================================================
THONG TIN DU AN
================================================================================

Phien ban:    v1.0.0
Ngay phat hanh: 27/10/2025
Kich thuoc:   9.5 MB
Java yeu cau: JRE 11+

Tinh nang:
- 3 do kho AI (Easy, Medium, Hard)
- Giao dien JavaFX hien dai
- Undo/Redo khong gioi han
- Theo doi diem so
- Cross-platform

================================================================================
LIEN HE
================================================================================

GitHub:  https://github.com/NamKhanhCTK46B/Caro-Game
Email:   2212391@dlu.edu.vn
Bao loi: https://github.com/NamKhanhCTK46B/Caro-Game/issues

Tac gia: Nguyen Hoang Nam Khanh (MSSV: 2212391)
Truong:  Dai hoc Da Lat
Mon hoc: Thiet ke Phan mem

================================================================================
Chuc ban choi game vui ve!
================================================================================
