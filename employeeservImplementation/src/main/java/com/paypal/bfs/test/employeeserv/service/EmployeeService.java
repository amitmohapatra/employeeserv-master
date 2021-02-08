package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> getEmployeeById(Integer id);

    Employee createEmployee(Employee employeeRequest);
}
