# 🎮 HUONG DAN SU DUNG CARO GAME

## ⚡ Cach chay game

### Cach 1: Double-click

1. Double-click vao file `caro-game.jar`
2. Game se tu dong khoi dong (neu Java da duoc cau hinh)

### Cach 2: Command line

```bash
# Windows
java -jar caro-game.jar

# macOS/Linux  
java -jar caro-game.jar
```

## 🎯 Cach choi

### Buoc 1: Chon do kho AI

Khi khoi dong, ban se thay menu voi 3 lua chon:

- **Easy** 🟢 - AI choi ngau nhien (de thang)
- **Medium** 🟡 - AI co chien thuat co ban
- **Hard** 🔴 - AI su dung Minimax Algorithm (kho thang)

### Buoc 2: Choi game

1. **Ban di truoc (X)** - AI di sau (O)
2. **Click vao o trong** de danh dau X
3. **AI tu dong danh** sau nuoc di cua ban
4. **Thang:** 5 quan lien tiep (ngang/doc/cheo)
5. **Dong 5 quan thang** se duoc highlight mau xanh

### Buoc 3: Su dung cac nut dieu khien

- **Undo** ⏪ - Hoan tac nuoc di
- **Redo** ⏩ - Lam lai nuoc di  
- **New Game** 🔄 - Bat dau van moi
- **Back to Menu** 🏠 - Ve menu chon do kho

## ✨ Tinh nang dac biet

- ✅ **Undo/Redo khong gioi han** - Co the hoan tac bat ky nuoc di nao
- ✅ **Danh dau chien thang** - Dong 5 quan thang duoc highlight
- ✅ **Theo doi diem so** - Diem duoc luu giua cac van choi
- ✅ **Giao dien muot ma** - Hover effects va animations

## 🔧 Yeu cau he thong

- **Java:** JRE/JDK 11 hoac cao hon
- **OS:** Windows 10/11, macOS 10.14+, Linux
- **RAM:** 2 GB toi thieu
- **Dung luong:** 50 MB

## 📚 Cac muc do AI

### 🟢 Easy - AI De

- **Thuat toan:** Random (Ngau nhien)
- **Do kho:** Rat de thang
- **Thoi gian suy nghi:** Instant
- **Phu hop:** Nguoi moi bat dau

### 🟡 Medium - AI Trung Binh  

- **Thuat toan:** Heuristic Evaluation
- **Chien thuat:**
  - Nhan dien pattern (2, 3, 4 quan lien tiep)
  - Chan nuoc di cua doi thu
  - Tim co hoi tao 5 quan
- **Do kho:** Trung binh
- **Thoi gian suy nghi:** <1 giay

### 🔴 Hard - AI Kho

- **Thuat toan:** Minimax voi Alpha-Beta Pruning
- **Chien thuat:**
  - Tim kiem cay tro choi (depth 3-4)
  - Toi uu hoa Alpha-Beta
  - Ke hoach chien luoc dai han
  - Nuoc di gan toi uu
- **Do kho:** Rat kho thang
- **Thoi gian suy nghi:** 1-3 giay

## 🔧 Khac phuc su co

### ❌ Loi: "java: command not found"

**Nguyen nhan:** Java chua duoc cai dat

**Giai phap:**
1. Xem file `docs\HUONG_DAN_CAI_DAT.md`
2. Hoac tai Java tai: https://adoptium.net/

### ❌ Game khong mo duoc bang double-click

**Windows:**
1. Chuot phai vao `caro-game.jar`
2. Chon "Open with" -> "Java^(TM^) Platform SE binary"

**macOS:**
1. Chuot phai vao `caro-game.jar`  
2. Chon "Open with" -> "Jar Launcher"

**Linux:**
```bash
chmod +x caro-game.jar
./caro-game.jar
```

### ❌ Game chay cham hoac lag

**Giai phap:** Tang bo nho heap

```bash
# Windows/macOS/Linux
java -Xmx512m -jar caro-game.jar

# Hoac 1GB
java -Xmx1g -jar caro-game.jar
```

## 🔗 Lien he va ho tro

- **GitHub:** https://github.com/NamKhanhCTK46B/Caro-Game
- **Bao loi:** https://github.com/NamKhanhCTK46B/Caro-Game/issues
- **Email:** 2212391@dlu.edu.vn

## 📝 Thong tin phien ban

- **Phien ban:** v1.0.0
- **Ngay phat hanh:** 27/10/2025
- **Kich thuoc JAR:** ~9.5 MB

---

**Chuc ban choi game vui ve!** 🎉
