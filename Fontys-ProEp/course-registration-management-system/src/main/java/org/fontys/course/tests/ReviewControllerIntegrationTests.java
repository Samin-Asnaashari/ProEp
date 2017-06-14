package org.fontys.course.tests;

import org.fontys.course.registration.Application;
import org.fontys.course.registration.model.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReviewControllerIntegrationTests {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void RetrieveAReview() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/reviews/IPV"),
                HttpMethod.GET, entity, String.class);

        String expected = "[{id:1,description:good course,negativePoints:null,positivePoints:null}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void addReview() {

        Review review = new Review("description","boring course","teacher explains well", 5 );

        HttpEntity<Review> entity = new HttpEntity<Review>(review, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/reviews/IPV"),
                HttpMethod.POST, entity, String.class);
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
