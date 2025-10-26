package com.kthp.tro_choi_caro.strategy;

import com.kthp.tro_choi_caro.model.Board;
import com.kthp.tro_choi_caro.model.Move;

/**
 * Strategy Pattern - Context
 * AIPlayer sử dụng một strategy để tìm nước đi
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 */
public class AIPlayer {
    private AIStrategy strategy;
    private String symbol;
    
    public AIPlayer(String symbol, AIStrategy strategy) {
        this.symbol = symbol;
        this.strategy = strategy;
    }
    
    /**
     * Thay đổi chiến thuật AI (Strategy Pattern)
     */
    public void setStrategy(AIStrategy strategy) {
        this.strategy = strategy;
    }
    
    public AIStrategy getStrategy() {
        return strategy;
    }
    
    /**
     * AI thực hiện nước đi
     */
    public Move makeMove(Board board) {
        if (strategy == null) {
            throw new IllegalStateException("AI Strategy is not set!");
        }
        return strategy.findBestMove(board, symbol);
    }
    
    public String getSymbol() {
        return symbol;
    }
}
