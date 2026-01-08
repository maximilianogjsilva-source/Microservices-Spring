package com.mycompany.employee_service.repository;

import com.mycompany.employee_service.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
