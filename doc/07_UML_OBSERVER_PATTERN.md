# 🎨 CLASS DIAGRAM UML - OBSERVER PATTERN

## Tổng quan

File này chứa Class Diagram UML chi tiết cho **Observer Pattern** được áp dụng trong kiến trúc MVC của Trò Chơi Caro.

 
**Tác giả:** 2212391- Nguyễn Hoàng Nam Khánh  
**Design Pattern:** Observer Pattern

---

## 1. Class Diagram Tổng thể (UML Notation)

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                          OBSERVER PATTERN                                    │
│                     MVC Architecture - Tro Choi Caro                         │
└─────────────────────────────────────────────────────────────────────────────┘

                    ┌────────────────────────────────────┐
                    │    «interface»                     │
                    │      GameObserver                  │
                    ├────────────────────────────────────┤
                    │ + onMoveMade(Move): void           │
                    │ + onGameStateChanged(...): void    │
                    │ + onBoardReset(): void             │
                    │ + onBoardRestored(): void          │
                    │ + onPlayerChanged(String): void    │
                    │ + onWinningLineFound(...): void    │
                    └────────────────────────────────────┘
                                    ▲
                                    │
                                    │ implements
                                    │
                ┌───────────────────┴───────────────────┐
                │                                       │
                │                                       │
┌───────────────┴────────┐               ┌──────────────┴─────────┐
│   GameController       │               │   MenuController       │
│  (Concrete Observer 1) │               │ (Concrete Observer 2)  │
├────────────────────────┤               ├────────────────────────┤
│ - gameModel: GameModel │               │ - gameModel: GameModel │
│ - boardGrid: GridPane  │               │ ...                    │
│ - statusLabel: Label   │               ├────────────────────────┤
│ ...                    │               │ + onMoveMade()         │
├────────────────────────┤               │ + onGameStateChanged() │
│ + onMoveMade()         │               │ + ... (6 methods)      │
│ + onGameStateChanged() │               └────────────────────────┘
│ + ... (6 methods)      │
└────────────────────────┘


                    ┌────────────────────────────────────┐
                    │          GameModel                 │
                    │     (ConcreteSubject)              │
                    ├────────────────────────────────────┤
                    │ - observers: List<GameObserver>    │
                    │ - board: Board                     │
                    │ - currentPlayer: String            │
                    │ - gameState: GameState             │
                    │ - moveNumber: int                  │
                    │ - winningLine: WinningLine         │
                    ├────────────────────────────────────┤
                    │ + addObserver(GameObserver): void  │
                    │ + removeObserver(GameObserver): void│
                    │ - notifyMoveMade(Move): void       │
                    │ - notifyGameStateChanged(...): void│
                    │ - notifyBoardReset(): void         │
                    │ - notifyBoardRestored(): void      │
                    │ - notifyPlayerChanged(String): void│
                    │ - notifyWinningLine(...): void     │
                    │ + makeMove(int, int): boolean      │
                    │ + resetGame(): void                │
                    └────────────────────────────────────┘
                                    │
                                    │ uses
                                    ▼
                    ┌────────────────────────────────────┐
                    │      GameObserver                  │
                    │      (Interface)                   │
                    └────────────────────────────────────┘
```

---

## 2. Class Diagram Chi tiết với Attributes và Methods

### 2.1 GameObserver (Observer Interface)

```
╔══════════════════════════════════════════════════════════════════╗
║                        «interface»                                ║
║                        GameObserver                               ║
╠══════════════════════════════════════════════════════════════════╣
║ «public abstract methods»                                         ║
║                                                                   ║
║ + onMoveMade(move: Move): void                                    ║
║   ↳ Được gọi khi có nước đi mới                                  ║
║   ↳ @param move - Thông tin nước đi (row, col, player)          ║
║   ↳ View cần cập nhật cell tương ứng trên board                 ║
║                                                                   ║
║ + onGameStateChanged(newState: GameState, winner: String): void  ║
║   ↳ Được gọi khi trạng thái game thay đổi                       ║
║   ↳ @param newState - PLAYING/X_WON/O_WON/DRAW                   ║
║   ↳ @param winner - "X", "O", hoặc null nếu hòa                 ║
║   ↳ View cần hiển thị thông báo và disable board nếu game over  ║
║                                                                   ║
║ + onBoardReset(): void                                            ║
║   ↳ Được gọi khi bàn cờ được reset (New Game)                   ║
║   ↳ View cần xóa tất cả quân cờ và reset UI về ban đầu          ║
║                                                                   ║
║ + onBoardRestored(): void                                         ║
║   ↳ Được gọi khi board được khôi phục từ Memento (Undo/Redo)    ║
║   ↳ View cần vẽ lại tất cả quân cờ từ board state hiện tại      ║
║   ↳ Khác với onBoardReset - vẽ lại thay vì xóa toàn bộ          ║
║                                                                   ║
║ + onPlayerChanged(currentPlayer: String): void                    ║
║   ↳ Được gọi khi người chơi hiện tại thay đổi (chuyển lượt)     ║
║   ↳ @param currentPlayer - "X" hoặc "O"                          ║
║   ↳ View cần update turn indicator                              ║
║                                                                   ║
║ + onWinningLineFound(winningLine: WinningLine): void             ║
║   ↳ Được gọi khi tìm thấy đường thắng (5 quân liên tiếp)        ║
║   ↳ @param winningLine - Thông tin 5 vị trí thắng               ║
║   ↳ View cần highlight các ô trong đường thắng                  ║
║   ↳ Được gọi TRƯỚC onGameStateChanged để UI highlight trước     ║
║                                                                   ║
╚══════════════════════════════════════════════════════════════════╝
```

**Responsibilities:**
- Định nghĩa contract cho tất cả Observer classes
- Bắt buộc implement 6 callback methods
- Cho phép Subject thông báo cho Observer mà không biết chi tiết implementation

**Pattern Role:** Observer Interface

**Event Flow:**
```
Model Change → Subject notifies → Observer callbacks → View updates
```

---

### 2.2 GameModel (ConcreteSubject)

```
╔══════════════════════════════════════════════════════════════════╗
║                         GameModel                                 ║
║                    (ConcreteSubject)                              ║
╠══════════════════════════════════════════════════════════════════╣
║ «private fields»                                                  ║
║                                                                   ║
║ - observers: List<GameObserver>                                   ║
║   ↳ Danh sách các observers đang lắng nghe                       ║
║   ↳ Được khởi tạo = new ArrayList<>()                            ║
║                                                                   ║
║ - board: Board                                                    ║
║   ↳ Bàn cờ 15×15                                                 ║
║                                                                   ║
║ - player1: Player                                                 ║
║   ↳ Player("X", "Player", false)                                 ║
║                                                                   ║
║ - player2: Player                                                 ║
║   ↳ Player("O", "AI", true)                                      ║
║                                                                   ║
║ - currentPlayer: String                                           ║
║   ↳ "X" hoặc "O" - người chơi hiện tại                           ║
║                                                                   ║
║ - gameState: GameState                                            ║
║   ↳ PLAYING / X_WON / O_WON / DRAW                               ║
║                                                                   ║
║ - moveNumber: int                                                 ║
║   ↳ Số nước đã đi                                                ║
║                                                                   ║
║ - winningLine: WinningLine                                        ║
║   ↳ Đường thắng (5 vị trí liên tiếp) nếu có                      ║
║                                                                   ║
║ - moveHistory: MoveHistory                                        ║
║   ↳ Caretaker cho Memento Pattern                                ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «constructors»                                                    ║
║                                                                   ║
║ + GameModel()                                                     ║
║   ↳ Khởi tạo board, players, observers list                      ║
║   ↳ currentPlayer = "X" (X đi trước)                             ║
║   ↳ gameState = PLAYING                                          ║
║   ↳ observers = new ArrayList<>()                                ║
║   ↳ Lưu trạng thái ban đầu vào history                           ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «Observer Pattern - Public Methods»                               ║
║                                                                   ║
║ + addObserver(observer: GameObserver): void                       ║
║   ↳ Thêm observer vào danh sách                                  ║
║   ↳ Kiểm tra !observers.contains() để tránh duplicate            ║
║   ↳ Algorithm: O(1)                                              ║
║                                                                   ║
║ + removeObserver(observer: GameObserver): void                    ║
║   ↳ Xóa observer khỏi danh sách                                  ║
║   ↳ Algorithm: O(n)                                              ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «Observer Pattern - Private Notify Methods»                       ║
║                                                                   ║
║ - notifyMoveMade(move: Move): void                                ║
║   ↳ for (observer : observers)                                   ║
║       observer.onMoveMade(move)                                  ║
║   ↳ Được gọi sau mỗi nước đi hợp lệ                              ║
║                                                                   ║
║ - notifyGameStateChanged(newState: GameState, winner: String): void║
║   ↳ for (observer : observers)                                   ║
║       observer.onGameStateChanged(newState, winner)              ║
║   ↳ Được gọi khi game kết thúc hoặc reset                        ║
║                                                                   ║
║ - notifyBoardReset(): void                                        ║
║   ↳ for (observer : observers)                                   ║
║       observer.onBoardReset()                                    ║
║   ↳ Được gọi khi resetGame()                                     ║
║                                                                   ║
║ - notifyBoardRestored(): void                                     ║
║   ↳ for (observer : observers)                                   ║
║       observer.onBoardRestored()                                 ║
║   ↳ Được gọi khi restoreFromMemento() (Undo/Redo)                ║
║                                                                   ║
║ - notifyPlayerChanged(currentPlayer: String): void                ║
║   ↳ for (observer : observers)                                   ║
║       observer.onPlayerChanged(currentPlayer)                    ║
║   ↳ Được gọi khi switchPlayer()                                  ║
║                                                                   ║
║ - notifyWinningLine(winningLine: WinningLine): void               ║
║   ↳ for (observer : observers)                                   ║
║       observer.onWinningLineFound(winningLine)                   ║
║   ↳ Được gọi TRƯỚC notifyGameStateChanged khi có người thắng     ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «Game Logic Methods»                                              ║
║                                                                   ║
║ + makeMove(row: int, col: int): boolean                           ║
║   ↳ Algorithm:                                                    ║
║     1. Validate: gameState == PLAYING && cell empty              ║
║     2. board.makeMove(row, col, currentPlayer)                   ║
║     3. moveNumber++                                              ║
║     4. notifyMoveMade(new Move(row, col, currentPlayer))         ║
║     5. Check win: foundWinningLine = board.findWinningLine()     ║
║     6. If win:                                                   ║
║        - this.winningLine = foundWinningLine                     ║
║        - gameState = X_WON or O_WON                              ║
║        - notifyWinningLine(winningLine) ← FIRST                  ║
║        - notifyGameStateChanged(gameState, winner) ← SECOND      ║
║     7. Else if board.isFull():                                   ║
║        - gameState = DRAW                                        ║
║        - notifyGameStateChanged(DRAW, null)                      ║
║     8. Else:                                                     ║
║        - switchPlayer()                                          ║
║     9. saveCurrentState() (Memento Pattern)                      ║
║                                                                   ║
║ + resetGame(): void                                               ║
║   ↳ Algorithm:                                                    ║
║     1. board.clear()                                             ║
║     2. currentPlayer = "X"                                       ║
║     3. gameState = PLAYING                                       ║
║     4. moveNumber = 0                                            ║
║     5. moveHistory.clear()                                       ║
║     6. winningLine = null                                        ║
║     7. notifyBoardReset()                                        ║
║     8. notifyPlayerChanged("X")                                  ║
║     9. notifyGameStateChanged(PLAYING, null)                     ║
║    10. saveCurrentState()                                        ║
║                                                                   ║
║ - switchPlayer(): void                                            ║
║   ↳ currentPlayer = (currentPlayer == "X") ? "O" : "X"           ║
║   ↳ notifyPlayerChanged(currentPlayer)                           ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «Getters»                                                         ║
║                                                                   ║
║ + getBoard(): Board                                               ║
║ + getCurrentPlayer(): String                                      ║
║ + getGameState(): GameState                                       ║
║ + getPlayer1(): Player                                            ║
║ + getPlayer2(): Player                                            ║
║ + getMoveNumber(): int                                            ║
║ + isGameOver(): boolean                                           ║
║ + getWinningLine(): WinningLine                                   ║
║                                                                   ║
╚══════════════════════════════════════════════════════════════════╝
```

**Notification Flow trong makeMove():**
```
makeMove(row, col)
    │
    ├─> 1. Validate move
    │
    ├─> 2. board.makeMove()
    │
    ├─> 3. notifyMoveMade(move) ───────────> Observer.onMoveMade()
    │                                           └─> Update cell UI
    │
    ├─> 4. Check winning condition
    │
    ├─> 5. If WIN:
    │     ├─> notifyWinningLine() ──────────> Observer.onWinningLineFound()
    │     │                                     └─> Highlight winning cells
    │     │
    │     └─> notifyGameStateChanged() ─────> Observer.onGameStateChanged()
    │                                           └─> Show winner message
    │
    ├─> 6. If DRAW:
    │     └─> notifyGameStateChanged() ─────> Observer.onGameStateChanged()
    │                                           └─> Show draw message
    │
    └─> 7. If CONTINUE:
          └─> switchPlayer()
                └─> notifyPlayerChanged() ──> Observer.onPlayerChanged()
                                                └─> Update turn label
```

**Pattern Role:** ConcreteSubject (Observable Implementation)

**Characteristics:**
- ✅ Maintains list of observers
- ✅ Provides methods to add/remove observers
- ✅ Notifies all observers when state changes
- ✅ Decoupled from concrete observer implementations
- ✅ Multiple notification types for different events

---

### 2.3 GameController (Concrete Observer 1)

```
╔══════════════════════════════════════════════════════════════════╗
║                      GameController                               ║
║              implements GameObserver                              ║
╠══════════════════════════════════════════════════════════════════╣
║ «FXML-injected fields»                                            ║
║                                                                   ║
║ @FXML - boardGrid: GridPane                                      ║
║   ↳ Container chứa 15×15 buttons                                 ║
║                                                                   ║
║ @FXML - statusLabel: Label                                       ║
║   ↳ Hiển thị trạng thái game (Đang chơi/Thắng/Thua/Hòa)          ║
║                                                                   ║
║ @FXML - turnLabel: Label                                         ║
║   ↳ Hiển thị lượt chơi hiện tại                                  ║
║                                                                   ║
║ @FXML - scoreLabel: Label                                        ║
║   ↳ Hiển thị điểm số                                             ║
║                                                                   ║
║ @FXML - difficultyLabel: Label                                   ║
║   ↳ Hiển thị độ khó AI                                           ║
║                                                                   ║
║ @FXML - undoButton: Button                                       ║
║ @FXML - redoButton: Button                                       ║
║ @FXML - newGameButton: Button                                    ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «private fields»                                                  ║
║                                                                   ║
║ - gameModel: GameModel                                           ║
║   ↳ Reference đến Subject (Model)                                ║
║                                                                   ║
║ - aiPlayer: AIPlayer                                             ║
║   ↳ AI player với Strategy Pattern                               ║
║                                                                   ║
║ - cellButtons: Button[][]                                        ║
║   ↳ 15×15 array of buttons                                       ║
║                                                                   ║
║ - difficulty: String                                             ║
║   ↳ "EASY" / "MEDIUM" / "HARD"                                   ║
║                                                                   ║
║ - scoreManager: ScoreManager                                     ║
║   ↳ Singleton quản lý điểm                                       ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «initialization»                                                  ║
║                                                                   ║
║ + initialize(): void                                              ║
║   ↳ @FXML lifecycle method                                       ║
║   ↳ Algorithm:                                                    ║
║     1. gameModel = new GameModel()                               ║
║     2. gameModel.addObserver(this) ← Register as observer        ║
║     3. initializeBoard()                                         ║
║     4. updateTurnLabel()                                         ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «GameObserver implementation»                                     ║
║                                                                   ║
║ + onMoveMade(move: Move): void                                    ║
║   ↳ Platform.runLater(() -> {                                    ║
║       updateCellButton(move.getRow(), move.getCol(),             ║
║                        move.getPlayer());                        ║
║     });                                                          ║
║   ↳ Cập nhật cell button với X hoặc O                           ║
║   ↳ Thread-safe với JavaFX Application Thread                   ║
║                                                                   ║
║ + onGameStateChanged(newState: GameState, winner: String): void  ║
║   ↳ Platform.runLater(() -> {                                    ║
║       switch (newState) {                                        ║
║         case X_WON:                                              ║
║           statusLabel.setText("🎉 Bạn thắng! 🎉");               ║
║           scoreManager.addWin("X");                              ║
║           disableBoard();                                        ║
║           break;                                                 ║
║         case O_WON:                                              ║
║           statusLabel.setText("😞 AI thắng! 😞");                ║
║           scoreManager.addWin("O");                              ║
║           disableBoard();                                        ║
║           break;                                                 ║
║         case DRAW:                                               ║
║           statusLabel.setText("🤝 Hòa! 🤝");                     ║
║           scoreManager.addDraw();                                ║
║           disableBoard();                                        ║
║           break;                                                 ║
║       }                                                          ║
║       updateScoreLabel();                                        ║
║     });                                                          ║
║                                                                   ║
║ + onBoardReset(): void                                            ║
║   ↳ Platform.runLater(() -> {                                    ║
║       clearBoard();                                              ║
║       enableBoard();                                             ║
║       statusLabel.setText("🎮 Đang chơi...");                    ║
║     });                                                          ║
║   ↳ Xóa toàn bộ quân cờ, reset UI                               ║
║                                                                   ║
║ + onBoardRestored(): void                                         ║
║   ↳ Platform.runLater(() -> {                                    ║
║       redrawBoard(gameModel.getBoard());                         ║
║       enableBoard();                                             ║
║     });                                                          ║
║   ↳ Vẽ lại board từ state (Undo/Redo)                           ║
║   ↳ Khác với onBoardReset - không xóa, chỉ vẽ lại              ║
║                                                                   ║
║ + onPlayerChanged(currentPlayer: String): void                    ║
║   ↳ Platform.runLater(() -> {                                    ║
║       updateTurnLabel();                                         ║
║       if (currentPlayer.equals("O")) {                           ║
║         makeAIMove();                                            ║
║       }                                                          ║
║     });                                                          ║
║   ↳ Update turn indicator, trigger AI nếu lượt AI               ║
║                                                                   ║
║ + onWinningLineFound(winningLine: WinningLine): void             ║
║   ↳ Platform.runLater(() -> {                                    ║
║       highlightWinningCells(winningLine);                        ║
║     });                                                          ║
║   ↳ Highlight 5 ô thắng với màu đặc biệt                        ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «private helper methods»                                          ║
║                                                                   ║
║ - updateCellButton(row: int, col: int, content: String): void    ║
║ - clearBoard(): void                                             ║
║ - redrawBoard(board: Board): void                                ║
║ - disableBoard(): void                                           ║
║ - enableBoard(): void                                            ║
║ - updateTurnLabel(): void                                        ║
║ - updateScoreLabel(): void                                       ║
║ - highlightWinningCells(winningLine: WinningLine): void          ║
║ - makeAIMove(): void                                             ║
║                                                                   ║
╚══════════════════════════════════════════════════════════════════╝
```

**Registration Flow:**
```
GameController.initialize()
    │
    ├─> gameModel = new GameModel()
    │
    ├─> gameModel.addObserver(this) ← Register observer
    │       │
    │       └─> GameModel.observers.add(this)
    │
    └─> Now GameController will receive all notifications
```

**Observer Callback Flow:**
```
User clicks cell (5, 7)
    │
    ├─> GameController.handleCellClick(5, 7)
    │       │
    │       └─> gameModel.makeMove(5, 7)
    │               │
    │               ├─> board.makeMove(5, 7, "X")
    │               │
    │               ├─> notifyMoveMade(Move(5, 7, "X"))
    │               │       │
    │               │       └─> for (observer : observers)
    │               │               observer.onMoveMade(move)
    │               │                   │
    │               │                   └─> GameController.onMoveMade()
    │               │                           │
    │               │                           └─> updateCellButton(5, 7, "X")
    │               │
    │               └─> switchPlayer() → notifyPlayerChanged("O")
    │                       │
    │                       └─> GameController.onPlayerChanged("O")
    │                               │
    │                               └─> makeAIMove()
    │
    └─> UI updated automatically via observer callbacks
```

**Pattern Role:** Concrete Observer

---

### 2.4 MenuController (Concrete Observer 2)

```
╔══════════════════════════════════════════════════════════════════╗
║                      MenuController                               ║
║              implements GameObserver                              ║
╠══════════════════════════════════════════════════════════════════╣
║ «private fields»                                                  ║
║                                                                   ║
║ - gameModel: GameModel                                           ║
║   ↳ Reference đến Subject (nếu cần)                              ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «GameObserver implementation»                                     ║
║                                                                   ║
║ + onMoveMade(move: Move): void                                    ║
║   ↳ // Menu không quan tâm đến moves                             ║
║                                                                   ║
║ + onGameStateChanged(newState: GameState, winner: String): void  ║
║   ↳ // Menu có thể log hoặc hiển thị stats                       ║
║                                                                   ║
║ + onBoardReset(): void                                            ║
║ + onBoardRestored(): void                                         ║
║ + onPlayerChanged(currentPlayer: String): void                    ║
║ + onWinningLineFound(winningLine: WinningLine): void             ║
║   ↳ // Empty implementations hoặc minimal logic                  ║
║                                                                   ║
╚══════════════════════════════════════════════════════════════════╝
```

**Note:** MenuController có thể implement GameObserver nhưng thường không cần xử lý nhiều events. Đây là ví dụ về **Selective Implementation** - chỉ xử lý events quan tâm.

**Pattern Role:** Concrete Observer (Optional/Minimal)

---

## 3. Relationships Diagram

### 3.1 Class Relationships

```
┌─────────────────────────────────────────────────────────────────┐
│                    RELATIONSHIPS OVERVIEW                        │
└─────────────────────────────────────────────────────────────────┘

GameModel (ConcreteSubject) ──manages──> List<GameObserver>
    │                                           │
    │                                           │ contains
    │                                           ▼
    │                              ┌──────────────────────────┐
    │                              │   GameObserver instances │
    └────────notifies─────────────>│   (GameController, etc)  │
                                   └──────────────────────────┘


GameObserver ◄──────implements────── GameController
 (Observer)                          (ConcreteObserver 1)
                                           │
GameObserver ◄──────implements────── MenuController
                                    (ConcreteObserver 2)


Relationship Types:
─────────> Association (uses/manages)
◄──────── Realization (implements interface)
- - - - -> Dependency (temporary usage)
```

### 3.2 Relationship Details

| From | To | Type | Multiplicity | Description |
|------|-----|------|--------------|-------------|
| GameModel (ConcreteSubject) | GameObserver | **Association** | 1 → * | ConcreteSubject manages observers |
| GameController (ConcreteObserver) | GameObserver | **Realization** | - | Implements observer interface |
| MenuController (ConcreteObserver) | GameObserver | **Realization** | - | Implements observer interface |
| GameModel | GameController | **Dependency** | - | Notifies via interface |
| GameObserver | Move | **Dependency** | - | Parameter in callback |
| GameObserver | GameState | **Dependency** | - | Parameter in callback |
| GameObserver | WinningLine | **Dependency** | - | Parameter in callback |

---

## 4. Sequence Diagram - Observer Pattern Flow

### 4.1 Complete Game Move Sequence

```
User    GameController    GameModel      List<Observer>    GameController
 │            │               │                │                 │
 │ click(5,7) │               │                │                 │
 ├───────────>│               │                │                 │
 │            │ makeMove(5,7) │                │                 │
 │            ├──────────────>│                │                 │
 │            │               │ board.makeMove()                 │
 │            │               ├────────────────┤                 │
 │            │               │                │                 │
 │            │               │ for each observer:               │
 │            │               ├───────────────>│                 │
 │            │               │ onMoveMade(move)                 │
 │            │               │                ├────────────────>│
 │            │               │                │ Platform.runLater│
 │            │               │                │ updateCellButton │
 │            │               │                │<────────────────┤
 │            │               │                │                 │
 │            │               │ switchPlayer() │                 │
 │            │               ├────────────────┤                 │
 │            │               │                │                 │
 │            │               │ for each observer:               │
 │            │               ├───────────────>│                 │
 │            │               │ onPlayerChanged("O")             │
 │            │               │                ├────────────────>│
 │            │               │                │ updateTurnLabel │
 │            │               │                │ makeAIMove()    │
 │            │               │<───────────────┤                 │
 │            │               │ makeMove(AI)   │                 │
 │            │               ├────────────────┤                 │
 │            │               │                │                 │
 │            │               │ (repeat notify cycle for AI move)│
 │<───────────┤               │                │                 │
 │ UI updated │               │                │                 │
```

### 4.2 Win Condition Sequence

```
GameModel              List<Observer>         GameController
    │                        │                      │
    │ makeMove() finds win   │                      │
    ├────────────────────────┤                      │
    │                        │                      │
    │ for each observer:     │                      │
    ├───────────────────────>│                      │
    │ onWinningLineFound(line)                      │
    │                        ├─────────────────────>│
    │                        │ Platform.runLater    │
    │                        │ highlightWinningCells│
    │                        │<─────────────────────┤
    │                        │                      │
    │ for each observer:     │                      │
    ├───────────────────────>│                      │
    │ onGameStateChanged(X_WON, "X")                │
    │                        ├─────────────────────>│
    │                        │ Platform.runLater    │
    │                        │ showWinMessage       │
    │                        │ disableBoard         │
    │                        │ updateScore          │
    │                        │<─────────────────────┤
```

**Important:** `onWinningLineFound()` is called **BEFORE** `onGameStateChanged()` to allow UI to highlight winning cells before showing message.

---

## 5. State Transition Diagram - Observer Notifications

```
                    ┌─────────────────┐
                    │   Game Start    │
                    └────────┬────────┘
                             │
                    notifyBoardReset()
                    notifyPlayerChanged("X")
                             │
                             ▼
            ┌────────────────────────────────┐
            │       PLAYING State            │
            │  (Accept user moves)           │
            └────────┬───────────────────────┘
                     │
              User makes move
                     │
                     ▼
       ┌─────────────────────────────────────┐
       │    notifyMoveMade(move)             │
       │    Observer: updateCellButton()     │
       └─────────────┬───────────────────────┘
                     │
            ┌────────┴─────────┐
            │                  │
      Check Win?          Check Draw?
            │                  │
        Yes │                  │ Yes
            ▼                  ▼
  ┌──────────────────┐  ┌──────────────────┐
  │ notifyWinningLine│  │ notifyGameState  │
  │ (line) FIRST     │  │ Changed(DRAW)    │
  │       ↓          │  │                  │
  │ notifyGameState  │  │ Observer:        │
  │ Changed(X_WON)   │  │ - Show draw msg  │
  │       ↓          │  │ - Disable board  │
  │ Observer:        │  └──────────────────┘
  │ - Highlight cells│
  │ - Show win msg   │
  │ - Disable board  │
  └──────────────────┘
            │
            No (continue game)
            │
            ▼
  ┌──────────────────────┐
  │ switchPlayer()       │
  │ notifyPlayerChanged()│
  │       ↓              │
  │ If AI turn:          │
  │   makeAIMove()       │
  │   → repeat cycle     │
  └──────────────────────┘


Transitions:
- Every move triggers at least 2 notifications (onMoveMade + onPlayerChanged)
- Win triggers 3 notifications (onMoveMade + onWinningLine + onGameStateChanged)
- Draw triggers 2 notifications (onMoveMade + onGameStateChanged)
```

---

## 6. Pattern Benefits & Trade-offs

### ✅ Benefits (Ưu điểm)

1. **Loose Coupling (Kết nối lỏng lẻo)**
   - ✅ Model không biết gì về View cụ thể
   - ✅ Chỉ biết về GameObserver interface
   - ✅ Dễ thay đổi View mà không ảnh hưởng Model

2. **Multiple Observers**
   - ✅ Nhiều views có thể lắng nghe cùng 1 model
   - ✅ GameController + MenuController cùng observe
   - ✅ Dễ thêm observers mới (Logger, Analytics...)

3. **Real-time Synchronization**
   - ✅ UI tự động update khi model thay đổi
   - ✅ Không cần polling hoặc manual refresh
   - ✅ Event-driven architecture

4. **Open/Closed Principle**
   - ✅ Open for extension: Thêm observers mới dễ dàng
   - ✅ Closed for modification: Model code không đổi

5. **Single Responsibility**
   - ✅ Model: Quản lý business logic
   - ✅ Observer: Quản lý UI updates
   - ✅ Clear separation of concerns

### ⚠️ Trade-offs (Đánh đổi)

1. **Notification Overhead**
   - ❌ Mỗi change trigger nhiều callbacks
   - ❌ Performance cost với nhiều observers
   - ⚠️ Cần Platform.runLater() cho JavaFX thread safety

2. **Unexpected Updates**
   - ❌ Hard to trace notification chains
   - ❌ Debugging có thể khó khăn
   - ⚠️ Order of notification không guaranteed

3. **Memory Leaks**
   - ❌ Observers không được remove có thể leak memory
   - ⚠️ Cần removeObserver() khi destroy view
   - ⚠️ Weak references có thể cần thiết

4. **Complexity**
   - ❌ Thêm layer of indirection
   - ❌ New developers cần hiểu pattern
   - ⚠️ 6 callback methods cần implement

---

## 7. Usage Example

### 7.1 Registering Observer

```java
public class GameController implements GameObserver {
    private GameModel gameModel;
    
    @FXML
    public void initialize() {
        // Create model
        gameModel = new GameModel();
        
        // Register this controller as observer
        gameModel.addObserver(this);
        
        // Now this controller will receive all notifications
    }
    
    // Implement 6 callback methods...
    @Override
    public void onMoveMade(Move move) {
        Platform.runLater(() -> {
            updateCellButton(move.getRow(), move.getCol(), 
                           move.getPlayer());
        });
    }
    
    @Override
    public void onGameStateChanged(GameState newState, String winner) {
        Platform.runLater(() -> {
            switch (newState) {
                case X_WON:
                    statusLabel.setText("🎉 Bạn thắng! 🎉");
                    break;
                case O_WON:
                    statusLabel.setText("😞 AI thắng! 😞");
                    break;
                case DRAW:
                    statusLabel.setText("🤝 Hòa! 🤝");
                    break;
            }
        });
    }
    
    // ... implement other 4 methods
}
```

### 7.2 Model Notifying Observers

```java
public class GameModel {
    private List<GameObserver> observers = new ArrayList<>();
    
    public void addObserver(GameObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }
    
    private void notifyMoveMade(Move move) {
        // Notify all observers
        for (GameObserver observer : observers) {
            observer.onMoveMade(move);
        }
    }
    
    public boolean makeMove(int row, int col) {
        // ... validate and make move
        
        board.makeMove(row, col, currentPlayer);
        
        // Notify observers
        Move move = new Move(row, col, currentPlayer);
        notifyMoveMade(move);
        
        // ... check win/draw, notify accordingly
        
        return true;
    }
}
```

### 7.3 Multiple Observers

```java
// Main application setup
GameModel gameModel = new GameModel();

// Register multiple observers
GameController gameController = new GameController();
gameModel.addObserver(gameController);

MenuController menuController = new MenuController();
gameModel.addObserver(menuController);

AnalyticsLogger logger = new AnalyticsLogger();
gameModel.addObserver(logger);

// Now all 3 observers receive notifications
gameModel.makeMove(5, 7);
// → gameController.onMoveMade()
// → menuController.onMoveMade()
// → logger.onMoveMade()
```

---

## 8. Extension Possibilities

### 8.1 Adding Analytics Observer

```java
public class GameAnalytics implements GameObserver {
    private int totalMoves = 0;
    private long gameStartTime;
    
    @Override
    public void onMoveMade(Move move) {
        totalMoves++;
        logMove(move);
    }
    
    @Override
    public void onGameStateChanged(GameState newState, String winner) {
        if (newState != GameState.PLAYING) {
            long duration = System.currentTimeMillis() - gameStartTime;
            logGameEnd(winner, totalMoves, duration);
        }
    }
    
    @Override
    public void onBoardReset() {
        totalMoves = 0;
        gameStartTime = System.currentTimeMillis();
    }
    
    // ... other methods with logging logic
}

// Usage
gameModel.addObserver(new GameAnalytics());
```

### 8.2 Network Multiplayer Observer

```java
public class NetworkGameObserver implements GameObserver {
    private WebSocket socket;
    
    @Override
    public void onMoveMade(Move move) {
        // Send move to opponent via network
        socket.send(JSON.stringify(move));
    }
    
    @Override
    public void onGameStateChanged(GameState newState, String winner) {
        // Notify opponent about game end
        socket.send(new GameEndMessage(newState, winner));
    }
    
    // ... implement other methods
}
```

### 8.3 Replay System Observer

```java
public class GameReplayRecorder implements GameObserver {
    private List<Move> moveSequence = new ArrayList<>();
    
    @Override
    public void onMoveMade(Move move) {
        moveSequence.add(move);
    }
    
    @Override
    public void onGameStateChanged(GameState newState, String winner) {
        if (newState != GameState.PLAYING) {
            saveReplay(moveSequence, winner);
        }
    }
    
    public void playback(List<Move> moves) {
        for (Move move : moves) {
            gameModel.makeMove(move.getRow(), move.getCol());
            Thread.sleep(500); // Delay for animation
        }
    }
}
```

---

## 9. Thread Safety Considerations

### JavaFX Thread Safety with Platform.runLater()

```java
@Override
public void onMoveMade(Move move) {
    // GameModel notifies from game logic thread
    // But JavaFX UI can only be updated from JavaFX Application Thread
    
    Platform.runLater(() -> {
        // This runs on JavaFX Application Thread
        updateCellButton(move.getRow(), move.getCol(), 
                        move.getPlayer());
    });
}
```

**Why Platform.runLater()?**
- ✅ GameModel may notify from any thread
- ✅ JavaFX UI components must be accessed from JavaFX Application Thread
- ✅ Platform.runLater() schedules UI update on correct thread
- ✅ Prevents "Not on FX application thread" exception

**Thread Flow:**
```
Game Logic Thread                JavaFX Application Thread
      │                                   │
      │ gameModel.makeMove()              │
      ├──────────────────────────────────>│
      │ notifyMoveMade()                  │
      │   ├─> observer.onMoveMade()       │
      │   │       │                       │
      │   │       └─> Platform.runLater() │
      │   │               │               │
      │   │               └──────────────>│ updateCellButton()
      │   │                               │ (safe UI update)
```

---

## 10. Design Decisions

### Why Observer Pattern?

1. **MVC Architecture**
   - ✅ Model và View cần tách biệt
   - ✅ Model không nên biết về View
   - ✅ Observer giải quyết vấn đề này hoàn hảo

2. **Multiple UI Components**
   - ✅ Nhiều labels cần update (status, turn, score)
   - ✅ Board grid cần update cells
   - ✅ Buttons cần enable/disable
   - ✅ Observer cho phép update tất cả cùng lúc

3. **Event-Driven**
   - ✅ Game events xảy ra bất đồng bộ
   - ✅ AI moves trigger events
   - ✅ User moves trigger events
   - ✅ Observer pattern phù hợp với event-driven

### Alternative Patterns Considered

**❌ Direct Method Calls (Model → View):**
```java
// Bad approach
public void makeMove(int row, int col) {
    // ...
    gameController.updateCell(row, col); // Tight coupling!
}
```
- Con: Tight coupling Model-View
- Con: Model phụ thuộc vào View
- Con: Không thể có multiple views

**❌ Polling (View → Model):**
```java
// Bad approach
while (true) {
    if (gameModel.hasChanged()) {
        updateUI();
    }
    Thread.sleep(100);
}
```
- Con: CPU waste
- Con: Delayed updates
- Con: Inefficient

**✅ Observer Pattern (Chosen):**
```java
// Good approach
gameModel.addObserver(this);
// Model notifies automatically
```
- Pro: Loose coupling
- Pro: Real-time updates
- Pro: Extensible (multiple observers)
- Pro: Standard pattern

---

## 11. Notification Order & Priority

### Current Implementation (FIFO)

```java
private void notifyMoveMade(Move move) {
    for (GameObserver observer : observers) {
        observer.onMoveMade(move);
    }
}
```

**Order:** Observers are notified in the order they were added to the list.

### Win Condition Special Case

```java
// In makeMove() when win is detected:

// 1. First notify about winning line (PRIORITY 1)
notifyWinningLine(winningLine);

// 2. Then notify about game state change (PRIORITY 2)
notifyGameStateChanged(gameState, currentPlayer);
```

**Why this order?**
- ✅ UI can highlight winning cells BEFORE showing winner message
- ✅ Better visual feedback for user
- ✅ More dramatic effect

### Potential Priority System

```java
// Future enhancement: Priority-based observers
public interface PriorityObserver extends GameObserver {
    int getPriority(); // Lower number = higher priority
}

private void notifyMoveMade(Move move) {
    observers.stream()
        .sorted(Comparator.comparingInt(o -> 
            o instanceof PriorityObserver 
                ? ((PriorityObserver)o).getPriority() 
                : Integer.MAX_VALUE))
        .forEach(observer -> observer.onMoveMade(move));
}
```

---

## 12. Observer Pattern Roles - Phân tích Chi tiết

### 12.1 Định nghĩa các Role trong Observer Pattern

Observer Pattern theo định nghĩa chuẩn GoF gồm 4 roles chính:

```
┌─────────────────────────────────────────────────────────────────┐
│           OBSERVER PATTERN - STANDARD ROLES                      │
└─────────────────────────────────────────────────────────────────┘

1. Subject (Abstract)
   ↳ Interface hoặc abstract class định nghĩa:
     - attach(Observer): Thêm observer
     - detach(Observer): Xóa observer
     - notify(): Thông báo cho tất cả observers

2. ConcreteSubject ⭐
   ↳ Class cụ thể implement Subject
     - Lưu trữ state cần observe
     - Implement các phương thức attach/detach/notify
     - Khi state thay đổi → gọi notify()

3. Observer (Interface)
   ↳ Interface định nghĩa update method
     - update(): Được gọi khi Subject thay đổi

4. ConcreteObserver
   ↳ Class cụ thể implement Observer
     - Maintain reference đến ConcreteSubject
     - Implement update() để sync với Subject
```

### 12.2 Mapping với Dự án Caro

```
╔══════════════════════════════════════════════════════════════════╗
║                  OBSERVER PATTERN ROLES MAPPING                   ║
╠══════════════════════════════════════════════════════════════════╣
║                                                                   ║
║ ┌────────────────────┬──────────────────────────────────────┐   ║
║ │ Standard Role      │ Implementation trong Dự án           │   ║
║ ├────────────────────┼──────────────────────────────────────┤   ║
║ │ Subject            │ KHÔNG CÓ (implicit)                  │   ║
║ │ (Abstract)         │ → GameModel trực tiếp implement      │   ║
║ │                    │    các phương thức Subject           │   ║
║ ├────────────────────┼──────────────────────────────────────┤   ║
║ │ ConcreteSubject ⭐ │ GameModel                            │   ║
║ │                    │ → Lưu trữ game state                 │   ║
║ │                    │ → Quản lý observers list             │   ║
║ │                    │ → Implement notify methods           │   ║
║ ├────────────────────┼──────────────────────────────────────┤   ║
║ │ Observer           │ GameObserver (interface)             │   ║
║ │ (Interface)        │ → Định nghĩa 6 callback methods      │   ║
║ ├────────────────────┼──────────────────────────────────────┤   ║
║ │ ConcreteObserver   │ GameController                       │   ║
║ │                    │ MenuController                       │   ║
║ │                    │ → Implement 6 callback methods       │   ║
║ └────────────────────┴──────────────────────────────────────┘   ║
║                                                                   ║
╚══════════════════════════════════════════════════════════════════╝
```

### 12.3 Chi tiết về ConcreteSubject - GameModel

**GameModel** là **ConcreteSubject** duy nhất trong hệ thống:

```java
/**
 * GameModel - ConcreteSubject trong Observer Pattern
 * 
 * Responsibilities:
 * 1. Lưu trữ game state (board, currentPlayer, gameState...)
 * 2. Quản lý danh sách observers
 * 3. Thông báo cho observers khi state thay đổi
 */
public class GameModel {
    // ===== STATE (ConcreteSubject State) =====
    private Board board;                    // State 1
    private String currentPlayer;           // State 2
    private GameState gameState;            // State 3
    private int moveNumber;                 // State 4
    private WinningLine winningLine;        // State 5
    
    // ===== OBSERVERS MANAGEMENT (Subject Role) =====
    private List<GameObserver> observers = new ArrayList<>();
    
    // Subject method: attach observer
    public void addObserver(GameObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }
    
    // Subject method: detach observer
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }
    
    // ===== NOTIFY METHODS (ConcreteSubject Implementation) =====
    
    // Notify về nước đi mới
    private void notifyMoveMade(Move move) {
        for (GameObserver observer : observers) {
            observer.onMoveMade(move);
        }
    }
    
    // Notify về thay đổi game state
    private void notifyGameStateChanged(GameState state, String winner) {
        for (GameObserver observer : observers) {
            observer.onGameStateChanged(state, winner);
        }
    }
    
    // ... 4 notify methods khác
    
    // ===== STATE CHANGE METHODS (Trigger Notifications) =====
    
    public boolean makeMove(int row, int col) {
        // 1. Thay đổi state
        board.makeMove(row, col, currentPlayer);
        moveNumber++;
        
        // 2. Notify observers về state change
        notifyMoveMade(new Move(row, col, currentPlayer));
        
        // 3. Check win/draw và notify
        if (hasWinner) {
            notifyWinningLine(winningLine);
            notifyGameStateChanged(gameState, winner);
        }
        
        return true;
    }
}
```

### 12.4 Tại sao GameModel là ConcreteSubject?

```
┌─────────────────────────────────────────────────────────────────┐
│         GAMEMODEL FULFILLS CONCRETESUBJECT ROLE                  │
└─────────────────────────────────────────────────────────────────┘

✅ 1. Lưu trữ State cần observe
    → board, currentPlayer, gameState, moveNumber, winningLine
    → Đây là dữ liệu mà Observers quan tâm

✅ 2. Quản lý Observers Collection
    → private List<GameObserver> observers
    → addObserver(), removeObserver()

✅ 3. Implement Notify Logic
    → 6 notify methods tương ứng 6 event types
    → Loop qua tất cả observers và gọi callbacks

✅ 4. Trigger Notifications khi State thay đổi
    → makeMove() → notifyMoveMade()
    → resetGame() → notifyBoardReset()
    → switchPlayer() → notifyPlayerChanged()

✅ 5. Không biết về ConcreteObserver
    → Chỉ biết về GameObserver interface
    → Loose coupling - core benefit của Observer Pattern
```

### 12.5 So sánh Standard Pattern vs Implementation

**Standard GoF Pattern:**
```
┌──────────────┐         ┌──────────────┐
│   Subject    │◄────────│   Observer   │
│ (abstract)   │         │ (interface)  │
└──────┬───────┘         └──────┬───────┘
       │                        │
       │ implements              │ implements
       ▼                        ▼
┌──────────────┐         ┌──────────────┐
│ ConcreteSubject│       │ConcreteObserver│
└──────────────┘         └──────────────┘
```

**Dự án Caro Implementation:**
```
  (KHÔNG CÓ)            ┌──────────────┐
                        │ GameObserver │
                        │ (interface)  │
                        └──────┬───────┘
                               │
                               │ implements
       ┌───────────────────────┼────────────────┐
       │                       │                │
       ▼                       ▼                ▼
┌──────────────┐      ┌──────────────┐  ┌──────────────┐
│  GameModel   │      │GameController│  │MenuController│
│(ConcreteSubj)│      │(ConcreteObs) │  │(ConcreteObs) │
└──────────────┘      └──────────────┘  └──────────────┘
```

**Giải thích:**
- ❌ **Không có Subject abstract class/interface** vì:
  - Chỉ có 1 ConcreteSubject duy nhất (GameModel)
  - Không cần abstraction khi chỉ có 1 implementation
  - Đơn giản hóa design - YAGNI principle
  
- ✅ **GameModel trực tiếp là ConcreteSubject:**
  - Implement tất cả Subject responsibilities
  - Không cần thừa kế từ abstract Subject

### 12.6 Diagram với ConcreteSubject Role

```
┌─────────────────────────────────────────────────────────────────┐
│              OBSERVER PATTERN - COMPLETE STRUCTURE               │
└─────────────────────────────────────────────────────────────────┘

                    ┌────────────────────────┐
                    │   GameObserver         │
                    │   (Observer)           │
                    ├────────────────────────┤
                    │ + onMoveMade()         │
                    │ + onGameStateChanged() │
                    │ + ... (6 methods)      │
                    └──────────┬─────────────┘
                               │
                               │ implements
                               │
              ┌────────────────┼────────────────┐
              │                │                │
              ▼                ▼                ▼
    ┌──────────────┐  ┌──────────────┐  ┌──────────────┐
    │ GameController│  │MenuController│  │AnalyticsLog  │
    │(ConcreteObs) │  │(ConcreteObs) │  │(ConcreteObs) │
    └──────────────┘  └──────────────┘  └──────────────┘
              ▲                ▲                ▲
              │                │                │
              └────────────────┼────────────────┘
                               │
                        registered with
                               │
                               ▼
                    ┌────────────────────────┐
                    │    GameModel           │
                    │  (ConcreteSubject) ⭐  │
                    ├────────────────────────┤
                    │ - observers: List<>    │
                    │ - board: Board         │
                    │ - currentPlayer: String│
                    │ - gameState: GameState │
                    ├────────────────────────┤
                    │ + addObserver()        │
                    │ + removeObserver()     │
                    │ - notifyMoveMade()     │
                    │ - notify...() (6 types)│
                    │ + makeMove()           │
                    │ + resetGame()          │
                    └────────────────────────┘
```

### 12.7 Kết luận về ConcreteSubject

**Xác định rõ ràng:**

🎯 **ConcreteSubject = GameModel**

**Lý do:**
1. ✅ Lưu trữ tất cả game state cần observe
2. ✅ Quản lý observers collection
3. ✅ Implement tất cả notify logic
4. ✅ Trigger notifications khi state thay đổi
5. ✅ Độc lập với ConcreteObserver (chỉ biết Observer interface)

**Không có Subject abstract class vì:**
- Chỉ có 1 subject trong hệ thống
- YAGNI (You Ain't Gonna Need It)
- Đơn giản hóa design

**Pattern đầy đủ:**
- Observer Interface: **GameObserver**
- ConcreteSubject: **GameModel** ⭐
- ConcreteObservers: **GameController**, **MenuController**, etc.

### 12.8 - Code Example từ Dự án: ConcreteSubject Implementation

Đây là đoạn code thực tế từ **GameModel.java** minh họa ConcreteSubject:

```java
package com.kthp.tro_choi_caro.model;

import com.kthp.tro_choi_caro.view.GameObserver;
import java.util.ArrayList;
import java.util.List;

/**
 * GameModel - Model chính của game (MVC Pattern)
 * Observer Pattern - ConcreteSubject: Quản lý danh sách observers và thông báo
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 */
public class GameModel {
    // ===== STATE (ConcreteSubject maintains state) =====
    private Board board;
    private Player player1; // X - Human
    private Player player2; // O - AI
    private String currentPlayer; // "X" hoặc "O"
    private GameState gameState;
    private int moveNumber;
    private WinningLine winningLine; // Đường đi thắng (nếu có)
    
    // ===== 1. DANH SÁCH OBSERVERS (ConcreteSubject manages observers) =====
    private List<GameObserver> observers;
    
    public GameModel() {
        this.board = new Board();
        this.player1 = new Player("X", "Player", false);
        this.player2 = new Player("O", "AI", true);
        this.currentPlayer = "X"; // X đi trước
        this.gameState = GameState.PLAYING;
        this.moveNumber = 0;
        this.observers = new ArrayList<>(); // ← Khởi tạo danh sách observers
    }
    
    // ===== 2. ATTACH METHOD - Thêm observer vào danh sách =====
    /**
     * attach(Observer) - Đăng ký observer
     */
    public void addObserver(GameObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }
    
    // ===== 3. DETACH METHOD - Xóa observer khỏi danh sách =====
    /**
     * detach(Observer) - Hủy đăng ký observer
     */
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }
    
    // ===== 4. NOTIFY METHODS - Riêng biệt cho từng sự kiện =====
    
    /**
     * notify về nước đi mới
     */
    private void notifyMoveMade(Move move) {
        for (GameObserver observer : observers) {
            observer.onMoveMade(move);
        }
    }
    
    /**
     * notify về thay đổi game state
     */
    private void notifyGameStateChanged(GameState newState, String winner) {
        for (GameObserver observer : observers) {
            observer.onGameStateChanged(newState, winner);
        }
    }
    
    /**
     * notify về đường đi thắng
     */
    private void notifyWinningLine(WinningLine winningLine) {
        for (GameObserver observer : observers) {
            observer.onWinningLineFound(winningLine);
        }
    }
    
    /**
     * notify về board reset
     */
    private void notifyBoardReset() {
        for (GameObserver observer : observers) {
            observer.onBoardReset();
        }
    }
    
    /**
     * notify về board restored (Undo/Redo)
     */
    private void notifyBoardRestored() {
        for (GameObserver observer : observers) {
            observer.onBoardRestored();
        }
    }
    
    /**
     * notify về chuyển lượt
     */
    private void notifyPlayerChanged(String currentPlayer) {
        for (GameObserver observer : observers) {
            observer.onPlayerChanged(currentPlayer);
        }
    }
    
    // ===== 5. BUSINESS LOGIC - Trigger notifications khi state thay đổi =====
    
    /**
     * Thực hiện nước đi - Minh họa cách ConcreteSubject notify observers
     */
    public boolean makeMove(int row, int col) {
        if (gameState != GameState.PLAYING || !board.isCellEmpty(row, col)) {
            return false;
        }
        
        // 1. Thay đổi state
        board.makeMove(row, col, currentPlayer);
        moveNumber++;
        
        // 2. Notify về nước đi mới ← NOTIFY 1
        Move move = new Move(row, col, currentPlayer);
        notifyMoveMade(move);
        
        // 3. Kiểm tra thắng và tìm đường thắng
        WinningLine foundWinningLine = board.findWinningLine(row, col, currentPlayer);
        if (foundWinningLine != null) {
            // Có người thắng
            this.winningLine = foundWinningLine;
            gameState = currentPlayer.equals("X") ? GameState.X_WON : GameState.O_WON;
            
            // Thông báo đường thắng TRƯỚC ← NOTIFY 2
            notifyWinningLine(winningLine);
            
            // Sau đó thông báo game state changed ← NOTIFY 3
            notifyGameStateChanged(gameState, currentPlayer);
        } 
        // 4. Kiểm tra hòa
        else if (board.isFull()) {
            gameState = GameState.DRAW;
            notifyGameStateChanged(gameState, null); // ← NOTIFY 4
        }
        // 5. Game tiếp tục - chuyển lượt
        else {
            switchPlayer(); // ← Sẽ gọi notifyPlayerChanged bên trong
        }
        
        return true;
    }
    
    /**
     * Chuyển lượt chơi
     */
    private void switchPlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        notifyPlayerChanged(currentPlayer); // ← NOTIFY 5
    }
    
    /**
     * Reset game - Minh họa nhiều notify calls
     */
    public void resetGame() {
        // Thay đổi state
        board.clear();
        currentPlayer = "X";
        gameState = GameState.PLAYING;
        moveNumber = 0;
        winningLine = null;
        
        // Notify observers về các thay đổi
        notifyBoardReset();                      // ← NOTIFY 1
        notifyPlayerChanged(currentPlayer);       // ← NOTIFY 2
        notifyGameStateChanged(gameState, null);  // ← NOTIFY 3
    }
}
```

**Phân tích đoạn code:**

```
╔══════════════════════════════════════════════════════════════════╗
║           CONCRETESUBJECT CHARACTERISTICS IN CODE                 ║
╠══════════════════════════════════════════════════════════════════╣
║                                                                   ║
║ 1. Danh sách Observers:                                          ║
║    private List<GameObserver> observers = new ArrayList<>();     ║
║    ↳ ConcreteSubject duy trì collection của observers            ║
║                                                                   ║
║ 2. attach(Observer):                                             ║
║    public void addObserver(GameObserver observer)                ║
║    ↳ Cho phép observers đăng ký                                  ║
║                                                                   ║
║ 3. detach(Observer):                                             ║
║    public void removeObserver(GameObserver observer)             ║
║    ↳ Cho phép observers hủy đăng ký                              ║
║                                                                   ║
║ 4. notify() - RIÊNG BIỆT CHO TỪNG SỰ KIỆN:                       ║
║    private void notifyMoveMade(Move move)                        ║
║    private void notifyGameStateChanged(...)                      ║
║    private void notifyWinningLine(...)                           ║
║    private void notifyBoardReset()                               ║
║    private void notifyBoardRestored()                            ║
║    private void notifyPlayerChanged(...)                         ║
║    ↳ Thay vì 1 notify() duy nhất, có 6 notify methods riêng biệt║
║                                                                   ║
║ 5. Trigger Notifications:                                        ║
║    Trong makeMove():                                             ║
║      - notifyMoveMade(move)           ← Sau khi đặt quân         ║
║      - notifyWinningLine(line)        ← Khi phát hiện thắng      ║
║      - notifyGameStateChanged(...)    ← Khi game kết thúc        ║
║    Trong switchPlayer():                                         ║
║      - notifyPlayerChanged(player)    ← Khi chuyển lượt          ║
║    Trong resetGame():                                            ║
║      - notifyBoardReset()             ← Khi reset board          ║
║      - notifyPlayerChanged(...)       ← Reset về player X        ║
║      - notifyGameStateChanged(...)    ← Reset state về PLAYING   ║
║                                                                   ║
╚══════════════════════════════════════════════════════════════════╝
```

**So sánh với Pattern chuẩn:**

| Standard GoF Pattern | Implementation trong Dự án |
|---------------------|----------------------------|
| `notify()` duy nhất | 6 `notify...()` methods riêng biệt |
| Loop qua observers một lần | Mỗi method loop riêng |
| `observer.update()` generic | `observer.onMoveMade()`, `observer.onGameStateChanged()`, etc. |
| 1 callback method | 6 callback methods specific |

**Ưu điểm của cách implement này:**
- ✅ **Type-safe:** Mỗi event có parameters rõ ràng
- ✅ **Clear intent:** Tên method cho biết event gì xảy ra
- ✅ **Selective handling:** Observer chỉ handle events quan tâm
- ✅ **No casting:** Không cần cast parameters như pattern chuẩn

**Flow Example - Khi user click vào ô (5, 7):**
```
User clicks cell (5, 7)
    │
    ├─> GameController.handleCellClick(5, 7)
    │       │
    │       └─> gameModel.makeMove(5, 7)
    │               │
    │               ├─> board.makeMove(5, 7, "X")
    │               │
    │               ├─> notifyMoveMade(new Move(5, 7, "X"))
    │               │       │
    │               │       └─> for (observer : observers)
    │               │               observer.onMoveMade(move)
    │               │                   │
    │               │                   └─> GameController.onMoveMade()
    │               │                           │
    │               │                           └─> updateCellButton(5, 7, "X")
    │               │
    │               ├─> Check win...
    │               │
    │               └─> switchPlayer()
    │                       │
    │                       └─> notifyPlayerChanged("O")
    │                               │
    │                               └─> for (observer : observers)
    │                                       observer.onPlayerChanged("O")
    │                                           │
    │                                           └─> updateTurnLabel()
    │
    └─> UI updated!
```

**Kết luận về code:**

🎯 **GameModel.java** thể hiện rõ ràng vai trò **ConcreteSubject** qua:

1. ✅ Duy trì danh sách observers: `private List<GameObserver> observers`
2. ✅ Cung cấp attach/detach: `addObserver()`, `removeObserver()`
3. ✅ Có các hàm notify riêng biệt cho từng sự kiện (6 methods)
4. ✅ Bên trong `makeMove()`, `switchPlayer()`, `resetGame()` gọi các notify methods tương ứng
5. ✅ Hoàn toàn độc lập với ConcreteObserver (chỉ biết GameObserver interface)

### 12.9 - Code Example từ Dự án: ConcreteObserver Implementation

**ConcreteObserver - GameController.java:**

- **GameController.java** đóng vai trò là **ConcreteObserver chính**, implements `GameObserver`.
- Trong phương thức `setupGame()` của lớp này, nó tự đăng ký với Model: `gameModel.addObserver(this);`.
- GameController cung cấp triển khai cho tất cả 6 phương thức sự kiện.
- **Lợi ích của mô hình "push" này:** Các phương thức update nhận được chính xác dữ liệu chúng cần (ví dụ: `onMoveMade(Move move)` nhận `move`), do đó Controller không cần truy vấn ngược lại Model.
- **Xử lý Thread-Safe:** Vì các phương thức notify được gọi từ luồng logic của Model, bất kỳ hành động cập nhật giao diện JavaFX nào cũng phải được thực hiện trên **JavaFX Application Thread** và vấn đề này được xử lý bằng cách sử dụng `Platform.runLater()`.

**Code thực tế:**

```java
package com.kthp.tro_choi_caro.controller;

import com.kthp.tro_choi_caro.model.*;
import com.kthp.tro_choi_caro.view.GameObserver;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * GameController - ConcreteObserver chính trong Observer Pattern
 * Implements GameObserver để nhận thông báo từ GameModel
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 */
public class GameController implements GameObserver {
    
    @FXML
    private GridPane boardGrid;
    
    @FXML
    private Label statusLabel;
    
    @FXML
    private Label turnLabel;
    
    @FXML
    private Button undoButton;
    
    @FXML
    private Button redoButton;
    
    private GameModel gameModel;
    private AIPlayer aiPlayer;
    private Button[][] cellButtons;
    private ScoreManager scoreManager;
    
    /**
     * Khởi tạo controller
     */
    @FXML
    public void initialize() {
        cellButtons = new Button[Board.BOARD_SIZE][Board.BOARD_SIZE];
        createBoard();
        scoreManager = ScoreManager.getInstance();
        updateScoreLabel();
    }
    
    /**
     * Thiết lập game - ĐĂNG KÝ OBSERVER
     */
    public void setupGame(String difficulty) {
        this.gameModel = new GameModel();
        
        // Tạo AI với strategy tương ứng
        AIStrategy strategy = createAIStrategy(difficulty);
        this.aiPlayer = new AIPlayer("O", strategy);
        
        // ===== ĐĂNG KÝ OBSERVER VỚI SUBJECT =====
        gameModel.addObserver(this); // ← attach(this)
        
        // Cập nhật UI ban đầu
        updateDifficultyLabel();
        updateButtonStates();
        updateTurnLabel();
        updateScoreLabel();
        statusLabel.setText("Trò chơi bắt đầu! Lượt của bạn (X)");
    }
    
    // ===== OBSERVER PATTERN - 6 CALLBACK IMPLEMENTATIONS =====
    
    /**
     * 1. Callback khi có nước đi mới
     * PUSH MODEL: Nhận Move object - không cần query Model
     * THREAD-SAFE: Sử dụng Platform.runLater() cho JavaFX UI thread
     */
    @Override
    public void onMoveMade(Move move) {
        // Model notify từ logic thread
        // JavaFX UI chỉ được update từ JavaFX Application Thread
        Platform.runLater(() -> {
            Button button = cellButtons[move.getRow()][move.getCol()];
            
            // Cập nhật UI với dữ liệu từ Move object
            String displayText = move.getPlayer();
            button.setText(displayText);
            button.setDisable(true);
            
            if (move.getPlayer().equals("X")) {
                // Style cho X
                button.setStyle(
                    "-fx-font-size: 28px; " +
                    "-fx-font-weight: 900; " +
                    "-fx-text-fill: #DD0000; " +
                    "-fx-background-color: #FFFFFF; " +
                    "-fx-border-color: #FF0000; " +
                    "-fx-border-width: 2px;"
                );
            } else {
                // Style cho O
                button.setStyle(
                    "-fx-font-size: 28px; " +
                    "-fx-font-weight: 900; " +
                    "-fx-text-fill: #0000DD; " +
                    "-fx-background-color: #FFFFFF; " +
                    "-fx-border-color: #0000FF; " +
                    "-fx-border-width: 2px;"
                );
            }
            
            updateButtonStates(); // Cập nhật Undo/Redo
        });
    }
    
    /**
     * 2. Callback khi game state thay đổi (thắng/thua/hòa)
     * PUSH MODEL: Nhận GameState và winner - không cần query
     */
    @Override
    public void onGameStateChanged(GameState newState, String winner) {
        Platform.runLater(() -> {
            switch (newState) {
                case X_WON:
                    statusLabel.setText("🎉 Bạn thắng! 🎉");
                    statusLabel.setStyle("-fx-text-fill: #00AA00; -fx-font-weight: bold;");
                    scoreManager.addWin("X");
                    updateScoreLabel();
                    disableBoard();
                    break;
                    
                case O_WON:
                    statusLabel.setText("😞 AI thắng! 😞");
                    statusLabel.setStyle("-fx-text-fill: #AA0000; -fx-font-weight: bold;");
                    scoreManager.addWin("O");
                    updateScoreLabel();
                    disableBoard();
                    break;
                    
                case DRAW:
                    statusLabel.setText("🤝 Hòa! 🤝");
                    statusLabel.setStyle("-fx-text-fill: #0000AA; -fx-font-weight: bold;");
                    scoreManager.addDraw();
                    updateScoreLabel();
                    disableBoard();
                    break;
                    
                default:
                    statusLabel.setStyle("-fx-text-fill: #000000;");
                    break;
            }
        });
    }
    
    /**
     * 3. Callback khi tìm thấy đường thắng
     * PUSH MODEL: Nhận WinningLine object với tất cả positions
     */
    @Override
    public void onWinningLineFound(WinningLine winningLine) {
        Platform.runLater(() -> {
            // Highlight các ô trong đường thắng
            for (WinningLine.Position pos : winningLine.getPositions()) {
                Button button = cellButtons[pos.getRow()][pos.getCol()];
                
                String winnerColor = winningLine.getWinner().equals("X") 
                    ? "#FF0000" : "#0000FF";
                    
                // Style đặc biệt cho winning line
                button.setStyle(
                    "-fx-font-size: 28px; " +
                    "-fx-font-weight: 900; " +
                    "-fx-text-fill: #FFFFFF; " +           // Chữ trắng
                    "-fx-background-color: " + winnerColor + "; " +
                    "-fx-border-color: #FFD700; " +        // Viền vàng
                    "-fx-border-width: 3px; " +
                    "-fx-effect: dropshadow(gaussian, rgba(255,215,0,0.8), 15, 0.7, 0, 0);"
                );
            }
        });
    }
    
    /**
     * 4. Callback khi board được reset
     */
    @Override
    public void onBoardReset() {
        Platform.runLater(() -> {
            for (int row = 0; row < Board.BOARD_SIZE; row++) {
                for (int col = 0; col < Board.BOARD_SIZE; col++) {
                    Button button = cellButtons[row][col];
                    button.setText("");
                    button.setDisable(false);
                    button.setStyle(
                        "-fx-background-color: #FFFFFF; " +
                        "-fx-border-color: #CCCCCC;"
                    );
                }
            }
            statusLabel.setText("Trò chơi bắt đầu! Lượt của bạn (X)");
            updateButtonStates();
        });
    }
    
    /**
     * 5. Callback khi player thay đổi
     */
    @Override
    public void onPlayerChanged(String currentPlayer) {
        Platform.runLater(this::updateTurnLabel);
    }
    
    /**
     * 6. Callback khi board được restore (Undo/Redo)
     */
    @Override
    public void onBoardRestored() {
        Platform.runLater(() -> {
            // Vẽ lại toàn bộ bàn cờ từ GameModel
            Board board = gameModel.getBoard();
            
            for (int row = 0; row < Board.BOARD_SIZE; row++) {
                for (int col = 0; col < Board.BOARD_SIZE; col++) {
                    Button button = cellButtons[row][col];
                    String content = board.getCell(row, col).getContent();
                    
                    if (content.isEmpty()) {
                        button.setText("");
                        button.setDisable(false);
                    } else {
                        button.setText(content);
                        button.setDisable(true);
                        // Apply style tương ứng...
                    }
                }
            }
            
            updateButtonStates();
            updateTurnLabel();
        });
    }
    
    // Helper methods...
    private void updateTurnLabel() {
        if (gameModel != null) {
            String player = gameModel.getCurrentPlayer();
            turnLabel.setText("Lượt: " + (player.equals("X") ? "Bạn (X)" : "AI (O)"));
        }
    }
}
```

**Phân tích ConcreteObserver:**

```
╔══════════════════════════════════════════════════════════════════╗
║        CONCRETEOBSERVER CHARACTERISTICS IN GAMECONTROLLER         ║
╠══════════════════════════════════════════════════════════════════╣
║                                                                   ║
║ 1. Implements Observer Interface:                                ║
║    public class GameController implements GameObserver           ║
║    ↳ Bắt buộc implement tất cả 6 callback methods                ║
║                                                                   ║
║ 2. Đăng ký với ConcreteSubject:                                  ║
║    gameModel.addObserver(this);  ← trong setupGame()             ║
║    ↳ Tự attach vào Subject để nhận notifications                 ║
║                                                                   ║
║ 3. Push Model - Nhận data trực tiếp:                             ║
║    onMoveMade(Move move)                    ← Nhận Move object   ║
║    onGameStateChanged(GameState, String)    ← Nhận state, winner ║
║    onWinningLineFound(WinningLine)          ← Nhận winning line  ║
║    ↳ KHÔNG cần query Model để lấy thông tin                      ║
║                                                                   ║
║ 4. Thread-Safe với Platform.runLater():                          ║
║    @Override                                                      ║
║    public void onMoveMade(Move move) {                           ║
║        Platform.runLater(() -> {                                 ║
║            // Update JavaFX UI safely                            ║
║        });                                                        ║
║    }                                                              ║
║    ↳ Model notify từ logic thread                                ║
║    ↳ UI update phải trên JavaFX Application Thread               ║
║    ↳ Platform.runLater() schedule task trên correct thread       ║
║                                                                   ║
║ 5. Maintain reference to Subject:                                ║
║    private GameModel gameModel;                                  ║
║    ↳ Để có thể query thêm thông tin nếu cần                      ║
║                                                                   ║
╚══════════════════════════════════════════════════════════════════╝
```

**So sánh Pull Model vs Push Model:**

| Aspect | Pull Model | Push Model (Dự án) |
|--------|-----------|-------------------|
| Callback signature | `update()` | `onMoveMade(Move move)` |
| Data passing | Không có parameters | Parameters chứa data |
| Observer action | Query Subject để lấy data | Nhận data trực tiếp |
| Coupling | Cao (Observer biết Subject API) | Thấp (Observer nhận data cần thiết) |
| Code example | `observer.update()`<br>`move = subject.getLastMove()` | `observer.onMoveMade(move)` |

**Ưu điểm Push Model:**
- ✅ **Type-safe:** Mỗi event có parameters rõ ràng
- ✅ **Efficient:** Observer không cần query Subject
- ✅ **Clear:** Tên method và parameters tự giải thích
- ✅ **Flexible:** Observer chỉ implement callbacks quan tâm

**Thread Safety Flow:**

```
┌─────────────────────────────────────────────────────────────────┐
│                   THREAD-SAFE NOTIFICATION FLOW                  │
└─────────────────────────────────────────────────────────────────┘

Game Logic Thread                    JavaFX Application Thread
       │                                        │
       │ gameModel.makeMove(5, 7)               │
       ├──────────────────────────────────────> │
       │ notifyMoveMade(move)                   │
       │   │                                    │
       │   ├─> observer.onMoveMade(move)        │
       │   │       │                            │
       │   │       └─> Platform.runLater(() -> { }) 
       │   │               │                    │
       │   │               └───────────────────>│ Execute on FX thread
       │   │                                    │ updateCellButton(5,7,"X")
       │   │                                    │ button.setText("X")
       │   │                                    │ button.setStyle(...)
       │                                        │
       │ Continue game logic...                 │ UI safely updated!
```

**Tại sao cần Platform.runLater()?**

❌ **Không dùng Platform.runLater():**
```java
@Override
public void onMoveMade(Move move) {
    // DANGER! Không an toàn nếu notify từ non-FX thread
    Button button = cellButtons[move.getRow()][move.getCol()];
    button.setText(move.getPlayer()); // ← Exception!
    // java.lang.IllegalStateException: Not on FX application thread
}
```

✅ **Dùng Platform.runLater():**
```java
@Override
public void onMoveMade(Move move) {
    Platform.runLater(() -> {
        // An toàn - chạy trên JavaFX Application Thread
        Button button = cellButtons[move.getRow()][move.getCol()];
        button.setText(move.getPlayer()); // ← OK!
    });
}
```

**ConcreteObserver khác - MenuController:**

Dự án cũng có ConcreteObserver khác như `MenuController` (nếu có), cùng implement `GameObserver` và đăng ký với `GameModel` để cập nhật menu UI, statistics, etc.

**Kết luận về ConcreteObserver:**

🎯 **GameController = ConcreteObserver chính**

**Đặc điểm:**
1. ✅ Implements `GameObserver` interface
2. ✅ Đăng ký với Subject qua `gameModel.addObserver(this)`
3. ✅ Implement 6 callback methods với Push Model
4. ✅ Thread-safe với `Platform.runLater()`
5. ✅ Cập nhật JavaFX UI dựa trên events từ Model
6. ✅ Loose coupling - chỉ biết Observer interface

---

## Kết luận

Observer Pattern được áp dụng xuất sắc trong kiến trúc MVC của Trò Chơi Caro:

✅ **Clean Separation:** Model hoàn toàn tách biệt khỏi View

✅ **6 Event Types:**
- onMoveMade - Cập nhật cell
- onGameStateChanged - Hiển thị kết quả
- onBoardReset - Reset UI
- onBoardRestored - Undo/Redo sync
- onPlayerChanged - Update turn indicator
- onWinningLineFound - Highlight winning cells

✅ **Multiple Observers:** GameController, MenuController, và có thể mở rộng

✅ **Thread-Safe:** Sử dụng Platform.runLater() cho JavaFX

✅ **Real-time Updates:** UI tự động sync với Model

✅ **Extensible:** Dễ thêm Analytics, Network, Replay observers

✅ **SOLID Compliance:**
- Single Responsibility ✓
- Open/Closed ✓
- Liskov Substitution ✓
- Interface Segregation ✓
- Dependency Inversion ✓

**Performance:** O(n) notification cost where n = number of observers (typically 1-3)

**Maintainability:** High - clear separation of concerns, easy to debug

---

**Tác giả:** 2212391- Nguyễn Hoàng Nam Khánh  
**Phiên bản:** 1.0  
**Loại diagram:** Class Diagram UML - Observer Pattern
