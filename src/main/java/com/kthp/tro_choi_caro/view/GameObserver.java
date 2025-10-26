package com.kthp.tro_choi_caro.view;

import com.kthp.tro_choi_caro.model.GameState;
import com.kthp.tro_choi_caro.model.Move;
import com.kthp.tro_choi_caro.model.WinningLine;

/**
 * Observer Pattern - Interface Observer
 * 
 * <p>Các lớp View implement interface này để nhận thông báo từ Model.
 * Khi Model thay đổi, nó sẽ gọi các callback methods này để thông báo cho View.
 * 
 * <p><strong>Design Pattern:</strong> Observer Pattern
 * <ul>
 *   <li>Subject: GameModel</li>
 *   <li>Observer: GameController (implements interface này)</li>
 * </ul>
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 * @version 2.1
 * @since 2025-10-20
 */
public interface GameObserver {
    /**
     * Được gọi khi có nước đi mới
     * 
     * @param move Thông tin nước đi (row, col, player)
     */
    void onMoveMade(Move move);
    
    /**
     * Được gọi khi trạng thái game thay đổi
     * 
     * @param newState Trạng thái mới (PLAYING/X_WON/O_WON/DRAW)
     * @param winner Người thắng ("X", "O", hoặc null nếu hòa)
     */
    void onGameStateChanged(GameState newState, String winner);
    
    /**
     * Được gọi khi bàn cờ được reset
     * 
     * <p>View cần xóa tất cả quân cờ và reset UI về trạng thái ban đầu
     */
    void onBoardReset();
    
    /**
     * Được gọi khi người chơi hiện tại thay đổi (chuyển lượt)
     * 
     * @param currentPlayer Người chơi hiện tại ("X" hoặc "O")
     */
    void onPlayerChanged(String currentPlayer);
    
    /**
     * Được gọi khi tìm thấy đường thắng (5 quân liên tiếp)
     * 
     * <p>View cần highlight các ô trong đường thắng để người chơi dễ nhận ra.
     * Callback này được gọi TRƯỚC {@link #onGameStateChanged} để UI có thể
     * highlight trước khi hiển thị thông báo thắng/thua.
     * 
     * @param winningLine Thông tin đường thắng (5 vị trí liên tiếp)
     */
    void onWinningLineFound(WinningLine winningLine);
    
    /**
     * Được gọi khi board được khôi phục từ Memento (Undo/Redo)
     * 
     * <p>View cần vẽ lại tất cả quân cờ từ board state hiện tại
     * thay vì xóa toàn bộ như onBoardReset().
     * 
     * <p><strong>Memento Pattern:</strong> Callback này được gọi sau khi
     * restoreFromMemento() để đồng bộ UI với state đã khôi phục.
     */
    void onBoardRestored();
}
