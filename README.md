# employeeserv

## Application Overview
employeeserv is a spring boot rest application which would provide the CRUD operations for `Employee` resource.

There are three modules in this application
- employeeservApi - This module contains the interface.
	- `v1/schema/employee.json` defines the employee resource.
	- `jsonschema2pojo-maven-plugin` is being used to create `Employee POJO` from json file.
	- `EmployeeResource.java` is the interface for CRUD operations on `Employee` resource.
		- GET `/v1/bfs/employees/{id}` endpoint is defined to fetch the resource.
- employeeservImplementation - This module contains the implementation for the rest endpoints.
	- `EmployeeResourceImpl.java` implements the `EmployeeResource` interface.
- employeeservFunctionalTests - This module would have the functional tests.

## Assignment
We would like you to enhance the existing project and see you complete the following requirements:

- `employee.json` has only `name`, and `id` elements. Please add `date of birth` and `address` elements to the `Employee` resource. Address will have `line1`, `line2`, `city`, `state`, `country` and `zip_code` elements. `line2` is an optional element.
- Add one more operation in `EmployeeResource` to create an employee. `EmployeeResource` will have two operations, one to create, and another to retrieve the employee resource.
- Implement create and retrieve operations in `EmployeeResourceImpl.java`.
- Resource created using create endpoint should be retrieved using retrieve/get endpoint.
- Please add the unit tests to validate your implementation.
- Please use h2 in-memory database or any other in-memory database to persist the `Employee` resource. Dependency for h2 in-memory database is already added to the parent pom.
- Please make sure the validations are done for the requests.
- Response codes are as per rest guidelines.
- Error handling in case of failures.
- Idempotency logic is implemented to avoid duplicate resource creation.

## Assignment Answer
We would like you to enhance the existing project and see you complete the following requirements:

- `employee.json` has only `name`, and `id` elements. Please add `date of birth` and `address` elements to the `Employee` resource. Address will have `line1`, `line2`, `city`, `state`, `country` and `zip_code` elements. `line2` is an optional element.
    - {
	  "first_name": "Amit",
	  "last_name": "Mohapatra",
	  "date_of_birth": "10/2/1990",
	  "address": {
	       "line1": "test address",
	       "city": "Bangalore",
	       "state": "Karnataka",
	       "country": "India",
	       "zip_code": "560035"
	       }
      }
	  
- Add one more operation in `EmployeeResource` to create an employee. `EmployeeResource` will have two operations, one to create, and another to retrieve the employee resource.
    - Done
- Implement create and retrieve operations in `EmployeeResourceImpl.java`.
	- Done
- Resource created using create endpoint should be retrieved using retrieve/get endpoint.
    - create endpoint
	  - `http://localhost:8080/v1/bfs/employee`
	  - `method : POST`
	  - body : {
	          "first_name": "Amit",
	          "last_name": "Mohapatra",
	          "date_of_birth": "10/2/1990",
	          "address": {
	                "line1": "test address",
	                "city": "Bangalore",
	                "state": "Karnataka",
	                "country": "India",
	                "zip_code": "560035"
	                 }
	          }
	- get endpoint
	  - `http://localhost:8080/v1/bfs/employee/1`
	  - `method : GET`
- Please add the unit tests to validate your implementation.
    - added in employeeservFunctionalTests
- Please use h2 in-memory database or any other in-memory database to persist the `Employee` resource. Dependency for h2 in-memory database is already added to the parent pom.
    - h2 configured
- Please make sure the validations are done for the requests.
    - Input request validations are done in employeeservFunctionalTests resource package (EmployeeResourceCreate400Test)
- Response codes are as per rest guidelines.
    - create endpoint
	  - `201(created)`
	  - `400(bad request)`
	  - `409(conflict)`
	  - `500(internal server eroor)`
	- get endpoint
	  - `200(ok)`
	  - `404(not found)`
	  - `500(internal server eroor)`
- Error handling in case of failures.
	- Done
- Idempotency logic is implemented to avoid duplicate resource creation.
    - DB unique constraint added in Entity

## How to run the application
- Please have Maven version `3.3.3` & Java 8 on your system.
- Use command `mvn clean install` to build the project.
- Use command `mvn spring-boot:run` from `employeeservImplementation` folder to run the project.
- Use postman or curl to access `http://localhost:8080/v1/bfs/employee` POST endpoint with body mentioned below. It will return an Employee resource on successful saving.
  - {
	"first_name": "Amit",
	"last_name": "Mohapatra",
	"date_of_birth": "10/2/1990",
	"address": {
	    "line1": "test address",
	    "city": "Bangalore",
	    "state": "Karnataka",
	    "country": "India",
	    "zip_code": "560035"
	    }
	}
- Use postman or curl to access `http://localhost:8080/v1/bfs/employee/1` GET endpoint. It will return an Employee resource.

## Assignment submission
Thank you very much for your time to take this test. Please upload this complete solution in Github and send us the link to `bfs-sor-interview@paypal.com`.
