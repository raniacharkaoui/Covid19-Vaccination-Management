
package Model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @authors Rania Charkaoui, Arthur Elskens & Gilles Feron
 */
@Entity
@Table(name = "VaccinationCenter")
@NamedQueries({
    @NamedQuery(name = "VaccinationCenter.findAll", query = "SELECT v FROM VaccinationCenter v"),
    @NamedQuery(name = "VaccinationCenter.findById", query = "SELECT v FROM VaccinationCenter v WHERE v.id = :id"),
    @NamedQuery(name = "VaccinationCenter.findByAvailableDoses", query = "SELECT v FROM VaccinationCenter v WHERE v.availableDoses = :availableDoses"),
    @NamedQuery(name = "VaccinationCenter.findByZone", query = "SELECT v FROM VaccinationCenter v WHERE v.zone = :zone"),
    @NamedQuery(name = "VaccinationCenter.findDuplicate", query = "SELECT v FROM VaccinationCenter v WHERE v.id = :id")})

public class VaccinationCenter implements Serializable {

    @Basic(optional = false)
    @Column(name = "zone")
    private String zone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccinationCenter")
    private Collection<WaitingList> waitingListCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccinationCenter")
    private ArrayList<Appointment> appointmentCollection;
    @OneToMany(mappedBy = "closestCenter")
    private Collection<Patient> patientCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "availableDoses")
    private int availableDoses;
   

    public VaccinationCenter() {
    }

    public VaccinationCenter(Integer id) {
        this.id = id;
    }

    public VaccinationCenter(Integer id, int availableDoses, String municipality) {
        this.id = id;
        this.availableDoses = availableDoses;
        
    }
    public VaccinationCenter(String zone) {
        this.zone = zone;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAvailableDoses() {
        return availableDoses;
    }

    public void setAvailableDoses(int availableDoses) {
        this.availableDoses = availableDoses;
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
        if (!(object instanceof VaccinationCenter)) {
            return false;
        }
        VaccinationCenter other = (VaccinationCenter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + id;
    }

    public ArrayList<Appointment> getAppointmentCollection() {
        return appointmentCollection;
    }

    public void setAppointmentCollection(ArrayList<Appointment> appointmentCollection) {
        this.appointmentCollection = appointmentCollection;
        
    }

    public Collection<Patient> getPatientCollection() {
        return patientCollection;
    }

    public void setPatientCollection(Collection<Patient> patientCollection) {
        this.patientCollection = patientCollection;
        
        
    }

    public Collection<WaitingList> getWaitingListCollection() {
        return waitingListCollection;
    }

    public void setWaitingListCollection(Collection<WaitingList> waitingListCollection) {
        this.waitingListCollection = waitingListCollection;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
    
}
