
package Services;

import Controller.PatientController;
import Controller.SendingEmail;
import Controller.WaitingListController;
import Model.Patient;
import Model.WaitingList;
import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.HL7Service;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v23.message.ACK;
import ca.uhn.hl7v2.model.v23.message.ADT_A08;
import ca.uhn.hl7v2.model.v23.message.SRM_S01;
import ca.uhn.hl7v2.model.v23.segment.ARQ;
import ca.uhn.hl7v2.model.v23.segment.EVN;
import ca.uhn.hl7v2.model.v23.segment.MSH;
import ca.uhn.hl7v2.model.v23.segment.PID;
import ca.uhn.hl7v2.model.v23.segment.PV1;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.protocol.ReceivingApplication;
import ca.uhn.hl7v2.protocol.ReceivingApplicationException;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @authors Rania Charkaoui, Arthur Elskens & Gilles Feron
 */
public class HL7Services {

    private HL7Service server;

    public void startServer() {
        int port = 54321;
        HapiContext ctx = new DefaultHapiContext();
        server = ctx.newServer(port, false);

        ReceivingApplication<Message> adthandler = new ADTReceiverApplication();
        server.registerApplication("ADT", "A08", adthandler);

        ReceivingApplication<Message> srmhandler = new SRMReceiverApplication();
        server.registerApplication("SRM", "S01", srmhandler);

        try {
            server.startAndWait();
        } catch (InterruptedException ex) {
            Logger.getLogger(HL7Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopServer() {
        server.stop();
    }

    public static boolean sendSRM_S01(Patient pat, String host, int port) {
        try {
            SRM_S01 srm = new SRM_S01();
            srm.initQuickstart("SRM", "S01", "P"); //D for debugging otherwise P for production

            MSH msh = srm.getMSH();
            msh.getSendingApplication().getNamespaceID().setValue("Patient");

            ARQ arq = srm.getARQ();
            arq.getArq1_PlacerAppointmentID().getEntityIdentifier().setValue("*"); // should correspond to new Object:WaitingList if approved
            arq.getArq1_PlacerAppointmentID().getEi3_UniversalID().setValue("VaccinationCenter");

            PID pid = srm.getPATIENT(0).getPID();
            pid.getPatientIDInternalID(0).getID().setValue(String.valueOf(pat.getId()));
            pid.getPatientIDInternalID(0).getIdentifierTypeCode().setValue("PI"); //patient internal identifier
            pid.getPatientName(0).getFamilyName().setValue(pat.getLastName());
            pid.getPatientName(0).getGivenName().setValue(pat.getFirstName());
            pid.getPatientName(0).getNameTypeCode().setValue("L"); //legal name

            HapiContext context = new DefaultHapiContext();
//            Parser parser = context.getPipeParser();
//            String encodedMessage = parser.encode(srm);
//
//            System.out.println("Printing HL7 SRM_S01 message:");
//            System.out.println(encodedMessage);

            Connection conn = context.newClient(host, port, false);

            Initiator init = conn.getInitiator();
            ACK response = (ACK) init.sendAndReceive(srm);

//            String responseString = parser.encode(response);
//            System.out.println("Response:");
//            System.out.println(responseString);

            return ("AA".equals(response.getMSA().getAcknowledgementCode().getValue()));
        } catch (HL7Exception | IOException | LLPException ex) {
            Logger.getLogger(HL7Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private static class SRMReceiverApplication implements ReceivingApplication<Message> {
        
        private final EntityManagerFactory emfac = Persistence.createEntityManagerFactory("MISproject2_PU");
        private final PatientController patientCtrl = new PatientController(emfac);
        private final WaitingListController listCtrl = new WaitingListController(emfac);
        

        public SRMReceiverApplication() {
        }

        @Override
        public Message processMessage(Message t, Map<String, Object> map) throws ReceivingApplicationException, HL7Exception {
//            System.out.println("Receiving SRM_S01 message.");
            SRM_S01 msg = (SRM_S01) t;
            
            Patient p = new Patient();
            
            p.setId(Integer.parseInt(msg.getPATIENT(0).getPID().getPatientIDInternalID(0).getID().getValue()));

            EntityManager em = null;
            em = patientCtrl.getEntityManager();
            
            Patient dbpatient = em.find(Patient.class, p.getId());
            dbpatient.setScore(dbpatient.getScore());
            patientCtrl.edit(dbpatient);
            
            if (dbpatient.getScore() > 10){
                //System.out.println("à ajouter à la waitingList");
                WaitingList list = new WaitingList(); 
                list.setPatient(dbpatient);
                list.setVaccinationCenter(dbpatient.getClosestCenter());
                listCtrl.create(list);
                //System.out.println("Patient added in waiting list");
                
                SendingEmail email = new SendingEmail();
                String subject ="Votre demande de vaccination";
                String message = "Bonjour " + dbpatient.getFirstName() +" "+ dbpatient.getLastName() + ",\n" +
                    " \n"+
                    " \n"+
                    "Merci pour votre inscription.\n" +
                    "Vous serez contactés une fois que votre vaccination sera possible.";
                
               
            }
            else{
                SendingEmail email = new SendingEmail();
                String subject ="Votre demande de vaccination";
                String message = "Bonjour " + dbpatient.getFirstName() +" "+ dbpatient.getLastName() + ",\n" +
                        " \n"+
                        " \n"+
                        "Malheureusment, vous n'êtes pas éligible à la vaccination selon les directives actuelles.\n" +
                        "N'hésitez pas à suivre l'actualité et retentez de vous inscrire";
                        
                try {
                    email.sendEmail(dbpatient.getEmail(),subject,message);
                } catch (MessagingException ex) {
                    Logger.getLogger(HL7Services.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            String encodedMessage = new DefaultHapiContext().getPipeParser().encode(t);
//            System.out.println("Received meesage:\n" + encodedMessage + "\n\n");

            try {
                return t.generateACK();
            } catch (IOException ex) {
                throw new HL7Exception(ex);
            }
        }

        @Override
        public boolean canProcess(Message t) {
            return (t instanceof SRM_S01);
        }
    }

    public static boolean sendADT_A08(Patient pat, String host, int port) {
        try {
            ADT_A08 adt = new ADT_A08();
            adt.initQuickstart("ADT", "A08", "P");

            MSH msh = adt.getMSH();
            msh.getSendingApplication().getNamespaceID().setValue("VaccinationCenter");

            EVN evn = adt.getEVN();
            evn.getEventTypeCode().setValue("A08");
            evn.getRecordedDateTime().getTimeOfAnEvent().setValue(new Date());
            evn.getEventReasonCode().setValue("02"); //02 for health practitioner order

            PID pid = adt.getPID();
            pid.getPatientIDInternalID(0).getID().setValue(String.valueOf(pat.getId()));
            pid.getPatientIDInternalID(0).getIdentifierTypeCode().setValue("PI");
            pid.getPatientName(0).getFamilyName().setValue(pat.getLastName());
            pid.getPatientName(0).getGivenName().setValue(pat.getFirstName());
            pid.getPatientName(0).getNameTypeCode().setValue("L");

            PV1 pv1 = adt.getPV1();
            pv1.getPatientClass().setValue("I"); //O for Outpatient for institutions not recording public health screening data; for patient grade level 16 - other 
            //pv1.getAdmissionType().setValue("R"); R routine, A accident, E emergency,...

            HapiContext context = new DefaultHapiContext();
//            Parser parser = context.getPipeParser();
//            String encodedMessage = parser.encode(adt);
//
//            System.out.println("Printing HL7 ADT_A08 message:");
//            System.out.println(encodedMessage);
            
            Connection conn = context.newClient(host, port, false);

            Initiator init = conn.getInitiator();
            ACK response = (ACK) init.sendAndReceive(adt);

//            String responseString = parser.encode(response);
//            System.out.println("Response:");
//            System.out.println(responseString);

            return ("AA".equals(response.getMSA().getAcknowledgementCode().getValue()));
        } catch (HL7Exception | IOException | LLPException ex) {
            Logger.getLogger(HL7Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private static class ADTReceiverApplication implements ReceivingApplication<Message> {
        
        private final EntityManagerFactory emfac = Persistence.createEntityManagerFactory("MISproject2_PU");
        private final PatientController patientCtrl = new PatientController(emfac);

        public ADTReceiverApplication() {
        }

        @Override
        public Message processMessage(Message t, Map<String, Object> map) throws ReceivingApplicationException, HL7Exception {
//            System.out.println("Receiving ADT_A08 message.");
            ADT_A08 msg = (ADT_A08) t;
            
            Patient p = new Patient();
            
            p.setId(Integer.parseInt(msg.getPID().getPatientIDInternalID(0).getID().getValue()));
            

            EntityManager em = null;
            em = patientCtrl.getEntityManager();
            
            Patient dbpatient = em.find(Patient.class, p.getId());
            int status = dbpatient.getVaccinated();
            
            if ( status == 0 ){
                dbpatient.setVaccinated(1);
            }
            else{   
                dbpatient.setVaccinated(2);
            }
            
            patientCtrl.edit(dbpatient);

            try {
                return t.generateACK();
            } catch (IOException ex) {
                throw new HL7Exception(ex);
            }
        }

        @Override
        public boolean canProcess(Message t) {
            return (t instanceof ADT_A08);
        }
    }
}
