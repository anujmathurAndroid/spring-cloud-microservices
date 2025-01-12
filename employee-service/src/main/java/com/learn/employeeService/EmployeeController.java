package com.learn.employeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getEmployeeWithDepartment(@PathVariable Long id) {
        Employee employee = service.getEmployeeById(id).orElse(null);
        if (employee == null) {
            return Map.of("error", "Employee not found");
        }

        // Fetch department from Department Service
        String departmentServiceUrl = "http://department-service/departments/" + employee.getDepartmentId();
        Map<String, Object> department = restTemplate.getForObject(departmentServiceUrl, Map.class);

        return Map.of("employee", employee, "department", department);
    }
}
