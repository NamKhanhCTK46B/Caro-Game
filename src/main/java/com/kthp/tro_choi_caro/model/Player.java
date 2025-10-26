package com.kthp.tro_choi_caro.model;

/**
 * Lớp đại diện cho một người chơi trong game Caro
 * 
 * <p>Trong game hiện tại có 2 người chơi:
 * <ul>
 *   <li><strong>Người chơi (Human):</strong> Symbol = "X", Name = "Player", isAI = false</li>
 *   <li><strong>AI:</strong> Symbol = "O", Name = "AI", isAI = true</li>
 * </ul>
 * </p>
 * 
 * <p><strong>Lưu ý:</strong> Lớp này hiện chưa được sử dụng trực tiếp trong code,
 * game đang sử dụng String "X" và "O" thay vì Player objects.
 * Có thể mở rộng trong tương lai để hỗ trợ multiplayer hoặc custom player names.
 * </p>
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 * @version 2.0
 * @since 2025-10-20
 */
public class Player {
    /** Ký hiệu của người chơi trên bàn cờ ("X" hoặc "O") */
    private final String symbol;
    
    /** Tên hiển thị của người chơi (ví dụ: "Player", "AI", "John") */
    private final String name;
    
    /** Flag đánh dấu người chơi có phải AI không */
    private final boolean isAI;
    
    /**
     * Constructor tạo một người chơi mới
     * 
     * @param symbol Ký hiệu trên bàn cờ ("X" cho người, "O" cho AI)
     * @param name Tên hiển thị của người chơi
     * @param isAI true nếu là AI, false nếu là người
     */
    public Player(String symbol, String name, boolean isAI) {
        this.symbol = symbol;
        this.name = name;
        this.isAI = isAI;
    }
    
    /**
     * Lấy ký hiệu của người chơi
     * @return "X" hoặc "O"
     */
    public String getSymbol() {
        return symbol;
    }
    
    /**
     * Lấy tên người chơi
     * @return Tên hiển thị (ví dụ: "Player", "AI")
     */
    public String getName() {
        return name;
    }
    
    /**
     * Kiểm tra người chơi có phải AI không
     * @return true nếu là AI, false nếu là người
     */
    public boolean isAI() {
        return isAI;
    }
    
    /**
     * Chuyển người chơi thành chuỗi để hiển thị
     * @return String dạng "Name (Symbol)", ví dụ: "Player (X)"
     */
    @Override
    public String toString() {
        return name + " (" + symbol + ")";
    }
}
