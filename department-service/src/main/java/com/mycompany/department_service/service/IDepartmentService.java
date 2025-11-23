package com.mycompany.department_service.service;

import com.mycompany.department_service.model.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {

    List<Department> listAll();

    Optional<Department> findById(Long id);

    Optional<Department> create(Department department);

    Optional<Department> update(Department department);

    Optional<Department> delete(Long id);

}
