package com.mycompany.department_service.service.interfaces;

import com.mycompany.department_service.http.response.DepartmentAndEmployees;
import com.mycompany.department_service.model.Department;

import java.util.List;
import java.util.Optional;

public interface IResponseService {

    List<DepartmentAndEmployees> listAll();

    Optional<DepartmentAndEmployees> findById(Long id);

    Optional<DepartmentAndEmployees> create(Department department);

    Optional<DepartmentAndEmployees> update(Department department);

    Optional<Department> delete(Long id);

}
