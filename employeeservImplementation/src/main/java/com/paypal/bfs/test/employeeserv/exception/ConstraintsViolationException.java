package com.paypal.bfs.test.employeeserv.exception;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ConstraintsViolationException {
    private String field;
    private String message;
}
