package Model;


import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @authors Rania Charkaoui, Arthur Elskens & Gilles Feron
 */
@Entity
@Table(name = "Patient")
@NamedQueries({
    @NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p"),
    @NamedQuery(name = "Patient.findById", query = "SELECT p FROM Patient p WHERE p.id = :id"),
    @NamedQuery(name = "Patient.findByFirstName", query = "SELECT p FROM Patient p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Patient.findByLastName", query = "SELECT p FROM Patient p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Patient.findByMunicipality", query = "SELECT p FROM Patient p WHERE p.municipality = :municipality"),
    @NamedQuery(name = "Patient.findByEmail", query = "SELECT p FROM Patient p WHERE p.email = :email"),
    @NamedQuery(name = "Patient.findByNiss", query = "SELECT p FROM Patient p WHERE p.niss = :niss"),
    @NamedQuery(name = "Patient.findDuplicate", query = "SELECT p FROM Patient p WHERE p.niss = :niss")})
public class Patient implements Serializable {


    @Column(name = "score")
    private Integer score;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private ArrayList<WaitingList> waitingListCollection;

    @Column(name = "comorbidity")
    private Boolean comorbidity;
    @Column(name = "Vaccinated")
    private Integer vaccinated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private ArrayList<Appointment> appointmentCollection;
    @JoinColumn(name = "closestCenter", referencedColumnName = "id")
    @ManyToOne
    private VaccinationCenter closestCenter;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "firstName")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "lastName")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "municipality")
    private String municipality;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "NISS")
    private String niss;

    public Patient() {
    }

    public Patient(Integer id) {
        this.id = id;
    }

    public Patient(Integer id, String firstName, String lastName, String municipality, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.municipality = municipality;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNiss() {
        return niss;
    }

    public void setNiss(String niss) {
        this.niss = niss;
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
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NISS: "+ niss+ " Nom: "+ lastName + " Prénom: " + firstName;
    }

    public Boolean getComorbidity() {
        return comorbidity;
    }

    public void setComorbidity(Boolean comorbidity) {
        this.comorbidity = comorbidity;
    }

    public Integer getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(Integer vaccinated) {
        this.vaccinated = vaccinated;
    }

    public ArrayList<Appointment> getAppointmentCollection() {
        return appointmentCollection;
    }

    public void setAppointmentCollection(ArrayList<Appointment> appointmentCollection) {
        this.appointmentCollection = appointmentCollection;
    }

    public VaccinationCenter getClosestCenter() {
        return closestCenter;
    }

    public void setClosestCenter(VaccinationCenter closestCenter) {
        this.closestCenter = closestCenter;
    }

    public Integer getScore() {
        return score;
    }

    
    public void setScore(Integer score) {
        int comorb = 0;
        if (this.getComorbidity() == true){
            comorb = 1;
        }
        
        int age = Integer.parseInt(niss.substring(0, 2));
        if (age > 80 || age <= 21){
            // between 1981 and 2021/1921 we suppose that centerenarian are already vaccinated
            age = 0; //minimum score will be 10 if comorb equals 1 and 0 otherwise for young people
        }
        else {
            age = 121 - age;
        }
        score = comorb*10 + age;
        this.score = score;
    }

    public ArrayList<WaitingList> getWaitingListCollection() {
        return waitingListCollection;
    }

    public void setWaitingListCollection(ArrayList<WaitingList> waitingListCollection) {
        this.waitingListCollection = waitingListCollection;
    }

    
    
}
