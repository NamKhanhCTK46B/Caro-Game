# 🎮 CARO GAME - TRÒ CHƠI CỜ CARO

**Phiên bản:** 1.0-SNAPSHOT  
**Tác giả:** 2212391 - Nguyễn Hoàng Nam Khánh  
**Ngày tạo:** 26/10/2025  
**Ngày cập nhật:** 27/10/2025

---

## 📖 Giới thiệu

Game Caro (Gomoku/Five in a Row) với 3 chế độ chơi:
- 🟢 **Easy AI** - Random moves (cho người mới)
- 🟡 **Medium AI** - Heuristic evaluation (trung bình)
- 🔴 **Hard AI** - Minimax + Alpha-Beta Pruning (khó)

### ✨ Tính năng nổi bật:

- ✅ Giao diện đồ họa JavaFX hiện đại
- ✅ 3 mức độ AI khác nhau
- ✅ Undo/Redo không giới hạn
- ✅ Highlight đường thắng
- ✅ Thống kê điểm số
- ✅ Responsive UI
- ✅ Clean Architecture (MVC + Design Patterns)

---

## 🚀 Cài đặt nhanh

### Người dùng (chỉ chơi game):

1. **Cài Java 11+**
   - Download: https://adoptium.net/

2. **Chạy game:**
   ```cmd
   java -jar target\caro-game.jar
   ```

### Developer (build từ source):

1. **Yêu cầu:**
   - JDK 11+
   - Maven 3.6+

2. **Build:**
   ```cmd
   mvn clean package -DskipTests
   ```

3. **Chạy:**
   ```cmd
   java -jar target\caro-game.jar
   ```

📘 **Hướng dẫn chi tiết:** Xem [HUONG_DAN_CAI_DAT.md](HUONG_DAN_CAI_DAT.md)

---

## 🎯 Cách chơi

1. Chọn mức độ: Easy / Medium / Hard
2. Click vào ô trống để đánh X
3. AI sẽ tự động đánh O
4. Người đầu tiên có 5 quân liên tiếp (ngang/dọc/chéo) thắng
5. Dùng Undo/Redo để quay lại nước đi
6. Click "New Game" để chơi lại

### Phím tắt:
- **Ctrl+Z** - Undo
- **Ctrl+Y** - Redo
- **Ctrl+N** - New Game
- **Esc** - Back to Menu

---

## 🏗️ Công nghệ sử dụng

### Core Technologies:
- **Java 11** - Programming language
- **JavaFX 21** - GUI framework
- **Maven** - Build tool & dependency management

### Design Patterns:
- **MVC Pattern** - Architecture
- **Strategy Pattern** - AI algorithms (3 strategies)
- **Observer Pattern** - View updates
- **Memento Pattern** - Undo/Redo
- **Singleton Pattern** - Score management

### AI Algorithms:
- **Easy:** Random selection - O(n²)
- **Medium:** Heuristic pattern matching - O(n² × 8)
- **Hard:** Minimax + Alpha-Beta Pruning - O(b^(d/2))

---

## 📁 Cấu trúc dự án

```
caro-game/
├── src/main/java/              # Source code
│   └── com.kthp.tro_choi_caro/
│       ├── App.java            # Entry point
│       ├── controller/         # MVC Controllers (2 classes)
│       ├── model/              # Business logic (10 classes)
│       ├── strategy/           # AI algorithms (5 classes)
│       └── view/               # Observer interface (1 class)
├── src/main/resources/         # FXML & CSS
├── doc/                        # Documentation (8 files)
├── scripts/                    # ⭐ Build scripts (4 files, tối ưu -60%)
├── target/                     # Build output
│   └── caro-game.jar          # ⭐ Executable JAR
├── pom.xml                     # Maven config
├── .gitignore                  # ⭐ Git ignore rules
├── README.md                   # This file
├── HUONG_DAN_CAI_DAT.md       # ⭐ Complete installation guide
├── TOI_UU_SCRIPTS.md          # ⭐ Scripts optimization docs
└── TOM_TAT_TOI_UU.md          # ⭐ Optimization summary
```

---

## 📊 Thống kê dự án

| Metric              | Value        |
|---------------------|--------------|
| **Lines of Code**   | ~2,000       |
| **Java Classes**    | 20 classes   |
| **Design Patterns** | 5 patterns   |
| **AI Algorithms**   | 3 algorithms |
| **Documentation**   | 12 MD files  |
| **JAR Size**        | 9.5 MB       |
| **Build Time**      | ~20 seconds  |

---

## 🎮 Screenshots

### Menu Screen
```
┌────────────────────────────────┐
│         CARO GAME              │
│                                │
│      [ Easy Mode ]             │
│      [ Medium Mode ]           │
│      [ Hard Mode ]             │
│                                │
│   High Scores: 10 - 5          │
│      [ Exit ]                  │
└────────────────────────────────┘
```

### Game Screen
```
┌────────────────────────────────┐
│ Status: Playing | Turn: X      │
│ Score: Player 3 - AI 2         │
├────────────────────────────────┤
│   0 1 2 3 4 5 6 7 8 9 ...      │
│ 0 . . . . . . . . . . ...      │
│ 1 . . X . . . . . . . ...      │
│ 2 . . . O . . . . . . ...      │
│ 3 . . X . O . . . . . ...      │
│ 4 . . . . . . . . . . ...      │
│ ... (15×15 board)              │
├────────────────────────────────┤
│ [Undo] [Redo] [New] [Menu]     │
└────────────────────────────────┘
```

---

## 📚 Tài liệu

### Tài liệu dự án (folder `doc/`):

1. **01_TONG_QUAN_DU_AN.md** - Tổng quan dự án
2. **02_KIEN_TRUC_PHAN_MEM.md** - Kiến trúc phần mềm
3. **03_THUAT_TOAN_AI.md** - Thuật toán AI chi tiết
4. **04_KET_LUAN_VA_DANH_GIA.md** - Kết luận và đánh giá
5. **05_SO_DO_LOP_CHI_TIET.md** - Sơ đồ lớp
6. **06_UML_STRATEGY_PATTERN.md** - UML Strategy Pattern
7. **07_UML_OBSERVER_PATTERN.md** - UML Observer Pattern
8. **08_UML_MEMENTO_PATTERN.md** - UML Memento Pattern

### Hướng dẫn:

- **HUONG_DAN_CAI_DAT.md** - Hướng dẫn cài đặt đầy đủ
- **scripts/README.md** - Hướng dẫn sử dụng scripts

---

## 🛠️ Scripts tiện ích

| Script                   | Mục đích                   |
|--------------------------|----------------------------|
| `build-jar.bat`          | Build executable JAR       |
| `run-jar.bat`            | Chạy game từ JAR           |
| `run.bat`                | Compile và chạy bằng Maven |
| `run-quick.bat`          | Chạy nhanh (không compile) |
| `clean.bat`              | Xóa build files            |
| `cleanup.bat`            | Tối ưu dung lượng dự án    |
| `install-maven-user.bat` | Cài Maven tự động          |
| `verify-and-build.bat`   | Verify + Build             |

**Cách dùng:**
```cmd
cd scripts
build-jar.bat
```

---

## 🔧 Build & Development

### Build commands:

```bash
# Clean project
mvn clean

# Compile only
mvn compile

# Run with Maven
mvn javafx:run

# Build JAR (recommended)
mvn clean package -DskipTests

# Build offline (fast)
mvn package -DskipTests -o

# Build with tests
mvn clean package
```

### Run commands:

```bash
# Run from JAR
java -jar target/caro-game.jar

# Run with Maven
mvn javafx:run

# Run with script
scripts\run-jar.bat
```

---

## 🧪 Testing

```bash
# Run tests
mvn test

# Run specific test
mvn test -Dtest=GameModelTest

# Run with coverage
mvn clean test jacoco:report
```

---

## 🐛 Xử lý lỗi

### Lỗi thường gặp:

1. **"java không được nhận dạng"**
   - Cài Java 11+
   - Thêm vào PATH

2. **"mvn không được nhận dạng"**
   - Đóng và mở lại terminal
   - Hoặc dùng script: `install-maven-user.bat`

3. **"BUILD FAILURE"**
   - Clean: `mvn clean`
   - Xóa cache: `rm -rf ~/.m2/repository`
   - Build lại: `mvn package`

4. **"JavaFX runtime missing"**
   - Dùng đúng JAR: `caro-game.jar`
   - Không dùng: `tro_choi_caro-1.0-SNAPSHOT.jar`

📘 **Chi tiết:** Xem [HUONG_DAN_CAI_DAT.md](HUONG_DAN_CAI_DAT.md) - Mục "Xử lý lỗi"

---

## 🎯 Roadmap

### Version 1.0 (Current) ✅
- [x] 3 AI levels
- [x] Undo/Redo
- [x] Score tracking
- [x] JavaFX GUI

### Version 1.1 (Planned)
- [ ] Multiplayer (LAN/Online)
- [ ] Save/Load game
- [ ] Custom board size
- [ ] Game replay
- [ ] Sound effects
- [ ] Themes

### Version 2.0 (Future)
- [ ] Neural Network AI
- [ ] Tournament mode
- [ ] Leaderboard
- [ ] Achievements
- [ ] Mobile version

---

## 🤝 Contributing

Contributions are welcome! Please:

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request


---

## 👨‍💻 Tác giả

**Nguyễn Hoàng Nam Khánh**
- MSSV: 2212391
- Trường: Đại học Đà Lạt

---

## 🙏 Acknowledgments

- **JavaFX** - GUI framework
- **Maven** - Build tool
- **Eclipse Adoptium** - JDK distribution
- **Stack Overflow** - Community support
- **GitHub Copilot** - AI assistant

---

## ⭐ Star History

Nếu bạn thấy project hữu ích, hãy cho một ⭐!

---

**Made with ❤️ by Nguyễn Hoàng Nam Khánh**

**Happy Gaming! 🎮🎯**
