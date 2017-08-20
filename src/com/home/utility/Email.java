package com.home.utility;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.hibernate.Session;

import java.net.PasswordAuthentication;

public class Email {

//    public static void sendEmail(Team theTeam, String theEmail){
//        // Recipient's email ID needs to be mentioned.
//        String to = theEmail;
//
//        // Sender's email ID needs to be mentioned
//        String from = "BrettsFantasyFootballLeague@gmail.com";
//        final String username = "BrettsFantasyFootballLeague";//change accordingly
//        final String password = "7UDR8eEnQB5qsR6";//change accordingly
//
//        // Assuming you are sending email through relay.jangosmtp.net
//        String host = "smtp.gmail.com";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "25");
//
//        // Get the Session object.
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//        try {
//            // Create a default MimeMessage object.
//            Message message = new MimeMessage(session);
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(from));
//
//            // Set To: header field of the header.
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(to));
//
//            // Set Subject: header field
//            message.setSubject("Testing Subject");
//
//            // Send the actual HTML message, as big as you like
//            message.setContent(
//                    "<h1>This is actual message embedded in HTML tags</h1>",
//                    "text/html");
//
//            // Send message
//            Transport.send(message);
//
//            System.out.println("Sent message successfully....");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
}
