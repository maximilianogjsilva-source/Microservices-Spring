package com.mycompany.department_service.service;

import com.mycompany.department_service.client.EmployeeClient;
import com.mycompany.department_service.model.Employee;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeClient employeeClient;

    @Retry(name = "department-service", fallbackMethod = "fallbackFindAllbyDepartmentId")
    public List<Employee> findAllbyDepartmentId(Long id){
        return employeeClient.findAllbyDepartmentId(id);
    }

    private List<Employee> fallbackFindAllbyDepartmentId(Long id, Throwable ex) {
        log.error("Employee Service is unavailable findAllbyDepartmentId {}, with error {}", id, ex.getMessage());
        return List.of();
    }

}
