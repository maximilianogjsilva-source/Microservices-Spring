package com.mycompany.department_service.service;

import com.mycompany.department_service.exception.DepartmentNotFoundException;
import com.mycompany.department_service.model.Department;
import com.mycompany.department_service.repository.DepartmentRepository;
import com.mycompany.department_service.service.interfaces.IDepartmentService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DepartmentService implements IDepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> listAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Optional<Department> create(Department department) {
        return Optional.of(departmentRepository.save(department));
    }

    @Override
    public Optional<Department> update(Department department) {
        return this.findById(department.getId()).map((param)-> {
            param.setName(department.getName());
            return departmentRepository.save(param);
        });
    }

    @Override
    @Transactional
    public Optional<Department> delete(Long id) {
        return departmentRepository.findById(id).stream().peek((department)->{
                    departmentRepository.delete(department);
        }).findFirst();
    }

}
