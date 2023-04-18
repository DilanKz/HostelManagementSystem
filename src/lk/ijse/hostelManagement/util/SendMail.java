package lk.ijse.hostelManagement.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {

    private static SendMail mail;

    private SendMail(String email,String code){
        final String username = "falonh45@gmail.com";
        final String password = "tarj qxjg drtb kcmw";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("falonh45@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Verification code");
            message.setText("This is your verification code "+code);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static SendMail getInstance(String email,String code){
        if (mail==null){
            mail=new SendMail(email,code);
        }
        return mail;
    }

    private void sendAMail(String email,String code){
        //Insert your credentials

    }
}
