# 📐 TECHNICAL REPORT: SƠ ĐỒ LỚP CHI TIẾT

## Mục lục
1. [Tổng quan](#1-tổng-quan)
2. [Sơ đồ Lớp Tổng thể](#2-sơ-đồ-lớp-tổng-thể)
3. [Package Model - Core Domain](#3-package-model---core-domain)
4. [Package Strategy - AI Algorithms](#4-package-strategy---ai-algorithms)
5. [Package Controller - MVC Controllers](#5-package-controller---mvc-controllers)
6. [Package View - Observer Interface](#6-package-view---observer-interface)
7. [Relationships và Dependencies](#7-relationships-và-dependencies)
8. [Design Patterns Mapping](#8-design-patterns-mapping)

---

## 1. Tổng quan

### 1.1 Thống kê Codebase

| Metric | Số lượng |
|--------|----------|
| **Tổng số Classes** | 17 |
| **Tổng số Interfaces** | 2 |
| **Tổng số Enums** | 2 |
| **Packages** | 5 |
| **Design Patterns** | 4 (Strategy, Observer, Memento, Singleton) |

### 1.2 Package Overview

```
com.kthp.tro_choi_caro/
├── App.java                    # JavaFX Application Entry
├── controller/                 # 2 Controllers
│   ├── MenuController
│   └── GameController
├── model/                      # 10 Classes/Enums
│   ├── GameModel
│   ├── Board
│   ├── Cell
│   ├── Move
│   ├── Player
│   ├── GameState (Enum)
│   ├── WinningLine
│   ├── GameStateMemento
│   ├── MoveHistory
│   └── ScoreManager
├── view/                       # 1 Interface
│   └── GameObserver
└── strategy/                   # 5 Classes/Interfaces
    ├── AIStrategy (Interface)
    ├── EasyAIStrategy
    ├── MediumAIStrategy
    ├── HardAIStrategy
    └── AIPlayer
```

---

## 2. Sơ đồ Lớp Tổng thể

### 2.1 Complete Class Diagram (UML Notation)

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                              APPLICATION LAYER                               │
│                                                                              │
│  ┌────────────────────────────────────────────────────────────────────────┐ │
│  │                              App                                        │ │
│  ├────────────────────────────────────────────────────────────────────────┤ │
│  │ + start(Stage): void                                                   │ │
│  │ + setRoot(String): void                                                │ │
│  │ + loadFXML(String): FXMLLoader                                         │ │
│  │ + main(String[]): void                                                 │ │
│  └────────────────────────────────────────────────────────────────────────┘ │
│                                    │                                         │
│                    ┌───────────────┴───────────────┐                        │
└────────────────────┼───────────────────────────────┼────────────────────────┘
                     ▼                               ▼
    ┌────────────────────────────┐   ┌────────────────────────────┐
    │     MenuController         │   │     GameController         │
    ├────────────────────────────┤   ├────────────────────────────┤
    │ - easyRadio: RadioButton   │   │ - gameModel: GameModel     │
    │ - mediumRadio: RadioButton │   │ - aiPlayer: AIPlayer       │
    │ - hardRadio: RadioButton   │   │ - boardGrid: GridPane      │
    │ - startButton: Button      │   │ - cellButtons: Button[][]  │
    │ - difficulty: String       │   │ - statusLabel: Label       │
    ├────────────────────────────┤   │ - turnLabel: Label         │
    │ + initialize(): void       │   │ - scoreLabel: Label        │
    │ + handleStartGame(): void  │   │ - difficultyLabel: Label   │
    │ + handleExit(): void       │   │ - newGameButton: Button    │
    │ + getSelectedDifficulty()  │   │ - undoButton: Button       │
    └────────────────────────────┘   │ - redoButton: Button       │
                                     ├────────────────────────────┤
                                     │ + initialize(): void       │
                                     │ + setDifficulty(String)    │
                                     │ + handleCellClick(int,int) │
                                     │ + handleNewGame(): void    │
                                     │ + handleBackToMenu(): void │
                                     │ + handleUndo(): void       │
                                     │ + handleRedo(): void       │
                                     │ + onMoveMade(Move): void   │
                                     │ + onGameStateChanged(...)  │
                                     │ + onBoardReset(): void     │
                                     │ + onBoardRestored(Board)   │
                                     │ + onPlayerChanged(String)  │
                                     │ + onWinningLineFound(...)  │
                                     └────────────────────────────┘
                                                │
                                                │ implements
                                                ▼
    ┌────────────────────────────────────────────────────────────────┐
    │               «interface» GameObserver                          │
    ├────────────────────────────────────────────────────────────────┤
    │ + onMoveMade(Move): void                                       │
    │ + onGameStateChanged(GameState, String): void                  │
    │ + onBoardReset(): void                                         │
    │ + onBoardRestored(Board): void                                 │
    │ + onPlayerChanged(String): void                                │
    │ + onWinningLineFound(WinningLine): void                        │
    └────────────────────────────────────────────────────────────────┘
                                                ▲
                                                │ observes
                                                │
┌───────────────────────────────────────────────┴─────────────────────────────┐
│                              MODEL LAYER                                     │
│                                                                              │
│  ┌────────────────────────────────────────────────────────────────────────┐ │
│  │                            GameModel                                    │ │
│  ├────────────────────────────────────────────────────────────────────────┤ │
│  │ - board: Board                                                         │ │
│  │ - player1: Player                                                      │ │
│  │ - player2: Player                                                      │ │
│  │ - currentPlayer: String                                                │ │
│  │ - gameState: GameState                                                 │ │
│  │ - winningLine: WinningLine                                             │ │
│  │ - moveNumber: int                                                      │ │
│  │ - observers: List<GameObserver>                                        │ │
│  │ - moveHistory: MoveHistory                                             │ │
│  ├────────────────────────────────────────────────────────────────────────┤ │
│  │ + GameModel()                                                          │ │
│  │ + addObserver(GameObserver): void                                      │ │
│  │ + removeObserver(GameObserver): void                                   │ │
│  │ + makeMove(int, int): boolean                                          │ │
│  │ + resetGame(): void                                                    │ │
│  │ + undo(): boolean                                                      │ │
│  │ + redo(): boolean                                                      │ │
│  │ + canUndo(): boolean                                                   │ │
│  │ + canRedo(): boolean                                                   │ │
│  │ + getCurrentPlayer(): String                                           │ │
│  │ + getBoard(): Board                                                    │ │
│  │ + getGameState(): GameState                                            │ │
│  │ + getWinningLine(): WinningLine                                        │ │
│  │ + getMoveNumber(): int                                                 │ │
│  │ + createMemento(): GameStateMemento                                    │ │
│  │ + restoreFromMemento(GameStateMemento): void                           │ │
│  │ - switchPlayer(): void                                                 │ │
│  │ - checkWinCondition(int, int, String): boolean                         │ │
│  │ - notifyMoveMade(Move): void                                           │ │
│  │ - notifyGameStateChanged(GameState, String): void                      │ │
│  │ - notifyBoardReset(): void                                             │ │
│  │ - notifyBoardRestored(Board): void                                     │ │
│  │ - notifyPlayerChanged(String): void                                    │ │
│  │ - notifyWinningLineFound(WinningLine): void                            │ │
│  └────────────────────────────────────────────────────────────────────────┘ │
│       │                    │                     │                           │
│       │ has                │ uses                │ uses                      │
│       ▼                    ▼                     ▼                           │
│  ┌──────────┐   ┌─────────────────┐   ┌──────────────────┐                │
│  │  Board   │   │  MoveHistory    │   │ GameStateMemento │                │
│  └──────────┘   └─────────────────┘   └──────────────────┘                │
└──────────────────────────────────────────────────────────────────────────────┘
```

---

## 3. Package Model - Core Domain

### 3.1 GameModel (Subject + Originator)

```
┌────────────────────────────────────────────────────────────────────┐
│                            GameModel                                │
├────────────────────────────────────────────────────────────────────┤
│ «private fields»                                                    │
│ - board: Board                                                     │
│ - player1: Player                                                  │
│ - player2: Player                                                  │
│ - currentPlayer: String                                            │
│ - gameState: GameState                                             │
│ - winningLine: WinningLine                                         │
│ - moveNumber: int                                                  │
│ - observers: List<GameObserver>                                    │
│ - moveHistory: MoveHistory                                         │
├────────────────────────────────────────────────────────────────────┤
│ «public methods»                                                    │
│ + GameModel()                                                      │
│                                                                     │
│ // Observer Pattern - Subject methods                              │
│ + addObserver(GameObserver observer): void                         │
│ + removeObserver(GameObserver observer): void                      │
│                                                                     │
│ // Game Logic                                                       │
│ + makeMove(int row, int col): boolean                              │
│ + resetGame(): void                                                │
│                                                                     │
│ // Memento Pattern - Originator methods                            │
│ + undo(): boolean                                                  │
│ + redo(): boolean                                                  │
│ + canUndo(): boolean                                               │
│ + canRedo(): boolean                                               │
│ + createMemento(): GameStateMemento                                │
│ + restoreFromMemento(GameStateMemento memento): void               │
│                                                                     │
│ // Getters                                                          │
│ + getCurrentPlayer(): String                                       │
│ + getBoard(): Board                                                │
│ + getGameState(): GameState                                        │
│ + getWinningLine(): WinningLine                                    │
│ + getMoveNumber(): int                                             │
├────────────────────────────────────────────────────────────────────┤
│ «private methods»                                                   │
│ - switchPlayer(): void                                             │
│ - checkWinCondition(int row, int col, String player): boolean      │
│ - notifyMoveMade(Move move): void                                  │
│ - notifyGameStateChanged(GameState state, String winner): void     │
│ - notifyBoardReset(): void                                         │
│ - notifyBoardRestored(Board board): void                           │
│ - notifyPlayerChanged(String player): void                         │
│ - notifyWinningLineFound(WinningLine line): void                   │
└────────────────────────────────────────────────────────────────────┘
```

**Design Patterns:**
- ✅ **Observer Pattern** - Subject (notifies observers)
- ✅ **Memento Pattern** - Originator (creates/restores mementos)

**Responsibilities:**
- Quản lý trạng thái game
- Validate và thực hiện nước đi
- Kiểm tra điều kiện thắng/thua/hòa
- Thông báo thay đổi cho observers
- Lưu và khôi phục trạng thái

---

### 3.2 Board (Game Board)

```
┌────────────────────────────────────────────────────────────────────┐
│                              Board                                  │
├────────────────────────────────────────────────────────────────────┤
│ «public constants»                                                  │
│ + BOARD_SIZE: int = 15                                             │
│ + WIN_CONDITION: int = 5                                           │
├────────────────────────────────────────────────────────────────────┤
│ «private fields»                                                    │
│ - cells: Cell[][]                                                  │
├────────────────────────────────────────────────────────────────────┤
│ «public methods»                                                    │
│ + Board()                                                          │
│ + Board(Board other)          // Copy constructor                  │
│                                                                     │
│ // Cell operations                                                  │
│ + getCell(int row, int col): Cell                                  │
│ + makeMove(int row, int col, String player): boolean               │
│ + isCellEmpty(int row, int col): boolean                           │
│ + isValidPosition(int row, int col): boolean                       │
│                                                                     │
│ // Board state                                                      │
│ + getEmptyCells(): List<Cell>                                      │
│ + isFull(): boolean                                                │
│ + clear(): void                                                    │
│ + deepCopy(): Board                                                │
│                                                                     │
│ // Win condition checking                                           │
│ + findWinningLine(int row, int col, String player): WinningLine    │
│ + checkDirection(int row, int col, int dRow, int dCol,             │
│                  String player): int                               │
│                                                                     │
│ // Utility                                                          │
│ + toString(): String                                               │
└────────────────────────────────────────────────────────────────────┘
         │
         │ contains 225 (15×15)
         ▼
┌────────────────────────────────────────────────────────────────────┐
│                               Cell                                  │
├────────────────────────────────────────────────────────────────────┤
│ «private fields»                                                    │
│ - row: int                                                         │
│ - col: int                                                         │
│ - content: String                                                  │
├────────────────────────────────────────────────────────────────────┤
│ «public methods»                                                    │
│ + Cell(int row, int col)                                           │
│ + Cell(Cell other)            // Copy constructor                  │
│                                                                     │
│ // Getters                                                          │
│ + getRow(): int                                                    │
│ + getCol(): int                                                    │
│ + getContent(): String                                             │
│                                                                     │
│ // Setters                                                          │
│ + setContent(String content): void                                 │
│                                                                     │
│ // State checking                                                   │
│ + isEmpty(): boolean                                               │
│ + clear(): void                                                    │
│                                                                     │
│ // Utility                                                          │
│ + toString(): String                                               │
│ + equals(Object obj): boolean                                      │
│ + hashCode(): int                                                  │
└────────────────────────────────────────────────────────────────────┘
```

**Responsibilities:**
- Quản lý ma trận 15×15 cells
- Validate vị trí nước đi
- Tìm đường thắng (5 quân liên tiếp)
- Cung cấp thông tin về trạng thái bàn cờ

---

### 3.3 Value Objects (Move, WinningLine)

```
┌────────────────────────────────────────────────────────────────────┐
│                               Move                                  │
├────────────────────────────────────────────────────────────────────┤
│ «private final fields»                                              │
│ - row: int                                                         │
│ - col: int                                                         │
│ - player: String                                                   │
├────────────────────────────────────────────────────────────────────┤
│ «public methods»                                                    │
│ + Move(int row, int col, String player)                            │
│                                                                     │
│ // Getters (immutable)                                              │
│ + getRow(): int                                                    │
│ + getCol(): int                                                    │
│ + getPlayer(): String                                              │
│                                                                     │
│ // Utility                                                          │
│ + toString(): String                                               │
│ + equals(Object obj): boolean                                      │
│ + hashCode(): int                                                  │
└────────────────────────────────────────────────────────────────────┘

┌────────────────────────────────────────────────────────────────────┐
│                          WinningLine                                │
├────────────────────────────────────────────────────────────────────┤
│ «nested enum»                                                       │
│ + Direction { HORIZONTAL, VERTICAL, DIAGONAL_DOWN, DIAGONAL_UP }   │
├────────────────────────────────────────────────────────────────────┤
│ «private final fields»                                              │
│ - positions: List<int[]>                                           │
│ - direction: Direction                                             │
│ - winner: String                                                   │
├────────────────────────────────────────────────────────────────────┤
│ «public methods»                                                    │
│ + WinningLine(List<int[]> positions, Direction dir, String winner) │
│                                                                     │
│ // Getters                                                          │
│ + getPositions(): List<int[]>                                      │
│ + getDirection(): Direction                                        │
│ + getWinner(): String                                              │
│                                                                     │
│ // Utility                                                          │
│ + toString(): String                                               │
└────────────────────────────────────────────────────────────────────┘
```

**Characteristics:**
- ✅ **Immutable** - Không thể thay đổi sau khi tạo
- ✅ **Value Objects** - So sánh bằng giá trị
- ✅ **Thread-safe** - An toàn với đa luồng

---

### 3.4 Player Entity

```
┌────────────────────────────────────────────────────────────────────┐
│                             Player                                  │
├────────────────────────────────────────────────────────────────────┤
│ «private fields»                                                    │
│ - symbol: String                // "X" or "O"                       │
│ - name: String                  // "Player" or "AI"                 │
│ - isHuman: boolean                                                 │
├────────────────────────────────────────────────────────────────────┤
│ «public methods»                                                    │
│ + Player(String symbol, String name, boolean isHuman)              │
│                                                                     │
│ // Getters                                                          │
│ + getSymbol(): String                                              │
│ + getName(): String                                                │
│ + isHuman(): boolean                                               │
│                                                                     │
│ // Setters                                                          │
│ + setName(String name): void                                       │
│                                                                     │
│ // Utility                                                          │
│ + toString(): String                                               │
└────────────────────────────────────────────────────────────────────┘
```

---

### 3.5 GameState Enum

```
┌────────────────────────────────────────────────────────────────────┐
│                        «enumeration»                                │
│                          GameState                                  │
├────────────────────────────────────────────────────────────────────┤
│ + PLAYING                                                          │
│ + X_WON                                                            │
│ + O_WON                                                            │
│ + DRAW                                                             │
└────────────────────────────────────────────────────────────────────┘
```

---

### 3.6 Memento Pattern Classes

```
┌────────────────────────────────────────────────────────────────────┐
│                      GameStateMemento                               │
│                         (Memento)                                   │
├────────────────────────────────────────────────────────────────────┤
│ «private final fields»                                              │
│ - board: Board                                                     │
│ - currentPlayer: String                                            │
│ - gameState: GameState                                             │
│ - moveNumber: int                                                  │
│ - timestamp: long                                                  │
├────────────────────────────────────────────────────────────────────┤
│ «package-private constructor»                                       │
│ GameStateMemento(Board board, String player,                       │
│                  GameState state, int moveNum)                     │
├────────────────────────────────────────────────────────────────────┤
│ «package-private getters»                                           │
│ Board getBoard()                                                   │
│ String getCurrentPlayer()                                          │
│ GameState getGameState()                                           │
│ int getMoveNumber()                                                │
│ long getTimestamp()                                                │
└────────────────────────────────────────────────────────────────────┘

┌────────────────────────────────────────────────────────────────────┐
│                         MoveHistory                                 │
│                        (Caretaker)                                  │
├────────────────────────────────────────────────────────────────────┤
│ «private fields»                                                    │
│ - history: Stack<GameStateMemento>                                 │
│ - redoStack: Stack<GameStateMemento>                               │
│ - maxHistorySize: int = 100                                        │
├────────────────────────────────────────────────────────────────────┤
│ «public methods»                                                    │
│ + MoveHistory()                                                    │
│                                                                     │
│ // History management                                               │
│ + saveState(GameStateMemento memento): void                        │
│ + undo(): GameStateMemento                                         │
│ + redo(): GameStateMemento                                         │
│                                                                     │
│ // State queries                                                    │
│ + canUndo(): boolean                                               │
│ + canRedo(): boolean                                               │
│ + getHistorySize(): int                                            │
│ + getRedoStackSize(): int                                          │
│                                                                     │
│ // Utility                                                          │
│ + clear(): void                                                    │
│ + clearRedoStack(): void                                           │
└────────────────────────────────────────────────────────────────────┘
```

**Design Pattern:** Memento Pattern
- **Originator:** GameModel
- **Memento:** GameStateMemento
- **Caretaker:** MoveHistory

---

### 3.7 ScoreManager (Singleton)

```
┌────────────────────────────────────────────────────────────────────┐
│                         ScoreManager                                │
│                         (Singleton)                                 │
├────────────────────────────────────────────────────────────────────┤
│ «private static field»                                              │
│ - instance: ScoreManager                                           │
├────────────────────────────────────────────────────────────────────┤
│ «private fields»                                                    │
│ - playerWins: int                                                  │
│ - aiWins: int                                                      │
│ - draws: int                                                       │
│ - totalGames: int                                                  │
├────────────────────────────────────────────────────────────────────┤
│ «private constructor»                                               │
│ - ScoreManager()                                                   │
├────────────────────────────────────────────────────────────────────┤
│ «public static method»                                              │
│ + getInstance(): ScoreManager                                      │
├────────────────────────────────────────────────────────────────────┤
│ «public methods»                                                    │
│ + addWin(String winner): void                                      │
│ + addDraw(): void                                                  │
│ + resetScores(): void                                              │
│                                                                     │
│ // Getters                                                          │
│ + getPlayerWins(): int                                             │
│ + getAiWins(): int                                                 │
│ + getDraws(): int                                                  │
│ + getTotalGames(): int                                             │
│                                                                     │
│ // Formatted output                                                 │
│ + getScoreString(): String                                         │
└────────────────────────────────────────────────────────────────────┘
```

**Design Pattern:** Singleton Pattern
- Đảm bảo chỉ có 1 instance quản lý điểm số
- Global access point

---

## 4. Package Strategy - AI Algorithms

### 4.1 Strategy Pattern Structure

```
┌────────────────────────────────────────────────────────────────────┐
│                    «interface» AIStrategy                           │
├────────────────────────────────────────────────────────────────────┤
│ + findBestMove(Board board, String aiPlayer): Move                 │
│ + getStrategyName(): String                                        │
└────────────────────────────────────────────────────────────────────┘
                              ▲
                              │ implements
            ┌─────────────────┼─────────────────┐
            │                 │                 │
┌───────────┴──────┐ ┌────────┴────────┐ ┌─────┴──────────┐
│ EasyAIStrategy   │ │MediumAIStrategy │ │HardAIStrategy  │
└──────────────────┘ └─────────────────┘ └────────────────┘

┌────────────────────────────────────────────────────────────────────┐
│                           AIPlayer                                  │
│                          (Context)                                  │
├────────────────────────────────────────────────────────────────────┤
│ «private fields»                                                    │
│ - strategy: AIStrategy                                             │
│ - symbol: String                                                   │
├────────────────────────────────────────────────────────────────────┤
│ «public methods»                                                    │
│ + AIPlayer(AIStrategy strategy, String symbol)                     │
│ + setStrategy(AIStrategy strategy): void                           │
│ + makeMove(Board board): Move                                      │
│ + getStrategyName(): String                                        │
│ + getSymbol(): String                                              │
└────────────────────────────────────────────────────────────────────┘
```

---

### 4.2 EasyAIStrategy (Random Selection)

```
┌────────────────────────────────────────────────────────────────────┐
│                       EasyAIStrategy                                │
│                    (Concrete Strategy)                              │
├────────────────────────────────────────────────────────────────────┤
│ «private fields»                                                    │
│ - random: Random                                                   │
├────────────────────────────────────────────────────────────────────┤
│ «public methods»                                                    │
│ + EasyAIStrategy()                                                 │
│                                                                     │
│ // AIStrategy interface                                             │
│ + findBestMove(Board board, String aiPlayer): Move                 │
│ + getStrategyName(): String                                        │
├────────────────────────────────────────────────────────────────────┤
│ «algorithm»                                                         │
│ 1. Get all empty cells from board                                  │
│ 2. Randomly select one cell                                        │
│ 3. Return Move with selected position                              │
│                                                                     │
│ Complexity: O(n) where n = number of empty cells                   │
│ Difficulty: Dễ (Easy)                                              │
└────────────────────────────────────────────────────────────────────┘
```

---

### 4.3 MediumAIStrategy (Heuristic Evaluation)

```
┌────────────────────────────────────────────────────────────────────┐
│                     MediumAIStrategy                                │
│                   (Concrete Strategy)                               │
├────────────────────────────────────────────────────────────────────┤
│ «private constants»                                                 │
│ - SCORE_FIVE: int = 100000                                         │
│ - SCORE_FOUR: int = 10000                                          │
│ - SCORE_THREE: int = 1000                                          │
│ - SCORE_TWO: int = 100                                             │
│ - SCORE_ONE: int = 10                                              │
├────────────────────────────────────────────────────────────────────┤
│ «public methods»                                                    │
│ + MediumAIStrategy()                                               │
│ + findBestMove(Board board, String aiPlayer): Move                 │
│ + getStrategyName(): String                                        │
├────────────────────────────────────────────────────────────────────┤
│ «private methods»                                                   │
│ - evaluatePosition(Board board, int row, int col,                  │
│                    String player): int                             │
│ - countConsecutive(Board board, int row, int col,                  │
│                     int dRow, int dCol, String player): int        │
│ - getScore(int count): int                                         │
├────────────────────────────────────────────────────────────────────┤
│ «algorithm»                                                         │
│ 1. Iterate through all empty cells                                 │
│ 2. For each cell, calculate score:                                 │
│    - Check 4 directions (H, V, D1, D2)                             │
│    - Count consecutive pieces                                      │
│    - Apply scoring function                                        │
│ 3. Select cell with highest score                                  │
│                                                                     │
│ Complexity: O(n × m) where:                                        │
│   n = empty cells, m = 4 directions                                │
│ Difficulty: Trung Bình (Medium)                                    │
└────────────────────────────────────────────────────────────────────┘
```

---

### 4.4 HardAIStrategy (Minimax + Alpha-Beta)

```
┌────────────────────────────────────────────────────────────────────┐
│                      HardAIStrategy                                 │
│                   (Concrete Strategy)                               │
├────────────────────────────────────────────────────────────────────┤
│ «private constants»                                                 │
│ - MAX_DEPTH: int = 4                                               │
│ - WIN_SCORE: int = 10000                                           │
│ - SEARCH_RADIUS: int = 2                                           │
├────────────────────────────────────────────────────────────────────┤
│ «public methods»                                                    │
│ + HardAIStrategy()                                                 │
│ + findBestMove(Board board, String aiPlayer): Move                 │
│ + getStrategyName(): String                                        │
├────────────────────────────────────────────────────────────────────┤
│ «private methods - Minimax»                                         │
│ - minimax(Board board, int depth, int alpha, int beta,             │
│           boolean isMaximizing, String aiPlayer,                   │
│           String opponent): int                                    │
│ - evaluateBoard(Board board, String aiPlayer,                      │
│                 String opponent): int                              │
├────────────────────────────────────────────────────────────────────┤
│ «private methods - Heuristics»                                      │
│ - evaluateLine(int aiCount, int opponentCount,                     │
│                int empty): int                                     │
│ - countInLine(Board board, int row, int col,                       │
│               int dRow, int dCol, String player): int              │
├────────────────────────────────────────────────────────────────────┤
│ «private methods - Optimization»                                    │
│ - getCandidateMoves(Board board): List<Cell>                       │
│ - isNearPiece(Board board, int row, int col,                       │
│               int radius): boolean                                 │
├────────────────────────────────────────────────────────────────────┤
│ «algorithm»                                                         │
│ 1. Generate candidate moves (within radius of pieces)              │
│ 2. For each candidate:                                             │
│    - Apply move on cloned board                                    │
│    - Call minimax(depth=4, alpha=-∞, beta=+∞)                      │
│    - Evaluate with Alpha-Beta pruning                              │
│ 3. Return move with best minimax value                             │
│                                                                     │
│ Minimax with Alpha-Beta Pruning:                                   │
│ - Maximizing player: AI (maximize score)                           │
│ - Minimizing player: Opponent (minimize score)                     │
│ - Alpha-Beta: Prune branches that won't affect result              │
│                                                                     │
│ Complexity: O(b^d) where:                                          │
│   b = branching factor (~20-40 moves)                              │
│   d = depth (4 levels)                                             │
│ Optimized with Alpha-Beta: ~O(b^(d/2))                             │
│                                                                     │
│ Difficulty: Khó (Hard)                                             │
└────────────────────────────────────────────────────────────────────┘
```

---

## 5. Package Controller - MVC Controllers

### 5.1 GameController (Observer + Controller)

```
┌────────────────────────────────────────────────────────────────────┐
│                        GameController                               │
│                  implements GameObserver                            │
├────────────────────────────────────────────────────────────────────┤
│ «FXML-injected fields»                                              │
│ @FXML - boardGrid: GridPane                                        │
│ @FXML - statusLabel: Label                                         │
│ @FXML - turnLabel: Label                                           │
│ @FXML - scoreLabel: Label                                          │
│ @FXML - difficultyLabel: Label                                     │
│ @FXML - newGameButton: Button                                      │
│ @FXML - undoButton: Button                                         │
│ @FXML - redoButton: Button                                         │
├────────────────────────────────────────────────────────────────────┤
│ «private fields»                                                    │
│ - gameModel: GameModel                                             │
│ - aiPlayer: AIPlayer                                               │
│ - cellButtons: Button[][]                                          │
│ - difficulty: String                                               │
│ - scoreManager: ScoreManager                                       │
├────────────────────────────────────────────────────────────────────┤
│ «lifecycle methods»                                                 │
│ + initialize(): void                                               │
│ + setDifficulty(String difficulty): void                           │
├────────────────────────────────────────────────────────────────────┤
│ «event handlers»                                                    │
│ + handleCellClick(int row, int col): void                          │
│ + handleNewGame(): void                                            │
│ + handleBackToMenu(): void                                         │
│ + handleUndo(): void                                               │
│ + handleRedo(): void                                               │
├────────────────────────────────────────────────────────────────────┤
│ «GameObserver implementation»                                       │
│ + onMoveMade(Move move): void                                      │
│ + onGameStateChanged(GameState state, String winner): void         │
│ + onBoardReset(): void                                             │
│ + onBoardRestored(Board board): void                               │
│ + onPlayerChanged(String currentPlayer): void                      │
│ + onWinningLineFound(WinningLine winningLine): void                │
├────────────────────────────────────────────────────────────────────┤
│ «private helper methods»                                            │
│ - initializeBoard(): void                                          │
│ - createCell(int row, int col): Button                             │
│ - updateCellButton(int row, int col, String content): void         │
│ - clearBoard(): void                                               │
│ - redrawBoard(Board board): void                                   │
│ - highlightWinningLine(WinningLine line): void                     │
│ - disableBoard(): void                                             │
│ - enableBoard(): void                                              │
│ - updateScoreDisplay(): void                                       │
│ - updateUndoRedoButtons(): void                                    │
│ - showWinMessage(String winner): void                              │
│ - showDrawMessage(): void                                          │
│ - handleAIMove(): void                                             │
└────────────────────────────────────────────────────────────────────┘
```

**Responsibilities:**
- Khởi tạo UI components (bàn cờ 15×15)
- Xử lý user events (clicks, buttons)
- Observe changes từ GameModel
- Update UI theo Model state
- Điều phối AI moves

---

### 5.2 MenuController

```
┌────────────────────────────────────────────────────────────────────┐
│                         MenuController                              │
├────────────────────────────────────────────────────────────────────┤
│ «FXML-injected fields»                                              │
│ @FXML - easyRadio: RadioButton                                     │
│ @FXML - mediumRadio: RadioButton                                   │
│ @FXML - hardRadio: RadioButton                                     │
│ @FXML - startButton: Button                                        │
│ @FXML - difficultyGroup: ToggleGroup                               │
├────────────────────────────────────────────────────────────────────┤
│ «private fields»                                                    │
│ - selectedDifficulty: String = "MEDIUM"                            │
├────────────────────────────────────────────────────────────────────┤
│ «lifecycle methods»                                                 │
│ + initialize(): void                                               │
├────────────────────────────────────────────────────────────────────┤
│ «event handlers»                                                    │
│ + handleStartGame(): void                                          │
│ + handleExit(): void                                               │
│ + handleDifficultyChange(): void                                   │
├────────────────────────────────────────────────────────────────────┤
│ «public methods»                                                    │
│ + getSelectedDifficulty(): String                                  │
├────────────────────────────────────────────────────────────────────┤
│ «private methods»                                                   │
│ - switchToGameScene(): void                                        │
└────────────────────────────────────────────────────────────────────┘
```

**Responsibilities:**
- Quản lý menu chọn độ khó
- Handle radio button selection
- Chuyển scene sang GameController với difficulty

---

## 6. Package View - Observer Interface

### 6.1 GameObserver Interface

```
┌────────────────────────────────────────────────────────────────────┐
│                   «interface» GameObserver                          │
├────────────────────────────────────────────────────────────────────┤
│ + onMoveMade(Move move): void                                      │
│   // Called when a move is made on the board                       │
│                                                                     │
│ + onGameStateChanged(GameState newState, String winner): void      │
│   // Called when game state changes (PLAYING → X_WON/O_WON/DRAW)   │
│                                                                     │
│ + onBoardReset(): void                                             │
│   // Called when board is cleared (New Game)                       │
│                                                                     │
│ + onBoardRestored(Board board): void                               │
│   // Called when board is restored from memento (Undo/Redo)        │
│                                                                     │
│ + onPlayerChanged(String currentPlayer): void                      │
│   // Called when turn switches (X → O or O → X)                    │
│                                                                     │
│ + onWinningLineFound(WinningLine winningLine): void                │
│   // Called when winning line is detected                          │
└────────────────────────────────────────────────────────────────────┘
                              ▲
                              │ implements
                              │
                    ┌─────────┴─────────┐
                    │  GameController   │
                    └───────────────────┘
```

**Design Pattern:** Observer Pattern
- **Subject:** GameModel
- **Observer Interface:** GameObserver
- **Concrete Observer:** GameController

---

## 7. Relationships và Dependencies

### 7.1 Package Dependency Diagram

```
┌──────────────────────────────────────────────────────────────────┐
│                         controller                                │
│  ┌────────────────┐          ┌────────────────┐                 │
│  │MenuController  │          │GameController  │                 │
│  └────────────────┘          └────────────────┘                 │
└──────┬────────────────────────────┬────────────────────┬────────┘
       │                            │                    │
       │ uses                       │ uses               │ implements
       ▼                            ▼                    ▼
┌──────────────┐         ┌──────────────────┐    ┌─────────────┐
│    model     │         │    strategy      │    │    view     │
│              │◄────────┤                  │    │             │
└──────────────┘  uses   └──────────────────┘    └─────────────┘
```

**Dependency Rules:**
```
controller → model     ✅ Controller uses Model
controller → view      ✅ Controller implements Observer interface
controller → strategy  ✅ Controller uses AIPlayer
strategy → model       ✅ Strategy uses Board, Move
model → view           ✅ Model notifies Observer (interface)
view → model           ❌ View không depend vào Model (chỉ data types)
```

---

### 7.2 Class Relationships Matrix

| From Class | To Class | Relationship | Multiplicity | Description |
|------------|----------|--------------|--------------|-------------|
| GameModel | Board | **Composition** | 1 → 1 | GameModel owns Board |
| GameModel | GameObserver | **Association** | 1 → * | Subject notifies Observers |
| GameModel | MoveHistory | **Composition** | 1 → 1 | GameModel owns MoveHistory |
| GameModel | GameStateMemento | **Dependency** | 1 → * | Creates Mementos |
| Board | Cell | **Composition** | 1 → 225 | Board contains 15×15 Cells |
| MoveHistory | GameStateMemento | **Aggregation** | 1 → * | Stores Mementos |
| GameController | GameModel | **Association** | 1 → 1 | Controller uses Model |
| GameController | AIPlayer | **Association** | 1 → 1 | Controller uses AI |
| GameController | GameObserver | **Realization** | - | Implements interface |
| AIPlayer | AIStrategy | **Association** | 1 → 1 | Context uses Strategy |
| EasyAIStrategy | AIStrategy | **Realization** | - | Implements interface |
| MediumAIStrategy | AIStrategy | **Realization** | - | Implements interface |
| HardAIStrategy | AIStrategy | **Realization** | - | Implements interface |

---

### 7.3 Association Types Explained

#### 🔷 **Composition** (Strong Ownership)
```
GameModel ◆────── Board
          ◆────── MoveHistory
```
- GameModel **owns** Board và MoveHistory
- Khi GameModel bị destroy, Board và MoveHistory cũng bị destroy
- Lifecycle phụ thuộc hoàn toàn

#### 🔶 **Aggregation** (Weak Ownership)
```
MoveHistory ◇────── GameStateMemento
```
- MoveHistory **chứa** Mementos nhưng không sở hữu hoàn toàn
- Mementos có thể tồn tại độc lập

#### ➡️ **Association** (Uses)
```
GameController ────── GameModel
GameController ────── AIPlayer
```
- Controller **sử dụng** Model và AIPlayer
- Quan hệ loose coupling

#### ⋯▷ **Dependency** (Temporary Usage)
```
GameModel ‥‥‥▷ GameStateMemento
AIStrategy ‥‥‥▷ Board
```
- GameModel tạo Memento khi cần
- Strategy nhận Board qua parameter

#### ◁─── **Realization** (Implements Interface)
```
GameController ◁───── GameObserver
EasyAIStrategy ◁───── AIStrategy
```
- Class implements interface

---

## 8. Design Patterns Mapping

### 8.1 Complete Pattern Overview

```
┌──────────────────────────────────────────────────────────────────┐
│                    DESIGN PATTERNS IN PROJECT                     │
├──────────────────────────────────────────────────────────────────┤
│                                                                   │
│  ┌────────────────────────────────────────────────────────────┐ │
│  │ 1. STRATEGY PATTERN                                        │ │
│  ├────────────────────────────────────────────────────────────┤ │
│  │ Interface:  AIStrategy                                     │ │
│  │ Context:    AIPlayer                                       │ │
│  │ Strategies: EasyAIStrategy                                 │ │
│  │             MediumAIStrategy                               │ │
│  │             HardAIStrategy                                 │ │
│  │                                                            │ │
│  │ Purpose: Swappable AI algorithms                          │ │
│  └────────────────────────────────────────────────────────────┘ │
│                                                                   │
│  ┌────────────────────────────────────────────────────────────┐ │
│  │ 2. OBSERVER PATTERN                                        │ │
│  ├────────────────────────────────────────────────────────────┤ │
│  │ Subject:    GameModel                                      │ │
│  │ Interface:  GameObserver                                   │ │
│  │ Observer:   GameController                                 │ │
│  │                                                            │ │
│  │ Events:     onMoveMade                                     │ │
│  │             onGameStateChanged                             │ │
│  │             onBoardReset                                   │ │
│  │             onBoardRestored                                │ │
│  │             onPlayerChanged                                │ │
│  │             onWinningLineFound                             │ │
│  │                                                            │ │
│  │ Purpose: Real-time UI updates                             │ │
│  └────────────────────────────────────────────────────────────┘ │
│                                                                   │
│  ┌────────────────────────────────────────────────────────────┐ │
│  │ 3. MEMENTO PATTERN                                         │ │
│  ├────────────────────────────────────────────────────────────┤ │
│  │ Originator: GameModel                                      │ │
│  │ Memento:    GameStateMemento                               │ │
│  │ Caretaker:  MoveHistory                                    │ │
│  │                                                            │ │
│  │ Features:   - Undo/Redo functionality                      │ │
│  │             - History stack (max 100)                      │ │
│  │             - Deep copy of Board                           │ │
│  │             - Immutable snapshots                          │ │
│  │                                                            │ │
│  │ Purpose: State restoration & history management           │ │
│  └────────────────────────────────────────────────────────────┘ │
│                                                                   │
│  ┌────────────────────────────────────────────────────────────┐ │
│  │ 4. SINGLETON PATTERN                                       │ │
│  ├────────────────────────────────────────────────────────────┤ │
│  │ Class:      ScoreManager                                   │ │
│  │                                                            │ │
│  │ Features:   - Private constructor                          │ │
│  │             - Static instance                              │ │
│  │             - Global access point                          │ │
│  │             - Lazy initialization                          │ │
│  │                                                            │ │
│  │ Purpose: Global score management                          │ │
│  └────────────────────────────────────────────────────────────┘ │
│                                                                   │
│  ┌────────────────────────────────────────────────────────────┐ │
│  │ 5. MVC PATTERN (Architecture)                              │ │
│  ├────────────────────────────────────────────────────────────┤ │
│  │ Model:      GameModel, Board, Cell, Move, etc.             │ │
│  │ View:       FXML files, CSS files, GameObserver            │ │
│  │ Controller: GameController, MenuController                 │ │
│  │                                                            │ │
│  │ Purpose: Separation of concerns                           │ │
│  └────────────────────────────────────────────────────────────┘ │
└──────────────────────────────────────────────────────────────────┘
```

---

### 8.2 Pattern Interaction Diagram

```
                    ┌──────────────────┐
                    │  GameController  │
                    │   (Controller)   │
                    └────────┬─────────┘
                             │
                ┌────────────┼────────────┐
                │            │            │
        implements      uses │ uses      uses
                │            │            │
                ▼            ▼            ▼
    ┌──────────────┐  ┌───────────┐  ┌──────────┐
    │GameObserver  │  │ GameModel │  │ AIPlayer │
    │ (Observer)   │  │ (Subject) │  │ (Context)│
    └──────────────┘  └─────┬─────┘  └────┬─────┘
            ▲               │              │
            │               │              │ uses
            │         creates/restores     │
            │               │              ▼
            │               ▼         ┌──────────┐
            │        ┌─────────────┐  │AIStrategy│
            │        │GameMemento  │  │(Strategy)│
            │        │  (Memento)  │  └────┬─────┘
            │        └──────┬──────┘       │
            │               │              │ implements
            │        stored in             │
            │               ▼              ▼
            │        ┌─────────────┐  ┌───────┐
            │        │MoveHistory  │  │Easy/  │
            │        │ (Caretaker) │  │Medium/│
            └────────┴─────────────┘  │Hard AI│
                  notifies             └───────┘
```

---

## Kết luận

### Tổng kết Thiết kế

✅ **17 Classes** được tổ chức trong 5 packages  
✅ **4 Design Patterns** áp dụng nhất quán  
✅ **SOLID Principles** tuân thủ nghiêm ngặt  
✅ **Loose Coupling** thông qua interfaces  
✅ **High Cohesion** - Mỗi class có trách nhiệm rõ ràng  

### Ưu điểm Kiến trúc

1. **Extensibility:** Dễ thêm AI strategies mới
2. **Maintainability:** Code rõ ràng, có cấu trúc
3. **Testability:** Mỗi component test độc lập
4. **Scalability:** Dễ mở rộng features
5. **Reusability:** Components có thể tái sử dụng

---

 
**Người viết:** 2212391- Nguyễn Hoàng Nam Khánh  
**Phiên bản:** 2.0  
**Tổng số trang:** 15+
