package com.mycompany.department_service.service;

import com.mycompany.department_service.client.EmployeeClient;
import com.mycompany.department_service.model.Department;
import com.mycompany.department_service.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService{

    private final DepartmentRepository departmentRepository;

    private final EmployeeClient employeeClient;

    @Override
    public List<Department> listAll() {
        return departmentRepository.findAll().stream().peek(department ->
                department.setEmployees( employeeClient.findAllbyDepartmentId(department.getId()) )
        ).toList();
    }

    @Override
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id).map(department -> {
            department.setEmployees(employeeClient.findAllbyDepartmentId(id));
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

}
