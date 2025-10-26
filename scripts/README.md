# 🚀 SCRIPTS - TỰ ĐỘNG HÓA DỰ ÁN (ĐÃ TỐI ƯU)# 🚀 SCRIPTS - TRỰ ĐỘNG HÓA DỰ ÁN



## 📁 Thư mục Scripts## 📁 Thư mục Scripts



Thư mục này chứa các script tự động hóa để build và chạy dự án **Trò Chơi Caro**.Thư mục này chứa các script tự động hóa để build và chạy dự án **Trò Chơi Caro**.



 



------



## 📊 Tối ưu Scripts## 📜 Danh sách Scripts



| Trước | Sau | Giảm |### 1️⃣ `run.bat` - Chạy ứng dụng (Khuyến nghị)

|-------|-----|------|

| 10 scripts | 4 scripts | **-60%** |**Mục đích:** Clean, compile và chạy ứng dụng đầy đủ

| Nhiều chức năng trùng | Gộp thành menu | ✅ Dễ dùng |

| Phải nhớ tên file | Chọn option | ✅ User-friendly |**Các bước thực hiện:**

1. ✅ Kiểm tra Maven và Java đã cài đặt

---2. ✅ Clean project (xóa build cũ)

3. ✅ Compile source code

## 📜 Danh sách Scripts (ĐÃ TỐI ƯU)4. ✅ Chạy ứng dụng JavaFX



### 1️⃣ `build.bat` ⭐ (Gộp 3 scripts cũ)**Cách sử dụng:**

```bash

**Thay thế cho:** `build.bat` (cũ), `package.bat`, `build-jar.bat`# Cách 1: Double-click file run.bat



**Mục đích:** Build dự án với 3 options# Cách 2: Chạy từ Command Prompt

cd D:\university\mau-thiet-ke\ket-thuc-hoc-phan\tro_choi_caro\scripts

**Các options:**run.bat

- `[1]` **Compile only** - Nhanh, chỉ compile source code```

- `[2]` **Package JAR** - Build full, tạo JAR file executable

- `[3]` **Clean + Package** - Xóa build cũ + build mới**Khi nào dùng:**

- ✅ Lần đầu chạy dự án

**Cách sử dụng:**- ✅ Sau khi thay đổi code

```bash- ✅ Khi có lỗi compile

cd scripts

build.bat---

# Chọn option 1, 2, hoặc 3

```### 2️⃣ `run-quick.bat` - Chạy nhanh (không clean)



**Khi nào dùng:****Mục đích:** Chạy ứng dụng nhanh chóng mà không compile lại

- Option [1]: Kiểm tra code có compile được không

- Option [2]: Tạo JAR file để chạy hoặc submit**Các bước thực hiện:**

- Option [3]: Build lại từ đầu (khi có lỗi cache)1. ✅ Chạy trực tiếp với build hiện tại



---**Cách sử dụng:**

```bash

### 2️⃣ `run.bat` ⭐ (Gộp 3 scripts cũ)cd D:\university\mau-thiet-ke\ket-thuc-hoc-phan\tro_choi_caro\scripts

run-quick.bat

**Thay thế cho:** `run.bat` (cũ), `run-quick.bat`, `run-jar.bat````



**Mục đích:** Chạy game với 3 options**Khi nào dùng:**

- ✅ Đã build thành công trước đó

**Các options:**- ✅ Không có thay đổi code

- `[1]` **Run từ source** - Clean + compile + run (mvn javafx:run) - An toàn nhất- ✅ Muốn chạy nhanh để test

- `[2]` **Run nhanh** - Không clean, chạy luôn - Nhanh nhất (cho development)

- `[3]` **Run từ JAR** - Chạy file JAR đã build - Như end-user---



**Cách sử dụng:**### 3️⃣ `build.bat` - Build project (không chạy)

```bash

cd scripts**Mục đích:** Chỉ compile code, không chạy ứng dụng

run.bat

# Chọn option 1, 2, hoặc 3**Các bước thực hiện:**

```1. ✅ Clean project

2. ✅ Compile source code

**Khi nào dùng:**3. ✅ Tạo file .class trong target/classes/

- Option [1]: Sau khi thay đổi code, lần đầu chạy

- Option [2]: Development, test nhanh (đã build trước)**Cách sử dụng:**

- Option [3]: Test JAR file, demo cho người khác```bash

cd D:\university\mau-thiet-ke\ket-thuc-hoc-phan\tro_choi_caro\scripts

---build.bat

```

### 3️⃣ `cleanup.bat` (Giữ lại)

**Khi nào dùng:**

**Thay thế cho:** `clean.bat`- ✅ Kiểm tra code có compile được không

- ✅ Prepare cho việc chạy sau

**Mục đích:** Xóa file build + tối ưu dung lượng dự án- ✅ CI/CD build



**Các bước thực hiện:**---

1. ✅ Xóa thư mục target/ (~9.2 MB)

2. ✅ Xóa dependency-reduced-pom.xml### 4️⃣ `package.bat` - Đóng gói JAR file

3. ✅ Xóa temporary files (*.tmp, *.log, *.bak)

4. ✅ Tùy chọn: Xóa Maven cache**Mục đích:** Tạo file JAR có thể phân phối



**Cách sử dụng:****Các bước thực hiện:**

```bash1. ✅ Clean project

cd scripts2. ✅ Compile và đóng gói thành JAR

cleanup.bat3. ✅ Tạo file: `target/tro_choi_caro-1.0-SNAPSHOT.jar`

```

**Cách sử dụng:**

**Khi nào dùng:**```bash

- ✅ Giải phóng dung lượng (giảm ~9.2 MB)cd D:\university\mau-thiet-ke\ket-thuc-hoc-phan\tro_choi_caro\scripts

- ✅ Trước khi commit/push lên Gitpackage.bat

- ✅ Fix lỗi build cache```

- ✅ Chuẩn bị archive project

**Khi nào dùng:**

---- ✅ Muốn tạo file JAR để chia sẻ

- ✅ Deploy ứng dụng

### 4️⃣ `install-maven.bat` ⭐ (Gộp 3 scripts cũ)- ✅ Submit bài tập



**Thay thế cho:** `install-maven.bat` (cũ), `install-maven-user.bat`, `verify-and-build.bat`---



**Mục đích:** Cài Maven tự động (smart installer)### 5️⃣ `clean.bat` - Xóa file build



**Tự động phát hiện:****Mục đích:** Xóa sạch thư mục target/ và các file build

- ✅ Nếu có quyền Admin → Cài system-wide (`C:\Program Files\Apache\maven`)

- ✅ Nếu không → Cài user mode (`%USERPROFILE%\maven`)**Các bước thực hiện:**

1. ✅ Xóa thư mục target/

**Các bước thực hiện:**

1. ✅ Kiểm tra Java**Cách sử dụng:**

2. ✅ Download Maven 3.9.6 (từ Apache servers)```bash

3. ✅ Giải nén và cài đặtcd D:\university\mau-thiet-ke\ket-thuc-hoc-phan\tro_choi_caro\scripts

4. ✅ Cấu hình MAVEN_HOME và PATHclean.bat

5. ✅ Hướng dẫn restart terminal```



**Cách sử dụng:****Khi nào dùng:**

```bash- ✅ Trước khi build từ đầu

cd scripts- ✅ Giải phóng dung lượng

install-maven.bat- ✅ Fix lỗi build cache

# Đợi hoàn tất

# ĐÓNG terminal cũ---

# MỞ terminal MỚI

# Test: mvn -version### 6️⃣ `build-jar.bat` - Build Executable JAR (MỚI) ⭐

```

**Mục đích:** Tạo file JAR độc lập có thể chạy trực tiếp (chứa tất cả dependencies)

**Khi nào dùng:**

- ✅ Lần đầu setup project**Các bước thực hiện:**

- ✅ Maven chưa được cài đặt1. ✅ Clean project (xóa build cũ)

- ✅ Cần update Maven lên version mới2. ✅ Compile source code

3. ✅ Đóng gói tất cả dependencies vào JAR

---4. ✅ Tạo file: `target/caro-game.jar` (executable)



## 🔧 Yêu cầu Hệ thống**Cách sử dụng:**

```bash

### ✅ Cần cài đặt:# Cách 1: Double-click file build-jar.bat



1. **JDK 11 hoặc cao hơn**# Cách 2: Chạy từ Command Prompt

   - Download: https://adoptium.net/cd D:\university\mau-thiet-ke\ket-thuc-hoc-phan\tro_choi_caro\scripts

   - Kiểm tra: `java -version`build-jar.bat

```

2. **Apache Maven 3.6+** (cài bằng `install-maven.bat`)

   - Kiểm tra: `mvn -version`**Output:**

```

---target/

├── caro-game.jar              ← Executable JAR (recommended)

## 📊 Workflow Khuyến nghị└── tro_choi_caro-1.0-SNAPSHOT.jar

```

### 🔷 Workflow 1: Lần đầu tiên

**Khi nào dùng:**

```bash- ✅ Muốn tạo file JAR để chia sẻ cho người khác

# Bước 1: Cài Maven (nếu chưa có)- ✅ Deploy ứng dụng lên server

cd scripts- ✅ Submit bài tập/đồ án

install-maven.bat- ✅ Tạo phiên bản release



# Bước 2: Đóng terminal, mở terminal MỚI**Ưu điểm:**

- 🚀 Chứa tất cả dependencies (JavaFX, etc.)

# Bước 3: Build project- 🚀 Chạy được trên bất kỳ máy nào có Java

build.bat- 🚀 Không cần Maven để chạy

# → Chọn [3] Clean + Package JAR- 🚀 Dễ dàng phân phối



# Bước 4: Chạy game---

run.bat

# → Chọn [3] Run từ JAR### 7️⃣ `run-jar.bat` - Chạy từ JAR file (MỚI) ⭐

```

**Mục đích:** Chạy ứng dụng từ file JAR đã build

---

**Điều kiện:**

### 🔷 Workflow 2: Development (code hằng ngày)- ✅ Đã chạy `build-jar.bat` trước đó

- ✅ File `target/caro-game.jar` tồn tại

```bash

# Sau khi thay đổi code**Cách sử dụng:**

cd scripts```bash

run.bat# Cách 1: Double-click file run-jar.bat

# → Chọn [2] Run nhanh (nhanh nhất)

# Cách 2: Chạy từ Command Prompt

# Hoặc nếu có lỗicd D:\university\mau-thiet-ke\ket-thuc-hoc-phan\tro_choi_caro\scripts

run.batrun-jar.bat

# → Chọn [1] Run từ source (clean + rebuild)

```# Cách 3: Chạy trực tiếp (không cần script)

java -jar target\caro-game.jar

---```



### 🔷 Workflow 3: Build JAR để submit/demo**Khi nào dùng:**

- ✅ Đã build JAR thành công

```bash- ✅ Muốn test JAR file trước khi phân phối

# Bước 1: Clean project- ✅ Demo cho người khác (không cần Maven)

cd scripts

cleanup.bat---



# Bước 2: Build JAR mới## 🔧 Yêu cầu Hệ thống

build.bat

# → Chọn [3] Clean + Package JAR### ✅ Cần cài đặt:



# Bước 3: Test JAR1. **JDK 11 hoặc cao hơn**

run.bat   - Download: https://www.oracle.com/java/technologies/downloads/

# → Chọn [3] Run từ JAR   - Kiểm tra: `java -version`



# Bước 4: Submit/Chia sẻ2. **Apache Maven 3.6+**

# File: target\caro-game.jar (~9.5 MB)   - Download: https://maven.apache.org/download.cgi

```   - Kiểm tra: `mvn -version`



---3. **Biến môi trường PATH**

   - Đảm bảo `java` và `mvn` có trong PATH

### 🔷 Workflow 4: Cleanup (giải phóng dung lượng)   - Cách thêm: System Properties → Environment Variables → PATH



```bash---

# Xóa build output (~9.2 MB)

cd scripts## 📊 Workflow Khuyến nghị

cleanup.bat

### Workflow 1: Phát triển thường ngày

# Rebuild khi cần```

build.bat1. Chỉnh sửa code

# → Chọn [2] Package JAR2. Chạy run.bat để test

```3. Nếu OK → commit code

4. Nếu lỗi → sửa và quay lại bước 2

---```



## ⚠️ Xử lý Lỗi Thường gặp### Workflow 2: Demo/Submit

```

### ❌ Lỗi 1: "Maven không được cài đặt"1. Chạy clean.bat (xóa build cũ)

2. Chạy build.bat (compile mới)

```3. Test với run.bat

[ERROR] Maven khong duoc cai dat!4. Nếu OK → build-jar.bat (tạo executable JAR)

```5. Test JAR: run-jar.bat

6. Submit file: target/caro-game.jar

**Giải pháp:**```

```bash

cd scripts### Workflow 3: Quick test

install-maven.bat```

# Sau đó MỞ TERMINAL MỚI1. Code đã compile trước đó

mvn -version  # Kiểm tra2. Chạy run-quick.bat

```3. Test nhanh tính năng

```

---

### Workflow 4: Phân phối JAR (MỚI) ⭐

### ❌ Lỗi 2: "Java không được cài đặt"```

1. Chạy build-jar.bat

```2. Copy file target/caro-game.jar

[ERROR] Java khong duoc cai dat!3. Gửi cho người dùng

```4. Người dùng chỉ cần: java -jar caro-game.jar

   (Không cần Maven, chỉ cần Java)

**Giải pháp:**```

1. Cài JDK 11+ từ: https://adoptium.net/

2. Restart terminal---

3. Test: `java -version`

## ⚠️ Xử lý Lỗi

---

### Lỗi 1: "Maven không được cài đặt"

### ❌ Lỗi 3: "JAR file không tồn tại"```

[ERROR] Maven khong duoc cai dat hoac khong co trong PATH!

``````

[ERROR] JAR file khong ton tai!

```**Giải pháp:**

1. Cài đặt Maven từ: https://maven.apache.org/download.cgi

**Giải pháp:**2. Thêm Maven vào PATH:

```bash   ```

cd scripts   C:\Program Files\Apache\maven\bin

build.bat   ```

# → Chọn [2] hoặc [3] để tạo JAR3. Restart Command Prompt

```4. Test: `mvn -version`



------



### ❌ Lỗi 4: "mvn -version" không hoạt động sau khi cài Maven### Lỗi 2: "Java không được cài đặt"

```

**Nguyên nhân:** Terminal chưa reload PATH[ERROR] Java khong duoc cai dat hoac khong co trong PATH!

```

**Giải pháp:**

1. **ĐÓNG** terminal hiện tại (QUAN TRỌNG!)**Giải pháp:**

2. **MỞ** terminal MỚI1. Cài đặt JDK 11+

3. Chạy: `mvn -version`2. Thêm Java vào PATH:

4. Nếu vẫn lỗi: Restart máy tính   ```

   C:\Program Files\Java\jdk-11\bin

---   ```

3. Restart Command Prompt

## 📖 Lệnh Maven Thủ công4. Test: `java -version`



Nếu muốn chạy Maven trực tiếp:---



```bash### Lỗi 3: "Compile thất bại"

# Compile only```

mvn compile[ERROR] Compile that bai!

```

# Package JAR

mvn package -DskipTests**Giải pháp:**

1. Kiểm tra syntax errors trong code

# Clean + Package2. Đảm bảo tất cả dependencies trong pom.xml

mvn clean package -DskipTests3. Chạy `clean.bat` rồi `build.bat` lại

4. Xem log chi tiết để tìm lỗi cụ thể

# Run từ source

mvn javafx:run---



# Clean project### Lỗi 4: "Module not found"

mvn clean```

```Error: JavaFX runtime components are missing

```

---

**Giải pháp:**

## 🎯 Checklist Trước khi Chạy1. Kiểm tra pom.xml có dependencies JavaFX

2. Chạy `mvn clean install` để tải dependencies

- [ ] JDK 11+ đã cài: `java -version`3. Đảm bảo JavaFX version 21 được cài đặt

- [ ] Maven đã cài: `mvn -version`

- [ ] Đang ở thư mục `scripts/`---

- [ ] Không có lỗi compile trong code

## 📖 Lệnh Maven Thủ công

---

Nếu muốn chạy Maven trực tiếp (không qua script):

## 📞 Hỗ trợ

```bash

**Nếu gặp vấn đề:**# Clean project

1. Đọc kỹ error messagemvn clean

2. Kiểm tra checklist ở trên

3. Xem file `HUONG_DAN_CAI_DAT.md` (hướng dẫn chi tiết)# Compile

4. Thử clean và build lại: `cleanup.bat` → `build.bat`mvn compile



---# Chạy ứng dụng

mvn javafx:run

## 📈 Lịch sử thay đổi

# Build JAR

### Version 2.0 (27/10/2025) - ĐÃ TỐI ƯUmvn package

- ✅ Gộp 10 scripts → 4 scripts (**-60%**)

- ✅ Thêm menu options (dễ dùng hơn)# Clean + Compile + Run

- ✅ Gộp install-maven thành 1 file (auto-detect admin/user)mvn clean javafx:run

- ✅ Xóa các script trùng lặp chức năng```

- ✅ Cải thiện error handling

- ✅ Thêm checklist và troubleshooting---



### Version 1.0 (26/10/2025)## 🎯 Checklist Trước khi Chạy

- Scripts ban đầu (10 files)

- [ ] JDK 11+ đã cài đặt

---- [ ] Maven đã cài đặt

- [ ] `java -version` hoạt động

**Tác giả:** 2212391 - Nguyễn Hoàng Nam Khánh  - [ ] `mvn -version` hoạt động

 

**Phiên bản:** 2.0 (Tối ưu)- [ ] Không có process Java nào đang chạy


---

## 📞 Hỗ trợ

**Nếu gặp vấn đề:**
1. Đọc kỹ error message trong console
2. Kiểm tra lại yêu cầu hệ thống
3. Thử clean và build lại từ đầu
4. Check Maven dependencies: `mvn dependency:tree`

---

**Tác giả:** 2212391- Nguyễn Hoàng Nam Khánh  
**Phiên bản:** 1.0
