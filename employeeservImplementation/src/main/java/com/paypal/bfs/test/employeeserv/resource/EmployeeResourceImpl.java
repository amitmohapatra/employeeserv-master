package com.paypal.bfs.test.employeeserv.resource;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exception.EmployeeRecordNotFoundException;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import com.paypal.bfs.test.employeeserv.validator.EmployeeRequestValidator;
import com.paypal.bfs.test.employeeserv.exception.ConstraintsViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    private final EmployeeService employeeService;
    private final EmployeeRequestValidator requestValidator;

    @Autowired
    private EmployeeResourceImpl(EmployeeService employeeService, EmployeeRequestValidator requestValidator) {
        this.employeeService = employeeService;
        this.requestValidator = requestValidator;
    }

    @Override
    public ResponseEntity<Employee> employeeGetById(Integer id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseThrow(() -> new EmployeeRecordNotFoundException("Employee record not found"));
    }

    @Override
    public ResponseEntity<?> employeeCreate(Employee employeeRequest) {
        ResponseEntity<?> result = null;

        Optional<List<ConstraintsViolationException>> error = requestValidator.validateRequest(employeeRequest);
        if (error.isPresent()) {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } else {
            result = new ResponseEntity<>(employeeService.createEmployee(employeeRequest), HttpStatus.CREATED);
        }
        return result;
    }
}

