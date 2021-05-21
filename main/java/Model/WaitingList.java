package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @authors Rania Charkaoui, Arthur Elskens & Gilles Feron
 */
@Entity
@Table(name = "WaitingList")
@NamedQueries({
    @NamedQuery(name = "WaitingList.findAll", query = "SELECT w FROM WaitingList w"),
    @NamedQuery(name = "WaitingList.findById", query = "SELECT w FROM WaitingList w WHERE w.id = :id"),
    @NamedQuery(name = "WaitingList.sort", query = "SELECT p FROM WaitingList w, Patient p WHERE w.patient.id = p.id ORDER BY p.score DESC"),
    @NamedQuery(name = "WaitingList.findByPatient", query = "SELECT w FROM WaitingList w WHERE w.patient = :patient"),
    @NamedQuery(name = "WaitingList.findByCenter", query = "SELECT w FROM WaitingList w WHERE w.vaccinationCenter = :vaccinationCenter")
})
public class WaitingList implements Serializable {

    @JoinColumn(name = "patient", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Patient patient;
    @JoinColumn(name = "vaccinationCenter", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private VaccinationCenter vaccinationCenter;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public WaitingList() {
    }

    public WaitingList(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WaitingList)) {
            return false;
        }
        WaitingList other = (WaitingList) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.WaitingList[ id=" + id + " ]";
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }
    
}
