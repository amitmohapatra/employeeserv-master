package com.paypal.bfs.test.employeeserv.service.impl;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeTable;
import com.paypal.bfs.test.employeeserv.exception.DuplicateRecordException;
import com.paypal.bfs.test.employeeserv.exception.EmployeeServiceException;
import com.paypal.bfs.test.employeeserv.generator.EmployeeGenerator;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    static Logger logger = Logger.getLogger(EmployeeServiceImpl.class.getName());
    private final EmployeeGenerator employeeGenerator;
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeGenerator employeeGenerator) {
        this.employeeGenerator = employeeGenerator;
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> getEmployeeById(Integer id) {
        try {
            Optional<EmployeeTable> employeeEntity = employeeRepository.findById(id);
            if (employeeEntity.isPresent()) {
                Employee employeeRecord = employeeGenerator.generateModel(employeeEntity.get());
                if (Objects.nonNull(employeeRecord)) {
                    return Optional.of(employeeRecord);
                }
            }
            return Optional.empty();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Fetching employee record by Id failed with an error", ex);
            throw new EmployeeServiceException("Fetching employee record by Id failed with an error");
        }
    }

    public Employee createEmployee(Employee employeeModel) {
        try {
            return employeeGenerator.generateModel(employeeRepository.
                    save(employeeGenerator.generateEntity(employeeModel)));
        } catch(DataIntegrityViolationException ex) {
            logger.log(Level.SEVERE, "Duplicate Employee Record found", ex);
            throw new DuplicateRecordException("Duplicate Employee Record found");
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Employee record saving failed with an error", ex);
            throw new EmployeeServiceException("Employee record saving failed with an error");
        }
    }
}
