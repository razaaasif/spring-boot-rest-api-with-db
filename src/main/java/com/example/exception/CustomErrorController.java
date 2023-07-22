package com.example.exception;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<ErrorDetails> handleError(WebRequest request) {
        // You can access error details from the request here and handle them as needed.
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "An error occurred.", request.getDescription(false), "GENERIC_ERROR");
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public String getErrorPath() {
        return "/error";
    }
}
