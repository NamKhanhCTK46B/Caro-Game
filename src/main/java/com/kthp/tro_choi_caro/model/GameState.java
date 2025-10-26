package com.kthp.tro_choi_caro.model;

/**
 * Enum đại diện cho các trạng thái của game Caro
 * 
 * <p>Enum này định nghĩa tất cả các trạng thái có thể của một ván chơi,
 * được sử dụng trong Observer Pattern để thông báo cho UI cập nhật.
 * </p>
 * 
 * <p><strong>Luồng chuyển trạng thái:</strong></p>
 * <pre>
 *   PLAYING → X_WON   (khi X thắng)
 *   PLAYING → O_WON   (khi O thắng)
 *   PLAYING → DRAW    (khi hòa - bàn cờ đầy)
 * </pre>
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 * @version 2.0
 * @since 2025-10-20
 */
public enum GameState {
    /** Trạng thái đang chơi - game đang diễn ra */
    PLAYING,
    
    /** Trạng thái X thắng - người chơi (X) chiến thắng */
    X_WON,
    
    /** Trạng thái O thắng - AI (O) chiến thắng */
    O_WON,
    
    /** Trạng thái hòa - bàn cờ đầy mà không ai thắng */
    DRAW
}
