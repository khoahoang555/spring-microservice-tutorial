package com.java.employeeservice.service.impl;

import com.java.employeeservice.dto.APIResponseDTO;
import com.java.employeeservice.dto.DepartmentDTO;
import com.java.employeeservice.dto.EmployeeDTO;
import com.java.employeeservice.entity.Employee;
import com.java.employeeservice.repository.EmployeeRepository;
import com.java.employeeservice.service.APIClient;
import com.java.employeeservice.service.EmployeeService;
import com.java.employeeservice.service.mapper.EmployeeMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private EmployeeMapper employeeMapper;

//    private RestTemplate restTemplate;

//    private WebClient webClient;

    private APIClient apiClient;

    public EmployeeServiceImpl(
            EmployeeRepository employeeRepository,
            EmployeeMapper employeeMapper,
            APIClient apiClient
//            WebClient webClient
//            RestTemplate restTemplate

    ) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
//        this.webClient = webClient;
        this.apiClient = apiClient;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO);
        Employee employeeSaved = employeeRepository.save(employee);
        return employeeMapper.toDTO(employeeSaved);
    }

    @Override
    public APIResponseDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();

    //        ResponseEntity<DepartmentDTO> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
    //                DepartmentDTO.class);
    //
    //        DepartmentDTO departmentDTO = responseEntity.getBody();

//        DepartmentDTO departmentDTO = webClient.get()
//                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDTO.class)
//                .block();

        DepartmentDTO departmentDTO = apiClient.getDepartment(employee.getDepartmentCode());

        EmployeeDTO employeeDTO = employeeMapper.toDTO(employee);

        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployeeDTO(employeeDTO);
        apiResponseDTO.setDepartmentDTO(departmentDTO);

        return apiResponseDTO;
    }
}
