package com.paypal.bfs.test.employeeserv.functionaltests.resource;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.functionaltests.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EmployeeResourceCreate201Test extends AbstractTest  {

    private final String uri = "/v1/bfs/employee";
    private Employee emp;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        emp = new Employee();
    }

    /*
       Line2  not provided. Record should be created.
       Input body : {"first_name":"Amit","last_name":"Mohapatra","date_of_birth":"10/2/1990","address":{"line1":"line1Test","city":"citytest","state":"statetest","country":"testcountry","zip_code":"testzipcode"}}
       Status code : 201
       Response Body : {"id":1,"first_name":"Amit","last_name":"Mohapatra","date_of_birth":"10/2/1990","address":{"line1":"line1Test","city":"citytest","state":"statetest","country":"testcountry","zip_code":"testzipcode"}}
     */
    @Test
    public void whenAddressWithoutLine2Provided() throws Exception {
        emp.setFirstName("Amit");
        emp.setLastName("Mohapatra");
        emp.setDateOfBirth("10/2/1990");

        //line2 not provided
        Address adrs = new Address();
        adrs.setLine1("line1Test");
        adrs.setCity("citytest");
        adrs.setState("statetest");
        adrs.setZipCode("testzipcode");
        adrs.setCountry("testcountry");

        emp.setAddress(adrs);

        String inputJson = super.mapToJson(emp);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(this.uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("{\"id\":1,\"first_name\":\"Amit\",\"last_name\":\"Mohapatra\",\"date_of_birth\":\"10/2/1990\",\"address\":{\"line1\":\"line1Test\",\"city\":\"citytest\",\"state\":\"statetest\",\"country\":\"testcountry\",\"zip_code\":\"testzipcode\"}}", content);
    }

    /*
       Line2 provided. Record should be created.
       Input body : {"first_name":"Amit","last_name":"Mohapatra","date_of_birth":"10/2/1990","address":{"line1":"line1Test","city":"citytest","state":"statetest","country":"testcountry","zip_code":"testzipcode"}}
       Status code : 201
       Response Body : {"id":1,"first_name":"Amit","last_name":"Mohapatra","date_of_birth":"10/2/1990","address":{"line1":"line1Test","line2":"line2Provided","city":"citytest","state":"statetest","country":"testcountry","zip_code":"testzipcode"}}
     */
    @Test
    public void whenAddressWithLine2Provided() throws Exception {
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
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(this.uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("{\"id\":1,\"first_name\":\"Amit\",\"last_name\":\"Mohapatra\",\"date_of_birth\":\"10/2/1990\",\"address\":{\"line1\":\"line1Test\",\"line2\":\"line2Provided\",\"city\":\"citytest\",\"state\":\"statetest\",\"country\":\"testcountry\",\"zip_code\":\"testzipcode\"}}", content);
    }
}
