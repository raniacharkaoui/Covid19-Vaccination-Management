package Model;

import Model.Patient;
import Model.VaccinationCenter;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-05-18T22:42:59", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Appointment.class)
public class Appointment_ { 

    public static volatile SingularAttribute<Appointment, VaccinationCenter> vaccinationCenter;
    public static volatile SingularAttribute<Appointment, Date> appointmentTime;
    public static volatile SingularAttribute<Appointment, Patient> patient;
    public static volatile SingularAttribute<Appointment, Integer> id;

}