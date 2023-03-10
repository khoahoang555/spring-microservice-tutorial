package com.java.departmentservice.service.impl;

import com.java.departmentservice.dto.DepartmentDTO;
import com.java.departmentservice.entity.Department;
import com.java.departmentservice.repository.DepartmentRepository;
import com.java.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(
            DepartmentRepository departmentRepository
    ) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department(
            departmentDTO.getId(),
            departmentDTO.getDepartmentName(),
            departmentDTO.getDepartmentDescription(),
            departmentDTO.getDepartmentCode()
        );

        Department departmentSaved = departmentRepository.save(department);

        DepartmentDTO departmentDTOSaved = new DepartmentDTO(
            departmentSaved.getId(),
            departmentSaved.getDepartmentName(),
            departmentSaved.getDepartmentDescription(),
            departmentSaved.getDepartmentCode()
        );

        return departmentDTOSaved;
    }

    @Override
    public DepartmentDTO getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        DepartmentDTO departmentDTO = new DepartmentDTO(
            department.getId(),
            department.getDepartmentName(),
            department.getDepartmentDescription(),
            department.getDepartmentCode()
        );
        return departmentDTO;
    }
}
