package com.kthp.tro_choi_caro.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Lớp đại diện cho đường đi thắng trong game Caro
 * 
 * <p>Lưu trữ thông tin về 5 quân liên tiếp tạo nên chiến thắng:
 * <ul>
 *   <li>Danh sách các vị trí (row, col) của 5 quân</li>
 *   <li>Hướng của đường thắng (ngang/dọc/chéo)</li>
 *   <li>Người chơi thắng</li>
 * </ul>
 * </p>
 * 
 * <p><strong>Sử dụng để:</strong></p>
 * <ul>
 *   <li>Highlight đường thắng trên UI</li>
 *   <li>Hiển thị animation chiến thắng</li>
 *   <li>Phân tích game</li>
 * </ul>
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 * @version 2.0
 * @since 2025-10-20
 */
public class WinningLine {
    
    /** Danh sách các ô tạo nên đường thắng (5 ô liên tiếp) */
    private final List<Position> positions;
    
    /** Hướng của đường thắng */
    private final Direction direction;
    
    /** Người chơi giành chiến thắng */
    private final String winner;
    
    /**
     * Enum đại diện cho hướng của đường thắng
     */
    public enum Direction {
        /** Ngang (→) */
        HORIZONTAL,
        
        /** Dọc (↓) */
        VERTICAL,
        
        /** Chéo chính (↘) - từ trên trái xuống dưới phải */
        DIAGONAL_MAIN,
        
        /** Chéo phụ (↙) - từ trên phải xuống dưới trái */
        DIAGONAL_ANTI
    }
    
    /**
     * Class đại diện cho một vị trí (row, col) trên bàn cờ
     */
    public static class Position {
        private final int row;
        private final int col;
        
        /**
         * Constructor tạo vị trí mới
         * 
         * @param row Chỉ số hàng (0-14)
         * @param col Chỉ số cột (0-14)
         */
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        /**
         * Lấy chỉ số hàng
         * @return Chỉ số hàng (0-14)
         */
        public int getRow() {
            return row;
        }
        
        /**
         * Lấy chỉ số cột
         * @return Chỉ số cột (0-14)
         */
        public int getCol() {
            return col;
        }
        
        @Override
        public String toString() {
            return String.format("(%d, %d)", row, col);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Position)) return false;
            Position other = (Position) obj;
            return this.row == other.row && this.col == other.col;
        }
        
        @Override
        public int hashCode() {
            return 31 * row + col;
        }
    }
    
    /**
     * Constructor tạo đường thắng mới
     * 
     * @param positions Danh sách 5 vị trí tạo nên đường thắng
     * @param direction Hướng của đường thắng
     * @param winner Người chơi giành chiến thắng ("X" hoặc "O")
     */
    public WinningLine(List<Position> positions, Direction direction, String winner) {
        this.positions = new ArrayList<>(positions);
        this.direction = direction;
        this.winner = winner;
    }
    
    /**
     * Lấy danh sách các vị trí tạo nên đường thắng
     * 
     * @return Danh sách 5 vị trí (immutable copy)
     */
    public List<Position> getPositions() {
        return new ArrayList<>(positions);
    }
    
    /**
     * Lấy hướng của đường thắng
     * 
     * @return Hướng (HORIZONTAL/VERTICAL/DIAGONAL_MAIN/DIAGONAL_ANTI)
     */
    public Direction getDirection() {
        return direction;
    }
    
    /**
     * Lấy người chơi giành chiến thắng
     * 
     * @return "X" hoặc "O"
     */
    public String getWinner() {
        return winner;
    }
    
    /**
     * Kiểm tra một vị trí có nằm trong đường thắng không
     * 
     * <p>Sử dụng để kiểm tra ô nào cần highlight
     * 
     * @param row Chỉ số hàng cần kiểm tra
     * @param col Chỉ số cột cần kiểm tra
     * @return true nếu vị trí nằm trong đường thắng
     */
    public boolean containsPosition(int row, int col) {
        for (Position pos : positions) {
            if (pos.getRow() == row && pos.getCol() == col) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Lấy số lượng ô trong đường thắng
     * 
     * @return Số lượng ô (luôn là 5 cho Caro)
     */
    public int size() {
        return positions.size();
    }
    
    /**
     * Chuyển đường thắng thành chuỗi để debug
     * 
     * @return String mô tả đường thắng
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WinningLine{");
        sb.append("winner=").append(winner);
        sb.append(", direction=").append(direction);
        sb.append(", positions=[");
        for (int i = 0; i < positions.size(); i++) {
            sb.append(positions.get(i));
            if (i < positions.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}
