/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package books;

import account.LoginFrame;
import booksdao.BooksDAO;
import connect.ConnectDB;
import connect.DBVator;
import connect.ImageBooks;
import connect.MessageDialog;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import textfield.SearchOptinEvent;
import textfield.SearchOption;

/**
 *
 * @author PC
 */
public class BooksALL extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
    private byte[] imageKIM;
    /**
     * Creates new form KimDong
     */
    public BooksALL() {
        initComponents();
                        
        desgintable();
        
        loadDataToTable();
        
        setLocationRelativeTo(null);
        
    }
    
    private void desgintable(){
        tableModel = new DefaultTableModel();
        tblKim.setModel(tableModel);
        tblKim.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD, 12));
        tblKim.getTableHeader().setOpaque(false);
        tblKim.getTableHeader().setBackground(new Color(32, 136, 203));
        
        Object[] column = {"Mã sách","Tên sách","Tập","Thể loại","Phiên bản","Loại sách","Ngày phát hành","Lượng in ấn","Đơn giá(VNĐ)","NXB"};
        
        tableModel.setColumnIdentifiers(column);
         
        TableCellRenderer rendererFromHeader = tblKim.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel)rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        
        tblKim.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblKim.getColumnModel().getColumn(1).setPreferredWidth(300);
        tblKim.getColumnModel().getColumn(2).setPreferredWidth(20);
        tblKim.getColumnModel().getColumn(3).setPreferredWidth(45);
        tblKim.getColumnModel().getColumn(4).setPreferredWidth(45); 
        tblKim.getColumnModel().getColumn(5).setPreferredWidth(50);
        tblKim.getColumnModel().getColumn(6).setPreferredWidth(80);
        tblKim.getColumnModel().getColumn(7).setPreferredWidth(50);
        tblKim.getColumnModel().getColumn(8).setPreferredWidth(65);
        tblKim.getColumnModel().getColumn(9).setPreferredWidth(60);

        
        
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(JLabel.CENTER);
        
        tblKim.getColumnModel().getColumn(0).setCellRenderer(centerRender);
        tblKim.getColumnModel().getColumn(2).setCellRenderer(centerRender);
        tblKim.getColumnModel().getColumn(3).setCellRenderer(centerRender);
        tblKim.getColumnModel().getColumn(4).setCellRenderer(centerRender);
        tblKim.getColumnModel().getColumn(5).setCellRenderer(centerRender);
        tblKim.getColumnModel().getColumn(6).setCellRenderer(centerRender);
        tblKim.getColumnModel().getColumn(7).setCellRenderer(centerRender);
        tblKim.getColumnModel().getColumn(8).setCellRenderer(centerRender);
        tblKim.getColumnModel().getColumn(9).setCellRenderer(centerRender);
        
        txtFilter.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                txtFilter.setHint("Tìm kiếm " + option.getName() + "...");
            }
        });
        txtFilter.addOption(new SearchOption("theo mã sách", new ImageIcon(getClass().getResource("/textfield/user.png"))));
        txtFilter.addOption(new SearchOption("theo tên sách", new ImageIcon(getClass().getResource("/textfield/email.png"))));
        txtFilter.setSelectedIndex(0); 
        
        tblKim.setDefaultEditor(Object.class, null);
    }
    
    public List<BooksClass> filter() throws Exception{
        String sql = "Select * From Books ";

        try (   
                Connection con = ConnectDB.openConnection();
                PreparedStatement prstm = con.prepareStatement(sql);
            ){
            
            try (ResultSet rs = prstm.executeQuery();){
                List<BooksClass> kims = new ArrayList<>();
                while (rs.next()) {
                    BooksClass K = createKim(rs);
                    kims.add(K);
                }
                return kims;
            } 
        }
    }

    private BooksClass createKim(final ResultSet rs) throws SQLException {
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
    
    private void loadDataToTable(){
        try {
            BooksDAO KD = new BooksDAO();
            List<BooksClass> kims = KD.findKIM();
            tableModel.setRowCount(0);
            for (BooksClass kim : kims) {
                tableModel.addRow(new Object[]{
                    kim.getMaSach(), kim.getTenSach(), kim.getTap(),kim.getTheLoai(),kim.getPhienBan(),
                    kim.getLoaiSach(),kim.getNgayPhatHanh(),kim.getLuongInAn(), kim.getDonGia(), kim.getNXB()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi");
        }
    }
    
    private void Reset(){
        txtMaSach.setText("");
        txtTenSach.setText("");
        txtNgayPhatHanh.setDate(null);
        txtTap.setText("");
        cbboxTheLoai.setSelectedIndex(0);
        cbboxPhienBan.setSelectedIndex(0);
        cbboxLoaiSach.setSelectedIndex(0);
        cbboxNXB.setSelectedIndex(0);
        txtLuongInAn.setText("");
        txtDonGia.setText("");
        imageKIM = null;
        ImageIcon icon = new ImageIcon(getClass().getResource(""));
        lblHinhAnhKIM.setIcon(icon);
    }
    
    private void Update(){
        StringBuilder sb = new StringBuilder();
        
        DBVator.validataEmpty(txtMaSach, sb, "Mã sách đang trống!\n");
        DBVator.validataEmpty(txtTenSach, sb, "Tên sách đang trống!\n");
        DBVator.validataEmpty(txtTap, sb, "Tập đang trống!\n");
        DBVator.validataEmpty(txtNgayPhatHanh, sb, "Ngày phát hành đang trống!\n");
        DBVator.validataEmpty(txtLuongInAn, sb, "Lượng in ấn đang trống!\n");
        DBVator.validataEmpty(txtDonGia, sb, "Đơn giá đang trống!\n");
        
        if (sb.length()>0) {
            MessageDialog.showErrorDialog(this, sb.toString(), "Lỗi");
            return;
        }
        
        DBVator.checkID(txtMaSach, sb, "Mã sách định dạng sai!\nVui lòng nhập lại!\nVí dụ:KD-1.205, AM-2.305, IPM-45.9123, ...\n");
        DBVator.checkInAnDG(txtLuongInAn, sb, "Lượng in ấn sai định dạng!\nVui long nhập lại!\nVí dụ: 1,0; 10,0; 100,0; 1.000, 10.000, ...\n");
        DBVator.checkInAnDG(txtDonGia, sb, "Đơn giá sai định dạng!\nVui lòng nhập lại!\nVí dụ: 1,0; 10,0; 100,0; 1.000, 10.000,...\n");
        
        if (sb.length()>0) {
            MessageDialog.showErrorDialog(this, sb.toString(), "Lỗi");
            return;
        }
        if (MessageDialog.showConfirmDialog(this, "Bạn có muốn cập nhật!", "Thông báo") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            BooksClass K = new BooksClass();
            
            K.setMaSach(txtMaSach.getText());
            K.setTenSach(txtTenSach.getText());            
            K.setTap(Float.parseFloat(txtTap.getText()));            
            K.setTheLoai(cbboxTheLoai.getSelectedItem().toString());
            K.setPhienBan(cbboxPhienBan.getSelectedItem().toString());
            K.setLoaiSach(cbboxLoaiSach.getSelectedItem().toString());
            K.setNgayPhatHanh(new SimpleDateFormat("yyyy-MM-dd").format(txtNgayPhatHanh.getDate()));
            K.setLuongInAn(txtLuongInAn.getText());
            K.setDonGia(txtDonGia.getText());
            K.setNXB(cbboxNXB.getSelectedItem().toString());
            K.setHinhAnh(imageKIM);
            
            BooksDAO KD = new BooksDAO();
            if (KD.update(K)) {
                MessageDialog.showMessageDialog(this, "Cập nhật thành công!", "Thông báo");
                Reset();
                loadDataToTable();
            }else {
                MessageDialog.showMessageDialog(this, "Cập nhật thất bại!", "Thông báo");
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.showErrorDialog(this, e.getMessage(), "Lỗi");
        }
    }
    
    private void Save(){
        StringBuilder sb = new StringBuilder();
        
        DBVator.validataEmpty(txtMaSach, sb, "Mã sách đang trống!\n");
        DBVator.validataEmpty(txtTenSach, sb, "Tên sách đang trống!\n");
        DBVator.validataEmpty(txtTap, sb, "Tập đang trống!\n");
        DBVator.validataEmpty(txtNgayPhatHanh, sb, "Ngày phát hành đang trống!\n");
        DBVator.validataEmpty(txtLuongInAn, sb, "Lượng in ấn đang trống!\n");
        DBVator.validataEmpty(txtDonGia, sb, "Đơn giá đang trống!\n");
        if (sb.length()>0) {
            MessageDialog.showErrorDialog(this, sb.toString(), "Lỗi");
            return;
        }
        
        DBVator.checkID(txtMaSach, sb, "Mã sách định dạng sai!\nVui lòng nhập lại!\nVí dụ:KD-1.205, AM-2.305, IPM-45.9123, ...\n");
        DBVator.checkInAnDG(txtLuongInAn, sb, "Lượng in ấn sai định dạng!\nVui long nhập lại!\nVí dụ: 1,0; 10,0; 100,0; 1.000, 10.000, ...\n");
        DBVator.checkInAnDG(txtDonGia, sb, "Đơn giá sai định dạng!\nVui lòng nhập lại!\nVí dụ: 1,0; 10,0; 100,0; 1.000, 10.000,...\n");
        if (sb.length()>0) {
            MessageDialog.showErrorDialog(this, sb.toString(), "Lỗi");
            return;
        }
        
        try {
            BooksClass K = new BooksClass();
            
            K.setMaSach(txtMaSach.getText());
            K.setTenSach(txtTenSach.getText());            
            K.setTap(Float.parseFloat(txtTap.getText()));            
            K.setTheLoai(cbboxTheLoai.getSelectedItem().toString());
            K.setPhienBan(cbboxPhienBan.getSelectedItem().toString());
            K.setLoaiSach(cbboxLoaiSach.getSelectedItem().toString());
            K.setNgayPhatHanh(new SimpleDateFormat("yyyy-MM-dd").format(txtNgayPhatHanh.getDate()));
            K.setLuongInAn(txtLuongInAn.getText());
            K.setDonGia(txtDonGia.getText());
            K.setNXB(cbboxNXB.getSelectedItem().toString());
            K.setHinhAnh(imageKIM);
            
            BooksDAO KD = new BooksDAO();
            
            if (KD.insert(K)) {
                MessageDialog.showMessageDialog(this, "Lưu thành công!", "Thông báo");
                loadDataToTable();
            }else {
                MessageDialog.showConfirmDialog(this, "Lưu thất bại!", "Thông báo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void Delete(){
        StringBuilder sb = new StringBuilder();
        
        DBVator.validataEmpty(txtMaSach, sb, "Mã sách đang trống!");
        if (sb.length()>0) {
            MessageDialog.showErrorDialog(this, sb.toString(), "Lỗi");
            return;
        }

        DBVator.checkID(txtMaSach, sb, "Mã sách định dạng sai!\nVui lòng nhập lại!\nVí dụ:KD-1.205, KD-2.305, KD-45.9123, ...\n");
        if (sb.length()>0) {
            MessageDialog.showErrorDialog(this, sb.toString(), "Lỗi");
            return;
        }
        
        if (MessageDialog.showConfirmDialog(this, "Bạn có muốn xóa thông tin!", "Thông báo") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            BooksDAO KD = new BooksDAO();
            if (KD.delete(txtMaSach.getText())) {
                MessageDialog.showMessageDialog(this, "Xóa thành công!", "Thông báo");
                Reset();
                loadDataToTable();
            }else {
                MessageDialog.showConfirmDialog(this, "Xóa thất bại!", "Thông báo");
            }
        } catch (Exception e) {
            MessageDialog.showErrorDialog(this, e.getMessage(), "Lỗi");
        }
    }
    
    private void ClickTable(){
        TableModel model = tblKim.getModel();
        int i = tblKim.getSelectedRow();
        int modelRow = tblKim.convertRowIndexToModel(i);
        int col = tblKim.getSelectedColumn();
        tblKim.getModel().getValueAt(modelRow, col);
        
        txtMaSach.setText(model.getValueAt(modelRow, 0).toString());
        txtTenSach.setText(model.getValueAt(modelRow, 1).toString());
        txtTap.setText(model.getValueAt(modelRow, 2).toString());
        cbboxTheLoai.setSelectedItem(model.getValueAt(modelRow, 3).toString());
        cbboxPhienBan.setSelectedItem(model.getValueAt(modelRow, 4).toString());
        cbboxLoaiSach.setSelectedItem(model.getValueAt(modelRow, 5).toString());
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(modelRow, 6));
            txtNgayPhatHanh.setDate(date);
        } catch (ParseException e) {
        }
        txtLuongInAn.setText(model.getValueAt(modelRow, 7).toString());
        txtDonGia.setText(model.getValueAt(modelRow, 8).toString());
        cbboxNXB.setSelectedItem(model.getValueAt(modelRow, 5).toString());
        try {
            imageKIM = (filter().get(modelRow).getHinhAnh());
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imageKIM).getImage().getScaledInstance(lblHinhAnhKIM.getWidth(),lblHinhAnhKIM.getHeight(), Image.SCALE_SMOOTH));
            lblHinhAnhKIM.setIcon(imageIcon);
        } catch (Exception ex) {
        }
    }
    
    private void FilterKim(){
        tableModel = (DefaultTableModel)this.tblKim.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
        tblKim.setRowSorter(sorter);
        if (txtFilter.isSelected()) {
            int option = txtFilter.getSelectedIndex();
            if(option==0){
                sorter.setRowFilter(RowFilter.regexFilter(".*"+txtFilter.getText()+".*",0));
            }else if(option==1){
                sorter.setRowFilter(RowFilter.regexFilter(".*"+txtFilter.getText()+".*",1));
            }
        }
    }
    
    private void AddPicture(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileFilter(){
            @Override
            public boolean accept(File file){
                if (file.isDirectory()) {
                    return true;
                }else{
                    return file.getName().toLowerCase().endsWith(".jpg");
                }
            }

            @Override
            public String getDescription() {
                return "Image File (*.jpg)";
            }
        });
        if (chooser.showOpenDialog(this) == JFileChooser.CANCEL_OPTION) {
            return;
        }
        File file = chooser.getSelectedFile();
        try {
            ImageIcon icon = new ImageIcon(file.getPath());
            Image image = ImageBooks.reSize(icon.getImage(), 290, 409);   
            ImageIcon reSizeIcon = new ImageIcon(image);
            lblHinhAnhKIM.setIcon(reSizeIcon);
            imageKIM = ImageBooks.arrayBooks(image, "jpg");
        } catch (IOException e) {
            e.printStackTrace();
            MessageDialog.showMessageDialog(this, e.getMessage(), "Lỗi");
        }
    }
    
    private void LogOut(){
        LoginFrame login = new LoginFrame();
        login.setVisible(true);
        this.dispose();
    }
    
    private void Exit(){
        System.exit(0);
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
        txtMaSach = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNgayPhatHanh = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtLuongInAn = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbboxPhienBan = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtTap = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTenSach = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        cbboxTheLoai = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        cbboxLoaiSach = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblHinhAnhKIM = new javax.swing.JLabel();
        btnThemHA = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnTaoMoi = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cbboxNXB = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblKim = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtFilter = new textfield.TextFieldSearchOption();
        btnResetFilter = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuItDangXuat = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuItThoat = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kim Đồng");
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Mã sách:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 9, -1, -1));
        jPanel1.add(txtMaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 6, 100, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tên sách:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 9, -1, -1));

        txtNgayPhatHanh.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(txtNgayPhatHanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 206, 177, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Ngày phát hành:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 209, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Lượng in ấn:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 128, -1, 19));

        txtLuongInAn.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jPanel1.add(txtLuongInAn, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 126, 100, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Phiên bản:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 169, -1, -1));

        cbboxPhienBan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thường", "Đặc biệt", "Giới hạn" }));
        jPanel1.add(cbboxPhienBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 166, 173, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Tập:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 49, -1, -1));
        jPanel1.add(txtTap, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 46, 100, -1));

        txtTenSach.setColumns(20);
        txtTenSach.setRows(5);
        txtTenSach.setToolTipText("");
        jScrollPane1.setViewportView(txtTenSach);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 6, 173, 102));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Thể loại:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 88, -1, 19));

        cbboxTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hành động", "Phiêu lưu", "Hài hước", "Drama", "Trinh thám", "Fantasy", "Game", "Kinh dị", "Âm nhạc", "Tình cảm", "Học đường", "Đời thường", "Yaoi", "Yuri" }));
        jPanel1.add(cbboxTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 86, 100, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Đơn giá:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 169, -1, -1));

        txtDonGia.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jPanel1.add(txtDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 166, 100, -1));

        cbboxLoaiSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Light Novel", "Manga", "Tiểu thuyết", "Giáo trình", "Văn hóa", "Lịch sử", "Kinh tế" }));
        jPanel1.add(cbboxLoaiSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 206, 100, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Loại sách:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 209, -1, -1));

        btnThemHA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemHA.setText("Thêm hình ảnh");
        btnThemHA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(btnThemHA)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHinhAnhKIM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHinhAnhKIM, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThemHA)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 240, 302, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnTaoMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTaoMoi.setIcon(new javax.swing.ImageIcon("C:\\Users\\PC\\OneDrive\\Desktop\\Code Name\\iconset4\\new-icon-16.png")); // NOI18N
        btnTaoMoi.setText("Tạo mới");
        btnTaoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon("C:\\Users\\PC\\OneDrive\\Desktop\\Code Name\\iconset4\\Actions-document-edit-icon-16.png")); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon("C:\\Users\\PC\\OneDrive\\Desktop\\Code Name\\iconset4\\Save-icon.png")); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon("C:\\Users\\PC\\OneDrive\\Desktop\\Code Name\\iconset4\\Actions-edit-delete-icon-16.png")); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTaoMoi, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTaoMoi)
                .addGap(18, 18, 18)
                .addComponent(btnCapNhat)
                .addGap(18, 18, 18)
                .addComponent(btnLuu)
                .addGap(18, 18, 18)
                .addComponent(btnXoa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(326, 385, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("NXB:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 128, -1, 19));

        cbboxNXB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kim Đồng", "Amak Books", "IPM", "Trẻ", "Shine Novel", "Hikari Books", "Sky Light Novel", "Shine Books" }));
        jPanel1.add(cbboxNXB, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 126, 173, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 465, 690));

        tblKim.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        tblKim.setRowHeight(30);
        tblKim.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblKim.setShowGrid(true);
        tblKim.getTableHeader().setReorderingAllowed(false);
        tblKim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKimMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblKim);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(477, 128, 1034, 570));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/books/4.jpg"))); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(805, 50, -1, 78));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/books/3.jpg"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(721, 50, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/books/2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(637, 50, -1, -1));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\Users\\PC\\OneDrive\\Desktop\\Code Name\\Java\\Wingbooks.png")); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 50, -1, 78));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/books/1.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(477, 50, -1, 78));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        txtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFilterKeyReleased(evt);
            }
        });

        btnResetFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/books/Reset.png"))); // NOI18N
        btnResetFilter.setPreferredSize(new java.awt.Dimension(40, 40));
        btnResetFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(1134, Short.MAX_VALUE)
                .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnResetFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(654, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1510, 700));

        jMenu1.setText("Hệ thống");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        menuItDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        menuItDangXuat.setIcon(new javax.swing.ImageIcon("C:\\Users\\PC\\OneDrive\\Desktop\\Code Name\\iconset4\\logout-icon-16.png")); // NOI18N
        menuItDangXuat.setText("Đăng xuất");
        menuItDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItDangXuatActionPerformed(evt);
            }
        });
        jMenu1.add(menuItDangXuat);
        jMenu1.add(jSeparator1);

        menuItThoat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        menuItThoat.setIcon(new javax.swing.ImageIcon("C:\\Users\\PC\\OneDrive\\Desktop\\Code Name\\iconset4\\Button-Close-icon-16.png")); // NOI18N
        menuItThoat.setText("Thoát");
        menuItThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItThoatActionPerformed(evt);
            }
        });
        jMenu1.add(menuItThoat);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiActionPerformed
        Reset();
    }//GEN-LAST:event_btnTaoMoiActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        Update();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        Save();
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        Delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblKimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKimMouseClicked
        ClickTable();
    }//GEN-LAST:event_tblKimMouseClicked

    private void btnThemHAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHAActionPerformed
        AddPicture();
    }//GEN-LAST:event_btnThemHAActionPerformed

    private void menuItDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItDangXuatActionPerformed
        LogOut();
    }//GEN-LAST:event_menuItDangXuatActionPerformed

    private void menuItThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItThoatActionPerformed
        Exit();
    }//GEN-LAST:event_menuItThoatActionPerformed

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        FilterKim();
    }//GEN-LAST:event_txtFilterKeyReleased

    private void btnResetFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetFilterActionPerformed
        txtFilter.setText("");
    }//GEN-LAST:event_btnResetFilterActionPerformed

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
            java.util.logging.Logger.getLogger(BooksALL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BooksALL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BooksALL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BooksALL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BooksALL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnResetFilter;
    private javax.swing.JButton btnTaoMoi;
    private javax.swing.JButton btnThemHA;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbboxLoaiSach;
    private javax.swing.JComboBox<String> cbboxNXB;
    private javax.swing.JComboBox<String> cbboxPhienBan;
    private javax.swing.JComboBox<String> cbboxTheLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblHinhAnhKIM;
    private javax.swing.JMenuItem menuItDangXuat;
    private javax.swing.JMenuItem menuItThoat;
    private javax.swing.JTable tblKim;
    private javax.swing.JTextField txtDonGia;
    private textfield.TextFieldSearchOption txtFilter;
    private javax.swing.JTextField txtLuongInAn;
    private javax.swing.JTextField txtMaSach;
    private com.toedter.calendar.JDateChooser txtNgayPhatHanh;
    private javax.swing.JTextField txtTap;
    private javax.swing.JTextArea txtTenSach;
    // End of variables declaration//GEN-END:variables
}
