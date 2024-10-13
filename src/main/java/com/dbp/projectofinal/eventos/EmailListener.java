package com.dbp.projectofinal.eventos;

import com.dbp.projectofinal.email.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class EmailListener {
    @Autowired
    private EmailService emailService;

    @Async
    @EventListener
    public void handleSendEmailEvent(SendMailEvent event) throws MessagingException, IOException {
        Map<String, Object> request = event.getDatos();

        String to = (String) request.get("to");
        String name = (String) request.get("name");
        emailService.sendEmail(to,name);
    }
}
