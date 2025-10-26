package com.kthp.tro_choi_caro.model;

/**
 * Memento Pattern - Lưu trạng thái game
 * Class này lưu trữ snapshot của trạng thái bàn cờ và thông tin game
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 */
public class GameStateMemento {
    private final Board board;
    private final String currentPlayer;
    private final GameState gameState;
    private final int moveNumber;
    
    public GameStateMemento(Board board, String currentPlayer, GameState gameState, int moveNumber) {
        this.board = board.deepCopy(); // Tạo bản sao sâu
        this.currentPlayer = currentPlayer;
        this.gameState = gameState;
        this.moveNumber = moveNumber;
    }
    
    public Board getBoard() {
        return board.deepCopy(); // Trả về bản sao để bảo vệ dữ liệu
    }
    
    public String getCurrentPlayer() {
        return currentPlayer;
    }
    
    public GameState getGameState() {
        return gameState;
    }
    
    public int getMoveNumber() {
        return moveNumber;
    }
}
