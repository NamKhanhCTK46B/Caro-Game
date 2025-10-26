package com.kthp.tro_choi_caro.controller;

import com.kthp.tro_choi_caro.model.GameModel;
import com.kthp.tro_choi_caro.model.GameState;
import com.kthp.tro_choi_caro.model.Move;
import com.kthp.tro_choi_caro.model.Board;
import com.kthp.tro_choi_caro.model.ScoreManager;
import com.kthp.tro_choi_caro.strategy.AIPlayer;
import com.kthp.tro_choi_caro.strategy.AIStrategy;
import com.kthp.tro_choi_caro.strategy.EasyAIStrategy;
import com.kthp.tro_choi_caro.strategy.MediumAIStrategy;
import com.kthp.tro_choi_caro.strategy.HardAIStrategy;
import com.kthp.tro_choi_caro.view.GameObserver;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.Optional;

/**
 * Controller chính của game (MVC Pattern)
 * Điều khiển logic game và cập nhật View
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 */
public class GameController implements GameObserver {
    
    @FXML
    private GridPane boardGrid;
    
    @FXML
    private Label statusLabel;
    
    @FXML
    private Label turnLabel;
    
    @FXML
    private Button undoButton;  // Nút Hoàn Tác (Memento Pattern)
    
    @FXML
    private Button redoButton;  // Nút Làm Lại (Memento Pattern)
    
    @FXML
    private Button newGameButton;
    
    @FXML
    private Label difficultyLabel;
    
    @FXML
    private Label scoreLabel;  // Label hiển thị điểm số
    
    private GameModel gameModel;
    private AIPlayer aiPlayer;
    private Button[][] cellButtons;
    private String difficulty;
    private ScoreManager scoreManager;  // Quản lý điểm số
    
    /**
     * Khởi tạo controller
     */
    @FXML
    public void initialize() {
        // Sẽ được gọi sau khi FXML được load
        cellButtons = new Button[Board.BOARD_SIZE][Board.BOARD_SIZE];
        createBoard();
        
        // Khởi tạo ScoreManager (Singleton)
        scoreManager = ScoreManager.getInstance();
        updateScoreLabel();
    }
    
    /**
     * Thiết lập game với mức độ khó
     */
    public void setupGame(String difficulty) {
        this.difficulty = difficulty;
        this.gameModel = new GameModel();
        
        // Tạo AI với strategy tương ứng
        AIStrategy strategy = createAIStrategy(difficulty);
        this.aiPlayer = new AIPlayer("O", strategy);
        
        // Đăng ký observer
        gameModel.addObserver(this);
        
        // Cập nhật UI
        updateDifficultyLabel();
        updateButtonStates(); // Cập nhật trạng thái nút Undo/Redo
        updateTurnLabel();
        updateScoreLabel();  // Cập nhật điểm số
        statusLabel.setText("Trò chơi bắt đầu! Lượt của bạn (X)");
    }
    
    /**
     * Tạo AI Strategy dựa trên độ khó
     */
    private AIStrategy createAIStrategy(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "easy":
                return new EasyAIStrategy();
            case "medium":
                return new MediumAIStrategy();
            case "hard":
                return new HardAIStrategy();
            default:
                return new MediumAIStrategy();
        }
    }
    
    /**
     * Tạo bàn cờ UI
     */
    private void createBoard() {
        boardGrid.getChildren().clear();
        
        for (int row = 0; row < Board.BOARD_SIZE; row++) {
            for (int col = 0; col < Board.BOARD_SIZE; col++) {
                Button button = new Button();
                // Thu nhỏ button để hiển thị vừa khung
                button.setPrefSize(40, 40);        // Giảm xuống 40x40
                button.setMinSize(35, 35);         // Min 35x35
                button.setMaxSize(45, 45);         // Max 45x45
                
                // Style đẹp cho ô trống
                button.setStyle(
                    "-fx-font-size: 20px; " +
                    "-fx-font-weight: bold; " +
                    "-fx-font-family: 'Arial'; " +
                    "-fx-background-color: #FFFFFF; " +
                    "-fx-border-color: #CCCCCC; " +
                    "-fx-border-width: 1px; " +
                    "-fx-background-radius: 3px; " +
                    "-fx-border-radius: 3px; " +
                    "-fx-cursor: hand;"              // Con trỏ tay khi hover
                );
                
                final int r = row;
                final int c = col;
                button.setOnAction(e -> handleCellClick(r, c));
                
                cellButtons[row][col] = button;
                boardGrid.add(button, col, row);
            }
        }
    }
    
    /**
     * Xử lý khi người chơi click vào ô
     */
    private void handleCellClick(int row, int col) {
        if (gameModel == null || gameModel.isGameOver()) {
            return;
        }
        
        // Kiểm tra xem có phải lượt người chơi không
        if (!gameModel.getCurrentPlayer().equals("X")) {
            return;
        }
        
        // Thực hiện nước đi
        if (gameModel.makeMove(row, col)) {
            // Nếu game chưa kết thúc và đến lượt AI
            if (!gameModel.isGameOver() && gameModel.getCurrentPlayer().equals("O")) {
                // Delay một chút để AI đi (tạo cảm giác tự nhiên)
                new Thread(() -> {
                    try {
                        Thread.sleep(500); // 0.5 giây
                        Platform.runLater(this::makeAIMove);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }).start();
            }
        }
    }
    
    /**
     * AI thực hiện nước đi
     */
    private void makeAIMove() {
        if (gameModel == null || gameModel.isGameOver()) {
            return;
        }
        
        Move aiMove = aiPlayer.makeMove(gameModel.getBoard());
        if (aiMove != null) {
            gameModel.makeMove(aiMove.getRow(), aiMove.getCol());
        }
    }
    
    /**
     * Xử lý nút Undo (Hoàn Tác) - Memento Pattern
     * Hoàn tác 2 nước đi (Player + AI) để trở về lượt của người chơi
     */
    @FXML
    private void handleUndo() {
        if (gameModel == null) {
            return;
        }
        
        // Kiểm tra nếu đang là lượt AI (game đang chờ AI), không cho undo
        if (!gameModel.getCurrentPlayer().equals("X")) {
            showAlert("Không thể hoàn tác", "Vui lòng đợi AI thực hiện nước đi!", Alert.AlertType.WARNING);
            return;
        }
        
        // Kiểm tra nếu game đã kết thúc
        if (gameModel.isGameOver()) {
            showAlert("Không thể hoàn tác", "Trò chơi đã kết thúc! Vui lòng bắt đầu ván mới.", Alert.AlertType.WARNING);
            return;
        }
        
        // Undo 2 lần: Nước của AI và nước của Player
        boolean success = false;
        
        // Undo lần 1: Nước của AI (nếu có)
        if (gameModel.canUndo()) {
            success = gameModel.undo();
            
            // Undo lần 2: Nước của Player
            if (success && gameModel.canUndo()) {
                success = gameModel.undo();
            }
        }
        
        if (success) {
            statusLabel.setText("✅ Đã hoàn tác 2 nước đi!");
            statusLabel.setStyle("-fx-text-fill: #4CAF50; -fx-font-weight: bold;");
            updateButtonStates();
        } else {
            showAlert("Không thể hoàn tác", "Không có đủ nước đi để hoàn tác!", Alert.AlertType.INFORMATION);
        }
    }
    
    /**
     * Xử lý nút Redo (Làm Lại) - Memento Pattern
     * Làm lại 2 nước đi (Player + AI) đã hoàn tác
     */
    @FXML
    private void handleRedo() {
        if (gameModel == null) {
            return;
        }
        
        // Kiểm tra nếu đang là lượt AI
        if (!gameModel.getCurrentPlayer().equals("X")) {
            showAlert("Không thể làm lại", "Vui lòng đợi AI thực hiện nước đi!", Alert.AlertType.WARNING);
            return;
        }
        
        // Redo 2 lần: Nước của Player và nước của AI
        boolean success = false;
        
        // Redo lần 1: Nước của Player
        if (gameModel.canRedo()) {
            success = gameModel.redo();
            
            // Redo lần 2: Nước của AI (nếu có)
            if (success && gameModel.canRedo()) {
                success = gameModel.redo();
            }
        }
        
        if (success) {
            statusLabel.setText("✅ Đã làm lại 2 nước đi!");
            statusLabel.setStyle("-fx-text-fill: #2196F3; -fx-font-weight: bold;");
            updateButtonStates();
        } else {
            showAlert("Không thể làm lại", "Không có nước đi để làm lại!", Alert.AlertType.INFORMATION);
        }
    }
    
    /**
     * Cập nhật trạng thái enable/disable của nút Undo/Redo
     * Memento Pattern - Kiểm tra MoveHistory
     */
    private void updateButtonStates() {
        if (gameModel == null || undoButton == null || redoButton == null) {
            return;
        }
        
        // Enable/disable nút dựa trên trạng thái của MoveHistory
        boolean canUndo = gameModel.canUndo();
        boolean canRedo = gameModel.canRedo();
        
        undoButton.setDisable(!canUndo || gameModel.isGameOver() || !gameModel.getCurrentPlayer().equals("X"));
        redoButton.setDisable(!canRedo || gameModel.isGameOver() || !gameModel.getCurrentPlayer().equals("X"));
    }
    
    /**
     * Hiển thị Alert dialog
     */
    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    /**
     * Xử lý nút New Game
     */
    @FXML
    private void handleNewGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Trò chơi mới");
        alert.setHeaderText("Bắt đầu trò chơi mới?");
        alert.setContentText("Trò chơi hiện tại sẽ bị mất.");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            gameModel.resetGame();
            updateButtonStates(); // Cập nhật trạng thái nút Undo/Redo
        }
    }
    
    /**
     * Xử lý nút Back to Menu
     */
    @FXML
    private void handleBackToMenu() {
        try {
            com.kthp.tro_choi_caro.App.setRoot("menu");
        } catch (Exception e) {
            showAlert("Lỗi", "Không thể quay về menu: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    // ===== OBSERVER PATTERN IMPLEMENTATIONS =====
    
    @Override
    public void onMoveMade(Move move) {
        Platform.runLater(() -> {
            Button button = cellButtons[move.getRow()][move.getCol()];
            
            // Set text TRƯỚC khi set style để đảm bảo hiển thị
            String displayText = move.getPlayer();
            button.setText(displayText);
            button.setDisable(true);
            
            if (move.getPlayer().equals("X")) {
                // X: CHỮ VỪA, ĐẬM, ĐỎ trên nền trắng với viền đỏ
                button.setStyle(
                    "-fx-font-size: 28px; " +              // Giảm xuống 28px để vừa ô 40x40
                    "-fx-font-weight: 900; " +             // Extra bold
                    "-fx-font-family: 'Arial Black', 'Arial', sans-serif; " +
                    "-fx-text-fill: #DD0000; " +           // Đỏ sáng
                    "-fx-background-color: #FFFFFF; " +    // Nền trắng
                    "-fx-border-color: #FF0000; " +        // Viền đỏ
                    "-fx-border-width: 2px; " +            // Viền 2px
                    "-fx-background-radius: 3px; " +       
                    "-fx-border-radius: 3px; " +
                    "-fx-alignment: CENTER; " +            
                    "-fx-padding: 0; " +
                    "-fx-effect: dropshadow(gaussian, rgba(255,0,0,0.3), 3, 0, 0, 0);"
                );
            } else {
                // O: CHỮ VỪA, ĐẬM, XANH trên nền trắng với viền xanh
                button.setStyle(
                    "-fx-font-size: 28px; " +              // Giảm xuống 28px để vừa ô 40x40
                    "-fx-font-weight: 900; " +             // Extra bold
                    "-fx-font-family: 'Arial Black', 'Arial', sans-serif; " +
                    "-fx-text-fill: #0000DD; " +           // Xanh sáng
                    "-fx-background-color: #FFFFFF; " +    // Nền trắng
                    "-fx-border-color: #0000FF; " +        // Viền xanh
                    "-fx-border-width: 2px; " +            // Viền 2px
                    "-fx-background-radius: 3px; " +       
                    "-fx-border-radius: 3px; " +
                    "-fx-alignment: CENTER; " +            
                    "-fx-padding: 0; " +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,255,0.3), 3, 0, 0, 0);"
                );
            }
            
            // Cập nhật trạng thái nút Undo/Redo sau mỗi nước đi
            updateButtonStates();
        });
    }
    
    @Override
    public void onGameStateChanged(GameState newState, String winner) {
        Platform.runLater(() -> {
            switch (newState) {
                case X_WON:
                    statusLabel.setText("🎉 Bạn thắng! 🎉");
                    statusLabel.setStyle("-fx-text-fill: #00AA00; -fx-font-weight: bold;");
                    scoreManager.addWin("X");
                    updateScoreLabel();
                    disableBoard();
                    break;
                    
                case O_WON:
                    statusLabel.setText("😞 AI thắng! 😞");
                    statusLabel.setStyle("-fx-text-fill: #AA0000; -fx-font-weight: bold;");
                    scoreManager.addWin("O");
                    updateScoreLabel();
                    disableBoard();
                    break;
                    
                case DRAW:
                    statusLabel.setText("🤝 Hòa! 🤝");
                    statusLabel.setStyle("-fx-text-fill: #0000AA; -fx-font-weight: bold;");
                    scoreManager.addDraw();
                    updateScoreLabel();
                    disableBoard();
                    break;
                    
                default:
                    statusLabel.setStyle("-fx-text-fill: #000000;");
                    break;
            }
            // updateButtonStates(); // Đã xóa vì không còn nút Undo/Redo
        });
    }
    
    @Override
    public void onBoardReset() {
        Platform.runLater(() -> {
            for (int row = 0; row < Board.BOARD_SIZE; row++) {
                for (int col = 0; col < Board.BOARD_SIZE; col++) {
                    Button button = cellButtons[row][col];
                    button.setText("");
                    button.setDisable(false);
                    button.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-font-family: 'Arial'; " +
                                  "-fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC; -fx-border-width: 1px;");
                }
            }
            statusLabel.setText("Trò chơi bắt đầu! Lượt của bạn (X)");
            statusLabel.setStyle("-fx-text-fill: #000000;");
            updateButtonStates(); // Cập nhật trạng thái nút Undo/Redo
        });
    }
    
    @Override
    public void onPlayerChanged(String currentPlayer) {
        Platform.runLater(this::updateTurnLabel);
    }
    
    @Override
    public void onBoardRestored() {
        Platform.runLater(() -> {
            // Vẽ lại toàn bộ bàn cờ từ GameModel
            Board board = gameModel.getBoard();
            
            for (int row = 0; row < Board.BOARD_SIZE; row++) {
                for (int col = 0; col < Board.BOARD_SIZE; col++) {
                    Button button = cellButtons[row][col];
                    String content = board.getCell(row, col).getContent();
                    
                    if (content.isEmpty()) {
                        // Ô trống - reset về mặc định
                        button.setText("");
                        button.setDisable(false);
                        button.setStyle(
                            "-fx-font-size: 18px; " +
                            "-fx-font-weight: bold; " +
                            "-fx-font-family: 'Arial'; " +
                            "-fx-background-color: #FFFFFF; " +
                            "-fx-border-color: #CCCCCC; " +
                            "-fx-border-width: 1px;"
                        );
                    } else {
                        // Có quân cờ - vẽ lại
                        button.setText(content);
                        button.setDisable(true);
                        
                        if (content.equals("X")) {
                            // X: Đỏ
                            button.setStyle(
                                "-fx-font-size: 28px; " +
                                "-fx-font-weight: 900; " +
                                "-fx-font-family: 'Arial Black', 'Arial', sans-serif; " +
                                "-fx-text-fill: #DD0000; " +
                                "-fx-background-color: #FFFFFF; " +
                                "-fx-border-color: #FF0000; " +
                                "-fx-border-width: 2px; " +
                                "-fx-background-radius: 3px; " +
                                "-fx-border-radius: 3px; " +
                                "-fx-alignment: CENTER; " +
                                "-fx-padding: 0; " +
                                "-fx-effect: dropshadow(gaussian, rgba(255,0,0,0.3), 3, 0, 0, 0);"
                            );
                        } else if (content.equals("O")) {
                            // O: Xanh
                            button.setStyle(
                                "-fx-font-size: 28px; " +
                                "-fx-font-weight: 900; " +
                                "-fx-font-family: 'Arial Black', 'Arial', sans-serif; " +
                                "-fx-text-fill: #0000DD; " +
                                "-fx-background-color: #FFFFFF; " +
                                "-fx-border-color: #0000FF; " +
                                "-fx-border-width: 2px; " +
                                "-fx-background-radius: 3px; " +
                                "-fx-border-radius: 3px; " +
                                "-fx-alignment: CENTER; " +
                                "-fx-padding: 0; " +
                                "-fx-effect: dropshadow(gaussian, rgba(0,0,255,0.3), 3, 0, 0, 0);"
                            );
                        }
                    }
                }
            }
            
            // Cập nhật status label
            if (gameModel.isGameOver()) {
                // Giữ nguyên message thắng/thua/hòa
            } else {
                statusLabel.setText("Tiếp tục chơi! Lượt: " + 
                    (gameModel.getCurrentPlayer().equals("X") ? "Bạn (X)" : "AI (O)"));
                statusLabel.setStyle("-fx-text-fill: #000000;");
            }
            
            // Cập nhật button states
            updateButtonStates();
        });
    }
    
    @Override
    public void onWinningLineFound(com.kthp.tro_choi_caro.model.WinningLine winningLine) {
        Platform.runLater(() -> {
            // Highlight các ô trong đường thắng
            
            for (com.kthp.tro_choi_caro.model.WinningLine.Position pos : winningLine.getPositions()) {
                Button button = cellButtons[pos.getRow()][pos.getCol()];
                
                // Style đặc biệt cho đường thắng - vàng gold với hiệu ứng sáng
                String winnerColor = winningLine.getWinner().equals("X") ? "#FF0000" : "#0000FF";
                button.setStyle(
                    "-fx-font-size: 28px; " +
                    "-fx-font-weight: 900; " +
                    "-fx-font-family: 'Arial Black', 'Arial', sans-serif; " +
                    "-fx-text-fill: #FFFFFF; " +           // Chữ trắng
                    "-fx-background-color: " + winnerColor + "; " +  // Nền màu người thắng
                    "-fx-border-color: #FFD700; " +        // Viền vàng gold
                    "-fx-border-width: 3px; " +            // Viền dày hơn
                    "-fx-background-radius: 5px; " +       
                    "-fx-border-radius: 5px; " +
                    "-fx-alignment: CENTER; " +
                    "-fx-padding: 0; " +
                    // Hiệu ứng sáng và bóng vàng
                    "-fx-effect: dropshadow(gaussian, rgba(255,215,0,0.8), 15, 0.7, 0, 0);"
                );
            }
        });
    }
    
    // ===== HELPER METHODS =====
    
    private void updateTurnLabel() {
        if (gameModel != null) {
            String player = gameModel.getCurrentPlayer();
            turnLabel.setText("Lượt: " + (player.equals("X") ? "Bạn (X)" : "AI (O)"));
        }
    }
    
    private void updateDifficultyLabel() {
        if (difficulty != null) {
            String diffText = "";
            switch (difficulty.toLowerCase()) {
                case "easy": diffText = "Dễ"; break;
                case "medium": diffText = "Trung Bình"; break;
                case "hard": diffText = "Khó"; break;
                default: diffText = difficulty.toUpperCase();
            }
            difficultyLabel.setText("Độ khó: " + diffText);
        }
    }
    
    /**
     * Cập nhật label hiển thị điểm số
     */
    private void updateScoreLabel() {
        if (scoreLabel != null && scoreManager != null) {
            String stats = scoreManager.getScoreStats();
            scoreLabel.setText(stats);
        }
    }
    
    /**
     * Hiển thị thống kê chi tiết
     */
    @FXML
    private void handleShowStats() {
        if (scoreManager != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thống Kê Chi Tiết");
            alert.setHeaderText("📊 Thống Kê Toàn Bộ Ván Chơi");
            alert.setContentText(scoreManager.getDetailedStats());
            alert.showAndWait();
        }
    }
    
    /**
     * Reset điểm số
     */
    @FXML
    private void handleResetScore() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác Nhận Reset Điểm");
        alert.setHeaderText("Bạn có chắc muốn reset toàn bộ điểm?");
        alert.setContentText("Hành động này không thể hoàn tác!");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            scoreManager.reset();
            updateScoreLabel();
            
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Thành Công");
            success.setHeaderText(null);
            success.setContentText("✅ Đã reset điểm thành công!");
            success.showAndWait();
        }
    }
    
    // Đã xóa updateButtonStates() vì không còn nút Undo/Redo
    
    private void disableBoard() {
        for (int row = 0; row < Board.BOARD_SIZE; row++) {
            for (int col = 0; col < Board.BOARD_SIZE; col++) {
                cellButtons[row][col].setDisable(true);
            }
        }
    }
}
