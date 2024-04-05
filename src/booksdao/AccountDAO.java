/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package booksdao;

import account.AccountClass;
import connect.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author PC
 */
public class AccountDAO {
    public AccountClass checkLog(String user, String pass) throws Exception{
        String sql = "Select * From Account Where TenDangNhap = ? and MatKhau = ?";
        try (   
                Connection con = ConnectDB.openConnection();
                PreparedStatement prstm = con.prepareStatement(sql);
            ){
            prstm.setString(1, user);
            prstm.setString(2, pass);
            try (ResultSet rs = prstm.executeQuery();){
                if(rs.next()){
                    AccountClass AC = new AccountClass();
                    AC.setUser(user);
                    return AC;
                }
            }
        }
        return null;
    }
}
