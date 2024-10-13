package com.dbp.projectofinal.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.File;
import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;


    public void sendEmail(String to,String name) throws MessagingException, IOException, MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        Context context = new Context();
        context.setVariable("name", name);

        String htmlContent = templateEngine.process("registeremail.html", context);

        helper.setText(htmlContent, true);  // True indica que el contenido es HTML
        helper.setTo(to);
        helper.setSubject("Registro Confirmado");

        // Adjuntar imagen y usar Content-ID (CID)
        FileSystemResource res = new FileSystemResource(new File("src/main/resources/templates/imagen.jpeg"));
        helper.addInline("logoImage", res); // El Content-ID debe coincidir con el 'cid:' del HTML


        mailSender.send(mimeMessage);
    }
}