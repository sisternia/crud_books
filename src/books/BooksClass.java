/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package books;


/**
 *
 * @author PC
 */
public class BooksClass {
    private String MaSach,TenSach,TheLoai,PhienBan,LoaiSach,NgayPhatHanh,LuongInAn,DonGia,NXB;
    private float Tap;
    private byte[] HinhAnh;

    public BooksClass() {
    }

    public BooksClass(String MaSach, String TenSach, String TheLoai, String PhienBan, String LoaiSach, String NgayPhatHanh, String LuongInAn, String DonGia, String NXB, float Tap, byte[] HinhAnh) {
        this.MaSach = MaSach;
        this.TenSach = TenSach;
        this.TheLoai = TheLoai;
        this.PhienBan = PhienBan;
        this.LoaiSach = LoaiSach;
        this.NgayPhatHanh = NgayPhatHanh;
        this.LuongInAn = LuongInAn;
        this.DonGia = DonGia;
        this.NXB = NXB;
        this.Tap = Tap;
        this.HinhAnh = HinhAnh;
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String MaSach) {
        this.MaSach = MaSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String TenSach) {
        this.TenSach = TenSach;
    }

    public String getTheLoai() {
        return TheLoai;
    }

    public void setTheLoai(String TheLoai) {
        this.TheLoai = TheLoai;
    }

    public String getPhienBan() {
        return PhienBan;
    }

    public void setPhienBan(String PhienBan) {
        this.PhienBan = PhienBan;
    }

    public String getLoaiSach() {
        return LoaiSach;
    }

    public void setLoaiSach(String LoaiSach) {
        this.LoaiSach = LoaiSach;
    }

    public String getNgayPhatHanh() {
        return NgayPhatHanh;
    }

    public void setNgayPhatHanh(String NgayPhatHanh) {
        this.NgayPhatHanh = NgayPhatHanh;
    }

    public String getLuongInAn() {
        return LuongInAn;
    }

    public void setLuongInAn(String LuongInAn) {
        this.LuongInAn = LuongInAn;
    }

    public String getDonGia() {
        return DonGia;
    }

    public void setDonGia(String DonGia) {
        this.DonGia = DonGia;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public float getTap() {
        return Tap;
    }

    public void setTap(float Tap) {
        this.Tap = Tap;
    }

    public byte[] getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(byte[] HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    @Override
    public String toString() {
        return "KimDongClass{" + "MaSach=" + MaSach + ", TenSach=" + TenSach + ", TheLoai=" + TheLoai + 
                ", PhienBan=" + PhienBan + ", LoaiSach=" + LoaiSach + ", NgayPhatHanh=" + NgayPhatHanh + ", LuongInAn=" + LuongInAn + 
                ", DonGia=" + DonGia + ", NXB=" + NXB + ", Tap=" + Tap + ", HinhAnh=" + HinhAnh + '}';
    }
}