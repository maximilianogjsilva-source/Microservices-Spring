package com.mycompany.department_service.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(Long id) {
        super("Department with ID "+id+" not found");
    }
}
