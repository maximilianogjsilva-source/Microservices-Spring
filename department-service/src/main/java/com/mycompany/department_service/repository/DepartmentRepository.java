package com.mycompany.department_service.repository;

import com.mycompany.department_service.model.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepository {

    private final List<Department> departments;

    public DepartmentRepository(){
        this.departments = new ArrayList<>();
    }

    public List<Department> findAll(){
        return departments;
    }

    public Optional<Department> create(Department department){
        departments.add(department);
        return Optional.of(departments.getLast());
    }

    public Optional<Department> findById(Long id){
        return departments.stream()
                .filter(department -> department.getId().equals(id))
                .findFirst();
    }

    public Optional<Department> update(Department department){
        return Optional.of(
                departments.set( department.getId().byteValue()-1, department )
        );
    }

    public void delete(Department department){
        departments.remove(department);
    }

}




