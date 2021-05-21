/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Patient;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author raniacharkaoui
 */
public class PatientController  implements Serializable {

    public PatientController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    

    
    public List<Patient> findPatientEntities() {
        // overloaded method
        return findPatientEntities(true, -1, -1);
    }
    
    public List<Patient> findPatientEntities(int maxResults, int firstResult) {
        // overloaded method
        return findPatientEntities(false, maxResults, firstResult);
    }
    
    
    private List<Patient> findPatientEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Patient.class));
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

    public void create(Patient patient) {

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(patient);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
                   }
    }

    public void edit(Patient patient) {
        EntityManager em = null;
       
            em = getEntityManager();
            em.getTransaction().begin();
            //System.out.println(patient.getId());
            Patient persistentPatient = em.find(Patient.class, patient.getId());

          
            patient = em.merge(patient);

            em.getTransaction().commit();
    }
    public Patient findDuplicate(Patient p){
        EntityManager em = getEntityManager();
        List<Patient> results = em.createNamedQuery("Patient.findDuplicate").setParameter("niss", p.getNiss()).getResultList();
        
        if( results.size() > 0 ){
            return results.get(0);
        }
        else
            return null;
    }
    
    
}
