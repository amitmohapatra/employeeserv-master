package com.paypal.bfs.test.employeeserv.validator;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exception.ConstraintsViolationException;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeRequestValidator {
    private static final String CITY = "city";
    private static final int MAX_LENGTH = 255;
    private static final String STATE = "state";
    private static final String COUNTRY = "country";
    private static final String ADDRESS = "address";
    private static final String ZIP_CODE = "zip_code";
    private static final String LAST_NAME = "last_name";
    private static final String ADDRESS_LINE_1 = "line1";
    private static final String FIRST_NAME = "first_name";
    private static final String DATE_OF_BIRTH = "date_of_birth";
    private static final String REQUIRED = "This field is required";
    private static final String DATE_REQUIRED = "Date of Birth should be in dd/MM/yyyy format.";

    public Optional<List<ConstraintsViolationException>> validateRequest(Employee employeeRequest) {
        List<ConstraintsViolationException> errorsList = new ArrayList<>();
        fieldRequiredValidator(employeeRequest, errorsList);
        fieldMaxLengthValidator(employeeRequest, errorsList);
        return errorsList.size() > 0 ? Optional.of(errorsList) : Optional.empty();

    }

    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    private boolean isMaxLength(String value) {
        return !isEmpty(value) && value.length() > EmployeeRequestValidator.MAX_LENGTH;
    }

    private boolean isValidDate(String value) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void fieldMaxLengthValidator(Employee employeeRequest, List<ConstraintsViolationException> errorsList) {
        if (isMaxLength(employeeRequest.getFirstName())) {
            errorsList.add(ConstraintsViolationException.builder().field(FIRST_NAME)
                    .message("Max length is " + MAX_LENGTH).build());
        }
        if (isMaxLength(employeeRequest.getLastName())) {
            errorsList.add(ConstraintsViolationException.builder().field(LAST_NAME)
                    .message("Max length is " + MAX_LENGTH).build());
        }

        if (employeeRequest.getAddress() != null) {

            if (isMaxLength(employeeRequest.getAddress().getLine1())) {
                errorsList.add(ConstraintsViolationException.builder().field(ADDRESS_LINE_1)
                        .message("Max length is " + MAX_LENGTH).build());
            }
            if (isMaxLength(employeeRequest.getAddress().getLine2())) {
                errorsList.add(ConstraintsViolationException.builder().field("Address Line 2")
                        .message("Max length is " + MAX_LENGTH).build());
            }
            if (isMaxLength(employeeRequest.getAddress().getCountry())) {
                errorsList.add(ConstraintsViolationException.builder().field(COUNTRY)
                        .message("Max length is " + MAX_LENGTH).build());
            }
            if (isMaxLength(employeeRequest.getAddress().getState())) {
                errorsList.add(ConstraintsViolationException.builder().field(STATE)
                        .message("Max length is " + MAX_LENGTH).build());
            }
            if (isMaxLength(employeeRequest.getAddress().getZipCode())) {
                errorsList.add(ConstraintsViolationException.builder().field(ZIP_CODE)
                        .message("Max length is " + MAX_LENGTH).build());
            }
        }
    }


    private void fieldRequiredValidator(Employee employeeRequest, List<ConstraintsViolationException> errorsList) {
        if (isEmpty(employeeRequest.getFirstName())) {
            errorsList.add(ConstraintsViolationException.builder().field(FIRST_NAME)
                    .message(REQUIRED).build());
        }

        if (isEmpty(employeeRequest.getLastName())) {
            errorsList.add(ConstraintsViolationException.builder().field(LAST_NAME)
                    .message(REQUIRED).build());
        }

        if (isEmpty(employeeRequest.getDateOfBirth())) {
            errorsList.add(ConstraintsViolationException.builder().field(DATE_OF_BIRTH).message(REQUIRED).build());
        } else {

            if (!isValidDate(employeeRequest.getDateOfBirth())) {
                errorsList.add(ConstraintsViolationException.builder().field(DATE_OF_BIRTH).message(DATE_REQUIRED).build());
            }
        }

        if (employeeRequest.getAddress() == null) {
            errorsList.add(ConstraintsViolationException.builder().field(ADDRESS).message(REQUIRED).build());
        } else {

            if (isEmpty(employeeRequest.getAddress().getLine1())) {
                errorsList.add(ConstraintsViolationException.builder().field(ADDRESS_LINE_1).message(REQUIRED).build());
            }

            if (isEmpty(employeeRequest.getAddress().getCity())) {
                errorsList.add(ConstraintsViolationException.builder().field(CITY).message(REQUIRED).build());
            }

            if (isEmpty(employeeRequest.getAddress().getState())) {
                errorsList.add(ConstraintsViolationException.builder().field(STATE).message(REQUIRED).build());
            }

            if (isEmpty(employeeRequest.getAddress().getZipCode())) {
                errorsList.add(ConstraintsViolationException.builder().field(ZIP_CODE).message(REQUIRED).build());
            }

            if (isEmpty(employeeRequest.getAddress().getCountry())) {
                errorsList.add(ConstraintsViolationException.builder().field(COUNTRY).message(REQUIRED).build());
            }
        }
    }
}
