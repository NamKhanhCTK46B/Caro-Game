package com.kthp.tro_choi_caro.model;

/**
 * Lớp đại diện cho một ô (cell) trên bàn cờ Caro
 * 
 * <p>Mỗi ô trên bàn cờ 15x15 được đại diện bởi một instance của lớp này.
 * Ô có thể chứa:
 * <ul>
 *   <li>"X" - Quân của người chơi</li>
 *   <li>"O" - Quân của AI</li>
 *   <li>"" (empty) - Ô trống</li>
 * </ul>
 * </p>
 * 
 * <p><strong>Sử dụng trong:</strong></p>
 * <ul>
 *   <li>Board: Mảng 2D chứa tất cả các ô</li>
 *   <li>AI Strategy: Tìm ô trống để đi</li>
 *   <li>GameController: Cập nhật UI khi ô thay đổi</li>
 * </ul>
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 * @version 2.0
 * @since 2025-10-20
 */
public class Cell {
    /** Chỉ số hàng của ô trên bàn cờ (0-14) */
    private final int row;
    
    /** Chỉ số cột của ô trên bàn cờ (0-14) */
    private final int col;
    
    /** Nội dung của ô: "X", "O", hoặc "" (trống) */
    private String content;
    
    /**
     * Constructor tạo ô trống
     * 
     * @param row Chỉ số hàng (0-14)
     * @param col Chỉ số cột (0-14)
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.content = "";
    }
    
    /**
     * Constructor tạo ô với nội dung cho trước
     * 
     * <p>Sử dụng khi copy ô hoặc restore từ memento
     * 
     * @param row Chỉ số hàng (0-14)
     * @param col Chỉ số cột (0-14)
     * @param content Nội dung ban đầu ("X", "O", hoặc "")
     */
    public Cell(int row, int col, String content) {
        this.row = row;
        this.col = col;
        this.content = content;
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
    
    /**
     * Lấy nội dung hiện tại của ô
     * @return "X", "O", hoặc "" (trống)
     */
    public String getContent() {
        return content;
    }
    
    /**
     * Đặt nội dung mới cho ô
     * 
     * <p>Được gọi khi:
     * <ul>
     *   <li>Người chơi đặt quân X</li>
     *   <li>AI đặt quân O</li>
     *   <li>Reset game (đặt về "")</li>
     * </ul>
     * 
     * @param content Nội dung mới ("X", "O", hoặc "")
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * Kiểm tra ô có trống không
     * 
     * <p>Ô trống là ô chưa có quân X hoặc O
     * 
     * @return true nếu ô trống, false nếu đã có quân
     */
    public boolean isEmpty() {
        return content.isEmpty();
    }
    
    /**
     * Xóa nội dung ô (đặt về trạng thái trống)
     * 
     * <p>Sử dụng khi reset game hoặc undo move
     */
    public void clear() {
        content = "";
    }
    
    /**
     * Chuyển ô thành chuỗi để hiển thị
     * 
     * @return "_" nếu trống, "X" hoặc "O" nếu có quân
     */
    @Override
    public String toString() {
        return content.isEmpty() ? "_" : content;
    }
}
