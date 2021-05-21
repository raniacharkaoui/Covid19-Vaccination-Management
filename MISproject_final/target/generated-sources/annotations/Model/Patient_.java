package Model;

import Model.Appointment;
import Model.VaccinationCenter;
import Model.WaitingList;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-05-21T13:48:24", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Patient.class)
public class Patient_ { 

    public static volatile SingularAttribute<Patient, String> lastName;
    public static volatile SingularAttribute<Patient, Integer> score;
    public static volatile SingularAttribute<Patient, String> firstName;
    public static volatile SingularAttribute<Patient, Boolean> comorbidity;
    public static volatile SingularAttribute<Patient, String> municipality;
    public static volatile SingularAttribute<Patient, String> niss;
    public static volatile SingularAttribute<Patient, VaccinationCenter> closestCenter;
    public static volatile SingularAttribute<Patient, Integer> id;
    public static volatile SingularAttribute<Patient, Integer> vaccinated;
    public static volatile ListAttribute<Patient, WaitingList> waitingListCollection;
    public static volatile SingularAttribute<Patient, String> email;
    public static volatile ListAttribute<Patient, Appointment> appointmentCollection;

}