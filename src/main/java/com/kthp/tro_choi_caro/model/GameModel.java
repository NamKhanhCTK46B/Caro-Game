package com.kthp.tro_choi_caro.model;

import com.kthp.tro_choi_caro.view.GameObserver;
import java.util.ArrayList;
import java.util.List;

/**
 * GameModel - Model chính của game (MVC Pattern)
 * Observer Pattern - Subject: Quản lý danh sách observers và thông báo khi có thay đổi
 * Memento Pattern - Originator: Tạo và khôi phục memento
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 */
public class GameModel {
    private Board board;
    private Player player1; // X - Human
    private Player player2; // O - AI
    private String currentPlayer; // "X" hoặc "O"
    private GameState gameState;
    private MoveHistory moveHistory;
    private int moveNumber;
    private WinningLine winningLine; // Đường đi thắng (nếu có)
    
    // Observer Pattern - Danh sách observers
    private List<GameObserver> observers;
    
    public GameModel() {
        this.board = new Board();
        this.player1 = new Player("X", "Player", false);
        this.player2 = new Player("O", "AI", true);
        this.currentPlayer = "X"; // X đi trước
        this.gameState = GameState.PLAYING;
        this.moveHistory = new MoveHistory();
        this.moveNumber = 0;
        this.observers = new ArrayList<>();
        
        // Lưu trạng thái ban đầu
        saveCurrentState();
    }
    
    // ===== OBSERVER PATTERN METHODS =====
    
    public void addObserver(GameObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }
    
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }
    
    private void notifyMoveMade(Move move) {
        for (GameObserver observer : observers) {
            observer.onMoveMade(move);
        }
    }
    
    private void notifyGameStateChanged(GameState newState, String winner) {
        for (GameObserver observer : observers) {
            observer.onGameStateChanged(newState, winner);
        }
    }
    
    /**
     * Thông báo cho observers về đường đi thắng
     * 
     * @param winningLine Đường đi thắng (5 quân liên tiếp)
     */
    private void notifyWinningLine(WinningLine winningLine) {
        for (GameObserver observer : observers) {
            observer.onWinningLineFound(winningLine);
        }
    }
    
    private void notifyBoardReset() {
        for (GameObserver observer : observers) {
            observer.onBoardReset();
        }
    }
    
    /**
     * Thông báo cho observers rằng board đã được restore từ Memento
     * Dùng cho Undo/Redo - Vẽ lại board thay vì xóa toàn bộ
     */
    private void notifyBoardRestored() {
        for (GameObserver observer : observers) {
            observer.onBoardRestored();
        }
    }
    
    private void notifyPlayerChanged(String currentPlayer) {
        for (GameObserver observer : observers) {
            observer.onPlayerChanged(currentPlayer);
        }
    }
    
    // ===== GAME LOGIC METHODS =====
    
    /**
     * Thực hiện nước đi
     */
    public boolean makeMove(int row, int col) {
        if (gameState != GameState.PLAYING || !board.isCellEmpty(row, col)) {
            return false;
        }
        
        // Thực hiện nước đi
        board.makeMove(row, col, currentPlayer);
        moveNumber++;
        
        Move move = new Move(row, col, currentPlayer);
        notifyMoveMade(move);
        
        // Kiểm tra thắng và tìm đường thắng
        WinningLine foundWinningLine = board.findWinningLine(row, col, currentPlayer);
        if (foundWinningLine != null) {
            // Có người thắng
            this.winningLine = foundWinningLine;
            gameState = currentPlayer.equals("X") ? GameState.X_WON : GameState.O_WON;
            
            // Thông báo đường thắng TRƯỚC để UI highlight
            notifyWinningLine(winningLine);
            
            // Sau đó thông báo game state changed
            notifyGameStateChanged(gameState, currentPlayer);
        } 
        // Kiểm tra hòa
        else if (board.isFull()) {
            gameState = GameState.DRAW;
            notifyGameStateChanged(gameState, null);
        }
        // Game tiếp tục - chuyển lượt
        else {
            switchPlayer();
        }
        
        // Lưu trạng thái sau khi đi
        saveCurrentState();
        
        return true;
    }
    
    /**
     * Chuyển lượt chơi
     */
    private void switchPlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        notifyPlayerChanged(currentPlayer);
    }
    
    /**
     * Reset game
     */
    public void resetGame() {
        board.clear();
        currentPlayer = "X";
        gameState = GameState.PLAYING;
        moveNumber = 0;
        moveHistory.clear();
        winningLine = null; // Xóa đường thắng cũ
        
        notifyBoardReset();
        notifyPlayerChanged(currentPlayer);
        notifyGameStateChanged(gameState, null);
        
        // Lưu trạng thái ban đầu
        saveCurrentState();
    }
    
    // ===== MEMENTO PATTERN METHODS =====
    
    /**
     * Tạo memento lưu trạng thái hiện tại
     */
    public GameStateMemento createMemento() {
        return new GameStateMemento(board, currentPlayer, gameState, moveNumber);
    }
    
    /**
     * Khôi phục trạng thái từ memento
     */
    public void restoreFromMemento(GameStateMemento memento) {
        if (memento != null) {
            this.board = memento.getBoard();
            this.currentPlayer = memento.getCurrentPlayer();
            this.gameState = memento.getGameState();
            this.moveNumber = memento.getMoveNumber();
            
            // Thông báo cho observers - Dùng BoardRestored thay vì BoardReset
            // để vẽ lại board chứ không xóa toàn bộ (cho Undo/Redo)
            notifyBoardRestored();
            notifyPlayerChanged(currentPlayer);
            notifyGameStateChanged(gameState, null);
        }
    }
    
    /**
     * Lưu trạng thái hiện tại vào lịch sử
     */
    private void saveCurrentState() {
        GameStateMemento memento = createMemento();
        moveHistory.saveState(memento);
    }
    
    /**
     * Undo - Quay lại nước đi trước
     */
    public boolean undo() {
        GameStateMemento memento = moveHistory.undo();
        if (memento != null) {
            restoreFromMemento(memento);
            return true;
        }
        return false;
    }
    
    /**
     * Redo - Làm lại nước đi
     */
    public boolean redo() {
        GameStateMemento memento = moveHistory.redo();
        if (memento != null) {
            restoreFromMemento(memento);
            return true;
        }
        return false;
    }
    
    // ===== GETTERS =====
    
    public Board getBoard() {
        return board;
    }
    
    public String getCurrentPlayer() {
        return currentPlayer;
    }
    
    public GameState getGameState() {
        return gameState;
    }
    
    public Player getPlayer1() {
        return player1;
    }
    
    public Player getPlayer2() {
        return player2;
    }
    
    public boolean canUndo() {
        return moveHistory.canUndo();
    }
    
    public boolean canRedo() {
        return moveHistory.canRedo();
    }
    
    public int getMoveNumber() {
        return moveNumber;
    }
    
    public boolean isGameOver() {
        return gameState != GameState.PLAYING;
    }
    
    /**
     * Lấy đường đi thắng (nếu có)
     * 
     * @return WinningLine nếu game đã kết thúc với người thắng, null nếu chưa có hoặc hòa
     */
    public WinningLine getWinningLine() {
        return winningLine;
    }
}
