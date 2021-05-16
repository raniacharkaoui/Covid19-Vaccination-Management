/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author raniacharkaoui
 */
@Entity
@Table(name = "VaccinationCenter")
@NamedQueries({
    @NamedQuery(name = "VaccinationCenter.findAll", query = "SELECT v FROM VaccinationCenter v"),
    @NamedQuery(name = "VaccinationCenter.findById", query = "SELECT v FROM VaccinationCenter v WHERE v.id = :id"),
    @NamedQuery(name = "VaccinationCenter.findByAvailableDoses", query = "SELECT v FROM VaccinationCenter v WHERE v.availableDoses = :availableDoses"),
    @NamedQuery(name = "VaccinationCenter.findByMunicipality", query = "SELECT v FROM VaccinationCenter v WHERE v.municipality = :municipality")})
public class VaccinationCenter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "availableDoses")
    private int availableDoses;
    @Basic(optional = false)
    @Column(name = "municipality")
    private String municipality;

    public VaccinationCenter() {
    }

    public VaccinationCenter(Integer id) {
        this.id = id;
    }

    public VaccinationCenter(Integer id, int availableDoses, String municipality) {
        this.id = id;
        this.availableDoses = availableDoses;
        this.municipality = municipality;
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

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
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
        return "Model.VaccinationCenter[ id=" + id + " ]";
    }
    
}
