# 📖 Note Basement System - Online Diary Platform

Hệ thống nhật ký trực tuyến (Online Diary) được xây dựng dựa trên kiến trúc **Microservices**. Hệ thống cho phép người
dùng lưu trữ kỷ niệm, quản lý lịch làm việc và chia sẻ bài viết với bạn bè thông qua cơ chế phân quyền bảo mật 4 cấp độ.

## 🚀 Kiến trúc Hệ thống (Architecture)

Dự án được chia thành các service nhỏ, giao tiếp với nhau thông qua REST API và OpenFeign:

| Service Name         | Port   | Nhiệm vụ chính (Responsibility)                                           | Database      |
|----------------------|--------|---------------------------------------------------------------------------|---------------|
| **Discovery Server** | `8761` | Service Registry (Eureka Server) - Quản lý định danh các service.         | N/A           |
| **API Gateway**      | `8080` | Cổng vào duy nhất, điều hướng request, xác thực cơ bản.                   | N/A           |
| **Identity Service** | `8081` | Đăng ký, Đăng nhập (JWT), Quản lý Profile, Quan hệ bạn bè (Friendship).   | `identity_db` |
| **Note Service**     | `8082` | Quản lý bài viết (Entry), Danh mục (Category), Bình luận, Phân quyền xem. | `note_db`     |
| **Schedule Service** | `8083` | *(Coming soon)* Quản lý lịch làm việc cá nhân.                            | `schedule_db` |

---

## 🛠 Công nghệ sử dụng (Tech Stack)

* **Core:** Java 21, Spring Boot 3.x
* **Database:** MySQL 8.0 (Docker Container)
* **ORM:** Spring Data JPA, Hibernate
* **Authentication:** Spring Security, JWT (Nimbus JOSE + JWT)
* **Inter-communication:** Spring Cloud OpenFeign
* **Service Discovery:** Netflix Eureka
* **Routing:** Spring Cloud Gateway
* **Build Tool:** Maven (Multi-module)
* **Infrastructure:** Docker, Docker Compose

---

## 🔐 Tính năng nổi bật

### 1. Phân quyền Nhật ký (Privacy Levels)

Hệ thống hỗ trợ 4 mức độ hiển thị bài viết phức tạp:

* **Private:** Chỉ mình tôi (Owner).
* **Protected 1 (Whitelist):** Chỉ một số bạn bè cụ thể được chỉ định.
* **Protected 2 (Friends):** Tất cả bạn bè (đã Accept kết bạn) đều xem được.
* **Public:** Công khai cho tất cả mọi người.

### 2. Quan hệ xã hội

* Gửi lời mời kết bạn (Friend Request).
* Chấp nhận/Từ chối lời mời.
* Kiểm tra quan hệ bạn bè giữa các Service (Cross-service check).

---

## ⚙️ Cài đặt & Chạy dự án (Installation)

### 1. Yêu cầu (Prerequisites)

* Java JDK 21 trở lên.
* Maven 3.8+.
* Docker & Docker Compose.
* IntelliJ IDEA (Recommended).

### 2. Khởi tạo Database & Hạ tầng

Sử dụng Docker Compose để dựng MySQL server (tránh xung đột version trên máy thật).

```bash
# Tại thư mục gốc của dự án
docker-compose up -d

```

* **MySQL Port:** `3307` (Map ra máy host)
* **Username/Password:** `root` / `root`

### 3. Cài đặt thư viện chung (Quan trọng)

Trước khi chạy các service, bạn **BẮT BUỘC** phải build module `common-library` để tạo file `.jar` cho các service khác
sử dụng.

```bash
cd common-library
mvn clean install
cd ..

```

### 4. Khởi chạy các Service

Thứ tự khởi chạy khuyến nghị để tránh lỗi connection:

1. 🟢 **Discovery Server** (Chờ khoảng 30s để khởi động xong).
2. 🟢 **Identity Service** & **Note Service** (Chạy song song).
3. 🟢 **API Gateway**.

---

## 📂 Cấu trúc thư mục (Project Structure)

```text
note-basement-system/
├── common-library/       # Chứa DTO, Exception, Utils dùng chung
├── discovery-server/     # Eureka Server
├── api-gateway/          # Spring Cloud Gateway
├── identity-service/     # Logic User & Auth
├── note-service/         # Logic Diary & Comment
├── docker-compose.yaml   # Cấu hình Docker
└── pom.xml               # Root BOM (Quản lý version Spring Cloud)

```

---

## ⚠️ Khắc phục lỗi thường gặp (Troubleshooting)

**1. Lỗi "Invalid MySQL server downgrade"**

* *Nguyên nhân:* Do chạy Docker image `mysql:latest` (v9.x) sau đó đổi về `mysql:8.0`.
* *Khắc phục:*

1. `docker-compose down`
2. Xóa volume docker cũ: `docker volume rm note-basement-system_mysql_data_container`
3. `docker-compose up -d`

**2. Lỗi `ClassNotFoundException` liên quan đến DTO/Exception**

* *Khắc phục:* Quên chạy `mvn install` ở `common-library`. Hãy chạy lại lệnh này.

**3. Lỗi Feign Client "Load balancer does not have available server"**

* *Khắc phục:* Service `identity-service` chưa đăng ký thành công lên Eureka. Hãy kiểm tra http://localhost:8761 xem đã
  hiện đủ service chưa.

---

## 🤝 Đóng góp (Contribution)

Dự án được phát triển bởi **[Tên Bạn]**.
Mọi đóng góp vui lòng tạo Pull Request hoặc Open Issue.

---

*Happy Coding! 🚀*