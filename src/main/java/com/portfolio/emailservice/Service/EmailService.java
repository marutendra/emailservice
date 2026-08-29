package com.portfolio.emailservice.Service;

import com.portfolio.emailservice.dto.ContactRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class EmailService {

    private final RestClient restClient;

    @Value("${resend.api-key}")
    private String resendApiKey;

    public EmailService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.resend.com")
                .build();
    }

    public void sendEmail(ContactRequest request) {

        Map<String, Object> body = Map.of(
                "from", "Portfolio <onboarding@resend.dev>",
                "to", "gfgh17268@gmail.com",
                "reply_to", request.getEmail(),
                "subject", "New Portfolio Message from " + request.getName(),
                "text",
                "You received a new message from your portfolio.\n\n" +
                        "Name: " + request.getName() + "\n" +
                        "Email: " + request.getEmail() + "\n\n" +
                        "Message:\n" +
                        request.getMessage()
        );

        restClient.post()
                .uri("/emails")
                .header(
                        HttpHeaders.AUTHORIZATION,
                        "Bearer " + resendApiKey
                )
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toBodilessEntity();
    }
}