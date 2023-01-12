package com.vexla.neweraspringboot.controller;

import com.vexla.neweraspringboot.model.Employee;
import com.vexla.neweraspringboot.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }


    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable(name = "id") String employeeId) {
        return employeeService.getEmployeeById(employeeId);

    }

    @DeleteMapping("/{id}")
    public String deleteEmployeeById(@PathVariable(name = "id") String employeeId) {
        return employeeService.deleteEmployeeById(employeeId);
    }

}
