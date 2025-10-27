CREATE DATABASE IF NOT EXISTS DB_Java3_FinalTest
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- Sử dụng database
USE DB_Java3_FinalTest;

-- 2. Tạo bảng KHACHHANG
DROP TABLE IF EXISTS KHACHHANG;

CREATE TABLE KHACHHANG (
    Username VARCHAR(50) NOT NULL PRIMARY KEY,
    Password VARCHAR(50) NULL,
    Hoten VARCHAR(50) NULL,
    Gioitinh VARCHAR(10) NULL,
    Email VARCHAR(50) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Thêm dữ liệu mẫu
INSERT INTO KHACHHANG (Username, Password, Hoten, Gioitinh, Email) VALUES
('duyhk6', '123456', 'Huỳnh Khắc Duy', 'Nam', 'duyhk6@fe.edu.vn'),
('thuylt', 'abc123', 'Lê Thị Thúy', 'Nữ', 'thuylt@fe.edu.vn'),
('hienhld', '123abc', 'Huỳnh Lê Dịu Hiền', 'Nữ', 'hienhld@fe.edu.vn');

-- 4. Kiểm tra dữ liệu đã được thêm
SELECT * FROM KHACHHANG;

-- 5. Hiển thị cấu trúc bảng
DESCRIBE KHACHHANG;
