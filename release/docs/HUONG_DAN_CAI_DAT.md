# 📦 HƯỚNG DẪN CÀI ĐẶT VÀ SỬ DỤNG - CARO GAME

**Phiên bản:** 2.0 (Đã tối ưu scripts)  
**Ngày cập nhật:** 27/10/2025  
**Tác giả:** 2212391 - Nguyễn Hoàng Nam Khánh

---

## ⚡ HƯỚNG DẪN NHANH (QUICK START)

### 👤 Cho người dùng (chỉ muốn chơi game):

```cmd
# 1. Cài Java (nếu chưa có)
- Download: https://adoptium.net/
- Cài đặt với option "Add to PATH"

# 2. Chạy game
java -jar caro-game.jar

# Hoặc double-click file caro-game.jar
```

**Thời gian:** 5 phút

---

### 💻 Cho developer (build từ source):

```cmd
# 1. Cài Maven (tự động, thông minh)
cd scripts
install-maven.bat
# Đóng terminal, mở lại

# 2. Build JAR file
build.bat
# → Chọn [3] Clean + Package JAR

# 3. Chạy game
run.bat
# → Chọn [3] Run từ JAR
```

**Thời gian:** 10 phút (bao gồm download dependencies)

**Scripts mới tối ưu:**
- ✅ Menu interface (dễ chọn)
- ✅ Tự động detect admin/user mode
- ✅ Better error handling
- ✅ Giảm 60% số scripts (10 → 4)

---

## 📋 MỤC LỤC CHI TIẾT

1. [Yêu cầu hệ thống](#-yêu-cầu-hệ-thống)
2. [Cài đặt Java](#-cài-đặt-java)
3. [Cài đặt Maven (cho developer)](#-cài-đặt-maven-cho-developer)
4. [Chạy game từ JAR file](#-chạy-game-từ-jar-file-người-dùng)
5. [Build từ source code](#-build-từ-source-code-developer)
6. [Cấu trúc dự án](#-cấu-trúc-dự-án)
7. [Xử lý lỗi thường gặp](#-xử-lý-lỗi-thường-gặp)
8. [FAQ](#-faq---câu-hỏi-thường-gặp)
9. [Tối ưu và bảo trì](#-tối-ưu-và-bảo-trì)

---

## ✅ Yêu cầu hệ thống

### Yêu cầu tối thiểu:
- **Hệ điều hành:** Windows 10/11, macOS 10.14+, Linux
- **RAM:** 2 GB
- **Dung lượng:** 50 MB trống
- **Màn hình:** 1024x768 pixel trở lên

### Phần mềm cần thiết:

#### Người dùng cuối (chạy game):
- ✅ **Java Runtime Environment (JRE) 11 hoặc cao hơn**

#### Developer (build từ source):
- ✅ **Java Development Kit (JDK) 11 hoặc cao hơn**
- ✅ **Apache Maven 3.6+**
- ✅ **Git** (tùy chọn - để clone repository)

---

## ☕ Cài đặt Java

### Cách 1: Eclipse Temurin (KHUYẾN NGHỊ) ⭐

#### Windows:
1. **Download:**
   - Truy cập: https://adoptium.net/
   - Chọn: **Temurin 11 (LTS)** hoặc **Temurin 17 (LTS)**
   - Platform: Windows x64
   - Package Type: JDK (cho developer) hoặc JRE (cho người dùng)

2. **Cài đặt:**
   ```
   - Chạy file .msi vừa tải
   - ✅ Chọn "Set JAVA_HOME variable"
   - ✅ Chọn "Add to PATH"
   - Next → Install → Finish
   ```

3. **Verify:**
   ```cmd
   # Mở Command Prompt mới
   java -version
   
   # Kết quả mong đợi:
   openjdk version "11.0.x" hoặc "17.0.x"
   ```

#### macOS:
```bash
# Dùng Homebrew
brew install openjdk@11

# Verify
java -version
```

#### Linux (Ubuntu/Debian):
```bash
# Update package list
sudo apt update

# Install OpenJDK 11
sudo apt install openjdk-11-jdk

# Verify
java -version
```

### Cách 2: Oracle JDK

1. Download: https://www.oracle.com/java/technologies/downloads/
2. Chọn version 11 hoặc 17
3. Cài đặt và thiết lập JAVA_HOME thủ công

### Kiểm tra Java đã cài đúng chưa:

```cmd
# Windows Command Prompt
java -version
javac -version  # (chỉ với JDK)

# Kiểm tra JAVA_HOME
echo %JAVA_HOME%
```

```bash
# macOS/Linux
java -version
javac -version

# Kiểm tra JAVA_HOME
echo $JAVA_HOME
```

---

## 🔧 Cài đặt Maven (cho Developer)

### Cách 1: Dùng script tự động (Windows) ⭐ KHUYẾN NGHỊ

Script thông minh tự động phát hiện quyền Admin và chọn cách cài phù hợp!

```cmd
# 1. Navigate đến thư mục scripts
cd d:\university\mau-thiet-ke\ket-thuc-hoc-phan\caro-game\scripts

# 2. Chạy script cài Maven (tự động detect admin/user mode)
install-maven.bat

# 3. Đợi cài đặt hoàn tất (1-2 phút)

# 4. QUAN TRỌNG: Đóng Command Prompt và mở lại terminal MỚI

# 5. Verify Maven đã cài thành công
mvn -version
```

**Lưu ý:**
- ✅ Script tự động detect: Nếu có quyền Admin → Cài system-wide
- ✅ Nếu không có Admin → Cài user mode (không cần quyền Admin)
- ✅ Tự động download Maven 3.9.6 từ Apache servers
- ✅ Tự động config MAVEN_HOME và PATH
- ⚠️ Phải mở terminal MỚI sau khi cài (để load PATH mới)

### Cách 2: Cài đặt thủ công

#### Windows:

1. **Download Maven:**
   - URL: https://maven.apache.org/download.cgi
   - File: `apache-maven-3.9.6-bin.zip`

2. **Extract:**
   ```
   - Giải nén vào: C:\Program Files\Apache\maven
   ```

3. **Thiết lập Environment Variables:**
   
   a. Mở System Properties:
   ```
   Win + Pause → Advanced System Settings
   → Environment Variables
   ```
   
   b. Tạo biến MAVEN_HOME:
   ```
   Variable: MAVEN_HOME
   Value: C:\Program Files\Apache\maven
   ```
   
   c. Thêm vào PATH:
   ```
   Tìm biến PATH → Edit
   → New → %MAVEN_HOME%\bin
   → OK
   ```

4. **Verify:**
   ```cmd
   # Đóng Command Prompt cũ
   # Mở Command Prompt mới
   mvn -version
   
   # Kết quả:
   Apache Maven 3.9.6
   Maven home: C:\Program Files\Apache\maven
   Java version: ...
   ```

#### macOS:

```bash
# Dùng Homebrew
brew install maven

# Verify
mvn -version
```

#### Linux (Ubuntu/Debian):

```bash
sudo apt update
sudo apt install maven

# Verify
mvn -version
```

### ⚠️ Lưu ý quan trọng:

**Sau khi cài Maven:**
1. ❌ Maven KHÔNG hoạt động ngay trong terminal hiện tại
2. ✅ Phải **đóng và mở lại** Command Prompt/Terminal
3. ✅ Hoặc dùng lệnh refresh (PowerShell):
   ```powershell
   $env:PATH = [System.Environment]::GetEnvironmentVariable("PATH","User") + ";" + [System.Environment]::GetEnvironmentVariable("PATH","Machine")
   ```

---

## 🎮 Chạy game từ JAR file (Người dùng)

### Cách 1: Double-click (Đơn giản nhất)

1. Navigate đến thư mục:
   ```
   d:\university\mau-thiet-ke\ket-thuc-hoc-phan\caro-game\target
   ```

2. Double-click file:
   ```
   caro-game.jar
   ```

3. Game sẽ tự động khởi động!

**Lưu ý:** Windows phải đã cài Java và liên kết .jar với Java.

### Cách 2: Command Line (Chắc chắn nhất) ⭐

```cmd
# Windows
cd d:\university\mau-thiet-ke\ket-thuc-hoc-phan\caro-game
java -jar target\caro-game.jar
```

```bash
# macOS/Linux
cd /path/to/caro-game
java -jar target/caro-game.jar
```

### Cách 3: Dùng script ⭐

```cmd
# Windows
cd d:\university\mau-thiet-ke\ket-thuc-hoc-phan\caro-game\scripts

# Chạy script với menu
run.bat
# → Chọn option [3] Run từ JAR file
```

**Lợi ích:** Script tự động kiểm tra JAR file có tồn tại không và báo lỗi rõ ràng.

---

## 🏗️ Build từ source code (Developer)

### Bước 1: Clone hoặc Download source code

```bash
# Nếu dùng Git
git clone <repository-url>
cd caro-game

# Hoặc download ZIP và giải nén
```

### Bước 2: Kiểm tra yêu cầu

```cmd
# Kiểm tra Java
java -version
javac -version

# Kiểm tra Maven
mvn -version
```

Nếu chưa có Maven, chạy: `scripts\install-maven.bat`

### Bước 3: Build JAR file

#### Option A: Dùng script (Windows) ⭐ KHUYẾN NGHỊ

```cmd
cd scripts

# Chạy script build với menu
build.bat
```

**Menu options:**
- `[1]` Compile only - Nhanh, chỉ compile source code
- `[2]` Package JAR - Build full, tạo executable JAR
- `[3]` Clean + Package JAR - Xóa build cũ + build mới (an toàn nhất)

**Khuyến nghị:** Chọn `[3]` cho lần build đầu tiên hoặc khi có lỗi.

#### Option B: Maven thủ công

```cmd
# Navigate đến project root
cd d:\university\mau-thiet-ke\ket-thuc-hoc-phan\caro-game

# Clean + Build
mvn clean package

# Hoặc skip tests (nhanh hơn)
mvn clean package -DskipTests

# Build offline (nếu đã tải dependencies)
mvn clean package -DskipTests -o
```

### Bước 4: Kiểm tra kết quả

```cmd
# File JAR sẽ được tạo tại:
dir target\caro-game.jar

# Kích thước: ~9-10 MB
# Chứa: Source code + JavaFX + tất cả dependencies
```

### Bước 5: Chạy thử

```cmd
java -jar target\caro-game.jar
```

---

## 📁 Cấu trúc dự án

```
caro-game/
│
├── src/                              # Source code
│   └── main/
│       ├── java/                     # Java files
│       │   ├── module-info.java
│       │   └── com/kthp/tro_choi_caro/
│       │       ├── App.java          # Entry point
│       │       ├── controller/       # MVC Controllers
│       │       ├── model/            # Business logic
│       │       ├── strategy/         # AI algorithms
│       │       └── view/             # Observer interface
│       └── resources/                # FXML, CSS
│           └── com/kthp/tro_choi_caro/
│               ├── game.fxml
│               ├── menu.fxml
│               └── css/
│
├── target/                           # Build output
│   ├── caro-game.jar                # ⭐ Executable JAR
│   └── classes/                      # Compiled classes
│
├── doc/                              # Documentation
│   ├── 01_TONG_QUAN_DU_AN.md
│   ├── 02_KIEN_TRUC_PHAN_MEM.md
│   ├── 03_THUAT_TOAN_AI.md
│   └── ...
│
├── scripts/                          # Build scripts (ĐÃ TỐI ƯU)
│   ├── build.bat                    # ⭐ Build menu (3 options)
│   ├── run.bat                      # ⭐ Run menu (3 options)
│   ├── cleanup.bat                  # Clean + optimize
│   ├── install-maven.bat            # ⭐ Smart Maven installer
│   └── README.md                    # Scripts documentation
│
├── pom.xml                          # Maven configuration
├── HUONG_DAN_CAI_DAT.md            # ⭐ File này
└── README.md                        # Project overview

```

### Dung lượng dự án:

| Thành phần                      | Dung lượng |
|---------------------------------|------------|
| Source code (src/)              | ~100 KB    |
| Documentation (doc/)            | ~50 KB     |
| JAR file (target/caro-game.jar) | ~9.5 MB    |
| Build cache (target/classes)    | ~50 KB     |
|----------------------------------------------|
| **Tổng cộng**                   | **~10 MB** |

**Lưu ý:** Thư mục `target/` có thể xóa và build lại bất cứ lúc nào.

---

## 🐛 Xử lý lỗi thường gặp

### ❌ Lỗi 1: "java không được nhận dạng"

**Triệu chứng:**
```
'java' is not recognized as an internal or external command
```

**Nguyên nhân:** Java chưa cài hoặc chưa có trong PATH

**Giải pháp:**

1. **Kiểm tra Java đã cài chưa:**
   ```cmd
   # Tìm trong Program Files
   dir "C:\Program Files\Java"
   dir "C:\Program Files\Eclipse Adoptium"
   ```

2. **Nếu chưa cài:**
   - Cài Java theo hướng dẫn ở trên
   - Chọn option "Add to PATH" khi cài

3. **Nếu đã cài nhưng không hoạt động:**
   
   a. Thêm vào PATH thủ công:
   ```
   Win + Pause → Advanced System Settings
   → Environment Variables
   → System variables → PATH → Edit
   → New → C:\Program Files\Java\jdk-11\bin
   → OK
   ```
   
   b. Restart Command Prompt
   
   c. Verify:
   ```cmd
   java -version
   ```

---

### ❌ Lỗi 2: "mvn không được nhận dạng"

**Triệu chứng:**
```
'mvn' is not recognized as an internal or external command
```

**Nguyên nhân:** Maven chưa cài hoặc PATH chưa refresh

**Giải pháp:**

1. **Kiểm tra Maven đã cài chưa:**
   ```cmd
   # Windows
   dir "%USERPROFILE%\maven"
   dir "C:\Program Files\Apache\maven"
   ```

2. **Nếu đã cài nhưng không hoạt động:**
   
   **Option A: Đóng và mở lại terminal** (Dễ nhất)
   - Đóng Command Prompt hiện tại
   - Mở Command Prompt mới
   - Test: `mvn -version`
   
   **Option B: Refresh PATH trong PowerShell**
   ```powershell
   $env:PATH = [System.Environment]::GetEnvironmentVariable("PATH","User") + ";" + [System.Environment]::GetEnvironmentVariable("PATH","Machine")
   mvn -version
   ```
   
   **Option C: Set PATH tạm thời**
   ```cmd
   set PATH=%PATH%;%USERPROFILE%\maven\bin
   mvn -version
   ```

3. **Nếu chưa cài:**
   - Chạy script: `scripts\install-maven.bat` (tự động detect admin/user)
   - Hoặc cài thủ công theo hướng dẫn ở trên
   - Nhớ mở terminal MỚI sau khi cài

---

### ❌ Lỗi 3: "BUILD FAILURE" khi chạy mvn

**Triệu chứng:**
```
[ERROR] Failed to execute goal ...
[INFO] BUILD FAILURE
```

**Nguyên nhân:** Có thể do nhiều lý do

**Giải pháp:**

1. **Dùng script build (tự động xử lý):**
   ```cmd
   cd scripts
   build.bat
   # Chọn [3] Clean + Package JAR
   ```

2. **Clean và build lại thủ công:**
   ```cmd
   mvn clean
   mvn package -DskipTests
   ```

3. **Xóa cache Maven:**
   ```cmd
   # Windows - Dùng script cleanup
   cd scripts
   cleanup.bat
   # Chọn YES để xóa Maven cache
   
   # Hoặc thủ công
   rmdir /s /q "%USERPROFILE%\.m2\repository"
   mvn clean install
   ```

4. **Kiểm tra lỗi cụ thể:**
   ```cmd
   # Build với log chi tiết
   mvn clean package -X > build.log 2>&1
   notepad build.log
   ```

4. **Các nguyên nhân thường gặp:**
   - Internet connection issue → Check network
   - Syntax error trong code → Fix code
   - Wrong Java version → Cài Java 11+
   - Corrupted dependencies → Xóa .m2/repository

---

### ❌ Lỗi 4: "Error: JavaFX runtime components are missing"

**Triệu chứng:**
```
Error: JavaFX runtime components are missing, and are required to run this application
```

**Nguyên nhân:** JAR không chứa JavaFX hoặc dùng sai JAR file

**Giải pháp:**

1. **Đảm bảo dùng đúng JAR:**
   ```cmd
   # ✅ ĐÚNG - File này chứa tất cả dependencies
   java -jar target\caro-game.jar
   
   # ❌ SAI - File này không chứa dependencies
   java -jar target\tro_choi_caro-1.0-SNAPSHOT.jar
   ```

2. **Kiểm tra pom.xml có maven-shade-plugin:**
   ```xml
   <plugin>
       <groupId>org.apache.maven.plugins</groupId>
       <artifactId>maven-shade-plugin</artifactId>
       <version>3.5.1</version>
       ...
   </plugin>
   ```

3. **Build lại:**
   ```cmd
   mvn clean package -DskipTests
   ```

4. **Verify JAR size:**
   ```cmd
   # File phải ~9-10 MB (chứa JavaFX)
   dir target\caro-game.jar
   ```

---

### ❌ Lỗi 5: "Module ... not found"

**Triệu chứng:**
```
Error occurred during initialization of boot layer
java.lang.module.FindException: Module ... not found
```

**Nguyên nhân:** Module path issues

**Giải pháp:**

1. **Dùng JAR đã build bằng maven-shade-plugin:**
   ```cmd
   java -jar target\caro-game.jar
   ```

2. **Không dùng module path:**
   ```cmd
   # ❌ SAI
   java --module-path ... -m com.kthp.tro_choi_caro/com.kthp.tro_choi_caro.App
   
   # ✅ ĐÚNG
   java -jar target\caro-game.jar
   ```

---

### ❌ Lỗi 6: "OutOfMemoryError" khi build

**Triệu chứng:**
```
java.lang.OutOfMemoryError: Java heap space
```

**Nguyên nhân:** Maven không đủ memory

**Giải pháp:**

```cmd
# Windows
set MAVEN_OPTS=-Xmx1024m
mvn clean package -DskipTests

# macOS/Linux
export MAVEN_OPTS="-Xmx1024m"
mvn clean package -DskipTests
```

---

### ❌ Lỗi 7: Game không hiển thị (chạy nhưng không thấy window)

**Triệu chứng:** Console không báo lỗi nhưng không thấy cửa sổ game

**Giải pháp:**

1. **Kiểm tra Java version:**
   ```cmd
   java -version
   # Phải >= Java 11
   ```

2. **Chạy với verbose logging:**
   ```cmd
   java -jar target\caro-game.jar --verbose
   ```

3. **Kiểm tra Graphics driver:**
   - Update graphics driver
   - Restart máy

4. **Thử chạy với software rendering:**
   ```cmd
   java -Dprism.order=sw -jar target\caro-game.jar
   ```

---

### ❌ Lỗi 8: "Access Denied" khi cài Maven

**Triệu chứng:**
```
Access is denied
```

**Nguyên nhân:** Không có quyền Administrator

**Giải pháp:**

**Option A: Dùng script user mode** (Không cần Admin)
```cmd
scripts\install-maven-user.bat
# Cài vào: C:\Users\<username>\maven
```

**Option B: Run as Administrator**
```
Right-click install-maven.bat
→ Run as administrator
```

---

### ❌ Lỗi 9: "Port already in use" (cho các dự án server)

**Lưu ý:** Caro Game không dùng port, nhưng để phòng trường hợp mở rộng:

**Triệu chứng:**
```
Address already in use: bind
```

**Giải pháp:**

```cmd
# Windows - Tìm process đang dùng port 8080
netstat -ano | findstr :8080

# Kill process
taskkill /PID <process_id> /F
```

---

## ❓ FAQ - Câu hỏi thường gặp

### 1. Tôi cần cài cả JDK hay chỉ JRE?

**Trả lời:**
- **Người dùng cuối** (chỉ chạy game): JRE là đủ
- **Developer** (build từ source): Cần JDK

### 2. Tôi nên dùng Java version nào?

**Trả lời:**
- **Khuyến nghị:** Java 11 LTS hoặc Java 17 LTS
- **Tối thiểu:** Java 11
- **Tương thích:** Java 11, 17, 21
- **Không hỗ trợ:** Java 8 trở xuống

### 3. Tại sao JAR file lại nặng ~10 MB?

**Trả lời:**
JAR chứa:
- Source code (~100 KB)
- JavaFX runtime (~8 MB)
- Dependencies khác (~1 MB)

Đây là **fat JAR** (uber JAR) - chứa tất cả để chạy độc lập.

### 4. Có thể chạy trên macOS/Linux không?

**Trả lời:**
✅ **Có!** Chỉ cần:
1. Cài Java 11+ cho macOS/Linux
2. Chạy: `java -jar caro-game.jar`

### 5. Làm sao để phân phối game cho người khác?

**Trả lời:**

**Cách 1: Chỉ gửi JAR file**
1. Copy file `target\caro-game.jar`
2. Gửi cho người dùng (email, USB, Google Drive)
3. Họ chỉ cần cài Java và chạy

**Cách 2: Tạo installer (nâng cao)**
```cmd
# Dùng jpackage (Java 14+)
jpackage --input target --name CaroGame --main-jar caro-game.jar --type exe
```

### 6. Có thể xóa thư mục target không?

**Trả lời:**
✅ **Có!** Thư mục `target/` chỉ chứa build output.
- Xóa để tiết kiệm dung lượng
- Build lại khi cần: `mvn clean package`

### 7. Làm sao để update game?

**Trả lời:**
1. Download source code mới
2. Build lại: `mvn clean package`
3. Chạy JAR mới: `java -jar target\caro-game.jar`

### 8. Game có cần Internet không?

**Trả lời:**
❌ **Không!** Game chạy hoàn toàn offline sau khi build.

(Maven cần Internet để download dependencies lần đầu build)

---

## 🔧 Tối ưu và bảo trì

### Giảm dung lượng dự án:

#### 1. Xóa build cache:
```cmd
# Xóa target/ (build output)
mvn clean

# Hoặc
rmdir /s /q target
```

#### 2. Xóa Maven cache (nếu cần):
```cmd
# Dùng script cleanup (có options)
cd scripts
cleanup.bat
# Chọn YES khi được hỏi về Maven cache

# Hoặc thủ công
rmdir /s /q "%USERPROFILE%\.m2\repository"
# Lưu ý: Sẽ phải download lại dependencies khi build
```

#### 3. Gitignore (cho version control):
```
target/
.idea/
.vscode/
*.class
*.jar  # Trừ JAR release
dependency-reduced-pom.xml
```

### Tối ưu performance:

#### 1. Build nhanh hơn:
```cmd
# Dùng script với menu
cd scripts
build.bat
# Chọn [1] Compile only - Nhanh nhất cho development
# Chọn [2] Package JAR - Khi cần JAR file

# Hoặc Maven thủ công
# Skip tests
mvn package -DskipTests

# Offline mode (sau lần build đầu)
mvn package -DskipTests -o

# Parallel build (multi-core)
mvn -T 4 package -DskipTests
```

#### 2. Giảm JAR size (nâng cao):
```xml
<!-- pom.xml - Loại bỏ modules không dùng -->
<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>21</version>
</dependency>
<!-- Chỉ include modules cần thiết -->
```

### Bảo trì định kỳ:

#### 1. Update dependencies:
```cmd
# Kiểm tra dependencies cũ
mvn versions:display-dependency-updates

# Update versions trong pom.xml
# Build và test lại
mvn clean package
```

#### 2. Check vulnerabilities:
```cmd
# Scan security issues
mvn dependency:tree

# Hoặc dùng online tools
# https://snyk.io/
```

---

## 📞 Hỗ trợ

### Gặp vấn đề không được đề cập?

1. **Kiểm tra logs:**
   ```cmd
   # Build với debug mode
   mvn clean package -X > build-debug.log 2>&1
   ```

2. **Check documentation:**
   - README.md - Project overview
   - HUONG_DAN_CAI_DAT.md - File này
   - TOI_UU_SCRIPTS.md - Scripts optimization
   - scripts/README.md - Scripts guide
   - doc/ folder - Technical docs

---

## 📚 Tài liệu tham khảo

- **Java Documentation:** https://docs.oracle.com/en/java/
- **JavaFX Documentation:** https://openjfx.io/
- **Maven Guide:** https://maven.apache.org/guides/
- **Project Documentation:** Xem folder `doc/`

---

## ✅ Checklist cài đặt thành công

### Cho người dùng cuối (chạy game):
- [ ] Java 11+ đã cài đặt (`java -version`)
- [ ] Download file `caro-game.jar`
- [ ] Chạy được: `java -jar caro-game.jar`
- [ ] Game khởi động thành công

### Cho developer (build từ source):
- [ ] Java 11+ đã cài đặt (`java -version`)
- [ ] JDK đã cài đặt (`javac -version`)
- [ ] Maven đã cài đặt (`mvn -version`)
- [ ] Clone/download source code thành công
- [ ] Build thành công: 
  - [ ] Dùng script: `scripts\build.bat` → Chọn [3]
  - [ ] Hoặc Maven: `mvn clean package -DskipTests`
- [ ] File `target\caro-game.jar` được tạo (~9.5 MB)
- [ ] Chạy được: `java -jar target\caro-game.jar`
- [ ] Game khởi động thành công

### Scripts hoạt động:
- [ ] `build.bat` - Hiển thị menu 3 options
- [ ] `run.bat` - Hiển thị menu 3 options
- [ ] `cleanup.bat` - Xóa được target/
- [ ] `install-maven.bat` - Cài Maven thành công (nếu cần)
- [ ] Build thành công (`BUILD SUCCESS`)
- [ ] JAR file tồn tại (`target\caro-game.jar`)
- [ ] Game chạy được (`java -jar target\caro-game.jar`)
- [ ] Menu hiển thị đúng
- [ ] Có thể chơi game (Easy/Medium/Hard AI)
- [ ] Undo/Redo hoạt động
- [ ] Score được tính đúng

---

## 🎯 Kết luận

**Cài đặt Caro Game rất đơn giản:**

### Người dùng cuối (chỉ muốn chơi):
1. Cài Java 11+ từ https://adoptium.net/
2. Download file `caro-game.jar` (~9.5 MB)
3. Double-click hoặc: `java -jar caro-game.jar`
4. Enjoy! 🎮

### Developer (build từ source):
1. Cài Java 11+ (JDK)
2. Cài Maven: Chạy `scripts\install-maven.bat` (tự động)
3. Build: Chạy `scripts\build.bat` → Chọn [3]
4. Run: Chạy `scripts\run.bat` → Chọn [3]
5. Done! 🚀

**Scripts mới (tối ưu):**
- ✅ `build.bat` - Menu 3 options (compile/package/clean+package)
- ✅ `run.bat` - Menu 3 options (source/quick/jar)
- ✅ `cleanup.bat` - Clean & optimize
- ✅ `install-maven.bat` - Smart installer (auto admin/user)

**Lợi ích:** Dễ dùng hơn, menu rõ ràng, tự động hóa tốt hơn!

---

**Tác giả:** 2212391 - Nguyễn Hoàng Nam Khánh  
**Phiên bản tài liệu:** 2.0 (Đã cập nhật scripts mới)  
**Ngày cập nhật:** 27/10/2025  
**Status:** ✅ Hoàn thành

---

## 📖 Tài liệu liên quan

- **README.md** - Project overview
- **scripts/README.md** - Scripts usage guide
- **TOI_UU_SCRIPTS.md** - Scripts optimization details
- **TOM_TAT_TOI_UU.md** - Overall optimization summary
- **CHANGELOG.md** - Version history
- **doc/** - Technical documentation
3. Chơi game! 🎮

### Developer:
1. Cài Java 11+ (JDK)
2. Cài Maven
3. Clone source
4. `mvn clean package`
5. Done! ✅

---

**Chúc bạn chơi game vui vẻ! 🎉**

---

**Phiên bản tài liệu:** 1.0  
**Ngày cập nhật:** 27/10/2025  
**Tác giả:** 2212391 - Nguyễn Hoàng Nam Khánh
