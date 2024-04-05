/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package account;

/**
 *
 * @author PC
 */
public class AccountClass {
    private String user,pass,confpass;

    public AccountClass() {
    }

    public AccountClass(String user, String pass, String confpass) {
        this.user = user;
        this.pass = pass;
        this.confpass = confpass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConfpass() {
        return confpass;
    }

    public void setConfpass(String confpass) {
        this.confpass = confpass;
    }

    @Override
    public String toString() {
        return "AccountClass{" + "user=" + user + ", pass=" + pass + ", confpass=" + confpass + '}';
    }
    
}
