package View;

import Controller.PatientController;
import Services.HL7Services;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @authors Rania Charkaoui, Arthur Elskens & Gilles Feron
 */
public class MainWindow extends javax.swing.JFrame {
    private final EntityManagerFactory emfac = Persistence.createEntityManagerFactory("MISproject2_PU");
    private final PatientController patientCtrl = new PatientController(emfac);
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainWindowLabel = new javax.swing.JLabel();
        doctorTextLabel = new javax.swing.JLabel();
        patientTextLabel = new javax.swing.JLabel();
        VaccinationCenterImageLabel = new javax.swing.JLabel();
        PatientImageLabel = new javax.swing.JLabel();
        DosesButton = new javax.swing.JButton();
        WaitingListButton = new javax.swing.JButton();
        WaitingListUpdateButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MainWindowLabel.setBackground(new java.awt.Color(86, 207, 225));
        MainWindowLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        MainWindowLabel.setForeground(new java.awt.Color(255, 255, 255));
        MainWindowLabel.setText("                Interface duale : gestion de la vaccination");
        MainWindowLabel.setOpaque(true);

        doctorTextLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doctorTextLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        doctorTextLabel.setText("Centre de Vaccination ");

        patientTextLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        patientTextLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        patientTextLabel.setText("Patient");

        VaccinationCenterImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/VaccinationCenter.png"))); // NOI18N

        PatientImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Patient.png"))); // NOI18N

        DosesButton.setText("Mettre à jour doses et appeler des patients");
        DosesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DosesButtonActionPerformed(evt);
            }
        });

        WaitingListButton.setText("Inscription liste d'attente");
        WaitingListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WaitingListButtonActionPerformed(evt);
            }
        });

        WaitingListUpdateButton.setText("Mettre à jour liste de rendez-vous");
        WaitingListUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WaitingListUpdateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainWindowLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(DosesButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(WaitingListUpdateButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(doctorTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(VaccinationCenterImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(patientTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(PatientImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(WaitingListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(91, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainWindowLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(patientTextLabel)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(doctorTextLabel)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PatientImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VaccinationCenterImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DosesButton)
                    .addComponent(WaitingListButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WaitingListUpdateButton)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void refreshPatientList(){
        List patients = patientCtrl.findPatientEntities();

    }
    private void DosesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DosesButtonActionPerformed
        
        VaccinationCenterWindow vacCenterPopup = new VaccinationCenterWindow();
        vacCenterPopup.setVisible(true);
        
        vacCenterPopup.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent evt){
                refreshPatientList();
            }
        });
    }//GEN-LAST:event_DosesButtonActionPerformed

    private void WaitingListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WaitingListButtonActionPerformed
        
        PatientWindow patientAddPopup = new PatientWindow();
        patientAddPopup.setVisible(true);
        
        //HL7 server
        HL7Services hl7Services = new HL7Services();
        hl7Services.startServer();
        
        patientAddPopup.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent evt){
                
                hl7Services.stopServer();
                
            }
        });
          
    }//GEN-LAST:event_WaitingListButtonActionPerformed

    private void WaitingListUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WaitingListUpdateButtonActionPerformed
        
        AppointmentListUpdateWindow WaitingListPopup = new AppointmentListUpdateWindow();
        WaitingListPopup.setVisible(true);
        //HL7 server
        HL7Services hl7Services = new HL7Services();
        hl7Services.startServer();
        
        WaitingListPopup.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent evt){
                refreshPatientList();
            }
        });
    }//GEN-LAST:event_WaitingListUpdateButtonActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DosesButton;
    private javax.swing.JLabel MainWindowLabel;
    private javax.swing.JLabel PatientImageLabel;
    private javax.swing.JLabel VaccinationCenterImageLabel;
    private javax.swing.JButton WaitingListButton;
    private javax.swing.JButton WaitingListUpdateButton;
    private javax.swing.JLabel doctorTextLabel;
    private javax.swing.JLabel patientTextLabel;
    // End of variables declaration//GEN-END:variables
}