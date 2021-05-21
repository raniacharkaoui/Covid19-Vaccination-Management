package Model;

import Model.Appointment;
import Model.Patient;
import Model.WaitingList;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-05-21T13:48:24", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(VaccinationCenter.class)
public class VaccinationCenter_ { 

    public static volatile SingularAttribute<VaccinationCenter, String> zone;
    public static volatile SingularAttribute<VaccinationCenter, Integer> availableDoses;
    public static volatile CollectionAttribute<VaccinationCenter, Patient> patientCollection;
    public static volatile SingularAttribute<VaccinationCenter, Integer> id;
    public static volatile CollectionAttribute<VaccinationCenter, WaitingList> waitingListCollection;
    public static volatile ListAttribute<VaccinationCenter, Appointment> appointmentCollection;

}