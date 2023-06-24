package com.developersstack.edumanager.util.tools;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {
    public SendEmail(String email, String subject, String setText){
        final String hostEmail = "amith@macsmiddleeast.com";
        final String password = "M@api98S";

        System.out.println("Sending Email" + hostEmail + " to " + email);

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "mail.macsmiddleeast.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(hostEmail, password);
            }
        });

        Message message = messageContent(session, email, subject, setText);
    }

    private Message messageContent(Session session, String receiver, String subject, String setText) {
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("amith@macsmiddleeast.com"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            msg.setSubject(subject);
            msg.setText(setText);
            Transport.send(msg);
            return msg;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
