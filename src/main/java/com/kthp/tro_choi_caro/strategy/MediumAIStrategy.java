package com.kthp.tro_choi_caro.strategy;

import com.kthp.tro_choi_caro.model.Board;
import com.kthp.tro_choi_caro.model.Cell;
import com.kthp.tro_choi_caro.model.Move;
import java.util.List;

/**
 * Strategy Pattern - Concrete Strategy
 * AI Trung Bình: Sử dụng hàm đánh giá dựa trên mẫu hình
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 */
public class MediumAIStrategy implements AIStrategy {
    
    // Điểm đánh giá cho các mẫu hình
    private static final int SCORE_FIVE = 100000;
    private static final int SCORE_FOUR_OPEN = 10000;
    private static final int SCORE_FOUR_HALF = 5000;
    private static final int SCORE_THREE_OPEN = 1000;
    private static final int SCORE_THREE_HALF = 100;
    private static final int SCORE_TWO_OPEN = 50;
    
    @Override
    public Move findBestMove(Board board, String aiPlayer) {
        List<Cell> emptyCells = board.getEmptyCells();
        
        if (emptyCells.isEmpty()) {
            return null;
        }
        
        String opponent = aiPlayer.equals("X") ? "O" : "X";
        
        Move bestMove = null;
        int bestScore = Integer.MIN_VALUE;
        
        // Đánh giá từng ô trống
        for (Cell cell : emptyCells) {
            int row = cell.getRow();
            int col = cell.getCol();
            
            // Tính điểm tấn công (AI đánh vào đây)
            int attackScore = evaluatePosition(board, row, col, aiPlayer);
            
            // Tính điểm phòng thủ (chặn đối thủ)
            int defenseScore = evaluatePosition(board, row, col, opponent) * 2; // x2 ưu tiên phòng thủ
            
            int totalScore = attackScore + defenseScore;
            
            if (totalScore > bestScore) {
                bestScore = totalScore;
                bestMove = new Move(row, col, aiPlayer);
            }
        }
        
        return bestMove;
    }
    
    /**
     * Đánh giá điểm của một vị trí
     */
    private int evaluatePosition(Board board, int row, int col, String player) {
        int totalScore = 0;
        
        // Kiểm tra 4 hướng: ngang, dọc, chéo chính, chéo phụ
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        
        for (int[] dir : directions) {
            totalScore += evaluateDirection(board, row, col, dir[0], dir[1], player);
        }
        
        return totalScore;
    }
    
    /**
     * Đánh giá điểm theo một hướng cụ thể
     */
    private int evaluateDirection(Board board, int row, int col, int dRow, int dCol, String player) {
        int count = 1; // Đếm quân liên tiếp
        int openEnds = 0; // Số đầu mở
        
        // Kiểm tra hướng thuận
        int r = row + dRow;
        int c = col + dCol;
        
        while (board.isValidPosition(r, c)) {
            if (board.getCell(r, c).getContent().equals(player)) {
                count++;
            } else if (board.getCell(r, c).isEmpty()) {
                openEnds++;
                break;
            } else {
                break; // Bị chặn bởi quân đối thủ
            }
            r += dRow;
            c += dCol;
        }
        
        // Kiểm tra hướng ngược
        r = row - dRow;
        c = col - dCol;
        
        while (board.isValidPosition(r, c)) {
            if (board.getCell(r, c).getContent().equals(player)) {
                count++;
            } else if (board.getCell(r, c).isEmpty()) {
                openEnds++;
                break;
            } else {
                break; // Bị chặn bởi quân đối thủ
            }
            r -= dRow;
            c -= dCol;
        }
        
        // Tính điểm dựa trên số quân liên tiếp và số đầu mở
        return calculateScore(count, openEnds);
    }
    
    /**
     * Tính điểm dựa trên số quân liên tiếp và số đầu mở
     */
    private int calculateScore(int count, int openEnds) {
        if (count >= 5) {
            return SCORE_FIVE;
        }
        
        if (openEnds == 0) {
            return 0; // Bị chặn cả hai đầu
        }
        
        switch (count) {
            case 4:
                return openEnds == 2 ? SCORE_FOUR_OPEN : SCORE_FOUR_HALF;
            case 3:
                return openEnds == 2 ? SCORE_THREE_OPEN : SCORE_THREE_HALF;
            case 2:
                return openEnds == 2 ? SCORE_TWO_OPEN : 10;
            default:
                return 1;
        }
    }
    
    @Override
    public String getStrategyName() {
        return "Medium AI";
    }
}
