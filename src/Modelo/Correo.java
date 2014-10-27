
package Modelo;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


public class Correo {
        private Properties props;
        private Session session;
        private MimeMessage message;
        
        public Correo(){
            props = new Properties();  
            props.put("mail.smtp.host", "smtp.gmail.com");  
            props.put("mail.smtp.socketFactory.port", "465");  
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
            props.put("mail.smtp.auth", "true");  
            props.put("mail.smtp.port", "465");  
            
            session = Session.getDefaultInstance(props,  
                new javax.mail.Authenticator() {  

                    protected PasswordAuthentication getPasswordAuthentication() {  
                    return new PasswordAuthentication("patronesdyf@gmail.com","patrones123");
                }  
            });  
        }
        
        public String sendMail(String to){
            try {  
                message = new MimeMessage(session);  
                message.setFrom(new InternetAddress("patronesdyf@gmail.com"));
                message.addRecipients(Message.RecipientType.TO,InternetAddress.parse(to));  
                message.setSubject("UCVBuster - Cartelera");  
                message.setText("Alquilen videos perrrrrras");  

                //send message  
                Transport.send(message);  

                return("El mensaje ha sido enviado satisfactoriamente.");  

            } catch (MessagingException e) {          
                return("No se pudo enviar el mensaje");
            }
        }
}
