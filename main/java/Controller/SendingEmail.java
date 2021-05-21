
package Controller;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @authors Rania Charkaoui, Arthur Elskens & Gilles Feron
 */
public class SendingEmail {
    
    public void sendEmail(String toAddress,
        String subject, String message) throws AddressException,
        MessagingException {
        /**
         * Allow to send an email with a few parameters
         * 
         * @param toAdress
         * @param subject
         * @param message           
         */

        final String username = "vaccination.management@gmail.com";
        final String password = "2021covid";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("vaccination.management@gmail.com"));
            msg.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toAddress)
            );
            msg.setSubject(subject);
            msg.setText(message);

            Transport.send(msg);

            System.out.println("Email successfully sent");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

}
}
