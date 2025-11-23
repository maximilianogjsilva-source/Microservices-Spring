package com.mycompany.employee_service.service;

import com.mycompany.employee_service.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    List<Employee> listAll();

    List<Employee> findByDepartmentId(Long departmentId);

    Optional<Employee> findById(Long id);

    Optional<Employee> create(Employee employee);

    Optional<Employee> update(Long id, Employee employee);

    Optional<Employee> delete(Long id);

}
