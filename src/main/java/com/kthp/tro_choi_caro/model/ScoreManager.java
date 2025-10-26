package com.kthp.tro_choi_caro.model;

/**
 * ScoreManager - Quản lý điểm số của game
 * Singleton Pattern để đảm bảo chỉ có 1 instance quản lý điểm
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 */
public class ScoreManager {
    private static ScoreManager instance;
    
    private int playerScore;    // Điểm của người chơi (X)
    private int aiScore;        // Điểm của AI (O)
    private int drawCount;      // Số ván hòa
    private int totalGames;     // Tổng số ván chơi
    
    private ScoreManager() {
        reset();
    }
    
    /**
     * Singleton Pattern - Lấy instance duy nhất
     */
    public static ScoreManager getInstance() {
        if (instance == null) {
            instance = new ScoreManager();
        }
        return instance;
    }
    
    /**
     * Cập nhật điểm khi có người thắng
     */
    public void addWin(String winner) {
        if (winner.equals("X")) {
            playerScore += calculateScore(true, false);  // Thắng: +3
        } else if (winner.equals("O")) {
            aiScore += calculateScore(true, false);      // Thắng: +3
        }
        totalGames++;
    }
    
    /**
     * Cập nhật khi hòa
     */
    public void addDraw() {
        playerScore += calculateScore(false, true);  // Hòa: +1
        aiScore += calculateScore(false, true);       // Hòa: +1
        drawCount++;
        totalGames++;
    }
    
    /**
     * Tính điểm dựa trên kết quả
     * - Thắng: 3 điểm
     * - Hòa: 1 điểm
     * - Thua: 0 điểm
     */
    private int calculateScore(boolean isWin, boolean isDraw) {
        if (isWin) return 3;
        if (isDraw) return 1;
        return 0;
    }
    
    /**
     * Reset toàn bộ điểm số
     */
    public void reset() {
        playerScore = 0;
        aiScore = 0;
        drawCount = 0;
        totalGames = 0;
    }
    
    /**
     * Lấy thông tin thống kê dạng text
     */
    public String getScoreStats() {
        return String.format(
            "🏆 Thống Kê: Bạn %d - AI %d (Hòa: %d) | Tổng: %d ván",
            playerScore, aiScore, drawCount, totalGames
        );
    }
    
    /**
     * Lấy thông tin chi tiết
     */
    public String getDetailedStats() {
        int playerWins = 0;
        int aiWins = 0;
        
        // Tính số ván thắng dựa trên điểm (mỗi ván thắng = 3 điểm)
        if (playerScore > 0) playerWins = (playerScore - drawCount) / 3;
        if (aiScore > 0) aiWins = (aiScore - drawCount) / 3;
        
        return String.format(
            "📊 Chi Tiết:\n" +
            "   • Bạn: %d điểm (%d thắng)\n" +
            "   • AI: %d điểm (%d thắng)\n" +
            "   • Hòa: %d ván\n" +
            "   • Tổng: %d ván",
            playerScore, playerWins,
            aiScore, aiWins,
            drawCount,
            totalGames
        );
    }
    
    // ===== GETTERS =====
    
    public int getPlayerScore() {
        return playerScore;
    }
    
    public int getAiScore() {
        return aiScore;
    }
    
    public int getDrawCount() {
        return drawCount;
    }
    
    public int getTotalGames() {
        return totalGames;
    }
}
