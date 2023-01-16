package com.vexla.neweraspringboot.service;

import com.vexla.neweraspringboot.exception.EmployeeNotFoundException;
import com.vexla.neweraspringboot.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    List<Employee> employees = new ArrayList<Employee>();

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public Employee save(Employee employee) {
        if (employee.getEmployeeId() == null || employee.getEmailId().isEmpty()) {
            employee.setEmployeeId(UUID.randomUUID().toString());
        }
         employees.add(employee);
        return employee;
    }

    //get Employee by id
    @Override
    public Employee getEmployeeById(String employeeId) {
        return employees.stream()
                .filter(employee -> employee.getEmployeeId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("" + "Employee not found with Id: " + employeeId));

    }

    @Override
    public String deleteEmployeeById(String employeeId) {
         Employee employee = employees
                 .stream()
                 .filter(e -> e.getEmployeeId().equalsIgnoreCase(employeeId))
                 .findFirst()
                 .get();

         employees.remove(employee);
         return "Employee deleted with Id: " + employeeId;
    }


}
