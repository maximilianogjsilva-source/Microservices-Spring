package com.mycompany.department_service.service;

import com.mycompany.department_service.model.Department;
import com.mycompany.department_service.repository.DepartmentRepository;
import com.mycompany.department_service.service.interfaces.IDepartmentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentService implements IDepartmentService {

    private final DepartmentRepository departmentRepository;

    private final EmployeeService employeeService;

    @Override
    @CircuitBreaker(name = "department-service", fallbackMethod = "fallbackListAll")
    public List<Department> listAll() {
        return departmentRepository.findAll().stream().peek(department ->
                department.setEmployees( employeeService.findAllbyDepartmentId(department.getId()) )
        ).toList();
    }

    @Override
    @CircuitBreaker(name = "department-service", fallbackMethod = "fallbackFindById")
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id).map(department -> {
            department.setEmployees(employeeService.findAllbyDepartmentId(id));
            return department;
        });
    }

    @Override
    public Optional<Department> create(Department department) {
        return departmentRepository.create(department);
    }

    @Override
    public Optional<Department> update(Department department) {
        return departmentRepository.findById(department.getId())
                .map(departmentRepository::update)
                .orElseThrow();
    }

    @Override
    public Optional<Department> delete(Long id) {
        return departmentRepository.findById(id).stream()
                .peek(departmentRepository::delete).findFirst();
    }

    private List<Department> fallbackListAll(Throwable ex){
        log.error("Department service is unavailable with error {}", ex.getMessage());
        return departmentRepository.findAll();
    }

    private Optional<Department> fallbackFindById(Long id, Throwable ex){
        log.error("Department service is unavailable findById {}, with error {}", id, ex.getMessage() );
        return departmentRepository.findById(id);
    }

}
