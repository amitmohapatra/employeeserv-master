package com.paypal.bfs.test.employeeserv.functionaltests.resource;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.functionaltests.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

public class EmployeeResourceGet404Test extends AbstractTest  {
    private final String getUri = "/v1/bfs/employee/2";
    private Employee emp;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        emp = new Employee();
    }

    /*
       When employee record not found.
       Status code : 404
    */
    @Test
    public void whenEmployeeRecordNotFound() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(this.getUri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
        String content = mvcResult.getResponse().getContentAsString();
    }
}
