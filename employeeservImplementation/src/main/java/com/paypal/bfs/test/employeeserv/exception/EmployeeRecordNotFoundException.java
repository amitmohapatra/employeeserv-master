package com.paypal.bfs.test.employeeserv.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class EmployeeRecordNotFoundException extends RuntimeException {

    public EmployeeRecordNotFoundException(String message) {
        super(message);
    }
}
