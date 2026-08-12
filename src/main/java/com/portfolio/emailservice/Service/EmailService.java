package com.portfolio.emailservice.Service;

import com.portfolio.emailservice.dto.ContactRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    public void sendEmail(ContactRequest request) {
        SimpleMailMessage mail = new SimpleMailMessage();
        //email - receiver
        mail.setTo("gfgh17268@gmail.com");
        // email - reply karne ke liye
        mail.setReplyTo(request.getEmail());
        // Subject
        mail.setSubject(
                "New Portfolio Message from " + request.getName()
        );
        // Email body
        mail.setText(
                "You received a new message from your portfolio.\n\n" +
                        "Name: " + request.getName() + "\n" +
                        "Email: " + request.getEmail() + "\n\n" +
                        "Message:\n" +
                        request.getMessage() + "\n\n"
                      //  + "You can reply directly to this email."
        );
        mailSender.send(mail);
    }
}
