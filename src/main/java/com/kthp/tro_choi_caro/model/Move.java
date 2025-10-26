package com.kthp.tro_choi_caro.model;

/**
 * Lớp đại diện cho một nước đi trong game Caro
 * 
 * <p>Immutable class chứa thông tin về một nước đi:
 * <ul>
 *   <li>Vị trí (row, col) trên bàn cờ</li>
 *   <li>Người chơi thực hiện nước đi (X hoặc O)</li>
 * </ul>
 * </p>
 * 
 * <p><strong>Sử dụng trong:</strong></p>
 * <ul>
 *   <li>Observer Pattern: Thông báo cho UI về nước đi mới</li>
 *   <li>AI Strategy: Trả về nước đi tốt nhất</li>
 *   <li>Memento Pattern: Lưu trữ trong lịch sử</li>
 * </ul>
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 * @version 2.0
 * @since 2025-10-20
 */
public class Move {
    /** Chỉ số hàng trên bàn cờ (0-14 cho bàn cờ 15x15) */
    private final int row;
    
    /** Chỉ số cột trên bàn cờ (0-14 cho bàn cờ 15x15) */
    private final int col;
    
    /** Ký hiệu người chơi thực hiện nước đi ("X" hoặc "O") */
    private final String player;
    
    /**
     * Constructor tạo một nước đi mới
     * 
     * @param row Chỉ số hàng (0-based index)
     * @param col Chỉ số cột (0-based index)
     * @param player Người chơi ("X" = người chơi, "O" = AI)
     */
    public Move(int row, int col, String player) {
        this.row = row;
        this.col = col;
        this.player = player;
    }
    
    /**
     * Lấy chỉ số hàng của nước đi
     * @return Chỉ số hàng (0-14)
     */
    public int getRow() {
        return row;
    }
    
    /**
     * Lấy chỉ số cột của nước đi
     * @return Chỉ số cột (0-14)
     */
    public int getCol() {
        return col;
    }
    
    /**
     * Lấy người chơi thực hiện nước đi
     * @return "X" hoặc "O"
     */
    public String getPlayer() {
        return player;
    }
    
    /**
     * Chuyển nước đi thành chuỗi để debug
     * @return String dạng "Move(row, col, player)"
     */
    @Override
    public String toString() {
        return String.format("Move(%d, %d, %s)", row, col, player);
    }
}
