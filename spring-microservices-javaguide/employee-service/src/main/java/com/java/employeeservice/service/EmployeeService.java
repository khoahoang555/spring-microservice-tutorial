package com.java.employeeservice.service;

import com.java.employeeservice.dto.APIResponseDTO;
import com.java.employeeservice.dto.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    APIResponseDTO getEmployeeById(Long employeeId);
}
