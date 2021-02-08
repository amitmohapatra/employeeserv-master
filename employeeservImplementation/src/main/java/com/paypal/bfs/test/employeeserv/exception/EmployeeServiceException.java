package com.paypal.bfs.test.employeeserv.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(INTERNAL_SERVER_ERROR)
public class EmployeeServiceException extends RuntimeException {

    public EmployeeServiceException(String message) {
        super(message);
    }
}
