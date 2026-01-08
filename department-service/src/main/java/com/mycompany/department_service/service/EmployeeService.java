package com.mycompany.department_service.service;

import com.mycompany.department_service.client.EmployeeClient;
import com.mycompany.department_service.model.Employee;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeClient employeeClient;

    public List<Employee> findAllbyDepartmentId(Long id){
        return employeeClient.findAllbyDepartmentId(id);
    }

    private List<Employee> fallbackFindAllbyDepartmentId(Long id, Throwable ex) {
        log.error("Employee Service is unavailable findAllbyDepartmentId {}, with error {}", id, ex.getMessage());
        return List.of();
    }

}
