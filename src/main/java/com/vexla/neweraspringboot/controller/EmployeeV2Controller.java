package com.vexla.neweraspringboot.controller;

import com.vexla.neweraspringboot.model.Employee;
import com.vexla.neweraspringboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/employees")
public class EmployeeV2Controller {
    @Qualifier("employeeV2ServiceImpl")
    @Autowired
    private  EmployeeService employeeService;

    //add qualifier so as two different version business logic to use one interface


    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
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
