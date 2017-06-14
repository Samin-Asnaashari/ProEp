package org.fontys.course.tests;

import org.fontys.course.registration.Application;
import org.fontys.course.registration.model.Teacher;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeacherControllerIntegrationTests {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void RetrieveATeacher() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/teachers/26646"),
                HttpMethod.GET, entity, String.class);

        String expected = "{pcn:26646,email:t.example@fontys.nl,firstName:Bert,lastName:Gestle}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void addTeacher() {

        Teacher teacher = new Teacher(81234, "333", "awesome@fontys.nl", "Aaaa", "Bel", "");


        HttpEntity<Teacher> entity = new HttpEntity<Teacher>(teacher, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/teachers"),
                HttpMethod.POST, entity, String.class);
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
