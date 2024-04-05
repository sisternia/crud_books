/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class DBVator {
    public static void validataEmpty(JTextField field, StringBuilder sb, String errorMes){
        if (field.getText().equals("")) {
            sb.append(errorMes).append("\n");
            field.requestFocus();   
        }else
            field.setBackground(Color.white);
    }
    
    public static void validataEmpty(JTextArea area, StringBuilder sb, String errorMes){
        if (area.getText().equals("")) {
            sb.append(errorMes).append("\n");
            area.requestFocus();
        }else{
            area.setBackground(Color.white);
        }
    } 
    
    public static void validataEmpty(JDateChooser dateChooser, StringBuilder sb, String errorMes){
        try {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate());
        } catch (Exception e) {
            sb.append(errorMes).append("\n");
        }
    }
    
    public static void validataEmpty(JPasswordField passwordField, StringBuilder sb, String errorMes){
        if (passwordField.equals("")) {
            sb.append(errorMes).append("\n");
            passwordField.requestFocus();   
        }else
            passwordField.setBackground(Color.white);
    }
    public static void checkID(JTextField field, StringBuilder sb, String errorMes){
        Pattern pattern = Pattern.compile("^(KD||AM||IPM||TR||SK||HK||SB)-[0-9]+\\D+[0-9]+$");
        Matcher matcher = pattern.matcher(field.getText());
        if (!matcher.find()) {
            sb.append(errorMes).append("\n");
            field.requestFocus();  
        }
        else{
            field.setBackground(Color.white);
        }
    }
    
    public static void checkInAnDG(JTextField field, StringBuilder sb, String errorMes){
        Pattern pattern = Pattern.compile("^[0-9]+\\D+[0-9]+$");
        Matcher matcher = pattern.matcher(field.getText());
        if (!matcher.find()) {
            sb.append(errorMes).append("\n");
            field.requestFocus();  
        }
        else{
            field.setBackground(Color.white);
        }
    }
    
}
