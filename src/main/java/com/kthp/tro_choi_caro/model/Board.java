package com.kthp.tro_choi_caro.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Lớp đại diện cho bàn cờ Caro
 * 
 * <p>Quản lý bàn cờ 15x15 và logic kiểm tra thắng/thua.
 * Hỗ trợ tìm đường thắng để highlight trên UI.
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 * @version 2.1
 * @since 2025-10-20
 */
public class Board {
    public static final int BOARD_SIZE = 15;
    public static final int WIN_CONDITION = 5; // 5 quân liên tiếp để thắng
    
    private Cell[][] cells;
    
    public Board() {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        initBoard();
    }
    
    private void initBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }
    
    public Cell getCell(int row, int col) {
        if (isValidPosition(row, col)) {
            return cells[row][col];
        }
        return null;
    }
    
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }
    
    public boolean isCellEmpty(int row, int col) {
        return isValidPosition(row, col) && cells[row][col].isEmpty();
    }
    
    public void makeMove(int row, int col, String player) {
        if (isCellEmpty(row, col)) {
            cells[row][col].setContent(player);
        }
    }
    
    public void undoMove(int row, int col) {
        if (isValidPosition(row, col)) {
            cells[row][col].clear();
        }
    }
    
    public List<Cell> getEmptyCells() {
        List<Cell> emptyCells = new ArrayList<>();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (cells[row][col].isEmpty()) {
                    emptyCells.add(cells[row][col]);
                }
            }
        }
        return emptyCells;
    }
    
    public boolean isFull() {
        return getEmptyCells().isEmpty();
    }
    
    public void clear() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                cells[row][col].clear();
            }
        }
    }
    
    /**
     * Kiểm tra xem có 5 quân liên tiếp từ vị trí (row, col) không
     * 
     * <p>Phương thức cũ chỉ trả về boolean. Sử dụng {@link #findWinningLine(int, int, String)} 
     * để lấy thông tin chi tiết về đường thắng.
     * 
     * @param row Hàng của nước đi vừa thực hiện
     * @param col Cột của nước đi vừa thực hiện
     * @param player Người chơi ("X" hoặc "O")
     * @return true nếu có 5 quân liên tiếp, false nếu không
     */
    public boolean checkWinFromPosition(int row, int col, String player) {
        return findWinningLine(row, col, player) != null;
    }
    
    /**
     * Tìm đường thắng (5 quân liên tiếp) từ vị trí vừa đi
     * 
     * <p>Kiểm tra 4 hướng: ngang, dọc, chéo chính, chéo phụ.
     * Nếu tìm thấy 5 quân liên tiếp, trả về đối tượng {@link WinningLine}
     * chứa thông tin chi tiết về đường thắng để UI có thể highlight.
     * 
     * @param row Hàng của nước đi vừa thực hiện
     * @param col Cột của nước đi vừa thực hiện
     * @param player Người chơi ("X" hoặc "O")
     * @return WinningLine nếu tìm thấy đường thắng, null nếu không
     */
    public WinningLine findWinningLine(int row, int col, String player) {
        if (!isValidPosition(row, col) || !cells[row][col].getContent().equals(player)) {
            return null;
        }
        
        // Kiểm tra 4 hướng: ngang, dọc, chéo chính, chéo phụ
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        WinningLine.Direction[] directionTypes = {
            WinningLine.Direction.HORIZONTAL,
            WinningLine.Direction.VERTICAL,
            WinningLine.Direction.DIAGONAL_MAIN,
            WinningLine.Direction.DIAGONAL_ANTI
        };
        
        for (int i = 0; i < directions.length; i++) {
            int[] dir = directions[i];
            
            // Đếm và thu thập vị trí các quân liên tiếp
            List<WinningLine.Position> positions = new ArrayList<>();
            positions.add(new WinningLine.Position(row, col)); // Ô hiện tại
            
            // Thu thập theo hướng thuận
            collectPositionsInDirection(row, col, dir[0], dir[1], player, positions);
            
            // Thu thập theo hướng ngược
            collectPositionsInDirection(row, col, -dir[0], -dir[1], player, positions);
            
            // Nếu có đủ 5 quân liên tiếp -> tìm thấy đường thắng
            if (positions.size() >= WIN_CONDITION) {
                // Sắp xếp positions để đường thắng có thứ tự từ đầu đến cuối
                sortPositions(positions, dir[0], dir[1]);
                
                // Chỉ lấy 5 quân đầu tiên (có thể có nhiều hơn 5)
                List<WinningLine.Position> winningPositions = positions.subList(0, WIN_CONDITION);
                
                return new WinningLine(winningPositions, directionTypes[i], player);
            }
        }
        
        return null;
    }
    
    /**
     * Đếm số quân liên tiếp theo một hướng
     */
    private int countInDirection(int row, int col, int dRow, int dCol, String player) {
        int count = 0;
        int r = row + dRow;
        int c = col + dCol;
        
        while (isValidPosition(r, c) && cells[r][c].getContent().equals(player)) {
            count++;
            r += dRow;
            c += dCol;
        }
        
        return count;
    }
    
    /**
     * Thu thập các vị trí quân liên tiếp theo một hướng
     * 
     * <p>Bắt đầu từ ô kế tiếp (row+dRow, col+dCol) và đi theo hướng cho đến khi
     * gặp ô khác màu hoặc hết bàn cờ. Thêm tất cả các vị trí tìm được vào list.
     * 
     * @param row Hàng bắt đầu
     * @param col Cột bắt đầu
     * @param dRow Hướng hàng (-1, 0, hoặc 1)
     * @param dCol Hướng cột (-1, 0, hoặc 1)
     * @param player Người chơi cần tìm
     * @param positions List để thêm các vị trí tìm được
     */
    private void collectPositionsInDirection(int row, int col, int dRow, int dCol, 
                                            String player, List<WinningLine.Position> positions) {
        int r = row + dRow;
        int c = col + dCol;
        
        while (isValidPosition(r, c) && cells[r][c].getContent().equals(player)) {
            positions.add(new WinningLine.Position(r, c));
            r += dRow;
            c += dCol;
        }
    }
    
    /**
     * Sắp xếp danh sách vị trí theo thứ tự từ đầu đến cuối của đường thắng
     * 
     * <p>Đảm bảo đường thắng được hiển thị theo thứ tự hợp lý từ trái sang phải
     * hoặc từ trên xuống dưới.
     * 
     * @param positions Danh sách vị trí cần sắp xếp
     * @param dRow Hướng hàng của đường thắng
     * @param dCol Hướng cột của đường thắng
     */
    private void sortPositions(List<WinningLine.Position> positions, int dRow, int dCol) {
        positions.sort((p1, p2) -> {
            // Sắp xếp theo hàng trước, nếu cùng hàng thì sắp xếp theo cột
            if (Math.abs(dRow) > 0) {
                // Có thay đổi hàng -> ưu tiên sort theo hàng
                int rowCompare = Integer.compare(p1.getRow(), p2.getRow());
                if (rowCompare != 0) return rowCompare;
            }
            // Sort theo cột
            return Integer.compare(p1.getCol(), p2.getCol());
        });
    }
    
    /**
     * Tạo bản sao sâu của bàn cờ
     */
    public Board deepCopy() {
        Board copy = new Board();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                copy.cells[row][col].setContent(this.cells[row][col].getContent());
            }
        }
        return copy;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                sb.append(cells[row][col]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
