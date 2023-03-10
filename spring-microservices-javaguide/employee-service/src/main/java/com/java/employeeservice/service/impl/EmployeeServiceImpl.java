package com.java.employeeservice.service.impl;

import com.java.employeeservice.dto.EmployeeDTO;
import com.java.employeeservice.entity.Employee;
import com.java.employeeservice.repository.EmployeeRepository;
import com.java.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(
            EmployeeRepository employeeRepository
    ) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
                employeeDTO.getId(),
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getEmail()
        );

        Employee employeeSaved = employeeRepository.save(employee);

        EmployeeDTO employeeDTOSaved = new EmployeeDTO(
                employeeSaved.getId(),
                employeeSaved.getFirstName(),
                employeeSaved.getLastName(),
                employeeSaved.getEmail()
        );

        return employeeDTOSaved;
    }
}
