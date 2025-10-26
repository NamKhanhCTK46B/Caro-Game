# 🎨 CLASS DIAGRAM UML - MEMENTO PATTERN

## Tổng quan

File này chứa Class Diagram UML chi tiết cho **Memento Pattern** được áp dụng để hỗ trợ chức năng Undo/Redo trong Trò Chơi Caro.

**Ngày tạo:** 26/10/2025  
**Tác giả:** 2212391- Nguyễn Hoàng Nam Khánh  
**Design Pattern:** Memento Pattern

---

## 1. Class Diagram Tổng thể (UML Notation)

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                          MEMENTO PATTERN                                     │
│                  Undo/Redo System - Tro Choi Caro                            │
└─────────────────────────────────────────────────────────────────────────────┘

                    ┌────────────────────────────────────┐
                    │     GameStateMemento               │
                    │        (Memento)                   │
                    ├────────────────────────────────────┤
                    │ - board: Board                     │
                    │ - currentPlayer: String            │
                    │ - gameState: GameState             │
                    │ - moveNumber: int                  │
                    ├────────────────────────────────────┤
                    │ + GameStateMemento(...)            │
                    │ + getBoard(): Board                │
                    │ + getCurrentPlayer(): String       │
                    │ + getGameState(): GameState        │
                    │ + getMoveNumber(): int             │
                    └────────────────────────────────────┘
                                    ▲
                                    │
                                    │ creates
                                    │
                    ┌───────────────┴────────────────────┐
                    │         GameModel                  │
                    │       (Originator)                 │
                    ├────────────────────────────────────┤
                    │ - board: Board                     │
                    │ - currentPlayer: String            │
                    │ - gameState: GameState             │
                    │ - moveNumber: int                  │
                    │ - moveHistory: MoveHistory         │
                    ├────────────────────────────────────┤
                    │ + createMemento(): GameStateMemento│
                    │ + restoreFromMemento(...): void    │
                    │ - saveCurrentState(): void         │
                    │ + undo(): boolean                  │
                    │ + redo(): boolean                  │
                    │ + canUndo(): boolean               │
                    │ + canRedo(): boolean               │
                    │ + makeMove(int, int): boolean      │
                    └────────────────────────────────────┘
                                    │
                                    │ uses
                                    ▼
                    ┌────────────────────────────────────┐
                    │       MoveHistory                  │
                    │       (Caretaker)                  │
                    ├────────────────────────────────────┤
                    │ - history: List<GameStateMemento>  │
                    │ - currentIndex: int                │
                    ├────────────────────────────────────┤
                    │ + saveState(GameStateMemento): void│
                    │ + undo(): GameStateMemento         │
                    │ + redo(): GameStateMemento         │
                    │ + canUndo(): boolean               │
                    │ + canRedo(): boolean               │
                    │ + clear(): void                    │
                    │ + getCurrentState(): GameStateMemento│
                    │ + getHistorySize(): int            │
                    └────────────────────────────────────┘
                                    │
                                    │ stores
                                    ▼
                    ┌────────────────────────────────────┐
                    │  List<GameStateMemento>            │
                    │  (History Stack)                   │
                    └────────────────────────────────────┘


Flow Diagram:
═════════════

User Action          Originator         Caretaker           Memento
    │                    │                  │                  │
    │ Make Move          │                  │                  │
    ├───────────────────>│                  │                  │
    │                    │ createMemento()  │                  │
    │                    ├─────────────────────────────────────>│
    │                    │                  │        new Memento│
    │                    │<─────────────────────────────────────┤
    │                    │ saveState(memento)                   │
    │                    ├─────────────────>│                  │
    │                    │                  │ history.add()    │
    │                    │                  │ currentIndex++   │
    │                                                           │
    │ Undo               │                  │                  │
    ├───────────────────>│                  │                  │
    │                    │ undo()           │                  │
    │                    ├─────────────────>│                  │
    │                    │                  │ currentIndex--   │
    │                    │                  │ return memento   │
    │                    │<─────────────────┤                  │
    │                    │ restoreFromMemento(memento)         │
    │                    ├─────────────────────────────────────>│
    │                    │ board = memento.getBoard()          │
    │                    │ currentPlayer = memento.getPlayer() │
    │<───────────────────┤                  │                  │
    │ Board restored     │                  │                  │
```

---

## 2. Class Diagram Chi tiết với Attributes và Methods

### 2.1 GameStateMemento (Memento)

```
╔══════════════════════════════════════════════════════════════════╗
║                    GameStateMemento                               ║
║                       (Memento)                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «private final fields» (Immutable)                                ║
║                                                                   ║
║ - board: Board                                                    ║
║   ↳ Deep copy của bàn cờ 15×15                                   ║
║   ↳ Bản sao độc lập, không share reference                       ║
║                                                                   ║
║ - currentPlayer: String                                           ║
║   ↳ "X" hoặc "O" - người chơi hiện tại                           ║
║   ↳ Immutable String                                             ║
║                                                                   ║
║ - gameState: GameState                                            ║
║   ↳ PLAYING / X_WON / O_WON / DRAW                               ║
║   ↳ Enum (immutable)                                             ║
║                                                                   ║
║ - moveNumber: int                                                 ║
║   ↳ Số nước đã đi tại thời điểm snapshot                         ║
║   ↳ Primitive type (immutable)                                   ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «constructor» (Package-Private)                                   ║
║                                                                   ║
║ GameStateMemento(board: Board, currentPlayer: String,             ║
║                  gameState: GameState, moveNumber: int)           ║
║   ↳ Algorithm:                                                    ║
║     1. this.board = board.deepCopy() ← CRITICAL!                 ║
║     2. this.currentPlayer = currentPlayer                        ║
║     3. this.gameState = gameState                                ║
║     4. this.moveNumber = moveNumber                              ║
║   ↳ Deep copy đảm bảo immutability                               ║
║   ↳ Package-private: Chỉ GameModel có thể tạo                   ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «getters» (Package-Private)                                       ║
║                                                                   ║
║ Board getBoard()                                                  ║
║   ↳ return board.deepCopy()                                      ║
║   ↳ Trả về bản sao để bảo vệ internal state                     ║
║   ↳ Ngăn modification từ bên ngoài                              ║
║                                                                   ║
║ String getCurrentPlayer()                                         ║
║   ↳ return currentPlayer                                         ║
║   ↳ String là immutable nên safe                                 ║
║                                                                   ║
║ GameState getGameState()                                          ║
║   ↳ return gameState                                             ║
║   ↳ Enum là immutable nên safe                                   ║
║                                                                   ║
║ int getMoveNumber()                                               ║
║   ↳ return moveNumber                                            ║
║   ↳ Primitive copy                                               ║
║                                                                   ║
╚══════════════════════════════════════════════════════════════════╝
```

**Key Characteristics:**
- ✅ **Immutable:** All fields are `final`
- ✅ **Deep Copy:** Board được deep copy 2 lần (tạo + trả về)
- ✅ **Encapsulation:** Package-private constructor và getters
- ✅ **Lightweight:** Chỉ chứa data, không có behavior
- ✅ **Snapshot:** Captures complete game state tại 1 thời điểm

**Deep Copy Flow:**
```
Create Memento:
    Original Board (in GameModel)
           │
           │ board.deepCopy()
           ▼
    Copied Board (in Memento) ← Independent copy
    
Restore from Memento:
    Memento Board
           │
           │ memento.getBoard() → board.deepCopy()
           ▼
    New Board (in GameModel) ← Another independent copy
```

**Why Deep Copy?**
```java
// Without deep copy (WRONG):
this.board = board; // Reference copy
// Problem: Changes to original board affect memento!

// With deep copy (CORRECT):
this.board = board.deepCopy(); // Value copy
// Solution: Memento has independent copy
```

**Pattern Role:** Memento (Stores state)

---

### 2.2 GameModel (Originator)

```
╔══════════════════════════════════════════════════════════════════╗
║                         GameModel                                 ║
║                       (Originator)                                ║
╠══════════════════════════════════════════════════════════════════╣
║ «private fields»                                                  ║
║                                                                   ║
║ - board: Board                                                    ║
║   ↳ Bàn cờ hiện tại                                              ║
║                                                                   ║
║ - currentPlayer: String                                           ║
║   ↳ Người chơi hiện tại                                          ║
║                                                                   ║
║ - gameState: GameState                                            ║
║   ↳ Trạng thái game hiện tại                                     ║
║                                                                   ║
║ - moveNumber: int                                                 ║
║   ↳ Số nước đã đi                                                ║
║                                                                   ║
║ - moveHistory: MoveHistory                                        ║
║   ↳ Caretaker quản lý history                                    ║
║   ↳ Reference đến Caretaker                                      ║
║                                                                   ║
║ - observers: List<GameObserver>                                   ║
║   ↳ Observer Pattern (separate concern)                          ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «constructors»                                                    ║
║                                                                   ║
║ + GameModel()                                                     ║
║   ↳ Initialize all fields                                        ║
║   ↳ moveHistory = new MoveHistory()                              ║
║   ↳ saveCurrentState() ← Lưu state ban đầu                      ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «Memento Pattern - Originator Methods»                            ║
║                                                                   ║
║ + createMemento(): GameStateMemento                               ║
║   ↳ Algorithm:                                                    ║
║     return new GameStateMemento(                                 ║
║         board,                                                   ║
║         currentPlayer,                                           ║
║         gameState,                                               ║
║         moveNumber                                               ║
║     );                                                           ║
║   ↳ Tạo snapshot của state hiện tại                              ║
║   ↳ Được gọi tự động sau mỗi move                                ║
║   ↳ Complexity: O(n²) - deep copy board 15×15                    ║
║                                                                   ║
║ + restoreFromMemento(memento: GameStateMemento): void             ║
║   ↳ Algorithm:                                                    ║
║     1. if (memento == null) return                               ║
║     2. this.board = memento.getBoard()                           ║
║     3. this.currentPlayer = memento.getCurrentPlayer()           ║
║     4. this.gameState = memento.getGameState()                   ║
║     5. this.moveNumber = memento.getMoveNumber()                 ║
║     6. notifyBoardRestored() ← Observer Pattern                  ║
║     7. notifyPlayerChanged(currentPlayer)                        ║
║     8. notifyGameStateChanged(gameState, null)                   ║
║   ↳ Khôi phục state từ memento                                   ║
║   ↳ Thông báo cho observers về state mới                         ║
║   ↳ Complexity: O(n²) - copy board                               ║
║                                                                   ║
║ - saveCurrentState(): void (Private)                              ║
║   ↳ Algorithm:                                                    ║
║     1. GameStateMemento memento = createMemento()                ║
║     2. moveHistory.saveState(memento)                            ║
║   ↳ Được gọi tự động sau mỗi move và reset                       ║
║   ↳ Wrapper cho Caretaker                                        ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «Undo/Redo Public Interface»                                      ║
║                                                                   ║
║ + undo(): boolean                                                 ║
║   ↳ Algorithm:                                                    ║
║     1. GameStateMemento memento = moveHistory.undo()             ║
║     2. if (memento != null)                                      ║
║          restoreFromMemento(memento)                             ║
║          return true                                             ║
║     3. return false                                              ║
║   ↳ Hoàn tác 1 nước đi                                           ║
║   ↳ Delegate to Caretaker                                        ║
║                                                                   ║
║ + redo(): boolean                                                 ║
║   ↳ Algorithm:                                                    ║
║     1. GameStateMemento memento = moveHistory.redo()             ║
║     2. if (memento != null)                                      ║
║          restoreFromMemento(memento)                             ║
║          return true                                             ║
║     3. return false                                              ║
║   ↳ Làm lại 1 nước đi đã undo                                    ║
║   ↳ Delegate to Caretaker                                        ║
║                                                                   ║
║ + canUndo(): boolean                                              ║
║   ↳ return moveHistory.canUndo()                                 ║
║   ↳ Kiểm tra có thể undo không                                   ║
║   ↳ Used by UI to enable/disable Undo button                    ║
║                                                                   ║
║ + canRedo(): boolean                                              ║
║   ↳ return moveHistory.canRedo()                                 ║
║   ↳ Kiểm tra có thể redo không                                   ║
║   ↳ Used by UI to enable/disable Redo button                    ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «Game Logic with Auto-Save»                                       ║
║                                                                   ║
║ + makeMove(row: int, col: int): boolean                           ║
║   ↳ Algorithm:                                                    ║
║     1. Validate move                                             ║
║     2. board.makeMove(row, col, currentPlayer)                   ║
║     3. moveNumber++                                              ║
║     4. notifyMoveMade(new Move(...))                             ║
║     5. Check win/draw                                            ║
║     6. If continue: switchPlayer()                               ║
║     7. saveCurrentState() ← AUTO SAVE!                           ║
║     8. return true                                               ║
║   ↳ Mỗi move tự động lưu vào history                             ║
║                                                                   ║
║ + resetGame(): void                                               ║
║   ↳ Algorithm:                                                    ║
║     1. board.clear()                                             ║
║     2. currentPlayer = "X"                                       ║
║     3. gameState = PLAYING                                       ║
║     4. moveNumber = 0                                            ║
║     5. moveHistory.clear() ← Xóa history cũ                      ║
║     6. Notify observers                                          ║
║     7. saveCurrentState() ← Lưu state mới                        ║
║                                                                   ║
╚══════════════════════════════════════════════════════════════════╝
```

**State Save Flow:**
```
makeMove(5, 7)
    │
    ├─> Validate and execute move
    │
    ├─> Update internal state
    │       - board[5][7] = "X"
    │       - moveNumber++
    │       - Check win/draw
    │
    └─> saveCurrentState()
            │
            ├─> createMemento()
            │       │
            │       └─> new GameStateMemento(board.deepCopy(), ...)
            │
            └─> moveHistory.saveState(memento)
                    │
                    └─> history.add(memento)
                        currentIndex++
```

**Undo/Redo Flow:**
```
undo()
    │
    ├─> moveHistory.undo()
    │       │
    │       ├─> currentIndex--
    │       └─> return history[currentIndex]
    │
    └─> restoreFromMemento(memento)
            │
            ├─> board = memento.getBoard()
            ├─> currentPlayer = memento.getCurrentPlayer()
            ├─> gameState = memento.getGameState()
            ├─> moveNumber = memento.getMoveNumber()
            │
            └─> notifyBoardRestored() ← Observer Pattern
```

**Pattern Role:** Originator (Creates and restores mementos)

---

### 2.3 MoveHistory (Caretaker)

```
╔══════════════════════════════════════════════════════════════════╗
║                       MoveHistory                                 ║
║                       (Caretaker)                                 ║
╠══════════════════════════════════════════════════════════════════╣
║ «private fields»                                                  ║
║                                                                   ║
║ - history: List<GameStateMemento>                                 ║
║   ↳ ArrayList lưu trữ tất cả mementos                            ║
║   ↳ Index 0: State ban đầu                                       ║
║   ↳ Index n: State sau n moves                                   ║
║                                                                   ║
║ - currentIndex: int                                               ║
║   ↳ Vị trí hiện tại trong history                                ║
║   ↳ -1: Chưa có state nào                                        ║
║   ↳ 0: State ban đầu                                             ║
║   ↳ n: Đang ở state thứ n                                        ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «constructors»                                                    ║
║                                                                   ║
║ + MoveHistory()                                                   ║
║   ↳ history = new ArrayList<>()                                  ║
║   ↳ currentIndex = -1                                            ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «History Management Methods»                                      ║
║                                                                   ║
║ + saveState(memento: GameStateMemento): void                      ║
║   ↳ Algorithm:                                                    ║
║     1. // Xóa tất cả states sau currentIndex                     ║
║        while (history.size() > currentIndex + 1)                 ║
║            history.remove(history.size() - 1)                    ║
║     2. history.add(memento)                                      ║
║     3. currentIndex++                                            ║
║   ↳ Lưu state mới                                                ║
║   ↳ Xóa redo stack nếu có move mới sau undo                      ║
║   ↳ Complexity: O(k) - k = số states cần xóa                     ║
║                                                                   ║
║ + undo(): GameStateMemento                                        ║
║   ↳ Algorithm:                                                    ║
║     1. if (canUndo())                                            ║
║          currentIndex--                                          ║
║          return history.get(currentIndex)                        ║
║     2. return null                                               ║
║   ↳ Quay lại state trước đó                                      ║
║   ↳ Complexity: O(1)                                             ║
║                                                                   ║
║ + redo(): GameStateMemento                                        ║
║   ↳ Algorithm:                                                    ║
║     1. if (canRedo())                                            ║
║          currentIndex++                                          ║
║          return history.get(currentIndex)                        ║
║     2. return null                                               ║
║   ↳ Tiến tới state tiếp theo                                     ║
║   ↳ Complexity: O(1)                                             ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «State Query Methods»                                             ║
║                                                                   ║
║ + canUndo(): boolean                                              ║
║   ↳ return currentIndex > 0                                      ║
║   ↳ Có thể undo khi currentIndex > 0 (không phải state đầu)     ║
║                                                                   ║
║ + canRedo(): boolean                                              ║
║   ↳ return currentIndex < history.size() - 1                     ║
║   ↳ Có thể redo khi có states sau currentIndex                   ║
║                                                                   ║
║ + getHistorySize(): int                                           ║
║   ↳ return history.size()                                        ║
║   ↳ Tổng số states trong history                                 ║
║                                                                   ║
║ + getCurrentState(): GameStateMemento                             ║
║   ↳ if (currentIndex >= 0 && currentIndex < history.size())     ║
║       return history.get(currentIndex)                           ║
║   ↳ return null                                                  ║
║   ↳ Lấy state hiện tại                                           ║
║                                                                   ║
╠══════════════════════════════════════════════════════════════════╣
║ «Utility Methods»                                                 ║
║                                                                   ║
║ + clear(): void                                                   ║
║   ↳ history.clear()                                              ║
║   ↳ currentIndex = -1                                            ║
║   ↳ Xóa toàn bộ history (khi reset game)                         ║
║                                                                   ║
╚══════════════════════════════════════════════════════════════════╝
```

**History State Diagram:**
```
Initial: currentIndex = -1, history = []

After saveState(M0):
    history = [M0]
    currentIndex = 0
    
After saveState(M1):
    history = [M0, M1]
    currentIndex = 1
    
After saveState(M2):
    history = [M0, M1, M2]
    currentIndex = 2
    
After undo():
    history = [M0, M1, M2] ← Không đổi
    currentIndex = 1        ← Giảm 1
    
After undo():
    history = [M0, M1, M2]
    currentIndex = 0
    
After saveState(M3):  ← New move sau undo
    // Xóa M1, M2 (sau currentIndex)
    history = [M0, M3]
    currentIndex = 1
    
After redo() when currentIndex = 1:
    Cannot redo (currentIndex == history.size() - 1)
```

**Save State with Branching:**
```
Timeline 1:
    M0 → M1 → M2 → M3
                ↑
         (undo 2 times, now at M1)

Timeline 2:
    M0 → M1 → M4 → M5
         ↑
    (M2, M3 deleted when M4 was saved)
```

**Pattern Role:** Caretaker (Manages memento collection)

---

## 3. Relationships Diagram

### 3.1 Class Relationships

```
┌─────────────────────────────────────────────────────────────────┐
│                    RELATIONSHIPS OVERVIEW                        │
└─────────────────────────────────────────────────────────────────┘

GameModel ────────creates─────────> GameStateMemento
    │                                      │
    │                                      │ stores state
    │                                      │
    │                                      ▼
    │                              ┌────────────────┐
    │                              │ board: Board   │
    │                              │ player: String │
    │                              │ state: GameState│
    │                              └────────────────┘
    │
    │ uses
    ▼
MoveHistory ──────stores──────> List<GameStateMemento>
    │                                      │
    │                                      │ manages
    │                                      ▼
    │                           ┌───────────────────────┐
    │                           │ [M0, M1, M2, ..., Mn] │
    │                           │ currentIndex pointer  │
    │                           └───────────────────────┘
    │
    │ returns
    ▼
GameStateMemento ◄────used by────── GameModel
                                     (restore)


Relationship Types:
─────────> Association (uses/creates/stores)
◄──────── Realization (implements interface)
- - - - -> Dependency (temporary usage)
```

### 3.2 Relationship Details

| From | To | Type | Multiplicity | Description |
|------|-----|------|--------------|-------------|
| GameModel | GameStateMemento | **Dependency** | - | Creates mementos |
| GameModel | MoveHistory | **Association** | 1 → 1 | Owns caretaker |
| MoveHistory | GameStateMemento | **Aggregation** | 1 → * | Stores mementos |
| GameStateMemento | Board | **Composition** | 1 → 1 | Contains board copy |
| GameStateMemento | GameState | **Dependency** | - | Uses enum |

---

## 4. Sequence Diagram - Memento Pattern Flow

### 4.1 Save State Sequence

```
User    GameController    GameModel    MoveHistory    GameStateMemento
 │            │               │              │                │
 │ click(5,7) │               │              │                │
 ├───────────>│               │              │                │
 │            │ makeMove(5,7) │              │                │
 │            ├──────────────>│              │                │
 │            │               │ Execute move │                │
 │            │               ├─────────────┤                │
 │            │               │              │                │
 │            │               │ saveCurrentState()            │
 │            │               ├──────────────┤                │
 │            │               │              │                │
 │            │               │ createMemento()               │
 │            │               ├──────────────────────────────>│
 │            │               │              │   new Memento  │
 │            │               │              │   board.deepCopy()
 │            │               │<──────────────────────────────┤
 │            │               │ memento      │                │
 │            │               │              │                │
 │            │               │ saveState(memento)            │
 │            │               ├─────────────>│                │
 │            │               │              │ history.add()  │
 │            │               │              │ currentIndex++ │
 │            │               │              │                │
 │<───────────┤               │              │                │
 │ Move saved │               │              │                │
```

### 4.2 Undo Sequence

```
User    GameController    GameModel    MoveHistory    GameStateMemento
 │            │               │              │                │
 │ Undo btn   │               │              │                │
 ├───────────>│               │              │                │
 │            │ undo()        │              │                │
 │            ├──────────────>│              │                │
 │            │               │ undo()       │                │
 │            │               ├─────────────>│                │
 │            │               │              │ currentIndex-- │
 │            │               │              │ return memento │
 │            │               │<─────────────┤                │
 │            │               │ memento      │                │
 │            │               │              │                │
 │            │               │ restoreFromMemento(memento)   │
 │            │               ├──────────────────────────────>│
 │            │               │              │   getBoard()   │
 │            │               │              │   deepCopy()   │
 │            │               │<──────────────────────────────┤
 │            │               │ board        │                │
 │            │               │              │                │
 │            │               │ board = memento.getBoard()    │
 │            │               │ player = memento.getPlayer()  │
 │            │               │ state = memento.getState()    │
 │            │               ├─────────────┤                │
 │            │               │              │                │
 │            │               │ notifyBoardRestored()         │
 │            │               ├──────────────┤ (Observer)     │
 │            │<──────────────┤              │                │
 │            │ onBoardRestored()            │                │
 │            │ redrawBoard() │              │                │
 │<───────────┤               │              │                │
 │ Undo done  │               │              │                │
```

### 4.3 Redo Sequence

```
User    GameController    GameModel    MoveHistory    GameStateMemento
 │            │               │              │                │
 │ Redo btn   │               │              │                │
 ├───────────>│               │              │                │
 │            │ redo()        │              │                │
 │            ├──────────────>│              │                │
 │            │               │ redo()       │                │
 │            │               ├─────────────>│                │
 │            │               │              │ currentIndex++ │
 │            │               │              │ return memento │
 │            │               │<─────────────┤                │
 │            │               │              │                │
 │            │               │ restoreFromMemento(memento)   │
 │            │               │ (same as undo)                │
 │<───────────┤               │              │                │
 │ Redo done  │               │              │                │
```

### 4.4 New Move After Undo (Branch Deletion)

```
GameModel         MoveHistory         History List
    │                  │                    │
    │                  │         [M0, M1, M2, M3, M4]
    │                  │         currentIndex = 4
    │                  │                    │
    │ undo() × 2       │                    │
    ├─────────────────>│                    │
    │                  │         [M0, M1, M2, M3, M4]
    │                  │         currentIndex = 2 ← Back to M2
    │                  │                    │
    │ makeMove(new)    │                    │
    ├─────────────────┤                    │
    │ saveState(M5)    │                    │
    ├─────────────────>│                    │
    │                  │ Remove M3, M4      │
    │                  │ Add M5             │
    │                  │         [M0, M1, M2, M5]
    │                  │         currentIndex = 3
    │                  │                    │
    │                  │  ← M3, M4 deleted! │
```

**Why delete future states?**
- ✅ User made different choice after undo
- ✅ Old timeline (M3, M4) no longer valid
- ✅ New timeline starts from M2 → M5
- ✅ Prevents conflicting histories

---

## 5. State Transition Diagram - History Management

```
                    ┌─────────────────┐
                    │   Empty History │
                    │  currentIndex=-1│
                    └────────┬────────┘
                             │
                      saveState(M0)
                             │
                             ▼
            ┌────────────────────────────────┐
            │       Has History              │
            │  [M0]  currentIndex=0          │
            └────────┬───────────────────────┘
                     │
          ┌──────────┴────────────┐
          │                       │
   saveState(M1)            undo()
          │                       │
          ▼                       ▼
┌──────────────────┐    ┌──────────────────┐
│ [M0, M1]         │    │ [M0]             │
│ currentIndex=1   │    │ currentIndex=0   │
│ canUndo=true     │    │ canUndo=false    │
│ canRedo=false    │    │ canRedo=true     │
└──────┬───────────┘    └────────┬─────────┘
       │                         │
       │                    redo()
       │                         │
       └─────────────────────────┘


Special Case: New Move After Undo
═════════════════════════════════

State: [M0, M1, M2, M3]
       currentIndex = 1 (after undo 2×)
       
       M0 → M1 → M2 → M3
            ↑
         (current)
       
Action: saveState(M4)
       
       1. Delete M2, M3 (states after currentIndex)
       2. Add M4
       
Result: [M0, M1, M4]
        currentIndex = 2
        
        M0 → M1 → M4
                  ↑
               (current)
```

---

## 6. Pattern Benefits & Trade-offs

### ✅ Benefits (Ưu điểm)

1. **Encapsulation (Đóng gói)**
   - ✅ GameStateMemento không expose implementation details
   - ✅ Package-private constructor/getters
   - ✅ Chỉ GameModel có thể tạo và restore memento
   - ✅ MoveHistory không cần biết cấu trúc memento

2. **Undo/Redo Support**
   - ✅ Dễ implement undo/redo functionality
   - ✅ Multiple levels of undo (unlimited)
   - ✅ Redo after undo
   - ✅ Intuitive for users

3. **Immutability**
   - ✅ Memento là immutable (all fields final)
   - ✅ Deep copy đảm bảo không shared references
   - ✅ Safe from external modification
   - ✅ Thread-safe

4. **Separation of Concerns**
   - ✅ GameModel: Business logic
   - ✅ GameStateMemento: State storage
   - ✅ MoveHistory: History management
   - ✅ Clear responsibilities

5. **Rollback Safety**
   - ✅ Có thể rollback to any previous state
   - ✅ State được lưu tự động
   - ✅ Không lo mất dữ liệu khi undo

### ⚠️ Trade-offs (Đánh đổi)

1. **Memory Overhead**
   - ❌ Mỗi memento = 1 copy of board (15×15 = 225 cells)
   - ❌ 100 moves = 100 mementos trong memory
   - ❌ Deep copy tốn memory
   - ⚠️ Có thể cần limit history size

2. **Performance Cost**
   - ❌ Deep copy board: O(n²) per move
   - ❌ 15×15 board copy ~225 operations
   - ❌ Slower than shallow copy
   - ⚠️ Acceptable cho Caro game (small board)

3. **Complexity**
   - ❌ 3 classes involved (Originator, Memento, Caretaker)
   - ❌ Deep copy logic phức tạp
   - ❌ Index management trong MoveHistory
   - ⚠️ Trade-off for clean design

4. **Branching Deletion**
   - ❌ Redo stack bị xóa khi có move mới
   - ❌ Không thể quay lại timeline cũ
   - ⚠️ Standard behavior, not a bug

---

## 7. Usage Example

### 7.1 Auto-Save on Every Move

```java
public class GameModel {
    private MoveHistory moveHistory = new MoveHistory();
    
    public boolean makeMove(int row, int col) {
        // ... validate and execute move
        
        board.makeMove(row, col, currentPlayer);
        moveNumber++;
        
        // Auto-save after every move
        saveCurrentState();
        
        return true;
    }
    
    private void saveCurrentState() {
        GameStateMemento memento = createMemento();
        moveHistory.saveState(memento);
    }
    
    public GameStateMemento createMemento() {
        return new GameStateMemento(
            board,           // Will be deep copied
            currentPlayer,
            gameState,
            moveNumber
        );
    }
}
```

### 7.2 Undo 2 Moves (Player + AI)

```java
public class GameController {
    @FXML
    private void handleUndo() {
        // Undo AI move
        if (gameModel.canUndo()) {
            gameModel.undo();
            
            // Undo player move
            if (gameModel.canUndo()) {
                gameModel.undo();
                
                statusLabel.setText("✅ Đã hoàn tác 2 nước đi!");
            }
        }
    }
}
```

### 7.3 Enable/Disable Undo/Redo Buttons

```java
private void updateButtonStates() {
    boolean canUndo = gameModel.canUndo();
    boolean canRedo = gameModel.canRedo();
    
    // Only enable Undo when:
    // - Can undo
    // - Game not over
    // - Current player is X (human)
    undoButton.setDisable(
        !canUndo || 
        gameModel.isGameOver() || 
        !gameModel.getCurrentPlayer().equals("X")
    );
    
    redoButton.setDisable(
        !canRedo || 
        gameModel.isGameOver() || 
        !gameModel.getCurrentPlayer().equals("X")
    );
}
```

---

## 8. Extension Possibilities

### 8.1 History Size Limit

```java
public class MoveHistory {
    private static final int MAX_HISTORY_SIZE = 100;
    
    public void saveState(GameStateMemento memento) {
        // Delete old states if history too large
        if (history.size() >= MAX_HISTORY_SIZE) {
            history.remove(0); // Remove oldest
            currentIndex--; // Adjust index
        }
        
        // ... rest of saveState logic
    }
}
```

### 8.2 Compressed Mementos

```java
public class CompressedMemento extends GameStateMemento {
    private byte[] compressedBoard;
    
    public CompressedMemento(Board board, ...) {
        // Compress board to byte array
        this.compressedBoard = compress(board);
    }
    
    @Override
    public Board getBoard() {
        return decompress(compressedBoard);
    }
    
    // Save ~70% memory for sparse boards
}
```

### 8.3 Delta Mementos (Incremental)

```java
public class DeltaMemento {
    private Move lastMove; // Only store the change
    private GameStateMemento baseState;
    
    public Board getBoard() {
        Board board = baseState.getBoard();
        // Apply delta
        board.undoMove(lastMove);
        return board;
    }
    
    // Memory: O(1) instead of O(n²)
    // Restore: O(k) where k = number of deltas
}
```

### 8.4 Persistent History (Save to File)

```java
public class PersistentMoveHistory extends MoveHistory {
    private File historyFile;
    
    @Override
    public void saveState(GameStateMemento memento) {
        super.saveState(memento);
        
        // Serialize to file
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(historyFile, true))) {
            oos.writeObject(memento);
        }
    }
    
    public void loadFromFile() {
        // Deserialize from file
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(historyFile))) {
            while (true) {
                GameStateMemento memento = 
                    (GameStateMemento) ois.readObject();
                history.add(memento);
            }
        }
    }
}
```

---

## 9. Memory Analysis

### Current Implementation

**Per Memento Memory:**
```
GameStateMemento:
    - Board (15×15 cells)
        = 225 × Cell object
        = 225 × (String player + boolean empty)
        ≈ 225 × 24 bytes
        ≈ 5,400 bytes
    - String currentPlayer: ~40 bytes
    - GameState enum: 4 bytes
    - int moveNumber: 4 bytes
    - Object overhead: ~16 bytes
    
Total per memento: ~5,464 bytes ≈ 5.3 KB
```

**For 100 Moves:**
```
100 mementos × 5.3 KB = 530 KB
```

**Analysis:**
- ✅ Acceptable for modern computers (MB of RAM)
- ✅ Small compared to other game assets
- ✅ No lag observed in practice
- ⚠️ Could optimize if needed (compression, delta)

### Alternative: Delta Encoding

```
Delta Memento:
    - Move (row, col, player): ~50 bytes
    - Reference to base state: 8 bytes
    
Total: ~58 bytes

100 moves with delta: ~5.8 KB (vs 530 KB)
Savings: 99% memory reduction!
```

**Trade-off:**
- ✅ Pro: Much less memory
- ❌ Con: Slower restore (need to replay moves)
- ❌ Con: More complex implementation

---

## 10. Design Decisions

### Why Memento Pattern?

1. **Undo/Redo Requirement**
   - ✅ User wants to undo wrong moves
   - ✅ Need multiple levels of undo
   - ✅ Memento is perfect solution

2. **Encapsulation**
   - ✅ GameModel state phải được bảo vệ
   - ✅ Không muốn expose internal structure
   - ✅ Memento provides clean interface

3. **Immutability**
   - ✅ Historical states không được thay đổi
   - ✅ Memento enforces immutability
   - ✅ Safe from bugs

### Alternative Patterns Considered

**❌ Command Pattern:**
```java
// Alternative approach
public class MoveCommand {
    public void execute() { ... }
    public void undo() { 
        board[row][col] = "";
        currentPlayer = opponent;
    }
}
```
- Con: Hard to undo complex state changes
- Con: Need to implement undo logic for each command
- Con: Error-prone

**❌ Prototype Pattern:**
```java
// Alternative approach
Board clonedBoard = board.clone();
```
- Con: Not designed for history management
- Con: No caretaker concept
- Con: Less organized

**✅ Memento Pattern (Chosen):**
```java
GameStateMemento memento = gameModel.createMemento();
moveHistory.saveState(memento);
// Later:
gameModel.restoreFromMemento(memento);
```
- Pro: Designed for this use case
- Pro: Clean separation of concerns
- Pro: Encapsulation preserved
- Pro: Standard pattern

### Deep Copy Decision

**Why Deep Copy?**
```java
// With shallow copy (WRONG):
public GameStateMemento(Board board, ...) {
    this.board = board; // Reference copy
}

// Problem:
GameModel changes board → Memento also changes! ❌

// With deep copy (CORRECT):
public GameStateMemento(Board board, ...) {
    this.board = board.deepCopy(); // Value copy
}

// Solution:
GameModel changes board → Memento unchanged ✓
```

**Deep Copy Implementation:**
```java
// In Board class
public Board deepCopy() {
    Board copy = new Board();
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            copy.cells[i][j] = this.cells[i][j].copy();
        }
    }
    return copy;
}
```

---

## Kết luận

Memento Pattern được áp dụng xuất sắc trong hệ thống Undo/Redo của Trò Chơi Caro:

✅ **3 Roles Hoàn hảo:**
- Originator: GameModel (tạo và khôi phục state)
- Memento: GameStateMemento (lưu trữ state immutable)
- Caretaker: MoveHistory (quản lý history stack)

✅ **Key Features:**
- Auto-save sau mỗi move
- Unlimited undo levels
- Redo support
- Branch deletion (new move after undo)
- Deep copy đảm bảo immutability
- Package-private encapsulation

✅ **Memory Efficient:**
- ~5.3 KB per memento
- 100 moves = 530 KB
- Acceptable overhead

✅ **Integration với Observer:**
- `notifyBoardRestored()` sau restore
- UI tự động sync với state đã restore
- Seamless MVC integration

✅ **SOLID Compliance:**
- Single Responsibility ✓
- Open/Closed ✓
- Liskov Substitution ✓
- Interface Segregation ✓
- Dependency Inversion ✓

**Performance:** O(n²) per save/restore (acceptable cho 15×15 board)

**Extensibility:** Có thể thêm compression, delta encoding, persistent storage

---

**Tác giả:** 2212391- Nguyễn Hoàng Nam Khánh  
**Ngày tạo:** 26/10/2025  
**Phiên bản:** 1.0  
**Loại diagram:** Class Diagram UML - Memento Pattern
