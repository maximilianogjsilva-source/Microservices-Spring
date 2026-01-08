package com.mycompany.department_service.controller;


import com.mycompany.department_service.http.response.DepartmentAndEmployees;
import com.mycompany.department_service.model.Department;
import com.mycompany.department_service.service.interfaces.IResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@Slf4j
public class DepartmentController {


    @Autowired
    private IResponseService responseService;


    @GetMapping
    public ResponseEntity<List<DepartmentAndEmployees>> findAll(){
        return ResponseEntity.ok(this.responseService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentAndEmployees> findById(@PathVariable Long id){
        return this.responseService.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Department department){
        return this.responseService.create(department).map(dep ->
                ResponseEntity.created(URI.create("/api/v1/departments".concat(dep.getId().toString()))).body(dep) )
                .orElse( ResponseEntity.notFound().build() );
    }

    @PutMapping
    private ResponseEntity<DepartmentAndEmployees> update(@RequestBody Department department){
        return this.responseService.update(department).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{idepar}")
    private ResponseEntity<?> delete(@PathVariable Long idepar){
        return this.responseService.delete(idepar).map(
                (dep)->ResponseEntity.noContent().build()
        ).orElse(ResponseEntity.notFound().build());
    }


}
