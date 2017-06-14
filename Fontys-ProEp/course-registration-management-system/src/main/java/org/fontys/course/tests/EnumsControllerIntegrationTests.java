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
import org.fontys.course.registration.model.enums.CourseType;
import org.fontys.course.registration.model.enums.Major;
import org.fontys.course.registration.service.EnumsService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnumsControllerIntegrationTests {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void RetrieveAllPossibleCourseStates() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/enums/courseTypes"),
                HttpMethod.GET, entity, String.class);

        String expected = "[MANDATORY,ELECTIVE,NOTAVAILABLE]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void RetrieveAllPossibleMajorValues() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/enums/majors"),
                HttpMethod.GET, entity, String.class);

        String expected = "[SOFTWARE,BUSINESS,TECHNOLOGY]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
