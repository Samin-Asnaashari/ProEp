package org.fontys.course.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.UnsupportedEncodingException;


@SpringBootApplication
@EnableScheduling
public class Application {
    public static void main(String[] args) throws UnsupportedEncodingException {
        SpringApplication.run(Application.class, args);
    }
}
