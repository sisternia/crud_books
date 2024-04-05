Create Datebase SQLBooks
Use SQLBooks

Create Table Account(
TenDangNhap varchar(20) not null,
MatKhau varchar(20) not null,
ConfirmMatKhau varchar(20) not null,
Constraint PK_Account Primary Key (TenDangNhap))

Create Table Books(
MaSach varchar(20) not null,
TenSach nvarchar(100) not null,
Tap float not null,
TheLoai nvarchar(30) not null,
PhienBan nvarchar(50) not null,
LoaiSach nvarchar(40) not null,
NgayPhatHanh date not null,
LuongInAn varchar(20) not null,
DonGia varchar(20) not null,
NXB nvarchar(20) not null,
HinhAnh image,
Constraint PK_Books Primary Key (MaSach))
Drop Table Books




