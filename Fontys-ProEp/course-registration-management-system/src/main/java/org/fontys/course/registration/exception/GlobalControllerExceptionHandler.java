package org.fontys.course.registration.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class handles exceptions thrown in the Spring Boot application.
 * It contains only general exceptions that might occur.
 * This class does not contain specific, detailed exception per data collection.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private void print(Exception ex) {
        // Printing the error, so we can see what it is.
        System.out.print("ERROR::::: " + ex.getMessage() + System.lineSeparator());
        ex.printStackTrace();
    }

    @ExceptionHandler(value = Exception.class)
    public void handleException(Exception ex, HttpServletResponse response) throws IOException {
        this.print(ex);
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public void handleDataIntegrityViolationException(Exception ex, HttpServletResponse response) throws IOException {
        this.print(ex);
        response.sendError(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
}
