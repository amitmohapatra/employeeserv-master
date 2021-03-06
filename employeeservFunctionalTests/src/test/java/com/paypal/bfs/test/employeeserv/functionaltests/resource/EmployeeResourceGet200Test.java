package com.paypal.bfs.test.employeeserv.functionaltests.resource;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.functionaltests.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

public class EmployeeResourceGet200Test extends AbstractTest {
    private final String createUri = "/v1/bfs/employee";
    private final String getUri = "/v1/bfs/employee/1";
    private Employee emp;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        emp = new Employee();
    }

    /*
       When Duplicate record inserted.
       Status code : 200
       Response Body : {"id":1,"first_name":"Amit","last_name":"Mohapatra","date_of_birth":"10/2/1990","address":{"line1":"line1Test","line2":"line2Provided","city":"citytest","state":"statetest","country":"testcountry","zip_code":"testzipcode"}}
     */
    @Test
    public void getEmployeeRecord() throws Exception {
        emp.setFirstName("Amit");
        emp.setLastName("Mohapatra");
        emp.setDateOfBirth("10/2/1990");

        Address adrs = new Address();
        adrs.setLine1("line1Test");
        //line2 provided
        adrs.setLine2("line2Provided");
        adrs.setCity("citytest");
        adrs.setState("statetest");
        adrs.setZipCode("testzipcode");
        adrs.setCountry("testcountry");

        emp.setAddress(adrs);

        String inputJson = super.mapToJson(emp);
        mvc.perform(MockMvcRequestBuilders.post(this.createUri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(this.getUri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
}
