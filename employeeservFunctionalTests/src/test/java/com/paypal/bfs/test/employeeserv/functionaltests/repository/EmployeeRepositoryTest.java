package com.paypal.bfs.test.employeeserv.functionaltests.repository;

import com.paypal.bfs.test.employeeserv.entity.EmployeeTable;
import com.paypal.bfs.test.employeeserv.functionaltests.AbstractTest;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertNotNull;


public class EmployeeRepositoryTest extends AbstractTest {

    @Autowired
    EmployeeRepository empRepo;

    @Test
    public void saveEmployee() {
        EmployeeTable empTbl = new EmployeeTable();
        empTbl.setFirstName("Amit");
        empTbl.setLastName("Mohapatra");
        empTbl.setDateOfBirth("10/2/1990");
        empTbl.setLine1("line1Test");
        empTbl.setLine2("line2Provided");
        empTbl.setCity("citytest");
        empTbl.setState("statetest");
        empTbl.setZipCode("testzipcode");
        empTbl.setCountry("testcountry");
        empRepo.save(empTbl);
        assertNotNull(empRepo.findById(1));
    }
}
