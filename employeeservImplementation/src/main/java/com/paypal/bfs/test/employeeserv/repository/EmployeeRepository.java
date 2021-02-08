package com.paypal.bfs.test.employeeserv.repository;

import com.paypal.bfs.test.employeeserv.entity.EmployeeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeTable, Integer> {
}
