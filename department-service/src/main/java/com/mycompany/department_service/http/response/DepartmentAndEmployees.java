package com.mycompany.department_service.http.response;

import com.mycompany.department_service.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentAndEmployees {

    private Long id;
    private String name;
    private List<Employee> employees;

}
