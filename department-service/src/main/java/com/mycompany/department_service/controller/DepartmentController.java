package com.mycompany.department_service.controller;

import com.mycompany.department_service.model.Department;
import com.mycompany.department_service.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
@Slf4j
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> findAll(){

        return ResponseEntity.ok(departmentService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id){
        return departmentService.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Department department){
        return departmentService.create(department).map(dep ->
                ResponseEntity.created(URI.create("/api/v1/departments".concat(dep.getId().toString()))).body(dep) )
                .orElse( ResponseEntity.notFound().build() );
    }

}
