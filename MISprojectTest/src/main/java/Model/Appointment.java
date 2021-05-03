/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import java.util.Date;
import java.util.List;



/**
 *
 * @author raniacharkaoui
 */

public class Appointment implements Serializable {
    private Integer idAppointment;
    private Date appointmentTime;
    private Patient idPatient;

    public Appointment() {
    }

    public Appointment(Integer idappointment) {
        this.idAppointment = idappointment;
    }

    public Integer getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(Integer idappointment) {
        this.idAppointment = idappointment;
    }

    public Date getAppointmenttime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmenttime) {
        this.appointmentTime = appointmenttime;
    }
    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idpatient) {
        this.idPatient = idpatient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAppointment != null ? idAppointment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.idAppointment == null && other.idAppointment != null) || (this.idAppointment != null && !this.idAppointment.equals(other.idAppointment))) {
            return false;
        }
        return true;
    }

    
    
}
