package com.javalife365.javalife365api.email;

import com.javalife365.javalife365api.exception.EmailDeliveryFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    public String from;


    @Async
    public void sendEmailAfterRegistration(String to) {
        try {
            log.info("attempting send email to {} after registering", to);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject("Welcome to our platform");
            message.setText(
                            "Hello " + to + ",\n\n"
                            + "Thank you for choosing us. \n\n" +
                            "Regards,\nJavaLife365"
            );
            javaMailSender.send(message);
            log.info("email sent successfully to {} after registering", to);
        } catch (Exception ex) {
            log.info("Failed to send email to {} after registering", to);
            throw new EmailDeliveryFailedException(ex.getMessage());
        }
    }
}
