package com.paypal.bfs.test.employeeserv.generator;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeTable;
import org.springframework.stereotype.Component;


@Component
public class EmployeeGenerator {

    public EmployeeTable generateEntity(Employee employee) {
        EmployeeTable employeeTable = new EmployeeTable();
        employeeTable.setFirstName(employee.getFirstName());
        employeeTable.setLastName(employee.getLastName());
        employeeTable.setDateOfBirth(employee.getDateOfBirth());
        employeeTable.setLine1(employee.getAddress().getLine1());
        employeeTable.setLine2(employee.getAddress().getLine2());
        employeeTable.setCity(employee.getAddress().getCity());
        employeeTable.setState(employee.getAddress().getState());
        employeeTable.setZipCode(employee.getAddress().getZipCode());
        employeeTable.setCountry(employee.getAddress().getCountry());
        return employeeTable;
    }

    public Employee generateModel(EmployeeTable employeeTable) {
        Employee employee = new Employee();
        employee.setAddress(new Address());
        employee.setId(employeeTable.getId());
        employee.setFirstName(employeeTable.getFirstName());
        employee.setLastName(employeeTable.getLastName());
        employee.setDateOfBirth(employeeTable.getDateOfBirth());
        employee.getAddress().setLine1(employeeTable.getLine1());
        employee.getAddress().setLine2(employeeTable.getLine2());
        employee.getAddress().setCity(employeeTable.getCity());
        employee.getAddress().setState(employeeTable.getState());
        employee.getAddress().setZipCode(employeeTable.getZipCode());
        employee.getAddress().setCountry(employeeTable.getCountry());
        return employee;

    }
}
