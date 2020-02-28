package com.example;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class PactConsumerTest {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("test_provider", "localhost", 8081, this);

    @Pact(consumer = "test_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
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
                .body("{\"condition\": true, \"name\": \"tom\"}")
                .toPact();
    }

    @Test
    @PactVerification()
    public void canProcessTheJsonPayloadFromTheProvider() {

        ResponseEntity<String> response = new RestTemplate()
                .getForEntity(mockProvider.getUrl() + "/provider", String.class);

        assertNotNull(response);
    }

}
