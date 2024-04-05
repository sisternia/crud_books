/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package booksdao;

import books.BooksClass;
import connect.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;


/**
 *
 * @author PC
 */
public class BooksDAO {
    public boolean insert(BooksClass K) throws Exception{
        String sql = "INSERT INTO Books (MaSach, TenSach, Tap, TheLoai, PhienBan, LoaiSach, NgayPhatHanh, LuongInAn, DonGia, NXB, HinhAnh)"+
                "Values(?,?,?,?,?,?,?,?,?,?,?)";
        
        try (   
                Connection con = ConnectDB.openConnection();
                PreparedStatement prstm = con.prepareStatement(sql);
            ){
            prstm.setString(1, K.getMaSach());
            prstm.setString(2, K.getTenSach());
            prstm.setFloat(3, K.getTap());
            prstm.setString(4, K.getTheLoai());
            prstm.setString(5, K.getPhienBan());
            prstm.setString(6, K.getLoaiSach());
            prstm.setString(7, K.getNgayPhatHanh());
            prstm.setString(8, K.getLuongInAn());
            prstm.setString(9, K.getDonGia());
            prstm.setString(10, K.getNXB());
            if (K.getHinhAnh()!= null) {
                Blob hinhanh = new SerialBlob(K.getHinhAnh());
                prstm.setBlob(11, hinhanh);
            }else{
                Blob hinhanh = null;
                prstm.setBlob(11, hinhanh);
            }
            return prstm.executeUpdate()>0;
        }
    }
    public boolean update(BooksClass K) throws Exception{
        String sql = "UPDATE Books\n" +
                " SET TenSach = ?,  Tap = ?, TheLoai = ?, PhienBan = ?, LoaiSach = ?, NgayPhatHanh = ?, LuongInAn = ?, DonGia = ?, NXB = ?, HinhAnh = ?" +
                " WHERE MaSach = ?";

        try (   
                Connection con = ConnectDB.openConnection();
                PreparedStatement prstm = con.prepareStatement(sql);
            ){
            
            prstm.setString(11, K.getMaSach());
            prstm.setString(1, K.getTenSach());
            prstm.setFloat(2, K.getTap());
            prstm.setString(3, K.getTheLoai());
            prstm.setString(4, K.getPhienBan());
            prstm.setString(5, K.getLoaiSach());
            prstm.setString(6, K.getNgayPhatHanh());
            prstm.setString(7, K.getLuongInAn());
            prstm.setString(8, K.getDonGia());
            prstm.setString(9, K.getNXB());
            if (K.getHinhAnh()!= null) {
                Blob hinhanh = new SerialBlob(K.getHinhAnh());
                prstm.setBlob(10, hinhanh);
            }else{
                Blob hinhanh = null;
                prstm.setBlob(10, hinhanh);
            }
            return prstm.executeUpdate()>0;
        }
    }
    public boolean delete(String maSachKim) throws Exception{
        String sql = "Delete From Books " +
                " WHERE MaSach = ?";
        
        try (   
                Connection con = ConnectDB.openConnection();
                PreparedStatement prstm = con.prepareStatement(sql);
            ){
            prstm.setString(1, maSachKim);
            return prstm.executeUpdate()>0; 
        }
    }
    public List<BooksClass> findKIM() throws Exception{
        String sql = "Select * From Books ";

        try (   
                Connection con = ConnectDB.openConnection();
                PreparedStatement prstm = con.prepareStatement(sql);
            ){
            
            try (ResultSet rs = prstm.executeQuery();){
                List<BooksClass> kims = new ArrayList<>();
                while (rs.next()) {
                    BooksClass K = createBooks(rs);
                    kims.add(K);
                }
                return kims;
            } 
        }
    }

    private BooksClass createBooks(final ResultSet rs) throws SQLException {
        BooksClass K = new BooksClass();
        K.setMaSach(rs.getString("MaSach"));
        K.setTenSach(rs.getString("TenSach"));
        K.setTap(rs.getFloat("Tap"));
        K.setTheLoai(rs.getString("TheLoai"));
        K.setPhienBan(rs.getString("PhienBan"));
        K.setLoaiSach(rs.getString("LoaiSach"));
        K.setNgayPhatHanh(rs.getString("NgayPhatHanh"));
        K.setLuongInAn(rs.getString("LuongInAn"));
        K.setDonGia(rs.getString("DonGia"));
        K.setNXB(rs.getString("NXB"));
        Blob blob = rs.getBlob("HinhAnh");
        if (blob != null) {
           K.setHinhAnh(blob.getBytes(1, (int) blob.length())); 
        }
        return K;
    }
}
