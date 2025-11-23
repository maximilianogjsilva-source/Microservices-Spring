package com.mycompany.employee_service.service;

import com.mycompany.employee_service.model.Employee;
import com.mycompany.employee_service.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService{

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> listAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findByDepartmentId(Long departmentId) {
        return employeeRepository.findAll().stream()
                .filter( employee -> employee.getDepartmentId().equals(departmentId) )
                .toList();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Optional<Employee> create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> update(Long id, Employee employee) {
        return employeeRepository.findById(id)
                .map(e -> employeeRepository.save(employee))
                .orElseThrow();
    }

    @Override
    public Optional<Employee> delete(Long id) {
        return employeeRepository.findById(id).stream()
                .peek( employeeRepository::delete )
                .findFirst();
    }
}
