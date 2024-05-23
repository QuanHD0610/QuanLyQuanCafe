use master
go
create database DoAnQuanLyBanCoffee
go
use DoAnQuanLyBanCoffee
go

create table NHANVIEN(
	MaNV int primary key identity(1, 1),
	TenNV nvarchar(50),
	GioiTinh nvarchar(5),
	DiaChi nvarchar(50),
	SoDienThoai nvarchar(11),
	PhanQuyen varchar(10),
	MatKhau varchar(20)
)
create table LOAIKH(
	MaLKH int primary key identity(1, 1),
	TenLKH nvarchar(50)
)
create table LOAIHH(
	MaLH int primary key identity(1, 1),
	TenLH nvarchar(50),
	MoTa nvarchar(50)
)
create table KHACHHANG(
	MaKH int primary key identity(1, 1),
	MaLKH int,
	TenKH nvarchar(50),
	DiaChi nvarchar(50),
	SDT nvarchar(11),
	DiemTichLuy int,
	constraint FK_LoaiKH Foreign key (MALKH) references LOAIKH(MALKH)
)
create table HANGHOA(
	MaHH int primary key identity(1,1),
	MaLH int,
	TenHH nvarchar(50),
	HinhAnh nvarchar(50),
	GiaSP int,
	constraint FK_LoaiHH Foreign key (MaLH) references LOAIHH(MALH)
)
create table BAN(
	MaBan int primary key identity(1,1),
	TenBan nvarchar(50),
	TrangThai nvarchar(50),
	HDHienTai int,
)
Create table HOADON(
	MaHDBH int primary key identity(1, 1),
	MaNV int,
	MaBan int,
	NgayXuatHD datetime,
	TongTien int,
	DiemTL int,
	GiamGia float,
	MaKH int,
	SoTienThanhToan int,
	constraint FK_HoaDon_NV foreign key (MaNV) references NHANVIEN(MaNV),
	constraint FK_HoaDon_Ban foreign key (MaBan) references BAN(MaBan),
	constraint FK_HoaDon_KH foreign key (MaKH) references KHACHHANG(MaKH)
)
create table CHITIETHOADON
(
	MaHDBH int,
	MaHH int,
	SoLuong int,
	ThanhTien int,
	constraint PK_ChiTietHoaDon Primary key (MaHDBH,MaHH),
	constraint FK_CTHD_HD Foreign key (MaHDBH) references HOADON(MaHDBH),
	constraint FK_CTHD_HH Foreign key (MaHH) references HANGHOA(MaHH)
)
--Tạo bảng NGUYENLIEU
create table NGUYENLIEU(
	MaNL int identity(1, 1) primary key,
	TenNL nvarchar(50),
	DonViTinh nvarchar(50),
	SoLuongTon float
)

--Tạo bảng CHITIETNGUYENLIEUHANGHOA
create table CHITIETNLHH(
	MaNL int,
	MaHH int,
	primary key(MaNL, MaHH),
	constraint FK_CHITIETNLHH_NL foreign key(MaNL) references NGUYENLIEU(MaNL),
	constraint FK_CHITIETNLHH_HH foreign key(MaHH) references HANGHOA(MaHH)
)
alter table ChiTietNLHH
add SoLuong float
--Tạo bảng NHAPHANG
create table NHAPHANG(
	MaNH int primary key identity(1,1),
	NgayNhap datetime
)
--Chi tiet nguyen lieu nhap hang
create table CHITIETNLNH(
	MaNL int,
	MaNH int,
	SoLuong float,
	primary key(MaNL, MaNH),
	constraint FK_CHITIETNLNH_NL foreign key(MaNL) references NGUYENLIEU(MaNL),
	constraint FK_CHITIETNLNH_NH foreign key(MaNH) references NHAPHANG(MaNH)
)
go
ALTER TABLE CHITIETHOADON
ADD CONSTRAINT CHK_SoLuong_LonHon0 CHECK (SoLuong >= 1);

ALTER TABLE KHACHHANG
ADD CONSTRAINT UN_SDT UNIQUE (SDT);

ALTER TABLE KHACHHANG
ADD CONSTRAINT DF_DiaChi DEFAULT N'TP Hồ Chí Minh' FOR DiaChi;

go
CREATE TRIGGER TinhThanhTien
ON CHITIETHOADON
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE CHITIETHOADON
    SET ThanhTien = (SELECT SoLuong * HANGHOA.GiaSP
                      FROM inserted
                      INNER JOIN HANGHOA ON inserted.MaHH = HANGHOA.MaHH)
    WHERE MaHDBH IN (SELECT MaHDBH FROM inserted) and MaHH in (select MaHH from inserted);
END;
go
--Triger tính thành tiền cho hóa đơn
CREATE TRIGGER trg_UpdateTongTien_Insert_Update
ON CHITIETHOADON
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    -- Tính lại tổng tiền cho hóa đơn có mã MaHDBH trong bảng CHITIETHOADON
    UPDATE HOADON
    SET TongTien = (
        SELECT SUM(ThanhTien)
        FROM CHITIETHOADON
        WHERE CHITIETHOADON.MaHDBH = HOADON.MaHDBH
    )
    WHERE MaHDBH IN (SELECT DISTINCT MaHDBH FROM INSERTED);
END
go
CREATE TRIGGER trg_UpdateTongTien_Delete
ON CHITIETHOADON
AFTER DELETE
AS
BEGIN
    SET NOCOUNT ON;

    -- Tính lại tổng tiền cho hóa đơn có mã MaHDBH trong bảng CHITIETHOADON
    UPDATE HOADON
    SET TongTien = (
        SELECT SUM(ThanhTien)
        FROM CHITIETHOADON
        WHERE CHITIETHOADON.MaHDBH = HOADON.MaHDBH
    )
    WHERE MaHDBH IN (SELECT DISTINCT MaHDBH FROM DELETED);
END
go
-- THÊM DỮ LIỆU CHO PHẦN LOẠI HÀNG HÓA
--Thêm Loại nước ngọt
INSERT INTO LOAIHH(TenLH, MoTa) VALUES
(N'Nước ngọt đóng chai', N'Đồ uống đóng chai')
--Thêm dữ liệu cho HANGHOA TƯƠNG ỨNG
DECLARE @LastMaLH INT;
SET @LastMaLH = (SELECT IDENT_CURRENT('LOAIHH'));
INSERT INTO HANGHOA (MaLH, TenHH, HinhAnh, GiaSP) VALUES
(@LastMaLH, N'Coca Cola', 'cocacola.jpg', 15000),
(@LastMaLH, N'Pepsi', 'pepsi.jpg', 15000),
(@LastMaLH, N'Sprite', 'sprite.jpg', 15000),
(@LastMaLH, N'Seven Up', '7up.jpg', 15000),
(@LastMaLH, N'Trà xanh không độ', 'traxanhkhongdo.jpg', 15000),
(@LastMaLH, N'Number 1', 'number1.jpg', 15000);
select * from HANGHOA
go
--THÊM DỮ LIỆU CHO PHẦN LOẠI HÀNG HÓA LÀ TRÀ SỮA
INSERT INTO LOAIHH (TenLH,MoTa) VALUES (N'Trà sữa',N'Trà sữa nhà làm');
--Thêm dữ liệu cho hàng hóa là trà sữa
DECLARE @LastMaLH INT;
SET @LastMaLH = (SELECT IDENT_CURRENT('LOAIHH'));
INSERT INTO HANGHOA (MaLH, TenHH, HinhAnh, GiaSP) VALUES 
(@LastMaLH, N'Trà sữa truyền thống', N'tra_sua_truyen_thong.jpg', 30000),
(@LastMaLH, N'Trà sữa trân châu đường đen', N'tra_sua_tran_chau_duong_den.jpg', 35000),
(@LastMaLH, N'Trà sữa matcha', N'tra_sua_matcha.jpg', 32000),
(@LastMaLH, N'Trà sữa hồng trà', N'tra_sua_hong_tra.jpg', 31000),
(@LastMaLH, N'Trà sữa sữa tươi', N'tra_sua_sua_tuoi.jpg', 34000);
go
-- Thêm loại hàng hóa mới vào bảng LOAIHH
INSERT INTO LOAIHH (TenLH,Mota) VALUES (N'Cà phê',N'Cà phê pha chất lượng');
-- Lấy giá trị MaLH cuối cùng vừa được thêm vào
DECLARE @LastMaLH INT;
SET @LastMaLH = (SELECT IDENT_CURRENT('LOAIHH'));
-- Chèn 5 dòng dữ liệu cho bảng HANGHOA với hàng hóa là "Cà phê"
INSERT INTO HANGHOA (MaLH, TenHH, HinhAnh, GiaSP) VALUES 
(@LastMaLH, N'Cà phê đen', N'ca_phe_den.jpg', 20000),
(@LastMaLH, N'Cà phê sữa', N'ca_phe_sua.jpg', 25000),
(@LastMaLH, N'Cà phê đá xay', N'ca_phe_da_xay.jpg', 30000),
(@LastMaLH, N'Cà phê cappuccino', N'ca_phe_cappuccino.jpg', 35000),
(@LastMaLH, N'Cà phê latte', N'ca_phe_latte.jpg', 40000);
go
--Thêm dữ liệu cho bàn
-- Chèn dữ liệu vào bảng BAN
INSERT INTO BAN (TenBan, TrangThai, HDHienTai)
VALUES 
    (N'Bàn 1', 'Trong', NULL),
    (N'Bàn 2', 'Trong', NULL),
    (N'Bàn 3', 'Trong', NULL),
    (N'Bàn 4', 'Trong', NULL),
    (N'Bàn 5', 'Trong', NULL),
    (N'Bàn 6', 'Trong', NULL),
    (N'Bàn 7', 'Trong', NULL),
    (N'Bàn 8', 'Trong', NULL),
    (N'Bàn 9', 'Trong', NULL),
    (N'Bàn 10', 'Trong', NULL),
    (N'Bàn 11', 'Trong', NULL),
    (N'Bàn 12', 'Trong', NULL),
    (N'Bàn 13', 'Trong', NULL),
    (N'Bàn 14', 'Trong', NULL),
    (N'Bàn 15', 'Trong', NULL),
    (N'Bàn 16', 'Trong', NULL),
    (N'Bàn 17', 'Trong', NULL),
    (N'Bàn 18', 'Trong', NULL),
    (N'Bàn 19', 'Trong', NULL),
    (N'Bàn 20', 'Trong', NULL);
update Ban set TrangThai = 'Trong', HDHienTai = NULL;
go
--Thêm LOẠI KHÁCH HÀNG VÀ KHÁCH HÀNG
insert into LOAIKH Values(N'VIP')
insert into KHACHHANG values(1,N'Ngô Thành Quang',N'Tây Thạnh HCM',N'0337449403',0)

go
--Thêm vào bảng nhân viên
select * from NHANVIEN
insert into NHANVIEN values (N'Ngô Thành Quang', N'Nam', N'72/34 Dương Đức Hiền',N'0337449403',N'user',N'12345')
go

--Thêm dữ liệu cho bảng nhân viên
insert into NHANVIEN values(N'Lê Thanh Yên', N'Nam', N'Long An', '0371527364', 'user', '12345');
insert into NHANVIEN values(N'Hoàng Đức Quân', N'Nam', N'Quản Trị', '0522407428', 'user', '12345');
insert into NHANVIEN values(N'Hồng Minh Ngọc', N'Nam', N'An Giang', '0585428055', 'user', '12345');
insert into NHANVIEN values(N'Giáp Xuân Hưởng', N'Nữ', N'Bà Rịa – Vũng Tàu', '0584731277', 'user', '12345');
insert into NHANVIEN values(N'Đoàn Thị Trúc Hà', N'Nữ', N'Bạc Liêu', '0582051407', 'user', '12345');
insert into NHANVIEN values(N'Nguyễn Duy Kiệt', N'Nam', N'Bắc Giang', '0330842361', 'user', '12345');
insert into NHANVIEN values(N'Đỗ Hữu Sơn', N'Nam', N'Bắc Kạn', '0938664428', 'user', '12345');
select * from NHANVIEN

--Thêm dữ liệu vào bảng nhập hàng
insert into NHAPHANG values('2023-01-23');
insert into NHAPHANG values('2024-01-19');
insert into NHAPHANG values('2024-01-20');
insert into NHAPHANG values('2023-09-03');
insert into NHAPHANG values('2023-09-01');
insert into NHAPHANG values('2023-05-06');
insert into NHAPHANG values('2024-01-22');
insert into NHAPHANG values('2023-03-17');
insert into NHAPHANG values('2024-03-09');
insert into NHAPHANG values('2023-07-14');
insert into NHAPHANG values('2024-01-29');
insert into NHAPHANG values('2024-02-11');
insert into NHAPHANG values('2023-06-19');
insert into NHAPHANG values('2023-09-18');
insert into NHAPHANG values('2023-10-08');
select * from nhaphang
select * from LOAIHH

--Thêm dữ liệu bảng nguyên liệu
INSERT INTO NGUYENLIEU(TenNL, DonViTinh, SoLuongTon) VALUES
(N'Lon Coca Cola', N'Lon', 150),
(N'Lon Pepsi', N'Lon', 230),
(N'Lon Sprite', N'Lon', 178),
(N'Lon Seven Up', N'Lon', 252),
(N'Chai Trà xanh không độ', N'Chai', 150),
(N'Chai Number 1', N'Chai', 150);
INSERT INTO NGUYENLIEU(TenNL, DonViTinh, SoLuongTon) VALUES(N'Cafe hạt', N'gam', 900);
INSERT INTO NGUYENLIEU(TenNL, DonViTinh, SoLuongTon) VALUES(N'Sữa đặc', N'mili lít', 11000);
INSERT INTO NGUYENLIEU(TenNL, DonViTinh, SoLuongTon) VALUES(N'Đường', N'gam', 7500);
INSERT INTO NGUYENLIEU(TenNL, DonViTinh, SoLuongTon) VALUES(N'Nước lọc', N'mili lít', 20000);
INSERT INTO NGUYENLIEU(TenNL, DonViTinh, SoLuongTon) VALUES(N'Bột trà xanh', N'miligam', 1000);
INSERT INTO NGUYENLIEU(TenNL, DonViTinh, SoLuongTon) VALUES(N'Trân châu', N'gam', 3000);
INSERT INTO NGUYENLIEU(TenNL, DonViTinh, SoLuongTon) VALUES(N'Sữa tươi', N'mili lít', 15000);
INSERT INTO NGUYENLIEU(TenNL, DonViTinh, SoLuongTon) VALUES(N'Bột sữa', N'gam', 4000);
INSERT INTO NGUYENLIEU(TenNL, DonViTinh, SoLuongTon) VALUES(N'Syrup đường đen', N'mili lít', 2500);
select * from NGUYENLIEU
-----

select * from NGUYENLIEU
select * from HANGHOA
select * from CHITIETNLNH
--DBCC CHECKIDENT ('NGUYENLIEU', RESEED, 6);


select h.MaHH, TenHH, GiaSP, c.MaNL, c.MaHH, SoLuong,TenNL from HANGHOA h, CHITIETNLHH c, NGUYENLIEU n where h.MaHH = c.MaHH and n.MaNL = c.MaNL
select * from nguyenlieu

--Thêm dữ liệu bảng ChiTietNLHH
--Nguyên liệu cho các đồ uống đóng chai
INSERT INTO CHITIETNLHH(MaNL, MaHH, SoLuong) VALUES(1, 1, 1);
INSERT INTO CHITIETNLHH(MaNL, MaHH, SoLuong) VALUES(2, 2, 1);
INSERT INTO CHITIETNLHH(MaNL, MaHH, SoLuong) VALUES(3, 3, 1);
INSERT INTO CHITIETNLHH(MaNL, MaHH, SoLuong) VALUES(4, 4, 1);
INSERT INTO CHITIETNLHH(MaNL, MaHH, SoLuong) VALUES(5, 5, 1);
INSERT INTO CHITIETNLHH(MaNL, MaHH, SoLuong) VALUES(6, 6, 1);
--Nguyên liệu cho trà sữa truyền thống
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(7, 8, 30);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(7, 9, 13);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(7, 10, 200);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(7, 11, 15);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(7, 14, 20);
--Nguyên liệu cho trà sữa trân châu đường đen
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(8, 8, 30);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(8, 9, 13);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(8, 10, 200);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(8, 11, 15);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(8, 14, 20);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(8, 12, 30);
--Nguyên liệu cho trà sữa Matcha
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(9, 8, 30);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(9, 9, 13);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(9, 10, 200);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(9, 11, 75);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(9, 14, 20);
--Nguyên liệu cho trà sữa hồng trà
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(10, 8, 30);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(10, 9, 13);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(10, 10, 200);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(10, 11, 10);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(10, 14, 20);
--Nguyên liệu cho trà sữa sữa tươi
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(11, 8, 30);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(11, 9, 13);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(11, 13, 200);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(11, 11, 75);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(11, 14, 20);

--Nguyên liệu cho cà phê đen
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(12, 7, 3);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(12, 9, 10);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(12, 10, 100);

--Nguyên liệu cho Cà phê sữa
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(13, 7, 3);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(13, 8, 50);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(13, 10, 100);

--Nguyên liệu cho Cà phê đá xay
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(14, 7, 3);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(14, 9, 10);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(14, 10, 100);

--Cà phê cappuccino
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(15, 7, 3);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(15, 8, 100);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(15, 10, 100);

--Cà phê latte
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(16, 7, 3);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(16, 8, 100);
INSERT INTO CHITIETNLHH(MaHH, MaNL, SoLuong) VALUES(16, 10, 100);

SELECT hh.MaHH, hh.TenHH, nl.MaNL, nl.TenNL, nlhh.SoLuong
FROM HANGHOA hh
LEFT JOIN CHITIETNLHH nlhh ON hh.MaHH = nlhh.MaHH
LEFT JOIN NGUYENLIEU nl ON nl.MaNL = nlhh.MaNL
UNION
SELECT hh.MaHH, hh.TenHH, nl.MaNL, nl.TenNL, nlhh.SoLuong
FROM NGUYENLIEU nl
LEFT JOIN CHITIETNLHH nlhh ON nl.MaNL = nlhh.MaNL
LEFT JOIN HANGHOA hh ON hh.MaHH = nlhh.MaHH;


--Thêm chi tiết nguyên liệu nhập hàngg
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(1, 1, 50.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(2, 1, 100.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(3, 2, 75.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(4, 2, 200.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(5, 3, 20.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(6, 3, 30.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(7, 4, 150.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(8, 4, 40.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(9, 5, 25.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(10, 5, 60.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(1, 6, 35.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(2, 6, 90.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(3, 7, 55.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(4, 7, 210.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(5, 8, 22.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(6, 8, 33.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(7, 9, 170.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(8, 9, 45.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(9, 10, 28.0);
INSERT INTO CHITIETNLNH(MaNL, MaNH, SoLuong) VALUES(10, 10, 65.0);
select * from CHITIETNLNH
go
CREATE TRIGGER trg_SetNullOnMinusOne
ON BAN
AFTER UPDATE
AS
BEGIN
    IF UPDATE(HDHienTai)  -- Kiểm tra xem cột HDHienTai đã được cập nhật hay không
    BEGIN
        UPDATE BAN
        SET HDHienTai = NULL
        WHERE HDHienTai = -1;  -- Nếu HDHienTai được cập nhật thành -1, đặt nó thành NULL
    END
END;
