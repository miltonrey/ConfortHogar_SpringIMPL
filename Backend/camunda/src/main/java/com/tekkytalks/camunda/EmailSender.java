package com.tekkytalks.camunda;

import java.util.Properties;
import javax.mail.Session;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EmailSender {

    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        // Dirección de correo
        mailSender.setUsername("camundamilton");

        mailSender.setPassword("rjsssgvtqkoihuaz");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        Session session = Session.getInstance(props);
        mailSender.setSession(session);

        return mailSender;
    }

}
