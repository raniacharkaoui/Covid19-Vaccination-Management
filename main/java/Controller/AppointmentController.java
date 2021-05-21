
package Controller;

import Model.Appointment;
import Model.Patient;
import Model.VaccinationCenter;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @authors Rania Charkaoui, Arthur Elskens & Gilles Feron
 */
public class AppointmentController implements Serializable{
    

    public AppointmentController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    
    public List<Patient> findAppointmentEntities(int maxResults, int firstResult) {
        // overloaded method
        return findAppointmentEntities(false, maxResults, firstResult);
    }
    
    
    private List<Patient> findAppointmentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Appointment.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public void create(Appointment appointment) {
       
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            Patient patient = appointment.getPatient();
            if (patient != null) {
                patient = em.getReference(patient.getClass(), patient.getId());
                appointment.setPatient(patient);
            }
            VaccinationCenter center = appointment.getVaccinationCenter();
            if (center != null) {
                center = em.getReference(center.getClass(), center.getId());
                appointment.setVaccinationCenter(center);
            }
            em.persist(appointment);
           
            if (patient != null) {
                patient.getAppointmentCollection().add(appointment);
                patient = em.merge(patient);
            }
            if (center != null) {
                center.getAppointmentCollection().add(appointment);
                center = em.merge(center);
            }
           
            
            em.getTransaction().commit();
            System.out.println("Create ok");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
        
    
    public void destroy(Integer id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Appointment appointment;
            try {
                appointment = em.getReference(Appointment.class, id);
                appointment.getId();
                em.remove(appointment);
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
    
    public List<Appointment> getCenter(VaccinationCenter c){
        EntityManager em = getEntityManager();
        System.out.println("center id " +  c.getId());
        
        List<Appointment> results = em.createNamedQuery("Appointment.findByCenter").setParameter("vaccinationCenter", c).getResultList();
        
        if( results.size() > 0 ){
            return results;
        }
        else
            return null;
    }
    
}
     