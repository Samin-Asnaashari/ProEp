package org.fontys.course.tests;

import org.fontys.course.registration.Application;
import org.fontys.course.registration.model.Course;
import org.fontys.course.registration.model.enums.Major;
import org.fontys.course.registration.model.enums.StudentType;
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
import org.fontys.course.registration.model.Student;
import org.fontys.course.registration.repository.StudentRepository;
import org.fontys.course.registration.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerIntegrationTests {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void RetrieveAStudent() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/students/37272"),
                HttpMethod.GET, entity, String.class);

        String expected = "{pcn:37272,email:agnes@student.fontys.nl,firstName:Agnes,lastName:Wasee,studentNumber:637367,studentType:REGULAR}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void addStudent() {

        Student student = new Student(4567, "234", "345@student.fontys.nl", "User", "Test", 272372, StudentType.REGULAR, Major.SOFTWARE, 8.0);


        HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/students"),
                HttpMethod.POST, entity, String.class);
    }

    @Test
    public void updateStudent() {

        Student student = new Student(4567, "234", "123@student.fontys.nl", "User", "Test12", 272372, StudentType.REGULAR, Major.SOFTWARE, 8.0);


        HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/students"),
                HttpMethod.PUT, entity, String.class);
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
