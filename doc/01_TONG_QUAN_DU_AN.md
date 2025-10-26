# 📘 TECHNICAL REPORT: TỔNG QUAN DỰ ÁN

## Thông tin Dự án

**Tên dự án:** Trò chơi Caro (Tic-Tac-Toe 15x15)  
**Phiên bản:** 2.1  
**Ngày bắt đầu:** 01/09/2025  
**Ngày hoàn thành:** 20/10/2025  
**Nhóm thực hiện:** Team KTHP  
**Môn học:** Thiết Kế Phần Mềm  

---

## 1. Giới thiệu Dự án

### 1.1 Bối cảnh

Dự án "Trò chơi Caro" được phát triển nhằm mục đích học tập và nghiên cứu về:
- **Design Patterns** (Các mẫu thiết kế phần mềm)
- **Software Architecture** (Kiến trúc phần mềm - MVC)
- **AI Algorithms** (Thuật toán trí tuệ nhân tạo cho game)
- **Clean Code Principles** (Nguyên tắc code sạch)

Trò chơi Caro (hay Gomoku) là một trò chơi dân gian phổ biến tại Việt Nam, với luật chơi đơn giản nhưng yêu cầu tư duy logic cao. Dự án này hiện đại hóa trò chơi truyền thống bằng cách:
- Xây dựng giao diện đồ họa bằng JavaFX
- Tích hợp AI đối thủ với 3 mức độ khó
- Áp dụng các mẫu thiết kế nâng cao
- Tuân thủ các nguyên tắc SOLID và Clean Code

### 1.2 Mục tiêu Dự án

#### Mục tiêu Chính:
1. **Tạo ra một ứng dụng game hoàn chỉnh** với đầy đủ tính năng gameplay
2. **Áp dụng thành công 3 Design Patterns** quan trọng:
   - Strategy Pattern (cho AI)
   - Observer Pattern (cho UI updates)
   - Memento Pattern (cho Undo/Redo)
3. **Xây dựng kiến trúc MVC rõ ràng** với sự tách biệt Model-View-Controller
4. **Implement AI thông minh** với nhiều mức độ khó khác nhau

#### Mục tiêu Phụ:
- Giao diện thân thiện, dễ sử dụng
- Code sạch, dễ đọc, có chú thích đầy đủ
- Hiệu suất tốt, không lag
- Dễ mở rộng và bảo trì

### 1.3 Phạm vi Dự án

#### Trong phạm vi (In Scope):
✅ Game Caro 15x15 với luật 5 quân liên tiếp  
✅ Chơi đơn (Player vs AI)  
✅ 3 mức độ AI: Dễ, Trung Bình, Khó  
✅ Undo/Redo không giới hạn  
✅ Giao diện JavaFX hiện đại  
✅ Quản lý điểm số  
✅ Highlight đường thắng  

#### Ngoài phạm vi (Out of Scope):
❌ Chế độ multiplayer (2 người chơi)  
❌ Chơi online qua mạng  
❌ Âm thanh và hiệu ứng đặc biệt  
❌ Tích hợp database  
❌ Hệ thống tài khoản người dùng  
❌ Leaderboard toàn cầu  

---

## 2. Đối tượng Người dùng

### 2.1 Đối tượng Chính

| Nhóm | Mô tả | Nhu cầu |
|------|-------|---------|
| **Người chơi thông thường** | Mọi lứa tuổi, yêu thích game Caro | Giải trí, thư giãn |
| **Sinh viên IT** | Nghiên cứu Design Patterns | Học hỏi, tham khảo code |
| **Giáo viên/Giảng viên** | Dạy môn Thiết Kế Phần Mềm | Ví dụ minh họa thực tế |

### 2.2 User Personas

#### Persona 1: "Anh Minh - Người chơi giải trí"
- **Tuổi:** 25  
- **Nghề nghiệp:** Nhân viên văn phòng  
- **Mục đích:** Chơi Caro giải trí giờ nghỉ trưa  
- **Yêu cầu:** Giao diện đơn giản, AI không quá khó  

#### Persona 2: "Em Lan - Sinh viên IT"
- **Tuổi:** 21  
- **Nghề nghiệp:** Sinh viên năm 3 Công nghệ Thông tin  
- **Mục đích:** Nghiên cứu Design Patterns để làm đồ án  
- **Yêu cầu:** Code rõ ràng, có chú thích, dễ hiểu  

#### Persona 3: "Thầy Nam - Giảng viên"
- **Tuổi:** 40  
- **Nghề nghiệp:** Giảng viên môn Thiết Kế Phần Mềm  
- **Mục đích:** Dùng làm case study trong bài giảng  
- **Yêu cầu:** Architecture tốt, follow best practices  

---

## 3. Công nghệ Sử dụng

### 3.1 Ngôn ngữ Lập trình

| Công nghệ | Version | Lý do Chọn |
|-----------|---------|------------|
| **Java** | 11+ | - Hỗ trợ OOP tốt<br>- Phù hợp với Design Patterns<br>- Cộng đồng lớn, tài liệu đầy đủ |

### 3.2 Framework & Libraries

| Công nghệ | Version | Mục đích |
|-----------|---------|----------|
| **JavaFX** | 21 | GUI Framework cho ứng dụng desktop |
| **FXML** | - | Định nghĩa layout UI (XML-based) |
| **CSS** | - | Styling giao diện |

### 3.3 Build Tool

| Công nghệ | Version | Mục đích |
|-----------|---------|----------|
| **Maven** | 3.8+ | Quản lý dependencies và build project |

### 3.4 Development Tools

| Tool | Mục đích |
|------|----------|
| **NetBeans IDE** | IDE chính cho development |
| **Git** | Version control |
| **GitHub** | Source code repository |

### 3.5 Dependency Management

```xml
<!-- pom.xml -->
<dependencies>
    <!-- JavaFX Controls -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21</version>
    </dependency>
    
    <!-- JavaFX FXML -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>21</version>
    </dependency>
</dependencies>
```

---

## 4. Các Tính năng Chính

### 4.1 Tính năng Gameplay

| ID | Tính năng | Mô tả | Độ ưu tiên |
|----|-----------|-------|------------|
| F-01 | **Bàn cờ 15x15** | Bàn cờ chuẩn với 225 ô | ⭐⭐⭐⭐⭐ Cao |
| F-02 | **Luật 5 quân** | Xếp 5 quân liên tiếp để thắng | ⭐⭐⭐⭐⭐ Cao |
| F-03 | **Phát hiện thắng** | Tự động kiểm tra 4 hướng | ⭐⭐⭐⭐⭐ Cao |
| F-04 | **Phát hiện hòa** | Khi bàn cờ đầy mà không ai thắng | ⭐⭐⭐⭐ Trung bình |
| F-05 | **Highlight đường thắng** | Đánh dấu 5 quân thắng cuộc | ⭐⭐⭐ Thấp |

### 4.2 Tính năng AI

| ID | Tính năng | Mô tả | Thuật toán |
|----|-----------|-------|------------|
| F-06 | **AI Dễ** | Chọn ngẫu nhiên ô trống | Random Selection |
| F-07 | **AI Trung Bình** | Đánh giá vị trí chiến lược | Heuristic Evaluation |
| F-08 | **AI Khó** | Tìm nước đi tối ưu | Minimax + Alpha-Beta |

### 4.3 Tính năng UI/UX

| ID | Tính năng | Mô tả | Mục đích |
|----|-----------|-------|----------|
| F-09 | **Menu màn hình** | Chọn độ khó AI | Cấu hình trước khi chơi |
| F-10 | **Màu phân biệt** | X (đỏ), O (xanh) | Dễ nhìn, dễ phân biệt |
| F-11 | **Responsive UI** | Window có thể resize | Tùy chỉnh kích thước |
| F-12 | **Trạng thái game** | Hiển thị lượt, thắng/thua | Thông tin real-time |

### 4.4 Tính năng Nâng cao

| ID | Tính năng | Mô tả | Design Pattern |
|----|-----------|-------|----------------|
| F-13 | **Undo** | Hoàn tác 2 nước (Player + AI) | Memento Pattern |
| F-14 | **Redo** | Làm lại nước đã hoàn tác | Memento Pattern |
| F-15 | **New Game** | Bắt đầu ván mới | - |
| F-16 | **Back to Menu** | Quay về menu chính | - |
| F-17 | **Score Tracking** | Theo dõi thắng/thua/hòa | Singleton Pattern |

---

## 5. Yêu cầu Hệ thống

### 5.1 Yêu cầu Phần cứng

| Thành phần | Tối thiểu | Khuyến nghị |
|------------|-----------|-------------|
| **CPU** | Dual-core 1.5GHz | Quad-core 2.0GHz+ |
| **RAM** | 2GB | 4GB+ |
| **Ổ cứng** | 200MB trống | 500MB trống |
| **Màn hình** | 1024x768 | 1920x1080 |

### 5.2 Yêu cầu Phần mềm

| Thành phần | Version | Ghi chú |
|------------|---------|---------|
| **OS** | Windows 10/11, macOS 10.14+, Linux | Cross-platform |
| **JDK** | 11 hoặc mới hơn | Cần có JavaFX runtime |
| **Maven** | 3.6+ | Để build project |

### 5.3 Yêu cầu Development

| Thành phần | Version | Mục đích |
|------------|---------|----------|
| **Java JDK** | 11+ | Compile và run |
| **Maven** | 3.8+ | Build automation |
| **NetBeans/IntelliJ/Eclipse** | Latest | IDE |
| **Git** | 2.x | Version control |

---

## 6. Ràng buộc và Hạn chế

### 6.1 Ràng buộc Kỹ thuật

1. **Ngôn ngữ:** Phải sử dụng Java (theo yêu cầu môn học)
2. **Framework:** Phải sử dụng JavaFX cho GUI
3. **Design Patterns:** Bắt buộc áp dụng ít nhất 3 patterns
4. **Kiến trúc:** Phải tuân theo MVC pattern

### 6.2 Ràng buộc Thời gian

- **Deadline:** 25/10/2025
- **Thời gian phát triển:** 8 tuần
- **Demo:** Tuần 9

### 6.3 Hạn chế Hiện tại

1. **Không hỗ trợ multiplayer:** Chỉ chơi với AI
2. **Không có âm thanh:** Chưa tích hợp sound effects
3. **Không lưu game:** Chưa có chức năng save/load
4. **Không có animation:** UI đơn giản, không có hiệu ứng động

### 6.4 Khả năng Mở rộng Tương lai

✨ **Có thể thêm:**
- Chế độ 2 người chơi local
- Lưu/tải game
- Animation cho nước đi
- Sound effects
- Thống kê chi tiết (biểu đồ)
- AI thông minh hơn (Deep Learning)

---

## 7. Tiêu chí Thành công

### 7.1 Tiêu chí Kỹ thuật

| Tiêu chí | Trạng thái | Ghi chú |
|----------|------------|---------|
| ✅ Áp dụng đúng 3 Design Patterns | ✔️ Hoàn thành | Strategy, Observer, Memento |
| ✅ Kiến trúc MVC rõ ràng | ✔️ Hoàn thành | Model-View-Controller tách biệt |
| ✅ Code có chú thích đầy đủ | ✔️ Hoàn thành | 100% classes có JavaDoc |
| ✅ Không có compile errors | ✔️ Hoàn thành | 0 errors |
| ✅ AI hoạt động đúng | ✔️ Hoàn thành | 3 mức độ test thành công |

### 7.2 Tiêu chí Chức năng

| Tiêu chí | Trạng thái |
|----------|------------|
| ✅ Chơi game bình thường | ✔️ Pass |
| ✅ AI đánh cờ hợp lệ | ✔️ Pass |
| ✅ Phát hiện thắng/thua/hòa | ✔️ Pass |
| ✅ Undo/Redo hoạt động | ✔️ Pass |
| ✅ UI responsive | ✔️ Pass |

### 7.3 Tiêu chí Người dùng

- ✅ Giao diện dễ hiểu, trực quan
- ✅ Không bị lag khi chơi
- ✅ AI phản hồi nhanh (< 2 giây)
- ✅ Có thể chơi nhiều ván liên tục

---

## 8. Rủi ro và Xử lý

### 8.1 Rủi ro Kỹ thuật

| Rủi ro | Mức độ | Biện pháp |
|--------|--------|-----------|
| AI quá chậm (Hard mode) | Trung bình | Giới hạn depth = 3, search space = 2 |
| Memory leak sau nhiều ván | Thấp | Clear observers khi reset |
| JavaFX runtime issues | Thấp | Dùng Maven JavaFX plugin |

### 8.2 Rủi ro Dự án

| Rủi ro | Mức độ | Biện pháp |
|--------|--------|-----------|
| Không đủ thời gian | Trung bình | Ưu tiên core features |
| Thiếu kinh nghiệm JavaFX | Cao | Học từ docs và tutorials |
| Khó implement Minimax | Trung bình | Tham khảo thuật toán sẵn có |

---

## 9. Tài liệu Tham khảo

### 9.1 Sách

1. **"Design Patterns: Elements of Reusable Object-Oriented Software"**  
   - Tác giả: Gang of Four  
   - Mục đích: Học về Design Patterns

2. **"Clean Code: A Handbook of Agile Software Craftsmanship"**  
   - Tác giả: Robert C. Martin  
   - Mục đích: Viết code sạch

### 9.2 Tài liệu Online

- [Oracle JavaFX Documentation](https://openjfx.io/)
- [Minimax Algorithm - Wikipedia](https://en.wikipedia.org/wiki/Minimax)
- [Maven Getting Started Guide](https://maven.apache.org/guides/)

### 9.3 Tutorials

- [JavaFX Tutorial - Jenkov.com](http://tutorials.jenkov.com/javafx/index.html)
- [Alpha-Beta Pruning - GeeksforGeeks](https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-4-alpha-beta-pruning/)

---

## 10. Kết luận

Dự án "Trò chơi Caro" đã đạt được tất cả mục tiêu đề ra:

✅ **Công nghệ:** Java 11 + JavaFX 21 + Maven  
✅ **Design Patterns:** Strategy, Observer, Memento  
✅ **Kiến trúc:** MVC rõ ràng  
✅ **AI:** 3 mức độ từ dễ đến khó  
✅ **Code Quality:** Clean, có chú thích đầy đủ  
✅ **Tính năng:** Đầy đủ, hoạt động ổn định  

Dự án là một ví dụ điển hình về cách áp dụng các nguyên tắc thiết kế phần mềm vào thực tế, phù hợp cho mục đích học tập và giảng dạy.

---

**Ngày tạo:** 21/10/2025  
**Người viết:** Team KTHP  
**Phiên bản:** 1.0  
