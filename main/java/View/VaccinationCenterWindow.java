package View;

import Controller.VaccinationCenterController;
import Controller.WaitingListController;
import Model.Appointment;
import Model.Patient;
import Model.VaccinationCenter;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @authors Rania Charkaoui, Arthur Elskens & Gilles Feron
 */
public class VaccinationCenterWindow extends javax.swing.JFrame {
    private final EntityManagerFactory emfac = Persistence.createEntityManagerFactory("MISproject2_PU");
    private final VaccinationCenterController vaccinationcenterCtrl = new VaccinationCenterController(emfac);
    private final WaitingListController listCtrl = new WaitingListController(emfac);
    
    private VaccinationCenter vaccinationcenter = null;

    /**
     * Creates new form VaccinationCenterWindow
     */
    public VaccinationCenterWindow() {
        initComponents();
    }
    
    public void setVaccinationCenter(VaccinationCenter vaccinationcenter){
    this.vaccinationcenter = vaccinationcenter;
    }
    public VaccinationCenter getVaccinationCenter(){
        updateVaccinationCenter();       
        return vaccinationcenter;
    }
    public void updateVaccinationCenter(){
        if( vaccinationcenter == null ){
            vaccinationcenter = new VaccinationCenter(); 
//            System.out.println("New VaccinationCenter created");
        }
        
        vaccinationcenter.setZone(ZoneList.getSelectedItem().toString());
        vaccinationcenter.setAvailableDoses(Integer.parseInt(AvailableDoses.getText()));
        vaccinationcenter.setId(Integer.parseInt(ZoneList.getSelectedItem().toString().substring(5,6)));
//        System.out.println("Info set");
    } 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        VaccinationCenterLabel = new javax.swing.JLabel();
        ZoneList = new javax.swing.JComboBox<>();
        Municipality = new javax.swing.JLabel();
        Doses = new javax.swing.JLabel();
        AvailableDoses = new javax.swing.JTextField();
        UpdateButton = new javax.swing.JButton();
        CallButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        VaccinationCenterLabel.setBackground(new java.awt.Color(86, 207, 225));
        VaccinationCenterLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        VaccinationCenterLabel.setForeground(new java.awt.Color(255, 255, 255));
        VaccinationCenterLabel.setText(" Centre de Vaccination");
        VaccinationCenterLabel.setOpaque(true);

        ZoneList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Zone 1", "Zone 2", "Zone 3", "Zone 4" }));

        Municipality.setText("Zone");

        Doses.setText("Nombre de doses restantes");

        UpdateButton.setText("Mettre ?? jour");
        UpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateButtonActionPerformed(evt);
            }
        });

        CallButton.setText("Appeler des patients");
        CallButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CallButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Si vous souhaitez cr??er des rendez-vous pour utiliser les doses restantes ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(VaccinationCenterLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(324, 324, 324)
                                .addComponent(AvailableDoses, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Doses)
                            .addComponent(UpdateButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(Municipality))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(ZoneList, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(CallButton)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(VaccinationCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(Municipality)
                .addGap(18, 18, 18)
                .addComponent(ZoneList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Doses)
                    .addComponent(AvailableDoses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(UpdateButton)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CallButton)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateButtonActionPerformed
        // TODO add your handling code here:
        updateVaccinationCenter();
        vaccinationcenterCtrl.edit(vaccinationcenter);   
       
    }//GEN-LAST:event_UpdateButtonActionPerformed

    private void CallButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CallButtonActionPerformed
        
        
        vaccinationcenter = new VaccinationCenter();
        
        vaccinationcenter.setId(Integer.parseInt(ZoneList.getSelectedItem().toString().substring(5,6)));
        
        VaccinationCenter dbcenter = vaccinationcenterCtrl.findDuplicate(vaccinationcenter);
        
        // Hypothesis : there are no appointments for a day different from today 
        ArrayList<Appointment> vaccinationlistappointment = dbcenter.getAppointmentCollection(); 
        int nbPatients = dbcenter.getAvailableDoses()-vaccinationlistappointment.size();
        
        ArrayList<Patient> patientsSorted = listCtrl.sortPatient(); // all patients ordered by score 
        ArrayList<Patient> patientsToCall = new ArrayList<>();
        
        // Creating PatientsToCall with only the Patients needed from the right vaccination Center
        for (Patient p : patientsSorted){
                
            if (Objects.equals(p.getClosestCenter().getId(), dbcenter.getId())){ //if good vaccinationCenter
                patientsToCall.add(p); // ordered patients within the good vaccinationCenter
            }
        }
        
        
        for (Patient p :patientsToCall ){
            // calling Patients 
            try {
                listCtrl.callPatient(p);
            } catch (MessagingException ex) {
                Logger.getLogger(VaccinationCenterWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            // removing patients called from Waiting List 
            listCtrl.destroy(listCtrl.findPatient(p).getId());
        }
        
        

    }//GEN-LAST:event_CallButtonActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AvailableDoses;
    private javax.swing.JButton CallButton;
    private javax.swing.JLabel Doses;
    private javax.swing.JLabel Municipality;
    private javax.swing.JButton UpdateButton;
    private javax.swing.JLabel VaccinationCenterLabel;
    private javax.swing.JComboBox<String> ZoneList;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
