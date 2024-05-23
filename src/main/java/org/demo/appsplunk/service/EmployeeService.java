package org.demo.appsplunk.service;

import java.util.List;
import java.util.stream.Collectors;

import org.demo.appsplunk.dto.EmployeeDTO;
import org.demo.appsplunk.entity.Employee;
import org.demo.appsplunk.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	private final ModelMapper modelMapper;

	
	public void saveTheEmp(EmployeeDTO employeeDTO) {
		Employee employee = Employee.builder()
									.empName(employeeDTO.getEmpName())
									.age(employeeDTO.getAge())
									.gender(employeeDTO.getGender())
									.salary(employeeDTO.getSalary())
									.deptName(employeeDTO.getDeptName())
									.city(employeeDTO.getCity())
									.build();
		   
		 employeeRepository.save(employee);
	       
		
	}


	public List<EmployeeDTO> getAllEmployees() {
		
		// getting all entities from DB 
	    List<Employee> employeeList = employeeRepository.findAll();
	    log.info("The customer payload from repo {}"+ employeeList);
	    return employeeList.stream()
	            .map(this::convertToDto)
	            .collect(Collectors.toList());
	}

	 public EmployeeDTO convertToDto(Employee employee) {
	    	
	    	// converting into DTO
	        return modelMapper.map(employee, EmployeeDTO.class);
	    }

}
