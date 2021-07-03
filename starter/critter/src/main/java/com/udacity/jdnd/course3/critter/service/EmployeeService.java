package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dao.EmployeeRepository;
import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long employeeId) {
        Optional<Employee> employee= employeeRepository.findById(employeeId);
        if(employee.isPresent()){
            return employee.get();
        }else{
            throw new EmployeeNotFoundException("Employee with id="+employeeId+" not found.");
        }
    }
}
