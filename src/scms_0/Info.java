/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package scms_0;

import com.formdev.flatlaf.FlatLightLaf;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author karen
 */
public class Info extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    /**
     * Creates new form CheckIn
     */
    public Info() {
        initComponents();
        }
    public Info(String id_number) {
    setUndecorated(true);
    initComponents();
    conn = Database.getConnection();
    try {
        String sql = "SELECT * FROM check_ins WHERE id_number = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, id_number);
        rs = ps.executeQuery();

        if (rs.next()) {
            String patientId = rs.getString("id_number");
            // Assuming id_number in patients table is VARCHAR
            String patientInfoQuery = "SELECT * FROM patients WHERE id_number = ?";
            PreparedStatement psPatientInfo = conn.prepareStatement(patientInfoQuery);
            psPatientInfo.setString(1, patientId);
            ResultSet rsPatientInfo = psPatientInfo.executeQuery();

            if (rsPatientInfo.next()) {
                txtIdNum.setText(rsPatientInfo.getString("id_number"));
                txtFirstName.setText(rsPatientInfo.getString("first_name"));
                txtLastName.setText(rsPatientInfo.getString("last_name"));
                txtDob.setDate(rsPatientInfo.getDate("date_of_birth"));
                txtEme.setText(rsPatientInfo.getString("emergency_no"));
                txtCon.setText(rsPatientInfo.getString("contact_no"));
                String role = rsPatientInfo.getString("role");
                jComboBox1.setSelectedItem(role);
                txtAllergies.setText(rsPatientInfo.getString("allergies"));
                String medicalCondition = rsPatientInfo.getString("medical_condition"); 
                if (medicalCondition != null) {
                    String[] conditions = medicalCondition.split(" ");
                    for (String condition : conditions) {
                        if (condition.equals("Heart")) {
                            txtHeart.setSelected(true);
                        } else if (condition.equals("Asthma")) {
                            txtAsthma.setSelected(true);
                        } else if (condition.equals("Seizure")) {
                            txtSeizure.setSelected(true);
                        } else if (condition.equals("Diabetes")) {
                            txtDiabetes.setSelected(true);
                        } else {
                            txtOthers.setText(txtOthers.getText().trim() + " " + condition);
                        }
                    }
                }
                txtProg.setText(rsPatientInfo.getString("occupation"));                    
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    // Query for check-in information
    String checkInInfoQuery = "SELECT id_number, reason, purpose, DATE_FORMAT(check_in_time, '%Y-%m-%d %H:%i:%s') AS check_in_time " +
         "FROM check_ins " +
         "WHERE id_number = ? " + // Assuming you want to filter by id_number
         "ORDER BY check_in_time DESC";
    try (PreparedStatement psCheckInInfo = conn.prepareStatement(checkInInfoQuery)) {
        psCheckInInfo.setString(1, id_number);
        try (ResultSet rsCheckInInfo = psCheckInInfo.executeQuery()) {
            DefaultTableModel model = (DefaultTableModel) checkUpTable1.getModel();
            model.setRowCount(0); 

            while (rsCheckInInfo.next()) {
                String reason = rsCheckInInfo.getString("reason");
                String purpose = rsCheckInInfo.getString("purpose");
                String dateTime = rsCheckInInfo.getString("check_in_time");
                model.addRow(new Object[]{reason, purpose, dateTime});
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "An error occurred while retrieving check-in information. Please try again later.");
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        tipName = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        checkUpText1 = new javax.swing.JLabel();
        txtDob = new com.toedter.calendar.JDateChooser();
        txtAsthma = new javax.swing.JCheckBox();
        txtHeart = new javax.swing.JCheckBox();
        txtProg = new javax.swing.JTextField();
        txtCon = new javax.swing.JTextField();
        othersText = new javax.swing.JLabel();
        txtOthers = new javax.swing.JTextField();
        allergiesText = new javax.swing.JLabel();
        emeText = new javax.swing.JLabel();
        txtEme = new javax.swing.JTextField();
        txtIdNum = new javax.swing.JTextField();
        txtAllergies = new javax.swing.JTextField();
        classText = new javax.swing.JLabel();
        txtDiabetes = new javax.swing.JCheckBox();
        txtSeizure = new javax.swing.JCheckBox();
        idNumText = new javax.swing.JLabel();
        firstNameText = new javax.swing.JLabel();
        progText = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        lastNameText = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        dobText = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        conText = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        medText = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        checkUpTable1 = new javax.swing.JTable();
        checkUpText2 = new javax.swing.JLabel();
        leftPanel8 = new javax.swing.JPanel();
        infoButton10 = new javax.swing.JButton();
        infoButton11 = new javax.swing.JButton();
        Patient1 = new javax.swing.JButton();
        infoButton13 = new javax.swing.JButton();
        tipLogo7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        topPanel.setBackground(new java.awt.Color(45, 46, 41));

        tipName.setFont(new java.awt.Font("Tw Cen MT", 1, 36)); // NOI18N
        tipName.setForeground(new java.awt.Color(255, 255, 255));
        tipName.setText("TECHNOLOGICAL INSTITUTE OF THE PHILIPPINES");

        exitButton.setBackground(new java.awt.Color(45, 46, 41));
        exitButton.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        exitButton.setForeground(new java.awt.Color(255, 255, 255));
        exitButton.setText("X");
        exitButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tipName)
                .addGap(161, 161, 161))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exitButton))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addComponent(exitButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(tipName)
                .addGap(37, 37, 37))
        );

        checkUpText1.setBackground(new java.awt.Color(45, 46, 41));
        checkUpText1.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        checkUpText1.setForeground(new java.awt.Color(45, 46, 41));
        checkUpText1.setText("PERSONAL INFORMATION");

        txtDob.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N

        txtAsthma.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        txtAsthma.setText("Asthma");
        txtAsthma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAsthmaActionPerformed(evt);
            }
        });

        txtHeart.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        txtHeart.setText("Heart");

        txtProg.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        txtProg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProgActionPerformed(evt);
            }
        });

        txtCon.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        txtCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConActionPerformed(evt);
            }
        });

        othersText.setBackground(new java.awt.Color(45, 46, 41));
        othersText.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        othersText.setForeground(new java.awt.Color(45, 46, 41));
        othersText.setText("OTHERS");

        txtOthers.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        txtOthers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOthersActionPerformed(evt);
            }
        });

        allergiesText.setBackground(new java.awt.Color(45, 46, 41));
        allergiesText.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        allergiesText.setForeground(new java.awt.Color(45, 46, 41));
        allergiesText.setText("ALLERGIES");

        emeText.setBackground(new java.awt.Color(45, 46, 41));
        emeText.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        emeText.setForeground(new java.awt.Color(45, 46, 41));
        emeText.setText("EMERGENCY NO.");

        txtEme.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        txtEme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmeActionPerformed(evt);
            }
        });

        txtIdNum.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        txtIdNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdNumActionPerformed(evt);
            }
        });

        txtAllergies.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        txtAllergies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAllergiesActionPerformed(evt);
            }
        });

        classText.setBackground(new java.awt.Color(45, 46, 41));
        classText.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        classText.setForeground(new java.awt.Color(45, 46, 41));
        classText.setText("Role");

        txtDiabetes.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        txtDiabetes.setText("Diabetes");
        txtDiabetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiabetesActionPerformed(evt);
            }
        });

        txtSeizure.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        txtSeizure.setText("Seizure");

        idNumText.setBackground(new java.awt.Color(45, 46, 41));
        idNumText.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        idNumText.setForeground(new java.awt.Color(45, 46, 41));
        idNumText.setText("ID NUMBER");

        firstNameText.setBackground(new java.awt.Color(45, 46, 41));
        firstNameText.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        firstNameText.setForeground(new java.awt.Color(45, 46, 41));
        firstNameText.setText("FIRST NAME");

        progText.setBackground(new java.awt.Color(45, 46, 41));
        progText.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        progText.setForeground(new java.awt.Color(45, 46, 41));
        progText.setText("PROGRAM / JOB");

        saveButton.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        saveButton.setText("SAVE");
        saveButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        lastNameText.setBackground(new java.awt.Color(45, 46, 41));
        lastNameText.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        lastNameText.setForeground(new java.awt.Color(45, 46, 41));
        lastNameText.setText("LAST NAME");

        backButton.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        backButton.setText("BACK");
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        dobText.setBackground(new java.awt.Color(45, 46, 41));
        dobText.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        dobText.setForeground(new java.awt.Color(45, 46, 41));
        dobText.setText("DATE OF BIRTH");

        jComboBox1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(45, 46, 41));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Select Role-", "1st Year Student", "2nd Year Student", "3rd Year Student", "4th Year Student", "Teacher", "Staff", "Visitor" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        conText.setBackground(new java.awt.Color(45, 46, 41));
        conText.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        conText.setForeground(new java.awt.Color(45, 46, 41));
        conText.setText("CONTACT NO.");

        txtFirstName.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        txtFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFirstNameActionPerformed(evt);
            }
        });

        txtLastName.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        txtLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLastNameActionPerformed(evt);
            }
        });

        medText.setBackground(new java.awt.Color(45, 46, 41));
        medText.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        medText.setForeground(new java.awt.Color(45, 46, 41));
        medText.setText("MEDICAL CONDITION");

        checkUpTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Reason", "Purpose", "Date & Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(checkUpTable1);

        checkUpText2.setBackground(new java.awt.Color(45, 46, 41));
        checkUpText2.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        checkUpText2.setForeground(new java.awt.Color(45, 46, 41));
        checkUpText2.setText("CHECK-IN HISTORY:");

        leftPanel8.setBackground(new java.awt.Color(45, 46, 41));
        leftPanel8.setPreferredSize(new java.awt.Dimension(135, 720));

        infoButton10.setBackground(new java.awt.Color(105, 107, 96));
        infoButton10.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        infoButton10.setForeground(new java.awt.Color(255, 255, 255));
        infoButton10.setText("Check-In");
        infoButton10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        infoButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoButton10ActionPerformed(evt);
            }
        });

        infoButton11.setBackground(new java.awt.Color(45, 46, 41));
        infoButton11.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        infoButton11.setForeground(new java.awt.Color(255, 255, 255));
        infoButton11.setText("Home");
        infoButton11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        infoButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoButton11ActionPerformed(evt);
            }
        });

        Patient1.setBackground(new java.awt.Color(45, 46, 41));
        Patient1.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        Patient1.setForeground(new java.awt.Color(255, 255, 255));
        Patient1.setText("Patients");
        Patient1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Patient1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Patient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Patient1ActionPerformed(evt);
            }
        });

        infoButton13.setBackground(new java.awt.Color(45, 46, 41));
        infoButton13.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        infoButton13.setForeground(new java.awt.Color(255, 255, 255));
        infoButton13.setText("Records");
        infoButton13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        infoButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoButton13ActionPerformed(evt);
            }
        });

        tipLogo7.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        tipLogo7.setForeground(new java.awt.Color(255, 255, 255));
        tipLogo7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scms_0/tip_logo.png"))); // NOI18N
        tipLogo7.setText("LOGO NI TIP");
        tipLogo7.setPreferredSize(new java.awt.Dimension(90, 90));

        javax.swing.GroupLayout leftPanel8Layout = new javax.swing.GroupLayout(leftPanel8);
        leftPanel8.setLayout(leftPanel8Layout);
        leftPanel8Layout.setHorizontalGroup(
            leftPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(infoButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Patient1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(infoButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
            .addGroup(leftPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(tipLogo7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(infoButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
        );
        leftPanel8Layout.setVerticalGroup(
            leftPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanel8Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(tipLogo7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(infoButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Patient1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(infoButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(leftPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(leftPanel8Layout.createSequentialGroup()
                    .addGap(131, 131, 131)
                    .addComponent(infoButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(499, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(leftPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkUpText1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lastNameText)
                                        .addComponent(dobText)
                                        .addComponent(txtDob, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtIdNum, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(idNumText)
                                        .addComponent(firstNameText)
                                        .addComponent(conText)
                                        .addComponent(txtCon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(emeText)
                                        .addComponent(txtEme, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtProg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(progText))
                                    .addComponent(classText))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAllergies, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(allergiesText)
                                    .addComponent(medText)
                                    .addComponent(txtAsthma)
                                    .addComponent(txtSeizure)
                                    .addComponent(txtDiabetes)
                                    .addComponent(txtHeart)
                                    .addComponent(othersText)
                                    .addComponent(txtOthers, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkUpText2)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(32, Short.MAX_VALUE))
                    .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leftPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(checkUpText1)
                            .addComponent(checkUpText2))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(idNumText, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(allergiesText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtIdNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAllergies))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lastNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(medText))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAsthma))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(firstNameText)
                                    .addComponent(txtSeizure))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDiabetes))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtHeart)
                                    .addComponent(dobText))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDob, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(othersText))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtOthers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(classText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(progText, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(txtProg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, 0)
                                .addComponent(conText, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txtEme, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emeText, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAsthmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAsthmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAsthmaActionPerformed

    private void txtProgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProgActionPerformed

    private void txtConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConActionPerformed

    private void txtOthersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOthersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOthersActionPerformed

    private void txtEmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmeActionPerformed

    private void txtIdNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdNumActionPerformed

    private void txtAllergiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAllergiesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAllergiesActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void txtFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFirstNameActionPerformed

    private void txtLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastNameActionPerformed

    private void txtDiabetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiabetesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiabetesActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        java.util.Date dob = txtDob.getDate();
        String eme = txtEme.getText();
        String con = txtCon.getText();
        Object selectedItem = jComboBox1.getSelectedItem();
        String role = selectedItem != null ? selectedItem.toString() : "";
        String occ = txtProg.getText();
        String missingFields = "";

        if (firstName.isEmpty()) {
            missingFields += "First Name\n";
        }
        if (lastName.isEmpty()) {
            missingFields += "Last Name\n";
        }
        if (eme.isEmpty()) {
            missingFields += "Emergency Contact Number\n";
        }
        if (dob == null) {
            missingFields += "Date of Birth\n";
        }
        if (con.isEmpty()) {
            missingFields += "Contact Number\n";
        }
        if ("- Select Role -".equals(role) || role.isEmpty()) {
            missingFields += "Role\n";
        }
        if (occ.isEmpty()) {
            missingFields += "Occupation\n";
        }

        // Check if any required fields are empty
        if (!missingFields.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in the following required fields:\n" + missingFields, "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method early if any field is empty
        }

        // If all required fields are filled, navigate back
        CheckIn CI = new CheckIn();
        CI.setVisible(true);
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        java.util.Date dob = txtDob.getDate();
        String eme = txtEme.getText();
        String con = txtCon.getText();
        Object selectedItem = jComboBox1.getSelectedItem();
        String role = selectedItem != null ? selectedItem.toString() : "";
        String allergies = txtAllergies.getText();
        String medicalCondition = "";
        if (txtHeart.isSelected()) {
            medicalCondition += "Heart ";
        }
        if (txtAsthma.isSelected()) {
            medicalCondition += "Asthma ";
        }
        if (txtSeizure.isSelected()) {
            medicalCondition += "Seizure ";
        }
        if (txtDiabetes.isSelected()) {
            medicalCondition += "Diabetes ";
        }
        medicalCondition += txtOthers.getText().trim();
        String occ = txtProg.getText();
        String missingFields = "";

        if (firstName.isEmpty()) {
            missingFields += "First Name\n";
        }
        if (lastName.isEmpty()) {
            missingFields += "Last Name\n";
        }
        if (eme.isEmpty()) {
            missingFields += "Emergency Contact Number\n";
        }
        if (dob == null) {
            missingFields += "Date of Birth\n";
        }
        if (con.isEmpty()) {
            missingFields += "Contact Number\n";
        }
        if ("- Select Role -".equals(role) || role.isEmpty()) {
            missingFields += "Role\n";
        }
        if (occ.isEmpty()) {
            missingFields += "Occupation\n";
        }

        // Check if any fields are missing
        if (!missingFields.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in the following required fields:\n" + missingFields, "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method early if any field is empty
        }     
        try {
            String ptsql = "UPDATE patients SET role =?, first_name =?, last_name =?, date_of_birth =?, emergency_no =?, contact_no =?, allergies =?, medical_condition =?, occupation = ? WHERE id_number =?";
            PreparedStatement ptps = conn.prepareStatement(ptsql);
            ptps.setString(1, role);
            ptps.setString(2, firstName);
            ptps.setString(3, lastName);
            ptps.setDate(4, new java.sql.Date(dob.getTime()));
            ptps.setString(5, eme);
            ptps.setString(6, con);
            ptps.setString(7, allergies);
            ptps.setString(8, medicalCondition);
            ptps.setString(9, occ);
            ptps.setString(10, txtIdNum.getText());
            int patientRowsUpdated = ptps.executeUpdate();

            // Update the records table as well
            String urSql = "UPDATE records SET role =?, first_name =?, last_name =?, date_of_birth =?, emergency_no =?, contact_no =?, allergies =?, medical_condition =?, occupation = ? WHERE id_number =?";
            PreparedStatement urps = conn.prepareStatement(urSql);
            urps.setString(1, role);
            urps.setString(2, firstName);
            urps.setString(3, lastName);
            urps.setDate(4, new java.sql.Date(dob.getTime()));
            urps.setString(5, eme);
            urps.setString(6, con);
            urps.setString(7, allergies);
            urps.setString(8, medicalCondition);
            urps.setString(9, occ);
            urps.setString(10, txtIdNum.getText());
            int recordsRowsUpdated = urps.executeUpdate();

            if (patientRowsUpdated > 0 && recordsRowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Patient information updated successfully.");
                CheckIn CI = new CheckIn();
                CI.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update patient information.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        CheckIn CI = new CheckIn();
        CI.setVisible(true);
        dispose();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void infoButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoButton10ActionPerformed
            CheckIn CI = new CheckIn();
            CI.setVisible(true);
            dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_infoButton10ActionPerformed

    private void infoButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoButton11ActionPerformed
            Home H = new Home();
            H.setVisible(true);
            dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_infoButton11ActionPerformed

    private void Patient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Patient1ActionPerformed
            Patients PT = new Patients();
            PT.setVisible(true);
            dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_Patient1ActionPerformed

    private void infoButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoButton13ActionPerformed
            Record RD = new Record();
            RD.setVisible(true);
            dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_infoButton13ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        FlatLightLaf.setup();
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Info().setVisible(true);
     
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Patient1;
    private javax.swing.JLabel allergiesText;
    private javax.swing.JButton backButton;
    private javax.swing.JTable checkUpTable1;
    private javax.swing.JLabel checkUpText1;
    private javax.swing.JLabel checkUpText2;
    private javax.swing.JLabel classText;
    private javax.swing.JLabel conText;
    private javax.swing.JLabel dobText;
    private javax.swing.JLabel emeText;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel firstNameText;
    private javax.swing.JLabel idNumText;
    private javax.swing.JButton infoButton10;
    private javax.swing.JButton infoButton11;
    private javax.swing.JButton infoButton13;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lastNameText;
    private javax.swing.JPanel leftPanel8;
    private javax.swing.JLabel medText;
    private javax.swing.JLabel othersText;
    private javax.swing.JLabel progText;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel tipLogo7;
    private javax.swing.JLabel tipName;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField txtAllergies;
    private javax.swing.JCheckBox txtAsthma;
    private javax.swing.JTextField txtCon;
    private javax.swing.JCheckBox txtDiabetes;
    private com.toedter.calendar.JDateChooser txtDob;
    private javax.swing.JTextField txtEme;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JCheckBox txtHeart;
    private javax.swing.JTextField txtIdNum;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtOthers;
    private javax.swing.JTextField txtProg;
    private javax.swing.JCheckBox txtSeizure;
    // End of variables declaration//GEN-END:variables
}
