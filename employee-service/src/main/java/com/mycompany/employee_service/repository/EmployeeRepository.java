package com.mycompany.employee_service.repository;

import com.mycompany.employee_service.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    private final List<Employee> employees;

    public EmployeeRepository(){
        employees = new ArrayList<>();
    }

    public List<Employee> findAll(){
        return employees;
    }

    public List<Employee> findByDepartmentId(Long departmentId){
        return employees.stream()
                .filter( employee -> employee.getDepartmentId().equals(departmentId) )
                .toList();
    }

    public Optional<Employee> findById(Long id){
        return employees.stream()
                .filter( employee -> employee.getId().equals(id) )
                .findFirst();
    }

    public Optional<Employee> save(Employee employee){

        if( employees.stream().anyMatch(empl -> empl.getId().equals(employee.getId())) ){
            return Optional.of(
                    employees.set(employee.getId().byteValue()-1, employee)
            );
        }
        employees.add(employee);
        return Optional.of( employees.getLast() );
    }

    public void delete(Employee employee){
        employees.remove(employee);
    }

}




