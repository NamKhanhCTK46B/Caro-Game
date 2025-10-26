package com.kthp.tro_choi_caro.strategy;

import com.kthp.tro_choi_caro.model.Board;
import com.kthp.tro_choi_caro.model.Move;

/**
 * Strategy Pattern - Interface Strategy
 * Định nghĩa interface chung cho các chiến thuật AI
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 */
public interface AIStrategy {
    /**
     * Tìm nước đi tốt nhất cho AI
     * @param board Bàn cờ hiện tại
     * @param aiPlayer Ký hiệu của AI ("O")
     * @return Nước đi tốt nhất
     */
    Move findBestMove(Board board, String aiPlayer);
    
    /**
     * Lấy tên của chiến thuật
     */
    String getStrategyName();
}
