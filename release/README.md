# 🎮 CARO GAME - RELEASE v1.0.0

## 📦 Gói phân phối Caro Game

Đây là gói phân phối chính thức của **Caro Game v1.0.0** - Ứng dụng desktop chơi cờ Caro với AI.

---

## 📋 Nội dung gói

```
release/
├── README.md                    # File này - Hướng dẫn sử dụng
├── caro-game.jar                # File thực thi (executable JAR)
├── CHANGELOG.md                 # Lịch sử phát triển
└── docs/                        # Tài liệu hướng dẫn
    ├── HUONG_DAN_SU_DUNG.md    # Hướng dẫn sử dụng game
    └── HUONG_DAN_CAI_DAT.md    # Hướng dẫn cài đặt Java
```

---

## ⚡ Cài đặt nhanh

### 🔧 Yêu cầu hệ thống

- **Java Runtime Environment (JRE):** 11 trở lên
- **Hệ điều hành:** Windows 10/11, macOS 10.14+, hoặc Linux
- **RAM:** Tối thiểu 2 GB
- **Dung lượng:** 50 MB

### 📥 Cách 1: Chạy trực tiếp (Đơn giản nhất)

**Bước 1:** Kiểm tra Java đã cài đặt chưa

```bash
# Windows (PowerShell hoặc CMD)
java -version

# macOS/Linux (Terminal)
java -version
```

Nếu hiển thị version 11 trở lên → Bạn đã sẵn sàng! ✅  
Nếu báo lỗi → Xem **Cách 2** bên dưới

**Bước 2:** Chạy game

```bash
# Windows
java -jar caro-game.jar

# macOS/Linux
java -jar caro-game.jar
```

### 📥 Cách 2: Cài đặt Java trước (Nếu chưa có Java)

#### Windows:

1. **Tải Java 21 (Khuyến nghị):**
   - Truy cập: https://adoptium.net/
   - Chọn: **Temurin 21 (LTS)** → **Windows** → **x64** → **JRE**
   - Tải file `.msi` và cài đặt

2. **Kiểm tra cài đặt:**
   ```cmd
   java -version
   ```

3. **Chạy game:**
   ```cmd
   java -jar caro-game.jar
   ```

#### macOS:

```bash
# Cài đặt Java qua Homebrew
brew install openjdk@21

# Kiểm tra
java -version

# Chạy game
java -jar caro-game.jar
```

#### Linux (Ubuntu/Debian):

```bash
# Cài đặt Java
sudo apt update
sudo apt install openjdk-21-jre

# Kiểm tra
java -version

# Chạy game
java -jar caro-game.jar
```

---

## 🎮 Hướng dẫn chơi

### Khởi động game

1. Double-click vào `caro-game.jar` (nếu Java đã được cấu hình)
2. Hoặc mở Terminal/CMD và chạy: `java -jar caro-game.jar`

### Menu chính

Khi mở game, bạn sẽ thấy menu với 3 lựa chọn:

- **Easy** 🟢 - AI dễ (Chơi ngẫu nhiên)
- **Medium** 🟡 - AI trung bình (Có chiến thuật cơ bản)
- **Hard** 🔴 - AI khó (Minimax với Alpha-Beta Pruning)

### Cách chơi

1. **Click chọn độ khó** → Game bắt đầu
2. **Bạn đi trước (X)** - AI đi sau (O)
3. **Click vào ô trống** để đánh dấu
4. **Chiến thắng:** 5 quân liên tiếp (ngang/dọc/chéo)
5. **Sử dụng nút điều khiển:**
   - **Undo** ⏪ - Hoàn tác nước đi
   - **Redo** ⏩ - Làm lại nước đi
   - **New Game** 🔄 - Chơi ván mới
   - **Back to Menu** 🏠 - Về menu chính

### Tính năng đặc biệt

- ✅ **Undo/Redo không giới hạn** - Có thể hoàn tác bất kỳ nước đi nào
- ✅ **Đánh dấu chiến thắng** - Dòng 5 quân thắng được highlight
- ✅ **Theo dõi điểm số** - Điểm được lưu giữa các ván chơi
- ✅ **Giao diện mượt mà** - Hover effects và animations

---

## 🔧 Khắc phục sự cố

### ❌ Lỗi: "no main manifest attribute"

**Nguyên nhân:** File JAR không được build đúng cách

**Giải pháp:**
```bash
# Tải lại file JAR từ GitHub Releases
# Hoặc build lại từ source code
```

### ❌ Lỗi: "java: command not found"

**Nguyên nhân:** Java chưa được cài đặt hoặc chưa được thêm vào PATH

**Giải pháp:**
1. Cài đặt Java theo hướng dẫn **Cách 2** ở trên
2. Kiểm tra lại với `java -version`

### ❌ Lỗi: "UnsupportedClassVersionError"

**Nguyên nhân:** Phiên bản Java quá cũ (dưới Java 11)

**Giải pháp:**
```bash
# Kiểm tra version
java -version

# Nếu < 11, cài đặt Java 21 mới hơn
# Xem hướng dẫn Cách 2
```

### ❌ Game không mở được bằng double-click

**Giải pháp:**

**Windows:**
1. Chuột phải vào `caro-game.jar`
2. Chọn **"Open with"** → **"Java(TM) Platform SE binary"**
3. Tick **"Always use this app"** → **OK**

**macOS:**
1. Chuột phải vào `caro-game.jar`
2. Chọn **"Open with"** → **"Jar Launcher"**

**Linux:**
```bash
# Thêm quyền thực thi
chmod +x caro-game.jar

# Chạy
./caro-game.jar
```

### ❌ Game chạy chậm hoặc lag

**Giải pháp:**
```bash
# Tăng bộ nhớ heap cho Java
java -Xmx512m -jar caro-game.jar

# Hoặc 1GB
java -Xmx1g -jar caro-game.jar
```

---

## 📚 Tài liệu chi tiết

Để biết thêm thông tin, xem các file tài liệu:

- **`docs/HUONG_DAN_SU_DUNG.md`** - Hướng dẫn sử dụng đầy đủ
- **`docs/HUONG_DAN_CAI_DAT.md`** - Hướng dẫn cài đặt môi trường
- **`CHANGELOG.md`** - Lịch sử phát triển và tính năng

---

## 🎯 Thông tin phiên bản

| Thông tin | Chi tiết |
|-----------|----------|
| **Phiên bản** | v1.0.0 |
| **Ngày phát hành** | 27/10/2025 |
| **Kích thước JAR** | ~9.5 MB |
| **Java yêu cầu** | JRE 11+ |
| **Giấy phép** | Educational Use |

---

## 🌟 Tính năng nổi bật

- ✅ **3 độ khó AI** với thuật toán khác nhau
- ✅ **Giao diện JavaFX** hiện đại và mượt mà
- ✅ **Undo/Redo** không giới hạn
- ✅ **Theo dõi điểm số** liên tục
- ✅ **Cross-platform** (Windows/macOS/Linux)
- ✅ **Không cần cài đặt** - Chỉ cần Java

---

## 🔗 Liên kết

- **GitHub Repository:** https://github.com/NamKhanhCTK46B/Caro-Game
- **Báo lỗi:** https://github.com/NamKhanhCTK46B/Caro-Game/issues
- **Source Code:** https://github.com/NamKhanhCTK46B/Caro-Game/tree/main/src

---

## 👨‍💻 Thông tin dự án

**Sinh viên thực hiện:** Nguyễn Hoàng Nam Khánh  
**MSSV:** 2212391  
**Email:** 2212391@dlu.edu.vn  
**Trường:** Đại học Đà Lạt  
**Môn học:** Thiết kế Phần mềm  
**Năm học:** 2024-2025

---

## 📄 Giấy phép

Dự án được phát triển cho mục đích học tập và nghiên cứu.

---

## 🙏 Cảm ơn

Cảm ơn bạn đã sử dụng **Caro Game**! Chúc bạn chơi game vui vẻ! 🎉

Nếu gặp vấn đề hoặc có góp ý, vui lòng tạo **Issue** trên GitHub.

---

**Version:** 1.0.0  
**Release Date:** October 27, 2025  
**Status:** ✅ Stable Release
