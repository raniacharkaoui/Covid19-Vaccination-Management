package View;

import Controller.AppointmentController;
import Controller.VaccinationCenterController;
import Model.Appointment;
import Model.Patient;
import Model.VaccinationCenter;
import Services.HL7Services;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @authors Rania Charkaoui, Arthur Elskens & Gilles Feron
 */
public class AppointmentListUpdateWindow extends javax.swing.JFrame {
    private final EntityManagerFactory emfac = Persistence.createEntityManagerFactory("MISproject2_PU");
    private final VaccinationCenterController centerCtrl = new VaccinationCenterController(emfac);
    private final AppointmentController appointmentCtrl = new AppointmentController(emfac);

    /**
     * Creates new form WaitingListUpdateWindow
     */
    public AppointmentListUpdateWindow() {
        initComponents();
        refreshList();
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
        Remove = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        AppointmentList = new javax.swing.JList<>();
        hl7_responseLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        VaccinationCenterLabel.setBackground(new java.awt.Color(86, 207, 225));
        VaccinationCenterLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        VaccinationCenterLabel.setForeground(new java.awt.Color(255, 255, 255));
        VaccinationCenterLabel.setText(" Mise à jour des rendez-vous");
        VaccinationCenterLabel.setOpaque(true);

        Remove.setText("Ok");
        Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveActionPerformed(evt);
            }
        });

        jTextField1.setText("Séléctionnez le rendez-vous à mettre à jour : ");

        jScrollPane1.setViewportView(AppointmentList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(VaccinationCenterLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(487, 487, 487))
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(356, 356, 356)
                .addComponent(Remove)
                .addGap(51, 51, 51)
                .addComponent(hl7_responseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(VaccinationCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hl7_responseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Remove, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveActionPerformed
       
        
        EntityListModel<Patient> model = (EntityListModel) AppointmentList.getModel();
        Patient selected = model.getList().get(AppointmentList.getSelectedIndex());
        
        if ( HL7Services.sendADT_A08(selected, "localhost", 54321) ){
            hl7_responseLabel.setText("Le statut du patient a été mis à jour.");
            
        }
        else{
            hl7_responseLabel.setText("Votre demande ne peut pas être reçue actuellement.");
        }
        Remove.setEnabled(false);
        
        ArrayList <Appointment> selectedApp = (ArrayList) selected.getAppointmentCollection();
        
        
        int doses = selected.getClosestCenter().getAvailableDoses();
        
        selected.getClosestCenter().setAvailableDoses(doses-1);
        
        VaccinationCenter c = selected.getClosestCenter();
        centerCtrl.edit(c);
        
        appointmentCtrl.destroy(selectedApp.get(0).getId());
        
        refreshList();
        
    }//GEN-LAST:event_RemoveActionPerformed

    private void refreshList(){

        VaccinationCenter c = new VaccinationCenter(2); // we could code this for all centers but
        // that would mean that every center could access and mess with the other's centers data
        // for this case we only took the center in zone 2 into consideration
        VaccinationCenter dbc = centerCtrl.findDuplicate(c);
        List <Appointment> appointments = appointmentCtrl.getCenter(dbc);
        ArrayList <Patient> patientsList = new ArrayList();
        for (Appointment i:appointments){
            patientsList.add(i.getPatient());
        }
       
        EntityListModel<Patient> model = new EntityListModel(patientsList);
        AppointmentList.setModel(model);
        
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> AppointmentList;
    private javax.swing.JButton Remove;
    private javax.swing.JLabel VaccinationCenterLabel;
    private javax.swing.JLabel hl7_responseLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}