package com.kthp.tro_choi_caro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Lớp chính của ứng dụng JavaFX - Entry Point
 * 
 * <p>Đây là điểm khởi đầu của ứng dụng game Caro (X-O).
 * Lớp này kế thừa từ {@link Application} và quản lý:
 * <ul>
 *   <li>Khởi tạo cửa sổ chính (Stage)</li>
 *   <li>Quản lý chuyển đổi giữa các màn hình (Scene)</li>
 *   <li>Load các file FXML để tạo giao diện</li>
 * </ul>
 * </p>
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 * @version 2.0
 * @since 2025-10-20
 */
public class App extends Application {

    /** Scene hiện tại của ứng dụng - chứa giao diện đang hiển thị */
    private static Scene scene;
    
    /** Stage chính của ứng dụng - cửa sổ chính */
    private static Stage primaryStage;

    /**
     * Phương thức khởi động ứng dụng JavaFX
     * 
     * <p>Được gọi tự động bởi JavaFX Runtime khi ứng dụng bắt đầu.
     * Thực hiện các công việc:
     * <ol>
     *   <li>Lưu trữ reference đến Stage chính</li>
     *   <li>Tạo Scene ban đầu với màn hình menu</li>
     *   <li>Cấu hình kích thước và thuộc tính cửa sổ</li>
     *   <li>Hiển thị cửa sổ lên màn hình</li>
     * </ol>
     * </p>
     *
     * @param stage Stage chính được JavaFX Runtime cung cấp
     * @throws IOException Nếu không thể load file FXML menu
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Lưu reference đến stage để sử dụng sau này
        primaryStage = stage;
        
        // Tạo scene ban đầu từ file menu.fxml với kích thước 1024x768
        scene = new Scene(loadFXML("menu"), 1024, 768);
        stage.setScene(scene);
        
        // Cấu hình thông tin cửa sổ
        stage.setTitle("Trò Chơi Caro - Game X-O");
        stage.setResizable(true);  // Cho phép resize cửa sổ
        
        // Đặt kích thước tối thiểu để đảm bảo UI không bị vỡ
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        
        // Mở cửa sổ ở chế độ maximize để tận dụng màn hình
        stage.setMaximized(true);
        
        // Hiển thị cửa sổ
        stage.show();
    }

    /**
     * Chuyển đổi màn hình (Scene Root)
     * 
     * <p>Phương thức static này cho phép chuyển đổi giữa các màn hình khác nhau
     * bằng cách thay đổi root node của Scene hiện tại.
     * 
     * <p><strong>Cách sử dụng:</strong></p>
     * <pre>
     *   App.setRoot("menu");    // Chuyển về màn hình menu
     *   App.setRoot("game");    // Chuyển đến màn hình game
     * </pre>
     * 
     * <p><strong>Quy trình:</strong></p>
     * <ol>
     *   <li>Kiểm tra primaryStage và scene có tồn tại không</li>
     *   <li>Lấy scene hiện tại từ primaryStage (đảm bảo đồng bộ)</li>
     *   <li>Load file FXML mới</li>
     *   <li>Thay thế root node của scene</li>
     * </ol>
     * </p>
     *
     * @param fxml Tên file FXML (không cần extension .fxml)
     *             Ví dụ: "menu", "game"
     * @throws IOException Nếu không tìm thấy hoặc không thể load file FXML
     */
    public static void setRoot(String fxml) throws IOException {
        // Kiểm tra và lấy scene hiện tại từ primaryStage
        if (primaryStage != null && primaryStage.getScene() != null) {
            scene = primaryStage.getScene();
        }
        
        // Thực hiện chuyển đổi root node
        if (scene != null) {
            Parent root = loadFXML(fxml);
            scene.setRoot(root);
        }
    }
    
    /**
     * Lấy Stage chính của ứng dụng
     * 
     * <p>Phương thức này cung cấp truy cập đến Stage chính từ các lớp khác.
     * Hữu ích khi cần:
     * <ul>
     *   <li>Thay đổi thuộc tính cửa sổ (title, size, etc.)</li>
     *   <li>Đóng cửa sổ</li>
     *   <li>Hiển thị dialog</li>
     * </ul>
     * </p>
     *
     * @return Stage chính của ứng dụng, có thể null nếu chưa được khởi tạo
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Load file FXML và tạo Parent node
     * 
     * <p>Phương thức helper private để load file FXML từ resources.
     * 
     * <p><strong>Quy trình:</strong></p>
     * <ol>
     *   <li>Tạo FXMLLoader mới</li>
     *   <li>Trỏ đến file FXML trong resources (tự động thêm .fxml extension)</li>
     *   <li>Load và parse FXML thành cây node JavaFX</li>
     *   <li>Trả về root node</li>
     * </ol>
     * </p>
     * 
     * <p><strong>Cấu trúc file FXML:</strong></p>
     * <pre>
     *   File: menu.fxml
     *   Location: src/main/resources/com/kthp/tro_choi_caro/menu.fxml
     * </pre>
     *
     * @param fxml Tên file FXML (không có extension)
     * @return Parent node đã được load từ FXML
     * @throws IOException Nếu file không tồn tại hoặc FXML không hợp lệ
     */
    private static Parent loadFXML(String fxml) throws IOException {
        // Tạo FXMLLoader với đường dẫn tới file FXML
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        
        // Load FXML và trả về root node
        return fxmlLoader.load();
    }

    /**
     * Điểm khởi đầu của chương trình Java
     * 
     * <p>Main method được JVM gọi khi chạy ứng dụng.
     * Phương thức {@code launch()} sẽ:
     * <ol>
     *   <li>Khởi tạo JavaFX Runtime</li>
     *   <li>Tạo instance của lớp App</li>
     *   <li>Gọi phương thức {@link #start(Stage)}</li>
     * </ol>
     * </p>
     * 
     * <p><strong>Cách chạy:</strong></p>
     * <pre>
     *   java -jar tro_choi_caro.jar
     *   hoặc
     *   mvn clean javafx:run
     * </pre>
     *
     * @param args Tham số dòng lệnh (không sử dụng trong ứng dụng này)
     */
    public static void main(String[] args) {
        // Launch JavaFX Application
        launch();
    }
}