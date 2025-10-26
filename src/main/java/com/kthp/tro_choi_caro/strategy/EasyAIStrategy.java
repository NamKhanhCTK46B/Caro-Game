package com.kthp.tro_choi_caro.strategy;

import com.kthp.tro_choi_caro.model.Board;
import com.kthp.tro_choi_caro.model.Cell;
import com.kthp.tro_choi_caro.model.Move;
import java.util.List;
import java.util.Random;

/**
 * Strategy Pattern - Concrete Strategy
 * AI Dễ: Chọn ngẫu nhiên một ô trống
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 */
public class EasyAIStrategy implements AIStrategy {
    private Random random;
    
    public EasyAIStrategy() {
        this.random = new Random();
    }
    
    @Override
    public Move findBestMove(Board board, String aiPlayer) {
        List<Cell> emptyCells = board.getEmptyCells();
        
        if (emptyCells.isEmpty()) {
            return null;
        }
        
        // Chọn ngẫu nhiên một ô trống
        Cell randomCell = emptyCells.get(random.nextInt(emptyCells.size()));
        return new Move(randomCell.getRow(), randomCell.getCol(), aiPlayer);
    }
    
    @Override
    public String getStrategyName() {
        return "Easy AI";
    }
}
