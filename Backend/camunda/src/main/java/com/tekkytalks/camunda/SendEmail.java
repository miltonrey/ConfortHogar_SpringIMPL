package com.tekkytalks.camunda;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SendEmail {

    public void sendEmail(String toAddress, long status, String rejectReason) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toAddress);

        msg.setSubject("Cotizacion Mueble");

        msg.setText("Estimado cliente " + rejectReason + ". Recuerde que el tiempo máximo de espera es 15 días");

        EmailSender emailSender = new EmailSender();
        JavaMailSender javamailsender = emailSender.getJavaMailSender();
        javamailsender.send(msg);

    }

}
