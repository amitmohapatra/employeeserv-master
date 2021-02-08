package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Interface for employee resource operations.
 */
@RestController
@RequestMapping("/v1/bfs/employee")
public interface EmployeeResource {

    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    @ApiOperation(value = "Retrieve employee record for the specified id", response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the employee record"),
            @ApiResponse(code = 404, message = "Employee record not found"),
            @ApiResponse(code = 500, message = "Fetching employee record by Id failed with an error") })
    @RequestMapping(value ="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<Employee> employeeGetById(@PathVariable("id") Integer id);

    // ----------------------------------------------------------
    // TODO - add a new operation for creating employee resource.
    // ----------------------------------------------------------

    /**
     * Create {@link Employee} resource.
     *
     * @param employee employee object.
     * @return {@link Employee} resource.
     */
    @ApiOperation(value = "Create employee record")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Employee record created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 409, message = "Duplicate Employee Record"),
            @ApiResponse(code = 500, message = "Employee record saving failed with an error")})
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<?> employeeCreate(@RequestBody Employee employee);

}
