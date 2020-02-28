package com.example;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.example.Client;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class PactConsumerTest {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("test_provider", "localhost", 8081, this);

    @Pact(consumer = "test_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        String date = "2019-02-28T15:31:20+10:00";

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("test GET")
                .uponReceiving("GET REQUEST")
                .path("/provider")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body("{\"test\": \"NO\", \"validDate\": \"" + date + "\", \"count\": 100}")
                .toPact();
    }

    @Test
    @PactVerification()
    public void canProcessTheJsonPayloadFromTheProvider() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response = new RestTemplate()
                .getForEntity(mockProvider.getUrl() + "/provider", String.class);

        assertNotNull(response);
    }

}
