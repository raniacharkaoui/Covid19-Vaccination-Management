package Controller;

import Model.Appointment;
import Model.Patient;
import Model.VaccinationCenter;
import Model.WaitingList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

/**
 *
 * @authors Rania Charkaoui, Arthur Elskens & Gilles Feron
 */
public class WaitingListController implements Serializable{
    public WaitingListController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public void create(WaitingList list) {

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(list);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
// code à ajouter somewhere où on va créer et update la liste
//    WaitingList list = null; 
//    Patient patient; 
//    public void updateList(){
//        if( list == null ){
//            list = new WaitingList(); 
//            System.out.println("New patient in List created");
//            System.out.println(list.getId());
//        }
//        
//        
//        list.setPatient(patient);
//        list.setVaccinationCenter(patient.getClosestCenter());
//        System.out.println("Patient added in waiting list");
//        
//    }
    public ArrayList<Patient> sortPatient(){
        /**
         * uses Query WaitingList.sort to sort Patient
         * by their score in descending order
         *           
         */
        EntityManager em = getEntityManager();
        
        
        List<Patient> results = em.createNamedQuery("WaitingList.sort").getResultList();
        
        ArrayList<Patient> ifirstpatients = new ArrayList<>(results);
        for (int k=0; k<ifirstpatients.size();k++){
            //System.out.println(ifirstpatients.get(k));
        }
        
        if( results.size() > 0 ){
            return ifirstpatients;
        }
        else
            return null;
        }
   
    public void callPatient(Patient p) throws MessagingException{
        /**
         * Sends email to patient p 
         * to call him/her at his/her vaccination center
         * and creates appointment accordingly   
         * @param Patient 
         */
        String address = p.getEmail();
        String subject = "Votre rendez-vous de vaccination";
        String message = "Bonjour " + p.getFirstName() +" "+ p.getLastName()+",\n" +
                        " \n"+
                        " \n"+
                "Votre vaccin est réservé et vous attend."+
                "Votre centre de vaccination est le suivant " + "Centre " +p.getClosestCenter().toString()+".";
        SendingEmail mail = new SendingEmail();
        mail.sendEmail(address, subject, message);
        Appointment a = new Appointment();
        a.setVaccinationCenter(p.getClosestCenter());
        a.setPatient(p);
        a.setAppointmentTime(new Date());
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MISproject2_PU");
        AppointmentController ctrl = new AppointmentController(emf);
        ctrl.create(a);
    }
    
    public ArrayList<WaitingList> findDuplicate(VaccinationCenter v){
        /**
         * Find duplicate of WaitingList object in the database
         * that has the same VaccinationCenter v as the one given in 
         * @param VaccinationCenter  
         */
        EntityManager em = getEntityManager();
        List<WaitingList> results = em.createNamedQuery("WaitingList.findByCenter").setParameter("vaccinationCenter", v).getResultList();
        

        if( results.size() > 0 ){
        return (ArrayList<WaitingList>) results;
        }
        else
        return null;
    }
    
    public void destroy(Integer id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            WaitingList list;
            try {
                list = em.getReference(WaitingList.class, id);
                list.getId();
                em.remove(list);
            } catch (EntityNotFoundException enfe) {
                //throw new NonexistentEntityException("The appointment with id " + id + " no longer exists.", enfe);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    
    public WaitingList findPatient(Patient p){
        
        EntityManager em = getEntityManager();
        List<WaitingList> results = em.createNamedQuery("WaitingList.findByPatient").setParameter("patient", p).getResultList();
        

        if( results.size() > 0 ){
            return results.get(0);
        }
        else
            return null;
    }
}
