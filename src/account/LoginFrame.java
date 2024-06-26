/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package account;

import books.BooksALL;
import booksdao.AccountDAO;
import connect.DBVator;
import connect.MessageDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class LoginFrame extends javax.swing.JFrame {

    /**
     * Creates new form LoginFrame
     */
    public LoginFrame() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private void Login(){
        StringBuilder sb = new StringBuilder();
        DBVator.validataEmpty(txtTenDangNhap, sb, "Tên đăng nhập không được để trống!");
        DBVator.validataEmpty(txtMatKhau, sb, "Mật khẩu không được để trống!");
        
        if (sb.length()>0) {
            MessageDialog.showErrorDialog(this, sb.toString(), "Lỗi");
            return;
        }
        AccountDAO AcDAO = new AccountDAO();
        try {
            AccountClass AC = AcDAO.checkLog(txtTenDangNhap.getText(), new String(txtMatKhau.getPassword()));
            if (AC == null) {
                MessageDialog.showErrorDialog(this, "Tên đăng nhập hoặc mật khẩu sai!", "Lỗi");
            }
            else{
                BooksALL menubooks = new BooksALL();
                menubooks.setVisible(true);
                this.dispose();
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");  
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.showErrorDialog(this, e.getMessage(), "Lỗi");
        }
    }
    
    private void Exit(){
        System.exit(0);
    }
    
    private void SignUp(){
        SignUpFrame signUp = new SignUpFrame();
        signUp.setVisible(true);
        dispose();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        btnDangNhap = new javax.swing.JButton();
        btnDangKy = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng nhập");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tên đăng nhập:");

        txtTenDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenDangNhap.setText("admin");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Mật khẩu:");

        txtMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMatKhau.setText("1");

        btnDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDangNhap.setText("Đăng nhập");
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });

        btnDangKy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDangKy.setText("Đăng kí");
        btnDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKyActionPerformed(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThoat.setIcon(new javax.swing.ImageIcon("C:\\Users\\PC\\OneDrive\\Desktop\\Code Name\\iconset4\\Button-Close-icon-16.png")); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnDangNhap)
                            .addGap(39, 39, 39)
                            .addComponent(btnDangKy, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(61, 61, 61)
                            .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel2)
                        .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel2)
                    .addGap(18, 18, 18)
                    .addComponent(txtTenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel3)
                    .addGap(18, 18, 18)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnDangNhap)
                        .addComponent(btnDangKy))
                    .addGap(18, 18, 18)
                    .addComponent(btnThoat)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 39, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        Login();
    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        Exit();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangKyActionPerformed
        SignUp();
    }//GEN-LAST:event_btnDangKyActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangKy;
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JButton btnThoat;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables
}
