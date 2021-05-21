
package Controller;

import Model.VaccinationCenter;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @authors Rania Charkaoui, Arthur Elskens & Gilles Feron
 */
public class VaccinationCenterController implements Serializable{
    public VaccinationCenterController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    

    
    public List<VaccinationCenter> findVaccinationCenterEntities() {
        // overloaded method
        return findVaccinationCenterEntities(true, -1, -1);
    }
    
    public List<VaccinationCenter> findVaccinationCenterEntities(int maxResults, int firstResult) {
        // overloaded method
        return findVaccinationCenterEntities(false, maxResults, firstResult);
    }
    
    
    private List<VaccinationCenter> findVaccinationCenterEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VaccinationCenter.class));
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

    public void create(VaccinationCenter vaccinationcenter) {

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vaccinationcenter);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public VaccinationCenter findDuplicate(VaccinationCenter c){
        EntityManager em = getEntityManager();
        List<VaccinationCenter> results = em.createNamedQuery("VaccinationCenter.findById").setParameter("id", c.getId()).getResultList();
        if( results.size() > 0 ){
        return results.get(0);
        }
        else
        return null;
    }
    
    public void edit(VaccinationCenter vaccinationcenter) {
        EntityManager em = null;
        em = getEntityManager();
        em.getTransaction().begin();
        System.out.println(vaccinationcenter.getId());
        VaccinationCenter persistentVaccinationCenter = em.find(VaccinationCenter.class, vaccinationcenter.getId());
        persistentVaccinationCenter = em.merge(vaccinationcenter);
        em.getTransaction().commit();
    }
    
    
}
