package com.kthp.tro_choi_caro.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller cho màn hình Menu - Phần Controller trong MVC Pattern
 * 
 * <p>Lớp này điều khiển màn hình menu chính của game, cho phép người chơi:
 * <ul>
 *   <li>Chọn độ khó AI (Dễ / Trung Bình / Khó)</li>
 *   <li>Bắt đầu game mới</li>
 *   <li>Thoát ứng dụng</li>
 * </ul>
 * </p>
 * 
 * <p><strong>Độ khó AI:</strong></p>
 * <ul>
 *   <li><strong>Dễ (Easy):</strong> AI đi ngẫu nhiên</li>
 *   <li><strong>Trung Bình (Medium):</strong> AI sử dụng heuristic evaluation</li>
 *   <li><strong>Khó (Hard):</strong> AI sử dụng thuật toán Minimax với Alpha-Beta Pruning</li>
 * </ul>
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 * @version 2.0
 * @since 2025-10-20
 */
public class MenuController {
    
    /** Radio button cho độ khó Dễ - liên kết với FXML */
    @FXML
    private RadioButton easyRadio;
    
    /** Radio button cho độ khó Trung Bình - liên kết với FXML */
    @FXML
    private RadioButton mediumRadio;
    
    /** Radio button cho độ khó Khó - liên kết với FXML */
    @FXML
    private RadioButton hardRadio;
    
    /** Button bắt đầu game - liên kết với FXML */
    @FXML
    private Button startButton;
    
    /** ToggleGroup để nhóm các radio buttons (chỉ chọn được 1) */
    private ToggleGroup difficultyGroup;
    
    /**
     * Khởi tạo controller sau khi FXML được load
     * 
     * <p>Phương thức này được JavaFX gọi tự động sau khi:
     * <ol>
     *   <li>File FXML được parse</li>
     *   <li>Tất cả @FXML fields được inject</li>
     *   <li>Controller được tạo</li>
     * </ol>
     * </p>
     * 
     * <p><strong>Công việc thực hiện:</strong></p>
     * <ul>
     *   <li>Tạo ToggleGroup để nhóm các radio buttons</li>
     *   <li>Thêm tất cả radio buttons vào group</li>
     *   <li>Chọn mặc định độ khó Trung Bình</li>
     * </ul>
     */
    @FXML
    public void initialize() {
        // Tạo toggle group để đảm bảo chỉ chọn được 1 radio button
        difficultyGroup = new ToggleGroup();
        
        // Thêm các radio buttons vào group
        easyRadio.setToggleGroup(difficultyGroup);
        mediumRadio.setToggleGroup(difficultyGroup);
        hardRadio.setToggleGroup(difficultyGroup);
        
        // Mặc định chọn Medium - phù hợp với hầu hết người chơi
        mediumRadio.setSelected(true);
    }
    
    /**
     * Xử lý sự kiện khi người dùng click nút "Bắt đầu"
     * 
     * <p><strong>Quy trình:</strong></p>
     * <ol>
     *   <li>Xác định độ khó được chọn từ radio buttons</li>
     *   <li>Load file game.fxml để tạo màn hình game</li>
     *   <li>Lấy GameController từ FXMLLoader</li>
     *   <li>Cấu hình game với độ khó đã chọn (áp dụng Strategy Pattern)</li>
     *   <li>Chuyển scene sang màn hình game</li>
     *   <li>Cấu hình kích thước cửa sổ phù hợp</li>
     * </ol>
     * </p>
     * 
     * <p><strong>Strategy Pattern được áp dụng:</strong></p>
     * <pre>
     *   Easy -> EasyAIStrategy (Random moves)
     *   Medium -> MediumAIStrategy (Heuristic evaluation)
     *   Hard -> HardAIStrategy (Minimax + Alpha-Beta)
     * </pre>
     * 
     * @throws IOException Nếu không thể load file game.fxml
     */
    @FXML
    private void handleStartGame() {
        // Xác định độ khó được chọn (mặc định medium nếu không có gì được chọn)
        String difficulty = "medium";
        
        if (easyRadio.isSelected()) {
            difficulty = "easy";
        } else if (mediumRadio.isSelected()) {
            difficulty = "medium";
        } else if (hardRadio.isSelected()) {
            difficulty = "hard";
        }
        
        try {
            // Load game FXML bằng FXMLLoader
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/kthp/tro_choi_caro/game.fxml")
            );
            Parent root = loader.load();
            
            // Lấy controller và setup game với độ khó đã chọn
            GameController gameController = loader.getController();
            gameController.setupGame(difficulty);
            
            // Lấy stage hiện tại và thiết lập scene mới
            Stage stage = (Stage) startButton.getScene().getWindow();
            Scene scene = new Scene(root, 1200, 800);
            
            stage.setScene(scene);
            stage.setTitle("Caro Game - " + difficulty.toUpperCase());
            stage.setResizable(true);
            stage.setMinWidth(1000);
            stage.setMinHeight(700);
            
        } catch (IOException e) {
            showError("Không thể khởi động game", 
                     "Đã xảy ra lỗi khi tải giao diện game: " + e.getMessage());
        }
    }
    
    /**
     * Xử lý sự kiện khi người dùng click nút "Thoát"
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
    /**
     * Hiển thị dialog lỗi
     */
    private void showError(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
            javafx.scene.control.Alert.AlertType.ERROR
        );
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
