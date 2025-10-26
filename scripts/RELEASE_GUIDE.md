# 📦 RELEASE PACKAGE SCRIPTS

## Hướng dẫn tạo gói phân phối Caro Game

### 🎯 Mục đích

2 scripts này giúp tạo gói phân phối (release package) hoàn chỉnh cho end-users.

---

## 📜 Scripts

### 1️⃣ `create-release.bat` (Đầy đủ)

**Chức năng:** Build JAR + Tạo package hoàn chỉnh

**Yêu cầu:** Maven đã cài đặt

**Kết quả:**
```
release/
├── caro-game.jar           (~9.5 MB)
├── README.md               (Hướng dẫn cho người dùng)
├── CHANGELOG.md            (Lịch sử phát triển)
└── docs/
    ├── HUONG_DAN_SU_DUNG.md
    └── HUONG_DAN_CAI_DAT.md
```

**Cách dùng:**
```bash
.\scripts\create-release.bat
```

---

### 2️⃣ `package-release.bat` (Nhanh)

**Chức năng:** Chỉ package (không build JAR)

**Yêu cầu:** File JAR đã build sẵn trong `target/`

**Phù hợp:** Khi cần cập nhật tài liệu hoặc tạo lại package

**Cách dùng:**
```bash
# Build JAR trước (nếu chưa có)
.\scripts\build.bat

# Sau đó package
.\scripts\package-release.bat
```

---

## 🚀 Quy trình phân phối đầy đủ

### Bước 1: Tạo release package

```bash
# Tùy chọn A: Build + Package (đầy đủ)
.\scripts\create-release.bat

# Tùy chọn B: Chỉ package (nếu có JAR sẵn)
.\scripts\package-release.bat
```

### Bước 2: Kiểm tra

```bash
cd release
dir

# Test chạy JAR
java -jar caro-game.jar
```

### Bước 3: Tạo file ZIP

**Windows Explorer:**
```
Chuột phải release\ > Send to > Compressed folder
```

**PowerShell:**
```powershell
Compress-Archive -Path release\* -DestinationPath caro-game-v1.0.0.zip
```

**CMD:**
```cmd
tar -a -c -f caro-game-v1.0.0.zip release\*
```

---

## 📤 Upload lên GitHub Release

1. Vào https://github.com/NamKhanhCTK46B/Caro-Game/releases
2. Click **"Create a new release"**
3. Chọn tag: `v1.0.0`
4. Điền release notes
5. Upload file: `caro-game-v1.0.0.zip`
6. Publish release

---

## 🎁 Nội dung gói phân phối

### File JAR
- `caro-game.jar` - Executable JAR (~9.5 MB)
- Chứa tất cả dependencies
- Cross-platform (Windows/macOS/Linux)

### Tài liệu
- **README.md** - Quick start guide cho end-users
- **CHANGELOG.md** - Version history
- **docs/HUONG_DAN_SU_DUNG.md** - Chi tiết cách chơi
- **docs/HUONG_DAN_CAI_DAT.md** - Hướng dẫn cài Java

---

## ✅ Checklist trước khi phân phối

- [ ] JAR file build thành công
- [ ] Test chạy JAR trên Windows
- [ ] Test chạy JAR trên macOS (nếu có)
- [ ] Kiểm tra tất cả tài liệu đầy đủ
- [ ] Version number chính xác
- [ ] CHANGELOG được cập nhật
- [ ] Tạo ZIP file thành công
- [ ] Upload lên GitHub Release

---

**Tác giả:** 2212391 - Nguyễn Hoàng Nam Khánh  
**Email:** 2212391@dlu.edu.vn  
**Ngày tạo:** 27/10/2025  
**Phiên bản:** 1.0
