package com.mycompany.employee_service.controller;

import com.mycompany.employee_service.model.Employee;
import com.mycompany.employee_service.repository.EmployeeRepository;
import com.mycompany.employee_service.service.EmployeeService;
import com.mycompany.employee_service.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeRepository;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll(){
        return ResponseEntity.ok(employeeRepository.listAll());
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<List<Employee>> findAllByDepartmentId(@PathVariable Long id){
        return ResponseEntity.ok(employeeRepository.findByDepartmentId(id));
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        return employeeRepository.create(employee).map( (e)->
            ResponseEntity.created(URI.create("/api/v1/employees".concat(e.getId().toString()))).body(e) )
                .orElse( ResponseEntity.notFound().build() );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee employee){
        return employeeRepository.update(id,employee).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return employeeRepository.delete(id).map( (e)->ResponseEntity.noContent().build() )
                .orElse(ResponseEntity.notFound().build());

    }

}
