package org.fontys.course.tests;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;

import org.fontys.course.registration.Application;
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
import org.fontys.course.registration.model.Course;
import org.fontys.course.registration.repository.CourseRepository;
import org.fontys.course.registration.service.CourseService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseControllerIntegrationTests {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testRetrieveACourse() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/courses/IPV"),
                HttpMethod.GET, entity, String.class);

        String expected = "{code:IPV,name:Image Processing,description:Not Added yet...,block:7,maxSeats:26,filledSeat:3,regStartDate:null,regEndDate:null}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void addCourse() {

        Course course = new Course("SAI2", "Software Applications Integration", "How to integrate different softwares",
                3, 13, 20, 1, new Date(2017,01,12), new Date(2017,10,13));


            HttpEntity<Course> entity = new HttpEntity<Course>(course, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/courses"),
                HttpMethod.POST, entity, String.class);
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
