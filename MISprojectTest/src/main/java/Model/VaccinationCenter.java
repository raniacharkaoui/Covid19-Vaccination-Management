/**
 *
 * @author raniacharkaoui
 */
package Model;

import java.util.ArrayList;


public class VaccinationCenter {
    private Integer availableDoses;
    private String adress; 
    private ArrayList<Patient> waitingList = new ArrayList<Patient>();
    private ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
       
}
// add/remove patient from waitingList
// if len(appointmentList) < availableDoses -> isAvailable/nbr de places dispo -> call people