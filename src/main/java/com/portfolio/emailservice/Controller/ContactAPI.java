package com.portfolio.emailservice.Controller;
import com.portfolio.emailservice.dto.ContactRequest;
import com.portfolio.emailservice.Service.EmailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactAPI {

        private final EmailService emailService;

        public ContactAPI(EmailService emailService) {
            this.emailService = emailService;
        }

        @PostMapping
        public ResponseEntity<String> sendMessage(
                @RequestBody @Valid ContactRequest request) {

            emailService.sendEmail(request);

            return ResponseEntity.ok("Message sent successfully!");
        }
    }

