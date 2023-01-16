package com.vexla.neweraspringboot.service;

import com.vexla.neweraspringboot.entity.EmployeeEntity;
import com.vexla.neweraspringboot.model.Employee;
import com.vexla.neweraspringboot.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeV2ServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeV2ServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        if (employee.getEmployeeId() == null || employee.getEmailId().isEmpty()) {
            employee.setEmployeeId(UUID.randomUUID().toString());
        }

        EmployeeEntity entity = new EmployeeEntity();
        BeanUtils.copyProperties(employee,entity);
        employeeRepository.save(entity);

        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        //get all employees
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();

        //stream through employee list
        return employeeEntityList
                .stream()
                .map(employeeEntity -> {
                    Employee employee = new Employee();
                    BeanUtils.copyProperties(employeeEntity, employee);
                    return employee;
                })
                .toList();
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
          EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);

          Employee employee = new Employee();
        assert employeeEntity != null;
        BeanUtils.copyProperties(employeeEntity, employee);
        return employee;
    }

    @Override
    public String deleteEmployeeById(String employeeId) {
        employeeRepository.deleteById(employeeId);

        return "Employee deleted with Id: " + employeeId;
    }
}
