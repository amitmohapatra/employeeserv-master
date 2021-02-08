package com.paypal.bfs.test.employeeserv.functionaltests.service;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeTable;
import com.paypal.bfs.test.employeeserv.exception.DuplicateRecordException;
import com.paypal.bfs.test.employeeserv.exception.EmployeeServiceException;
import com.paypal.bfs.test.employeeserv.functionaltests.AbstractTest;
import com.paypal.bfs.test.employeeserv.generator.EmployeeGenerator;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import com.paypal.bfs.test.employeeserv.service.impl.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class EmployeeServiceTest extends AbstractTest {

    private Employee emp;
    private EmployeeTable empTbl;
    private EmployeeRepository empRepo;
    private EmployeeGenerator empGenerator;
    private EmployeeService employeeService;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        emp = new Employee();
        empTbl = new EmployeeTable();
        empRepo = Mockito.mock(EmployeeRepository.class);
        empGenerator = Mockito.mock(EmployeeGenerator.class);
        employeeService = new EmployeeServiceImpl(empRepo, empGenerator);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void WhenEntityToModelNullForGetEmployeeById() {
        Mockito.when(empRepo.findById(1)).thenReturn(Optional.of(empTbl));
        assertEquals(Optional.empty(), employeeService.getEmployeeById(1));
    }

    @Test
    public void WhenEntityToEmployeeModelForGetEmployeeById() {
        empTbl.setFirstName("Amit");
        empTbl.setLastName("Mohapatra");
        empTbl.setDateOfBirth("10/2/1990");
        empTbl.setLine1("line1Test");
        empTbl.setLine2("line2Provided");
        empTbl.setCity("citytest");
        empTbl.setState("statetest");
        empTbl.setZipCode("testzipcode");
        empTbl.setCountry("testcountry");

        Employee empModelRec = new EmployeeGenerator().generateModel(empTbl);
        Optional<EmployeeTable> empTabOp = Optional.of(empTbl);
        Mockito.when(empGenerator.generateModel(empTabOp.get())).thenReturn(empModelRec);
        Mockito.when(empRepo.findById(1)).thenReturn(empTabOp);
        assertEquals(Optional.of(empModelRec), employeeService.getEmployeeById(1));
    }

    @Test
    public void GetEmployeeByIdException() {
        Mockito.doThrow(new RuntimeException("RuntimeException occurred")).when(empRepo).findById(1);
        exceptionRule.expect(EmployeeServiceException.class);
        exceptionRule.expectMessage("Fetching employee record by Id failed with an error");
        employeeService.getEmployeeById(1);
    }

    @Test
    public void createEmployeeSuccess() {
        Mockito.when(empGenerator.generateEntity(emp)).thenReturn(empTbl);
        Mockito.when(empRepo.save(empTbl)).thenReturn(empTbl);
        Mockito.when(empGenerator.generateModel(empTbl)).thenReturn(emp);
        assertEquals(emp, employeeService.createEmployee(emp));
    }

    @Test
    public void createEmployeeDataIntegrityViolationException() {
        Mockito.when(empGenerator.generateEntity(emp)).thenReturn(empTbl);
        Mockito.doThrow(new DataIntegrityViolationException("DataIntegrityViolationException occurred"))
                .when(empRepo).save(empTbl);
        exceptionRule.expect(DuplicateRecordException.class);
        exceptionRule.expectMessage("Duplicate Employee Record found");
        employeeService.createEmployee(emp);
    }

    @Test
    public void createEmployeeException() {
        Mockito.when(empGenerator.generateEntity(emp)).thenReturn(empTbl);
        Mockito.doThrow(new RuntimeException("RuntimeException occurred"))
                .when(empRepo).save(empTbl);
        exceptionRule.expect(EmployeeServiceException.class);
        exceptionRule.expectMessage("Employee record saving failed with an error");
        employeeService.createEmployee(emp);
    }
}
