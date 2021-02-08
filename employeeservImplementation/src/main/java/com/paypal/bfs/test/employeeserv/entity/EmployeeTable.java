package com.paypal.bfs.test.employeeserv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee", uniqueConstraints = @UniqueConstraint(columnNames =
        { "first_name",  "last_name", "date_of_birth", "address_line1", "city", "state", "country", "zipCode"}))
public class EmployeeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "date_of_birth")
    private String dateOfBirth;

    @Column(nullable = false, name = "address_line1")
    private String line1;

    @Column(name = "address_line2")
    private String line2;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String zipCode;
}
