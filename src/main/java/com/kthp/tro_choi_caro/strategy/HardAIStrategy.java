package com.kthp.tro_choi_caro.strategy;

import com.kthp.tro_choi_caro.model.Board;
import com.kthp.tro_choi_caro.model.Cell;
import com.kthp.tro_choi_caro.model.Move;
import java.util.ArrayList;
import java.util.List;

/**
 * Strategy Pattern - Concrete Strategy
 * AI Khó: Sử dụng Minimax với Alpha-Beta Pruning
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 */
public class HardAIStrategy implements AIStrategy {
    
    private static final int MAX_DEPTH = 3; // Độ sâu tìm kiếm
    private static final int WIN_SCORE = 1000000;
    private static final int SEARCH_RADIUS = 2; // Chỉ xét ô trong bán kính 2 của quân cờ đã đặt
    
    @Override
    public Move findBestMove(Board board, String aiPlayer) {
        String opponent = aiPlayer.equals("X") ? "O" : "X";
        
        // Lấy danh sách các ô có thể đi (giới hạn không gian tìm kiếm)
        List<Cell> candidateCells = getCandidateCells(board);
        
        if (candidateCells.isEmpty()) {
            candidateCells = board.getEmptyCells();
            if (candidateCells.isEmpty()) {
                return null;
            }
        }
        
        Move bestMove = null;
        int bestScore = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        
        // Đánh giá từng ô có thể đi
        for (Cell cell : candidateCells) {
            int row = cell.getRow();
            int col = cell.getCol();
            
            // Thử đi vào ô này
            board.makeMove(row, col, aiPlayer);
            
            // Kiểm tra nước đi này có thắng ngay không
            if (board.checkWinFromPosition(row, col, aiPlayer)) {
                board.undoMove(row, col);
                return new Move(row, col, aiPlayer); // Thắng ngay lập tức
            }
            
            // Tính điểm bằng Minimax
            int score = minimax(board, MAX_DEPTH - 1, false, alpha, beta, aiPlayer, opponent);
            
            // Hoàn tác nước đi
            board.undoMove(row, col);
            
            if (score > bestScore) {
                bestScore = score;
                bestMove = new Move(row, col, aiPlayer);
            }
            
            alpha = Math.max(alpha, score);
        }
        
        return bestMove;
    }
    
    /**
     * Thuật toán Minimax với Alpha-Beta Pruning
     */
    private int minimax(Board board, int depth, boolean isMaximizing, int alpha, int beta, 
                        String aiPlayer, String opponent) {
        // Điều kiện dừng
        if (depth == 0) {
            return evaluateBoard(board, aiPlayer, opponent);
        }
        
        List<Cell> candidateCells = getCandidateCells(board);
        if (candidateCells.isEmpty()) {
            return evaluateBoard(board, aiPlayer, opponent);
        }
        
        if (isMaximizing) {
            // AI đang đi (Maximizer)
            int maxScore = Integer.MIN_VALUE;
            
            for (Cell cell : candidateCells) {
                int row = cell.getRow();
                int col = cell.getCol();
                
                board.makeMove(row, col, aiPlayer);
                
                // Kiểm tra thắng
                if (board.checkWinFromPosition(row, col, aiPlayer)) {
                    board.undoMove(row, col);
                    return WIN_SCORE + depth; // Thắng càng sớm càng tốt
                }
                
                int score = minimax(board, depth - 1, false, alpha, beta, aiPlayer, opponent);
                board.undoMove(row, col);
                
                maxScore = Math.max(maxScore, score);
                alpha = Math.max(alpha, score);
                
                // Alpha-Beta Pruning
                if (beta <= alpha) {
                    break;
                }
            }
            
            return maxScore;
        } else {
            // Đối thủ đang đi (Minimizer)
            int minScore = Integer.MAX_VALUE;
            
            for (Cell cell : candidateCells) {
                int row = cell.getRow();
                int col = cell.getCol();
                
                board.makeMove(row, col, opponent);
                
                // Kiểm tra đối thủ thắng
                if (board.checkWinFromPosition(row, col, opponent)) {
                    board.undoMove(row, col);
                    return -WIN_SCORE - depth; // Thua càng muộn càng tốt
                }
                
                int score = minimax(board, depth - 1, true, alpha, beta, aiPlayer, opponent);
                board.undoMove(row, col);
                
                minScore = Math.min(minScore, score);
                beta = Math.min(beta, score);
                
                // Alpha-Beta Pruning
                if (beta <= alpha) {
                    break;
                }
            }
            
            return minScore;
        }
    }
    
    /**
     * Đánh giá điểm của bàn cờ (sử dụng heuristic giống Medium AI)
     */
    private int evaluateBoard(Board board, String aiPlayer, String opponent) {
        int aiScore = 0;
        int opponentScore = 0;
        
        // Đánh giá tất cả các ô đã được đặt quân
        for (int row = 0; row < Board.BOARD_SIZE; row++) {
            for (int col = 0; col < Board.BOARD_SIZE; col++) {
                Cell cell = board.getCell(row, col);
                if (!cell.isEmpty()) {
                    if (cell.getContent().equals(aiPlayer)) {
                        aiScore += evaluateCell(board, row, col, aiPlayer);
                    } else {
                        opponentScore += evaluateCell(board, row, col, opponent);
                    }
                }
            }
        }
        
        return aiScore - opponentScore;
    }
    
    /**
     * Đánh giá điểm của một ô
     */
    private int evaluateCell(Board board, int row, int col, String player) {
        int score = 0;
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        
        for (int[] dir : directions) {
            int count = countInDirection(board, row, col, dir[0], dir[1], player);
            score += getScoreForCount(count);
        }
        
        return score;
    }
    
    /**
     * Đếm số quân liên tiếp theo một hướng
     */
    private int countInDirection(Board board, int row, int col, int dRow, int dCol, String player) {
        int count = 1;
        
        // Đếm theo hướng thuận
        int r = row + dRow;
        int c = col + dCol;
        while (board.isValidPosition(r, c) && board.getCell(r, c).getContent().equals(player)) {
            count++;
            r += dRow;
            c += dCol;
        }
        
        // Đếm theo hướng ngược
        r = row - dRow;
        c = col - dCol;
        while (board.isValidPosition(r, c) && board.getCell(r, c).getContent().equals(player)) {
            count++;
            r -= dRow;
            c -= dCol;
        }
        
        return count;
    }
    
    /**
     * Lấy điểm dựa trên số quân liên tiếp
     */
    private int getScoreForCount(int count) {
        switch (count) {
            case 5: return 100000;
            case 4: return 10000;
            case 3: return 1000;
            case 2: return 100;
            default: return 10;
        }
    }
    
    /**
     * Lấy danh sách các ô ứng viên (giới hạn không gian tìm kiếm)
     * Chỉ xét các ô trong bán kính SEARCH_RADIUS của các quân cờ đã đặt
     */
    private List<Cell> getCandidateCells(Board board) {
        List<Cell> candidates = new ArrayList<>();
        boolean[][] visited = new boolean[Board.BOARD_SIZE][Board.BOARD_SIZE];
        
        // Tìm tất cả các ô đã có quân
        for (int row = 0; row < Board.BOARD_SIZE; row++) {
            for (int col = 0; col < Board.BOARD_SIZE; col++) {
                if (!board.getCell(row, col).isEmpty()) {
                    // Thêm các ô lân cận vào danh sách ứng viên
                    addNeighborCells(board, row, col, candidates, visited);
                }
            }
        }
        
        return candidates;
    }
    
    /**
     * Thêm các ô lân cận vào danh sách ứng viên
     */
    private void addNeighborCells(Board board, int row, int col, List<Cell> candidates, boolean[][] visited) {
        for (int dr = -SEARCH_RADIUS; dr <= SEARCH_RADIUS; dr++) {
            for (int dc = -SEARCH_RADIUS; dc <= SEARCH_RADIUS; dc++) {
                int r = row + dr;
                int c = col + dc;
                
                if (board.isValidPosition(r, c) && !visited[r][c] && board.getCell(r, c).isEmpty()) {
                    candidates.add(board.getCell(r, c));
                    visited[r][c] = true;
                }
            }
        }
    }
    
    @Override
    public String getStrategyName() {
        return "Hard AI";
    }
}
