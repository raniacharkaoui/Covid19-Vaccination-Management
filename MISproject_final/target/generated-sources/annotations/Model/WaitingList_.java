package Model;

import Model.Patient;
import Model.VaccinationCenter;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-05-21T13:48:24", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(WaitingList.class)
public class WaitingList_ { 

    public static volatile SingularAttribute<WaitingList, VaccinationCenter> vaccinationCenter;
    public static volatile SingularAttribute<WaitingList, Patient> patient;
    public static volatile SingularAttribute<WaitingList, Integer> id;

}