# 📝 CHANGELOG - CARO GAME PROJECT

Lịch sử phát triển và cập nhật dự án Caro Game

---

## [1.0.0] - 2025-10-27 - Initial Release 🎉

### ✨ Features

#### Core Game
- ✅ **15x15 Caro/Gomoku board** - Classic game board size
- ✅ **Win detection** - Automatic 5-in-a-row detection (horizontal, vertical, diagonal)
- ✅ **Winning line highlight** - Visual feedback when game ends
- ✅ **Turn-based gameplay** - Player vs AI alternating moves

#### AI System (3 Difficulty Levels)
- 🟢 **Easy AI** - Random move selection
  - Simple random placement
  - Good for beginners
  - Fast response time
  
- 🟡 **Medium AI** - Heuristic evaluation
  - Pattern recognition (2, 3, 4 in a row)
  - Blocking opponent threats
  - Creating winning opportunities
  
- 🔴 **Hard AI** - Minimax with Alpha-Beta Pruning
  - Game tree search (depth 3-4)
  - Alpha-Beta optimization
  - Strategic planning
  - Near-optimal moves

#### UI/UX Features
- ✅ **JavaFX GUI** - Modern desktop interface
- ✅ **Responsive design** - Adapts to window size
- ✅ **Custom styling** - CSS-based theming
- ✅ **Smooth animations** - Cell hover effects
- ✅ **Clear visual feedback** - X and O markers

#### Game Features
- ✅ **Undo/Redo** - Unlimited undo/redo moves
- ✅ **Score tracking** - Persistent score system
- ✅ **Game reset** - Start new game anytime
- ✅ **Difficulty selection** - Choose AI level from menu

---

### 🏗️ Architecture

#### Design Patterns Implemented
- ✅ **MVC Pattern** - Separation of concerns
  - Model: Game logic (10 classes)
  - View: FXML + Observer interface
  - Controller: GameController, MenuController

- ✅ **Strategy Pattern** - AI algorithms
  - AIStrategy interface
  - EasyAIStrategy, MediumAIStrategy, HardAIStrategy
  - Runtime AI switching

- ✅ **Observer Pattern** - UI updates
  - GameObserver interface
  - GameController observes GameModel
  - Automatic UI synchronization

- ✅ **Memento Pattern** - Undo/Redo
  - GameStateMemento for state capture
  - MoveHistory for state management
  - Full game state restoration

- ✅ **Singleton Pattern** - Score management
  - ScoreManager singleton
  - Thread-safe implementation
  - Centralized score tracking

---

### 🔧 Technical Stack

#### Core Technologies
- ☕ **Java 11+** - Programming language
- 🎨 **JavaFX 21** - GUI framework
- 🔨 **Maven 3.6+** - Build automation

#### Project Structure
```
src/main/java/com/kthp/tro_choi_caro/
├── App.java                    # Entry point
├── controller/                 # MVC Controllers (2 classes)
│   ├── GameController.java
│   └── MenuController.java
├── model/                      # Business logic (10 classes)
│   ├── Board.java
│   ├── Cell.java
│   ├── GameModel.java
│   ├── GameState.java
│   ├── GameStateMemento.java
│   ├── Move.java
│   ├── MoveHistory.java
│   ├── Player.java
│   ├── ScoreManager.java
│   └── WinningLine.java
├── strategy/                   # AI algorithms (5 classes)
│   ├── AIStrategy.java
│   ├── AIPlayer.java
│   ├── EasyAIStrategy.java
│   ├── MediumAIStrategy.java
│   └── HardAIStrategy.java
└── view/                       # Observer interface (1 class)
    └── GameObserver.java
```

---

### 📚 Documentation

#### Comprehensive Documentation (8 files)
- ✅ `01_TONG_QUAN_DU_AN.md` - Project overview
- ✅ `02_KIEN_TRUC_PHAN_MEM.md` - Software architecture
- ✅ `03_THUAT_TOAN_AI.md` - AI algorithms (~2,500 lines)
- ✅ `04_KET_LUAN_VA_DANH_GIA.md` - Conclusion & evaluation
- ✅ `05_SO_DO_LOP_CHI_TIET.md` - Detailed class diagrams
- ✅ `06_UML_STRATEGY_PATTERN.md` - Strategy pattern UML
- ✅ `07_UML_OBSERVER_PATTERN.md` - Observer pattern UML
- ✅ `08_UML_MEMENTO_PATTERN.md` - Memento pattern UML

#### User Guides
- ✅ `README.md` - Project overview & quick start
- ✅ `HUONG_DAN_CAI_DAT.md` - Complete installation guide
  - Java/Maven setup (Windows/macOS/Linux)
  - Build instructions
  - Troubleshooting (9 common errors)
  - FAQ (8 questions)

---

### 🔨 Build Scripts (Optimized)

#### 4 Automated Scripts
- ✅ `build.bat` - Build menu (3 options)
  - [1] Compile only
  - [2] Package JAR
  - [3] Clean + Package
  
- ✅ `run.bat` - Run menu (3 options)
  - [1] Run from source
  - [2] Quick run
  - [3] Run from JAR
  
- ✅ `cleanup.bat` - Clean & optimize
  - Remove target/ (~9.2 MB)
  - Remove temp files
  - Optional: Clean Maven cache
  
- ✅ `install-maven.bat` - Smart Maven installer
  - Auto-detect: Admin/User mode
  - Download Maven 3.9.6
  - Configure PATH automatically

**Script Documentation:**
- ✅ `scripts/README.md` - Detailed scripts guide

---

### 📊 Project Statistics

| Metric | Value |
|--------|-------|
| **Java Classes** | 20 classes |
| **Lines of Code** | ~2,000 LOC |
| **Documentation** | 8 MD files |
| **Build Scripts** | 4 BAT files |
| **FXML Files** | 2 files |
| **CSS Files** | 2 files |
| **Design Patterns** | 5 patterns |
| **AI Algorithms** | 3 strategies |
| **JAR Size** | ~9.5 MB |

---

### 🎯 Code Quality

#### Best Practices
- ✅ Clean code principles
- ✅ SOLID principles
- ✅ DRY (Don't Repeat Yourself)
- ✅ Separation of concerns
- ✅ Encapsulation
- ✅ Interface-based design

#### Testing
- ✅ Manual testing completed
- ✅ All AI levels tested
- ✅ Undo/Redo verified
- ✅ Score tracking validated
- ✅ Win detection confirmed

---

### 📦 Distribution

#### Deliverables
- ✅ **Source code** - Full project source
- ✅ **Executable JAR** - `target/caro-game.jar` (~9.5 MB)
- ✅ **Documentation** - Complete guides
- ✅ **Build scripts** - Automated build tools
- ✅ **.gitignore** - Version control configuration

#### System Requirements
- **OS:** Windows 10/11, macOS 10.14+, Linux
- **RAM:** 2 GB minimum
- **Storage:** 50 MB
- **Java:** JRE/JDK 11 or higher

---

### 🔄 Version Control

#### Git Configuration
- ✅ Git repository initialized
- ✅ `.gitignore` configured
  - Ignores: target/, .idea/, .vscode/, *.class
  - Tracks: source code, docs, scripts
- ✅ Initial commit with detailed description
- ✅ Pushed to GitHub: `NamKhanhCTK46B/Caro-Game`

---

### 🚀 Future Enhancements (Roadmap)

#### Version 1.1 (Planned)
- [ ] Online multiplayer support
- [ ] Game replay system
- [ ] Advanced statistics dashboard
- [ ] Custom board sizes (10x10, 20x20)
- [ ] Themes and skins

#### Version 2.0 (Future)
- [ ] AI difficulty customization
- [ ] Tournament mode
- [ ] Achievements system
- [ ] Cross-platform mobile version
- [ ] Cloud save/sync

---

### 🙏 Acknowledgments

**Developed by:** 2212391 - Nguyễn Hoàng Nam Khánh  
**Course:** Thiết kế Phần mềm  
**University:** Cao Đẳng Kỹ Thuật - Công Nghệ Bà Rịa - Vũng Tàu  
**Academic Year:** 2024-2025

---

### 📄 License

This project is created for educational purposes.

---

### 🔗 Links

- **GitHub Repository:** https://github.com/NamKhanhCTK46B/Caro-Game
- **Issues/Bug Reports:** https://github.com/NamKhanhCTK46B/Caro-Game/issues
- **Documentation:** See `doc/` folder

---

**Release Date:** October 27, 2025  
**Version:** 1.0.0  
**Status:** ✅ Stable Release
