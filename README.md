# Trò chơi Caro

Ứng dụng cờ Caro trên bàn cờ 15 × 15, được xây dựng bằng JavaFX. Người chơi sử dụng quân **X** để đấu với máy (**O**) và giành chiến thắng bằng cách tạo một hàng gồm 5 quân liên tiếp theo chiều ngang, dọc hoặc chéo.

## Tính năng

- Ba mức độ: **Dễ**, **Trung bình** và **Khó**.
- AI thay đổi chiến thuật theo độ khó đã chọn.
- Hoàn tác và làm lại theo từng cặp nước đi của người chơi và AI.
- Làm nổi bật 5 ô tạo thành đường thắng.
- Theo dõi số ván thắng, thua và hòa trong phiên chơi.
- Xem thống kê chi tiết hoặc đặt lại bảng điểm.
- Bắt đầu ván mới và quay lại menu ngay trong màn hình chơi.

## Chạy ứng dụng

Yêu cầu:

- JDK 11 trở lên
- Apache Maven 3.6 trở lên

```powershell
mvn javafx:run
```

Nếu Maven báo cấu hình `JAVA_HOME` chưa hợp lệ, hãy đặt biến này về thư mục JDK đang sử dụng rồi chạy lại lệnh.

> **Lưu ý:** bản JAR hiện có trong `release/` có thể báo thiếu JavaFX runtime trên một số phiên bản JDK. Chạy qua Maven là phương án ổn định được khuyến nghị.

## Video demo

Video minh họa các thao tác chính của ứng dụng: chọn độ khó, đánh với AI, hoàn tác/làm lại, tạo ván mới, xem thống kê, đặt lại điểm và quay về menu.

[Xem video demo](artifacts/caro-game-demo.mp4)

## Biên dịch và đóng gói

```powershell
mvn clean package -DskipTests
```

Sau khi hoàn tất, tệp đóng gói được tạo tại:

```text
target/caro-game.jar
```

Bạn có thể thử khởi chạy tệp vừa tạo bằng lệnh:

```powershell
java -jar target/caro-game.jar
```

Nếu gặp thông báo `JavaFX runtime components are missing`, hãy dùng `mvn javafx:run`.

Trên Windows, thư mục `scripts/` cung cấp thêm các tiện ích:

| Tệp | Công dụng |
| --- | --- |
| `build.bat` | Biên dịch hoặc đóng gói dự án |
| `run.bat` | Chạy từ mã nguồn hoặc tệp JAR |
| `install-maven.bat` | Hỗ trợ cài đặt Maven |
| `cleanup.bat` | Xóa kết quả build và tệp tạm |

## Cách chơi

1. Chọn một trong ba mức độ ở màn hình chính.
2. Nhấn **Bắt đầu chơi**.
3. Chọn một ô trống trên bàn cờ để đặt quân **X**.
4. Chờ AI đặt quân **O**, sau đó tiếp tục lượt của bạn.
5. Người đầu tiên tạo được 5 quân liên tiếp sẽ thắng.

Các nút điều khiển trong màn hình chơi:

- **Ván mới**: xóa bàn cờ hiện tại và bắt đầu lại.
- **Menu**: trở về màn hình chọn độ khó.
- **Hoàn tác**: quay lại một lượt đầy đủ, gồm nước của người chơi và AI.
- **Làm lại**: khôi phục lượt vừa hoàn tác.
- **Thống kê**: hiển thị kết quả chi tiết của phiên chơi.
- **Reset điểm**: đưa toàn bộ số liệu thắng, thua và hòa về 0.

## Công nghệ và kiến trúc

- **Java 11**: phiên bản ngôn ngữ mục tiêu khi biên dịch.
- **JavaFX 21**: giao diện đồ họa.
- **Maven**: quản lý thư viện và quy trình đóng gói.
- **MVC**: tách biệt mô hình, giao diện và bộ điều khiển.
- **Strategy**: chuyển đổi thuật toán AI theo độ khó.
- **Observer**: đồng bộ trạng thái trò chơi với giao diện.
- **Memento**: lưu trạng thái phục vụ hoàn tác và làm lại.
- **Singleton**: quản lý bảng điểm dùng chung trong phiên chạy.

## Cấu trúc dự án

```text
caro-game/
├── src/main/java/                         # Mã nguồn Java
│   └── com/kthp/tro_choi_caro/
│       ├── controller/                    # Điều khiển menu và bàn chơi
│       ├── model/                         # Luật chơi, bàn cờ và lịch sử nước đi
│       ├── strategy/                      # Ba chiến thuật AI
│       ├── view/                          # Giao diện Observer
│       └── App.java                       # Điểm khởi chạy ứng dụng
├── src/main/resources/                    # Giao diện FXML và CSS
├── doc/                                   # Tài liệu phân tích, kiến trúc và UML
├── scripts/                               # Tiện ích dành cho Windows
├── release/caro-game.jar                  # Bản chạy đã đóng gói
├── pom.xml                                # Cấu hình Maven
└── HUONG_DAN_CAI_DAT.md                   # Hướng dẫn cài đặt chi tiết
```

## Tài liệu

- [Tổng quan dự án](doc/01_TONG_QUAN_DU_AN.md)
- [Kiến trúc phần mềm](doc/02_KIEN_TRUC_PHAN_MEM.md)
- [Thuật toán AI](doc/03_THUAT_TOAN_AI.md)
- [Kết luận và đánh giá](doc/04_KET_LUAN_VA_DANH_GIA.md)
- [Sơ đồ lớp chi tiết](doc/05_SO_DO_LOP_CHI_TIET.md)
- [UML Strategy Pattern](doc/06_UML_STRATEGY_PATTERN.md)
- [UML Observer Pattern](doc/07_UML_OBSERVER_PATTERN.md)
- [UML Memento Pattern](doc/08_UML_MEMENTO_PATTERN.md)
- [Hướng dẫn cài đặt đầy đủ](HUONG_DAN_CAI_DAT.md)

## Tác giả

Nguyễn Hoàng Nam Khánh — MSSV 2212391
