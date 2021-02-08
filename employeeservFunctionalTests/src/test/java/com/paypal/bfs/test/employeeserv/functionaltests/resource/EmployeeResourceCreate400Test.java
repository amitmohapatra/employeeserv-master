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
public class EmployeeResourceCreate400Test extends AbstractTest {

    private final String uri = "/v1/bfs/employee";
    private Employee emp;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        emp = new Employee();
    }


    /*
       When empty input provided.
       Input body : {}
       Status code : 400
       Response Body : [{"field":"first_name","message":"This field is required"},{"field":"last_name","message":"This field is required"},{"field":"date_of_birth","message":"This field is required"},{"field":"address","message":"This field is required"}]
     */
    @Test
    public void whenEmptyBodyProvided() throws Exception {
        String inputJson = super.mapToJson(emp);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(this.uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("[{\"field\":\"first_name\",\"message\":\"This field is required\"},{\"field\":\"last_name\",\"message\":\"This field is required\"},{\"field\":\"date_of_birth\",\"message\":\"This field is required\"},{\"field\":\"address\",\"message\":\"This field is required\"}]", content);
    }


    /*
       When Address is empty.
       Input body : {"first_name":"Amit","last_name":"Mohapatra","date_of_birth":"10/2/1990","address":{}}
       Status code : 400
       Response Body : [{"field":"line1","message":"This field is required"},{"field":"city","message":"This field is required"},{"field":"state","message":"This field is required"},{"field":"zip_code","message":"This field is required"},{"field":"country","message":"This field is required"}]
     */
    @Test
    public void whenEmptyAddressProvided() throws Exception {
        emp.setFirstName("Amit");
        emp.setLastName("Mohapatra");
        emp.setDateOfBirth("10/2/1990");
        emp.setAddress(new Address());
        String inputJson = super.mapToJson(emp);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(this.uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("[{\"field\":\"line1\",\"message\":\"This field is required\"},{\"field\":\"city\",\"message\":\"This field is required\"},{\"field\":\"state\",\"message\":\"This field is required\"},{\"field\":\"zip_code\",\"message\":\"This field is required\"},{\"field\":\"country\",\"message\":\"This field is required\"}]", content);
    }

    /*
       When date of birth provided other than dd/MM/yyyy format.
       Input body : {"first_name":"Amit","last_name":"Mohapatra","date_of_birth":"10-2-1990","address":{"line1":"line1Test","city":"citytest","state":"statetest","country":"testcountry","zip_code":"testzipcode"}}
       Status code : 400
       Response Body : [{"field":"date_of_birth","message":"Date of Birth should be in dd/MM/yyyy format."}]
     */

    @Test
    public void dateOfBirthValidation() throws Exception {
        emp.setFirstName("Amit");
        emp.setLastName("Mohapatra");
        emp.setDateOfBirth("10-2-1990");

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
        assertEquals(400, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("[{\"field\":\"date_of_birth\",\"message\":\"Date of Birth should be in dd/MM/yyyy format.\"}]", content);
    }

    /*
       When field length more than 255 . Line2 is optional but can not exceeded 255.
       Input body : {"first_name":"Amit","last_name":"Mohapatra","date_of_birth":"10-2-1990","address":{"line1":"line1Test","city":"citytest","state":"statetest","country":"testcountry","zip_code":"testzipcode"}}
       Status code : 400
       Response Body : [{"field":"date_of_birth","message":"Date of Birth should be in dd/MM/yyyy format."},{"field":"first_name","message":"Max length is 255"},{"field":"last_name","message":"Max length is 255"},{"field":"line1","message":"Max length is 255"},{"field":"Address Line 2","message":"Max length is 255"},{"field":"country","message":"Max length is 255"},{"field":"state","message":"Max length is 255"},{"field":"zip_code","message":"Max length is 255"}]
     */

    @Test
    public void fieldMaxLengthValidation() throws Exception {
        String input = "Aged thirteen, Vasudev took yoga lessons from Malladihalli Raghavendra, and kept practicing asanas and pranayama[14] daily throughout his youth, albeit without spiritual aspirations.[15] At the age of 25, on 23 September 1982, he went up Chamundi Hill and sat on a rock, where he had a 'spiritual experience'.[13] Six weeks afterwards, he left his business to his friend and travelled extensively in an effort to gain insight into his mystical experience.[13] After about a year of meditation and travel, he decided to teach yoga to share his inner experience.[13]\n" +
                "\n" +
                "In 1983, he taught his first yoga class with seven participants in Mysore. Over time, he began conducting yoga classes across Karnataka and Hyderabad travelling on his motorcycle, subsisting on the produce of his poultry farm rental and donating the collections received from his students to a local charity on the last day of the class.[13]";
        emp.setFirstName(input);
        emp.setLastName(input);
        emp.setDateOfBirth("10-2-1990");

        Address adrs = new Address();
        adrs.setLine1(input);
        //line2 provided
        adrs.setLine2(input);
        adrs.setCity(input);
        adrs.setState(input);
        adrs.setZipCode(input);
        adrs.setCountry(input);

        emp.setAddress(adrs);

        String inputJson = super.mapToJson(emp);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(this.uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("[{\"field\":\"date_of_birth\",\"message\":\"Date of Birth should be in dd/MM/yyyy format.\"},{\"field\":\"first_name\",\"message\":\"Max length is 255\"},{\"field\":\"last_name\",\"message\":\"Max length is 255\"},{\"field\":\"line1\",\"message\":\"Max length is 255\"},{\"field\":\"Address Line 2\",\"message\":\"Max length is 255\"},{\"field\":\"country\",\"message\":\"Max length is 255\"},{\"field\":\"state\",\"message\":\"Max length is 255\"},{\"field\":\"zip_code\",\"message\":\"Max length is 255\"}]", content);
    }
}
