package com.mycompany.department_service.service;

import com.mycompany.department_service.http.response.DepartmentAndEmployees;
import com.mycompany.department_service.model.Department;
import com.mycompany.department_service.service.interfaces.IDepartmentService;
import com.mycompany.department_service.service.interfaces.IResponseService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ResponseService implements IResponseService {

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;


    @Override
    public List<DepartmentAndEmployees> listAll() {
        return departmentService.listAll().stream().map((dep)->
                DepartmentAndEmployees.builder()
                        .id(dep.getId())
                        .name(dep.getName())
                        .employees( employeeService.findAllbyDepartmentId(dep.getId()) )
                        .build()
        ).toList();
    }

    @Override
    public Optional<DepartmentAndEmployees> findById(Long id) {
        return departmentService.findById(id)
                .map( (dep)-> DepartmentAndEmployees.builder()
                        .id(dep.getId())
                        .name(dep.getName())
                        .employees( employeeService.findAllbyDepartmentId(dep.getId()) )
                        .build()
                );
    }

    @Override
    public Optional<DepartmentAndEmployees> create(Department department) {
        return departmentService.create(department)
                .map( (dep)-> DepartmentAndEmployees.builder()
                        .id(dep.getId())
                        .name(dep.getName())
                        .employees( employeeService.findAllbyDepartmentId(dep.getId()) )
                        .build()
                );
    }

    @Override
    public Optional<DepartmentAndEmployees> update(Department department) {
        return departmentService.update(department)
                .map((dep)->DepartmentAndEmployees.builder()
                        .id(dep.getId())
                        .name(dep.getName())
                        .employees( employeeService.findAllbyDepartmentId(dep.getId()) )
                        .build()
                );
    }

    @Override
    public Optional<Department> delete(Long id) {
        return departmentService.delete(id);
    }

}
