package com.java.employeeservice.service.mapper;

import com.java.employeeservice.dto.EmployeeDTO;
import com.java.employeeservice.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper implements EntityMapper<EmployeeDTO, Employee>{

    @Override
    public Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setEmail(dto.getEmail());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setDepartmentCode(dto.getDepartmentCode());
        return employee;
    }

    @Override
    public EmployeeDTO toDTO(Employee entity) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(entity.getId());
        employeeDTO.setEmail(entity.getEmail());
        employeeDTO.setFirstName(entity.getFirstName());
        employeeDTO.setLastName(entity.getLastName());
        employeeDTO.setDepartmentCode(entity.getDepartmentCode());
        return employeeDTO;
    }
}
