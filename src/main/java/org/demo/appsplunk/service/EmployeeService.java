package org.demo.appsplunk.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.demo.appsplunk.dto.EmployeeDTO;
import org.demo.appsplunk.entity.Employee;
import org.demo.appsplunk.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	//private final ModelMapper modelMapper;
	//model mapper is not working 

	
	public String saveTheEmp(EmployeeDTO employeeDTO) {
		Employee employee = Employee.builder()
				                    .empId(employeeDTO.getEmpId())
									.empName(employeeDTO.getEmpName())
									.age(employeeDTO.getAge())
									.gender(employeeDTO.getGender())
									.salary(employeeDTO.getSalary())
									.deptName(employeeDTO.getDeptName())
									.city(employeeDTO.getCity())
									.build();
		   
		 employeeRepository.save(employee);
		 log.info("saved employee is{} "+ employee);
	       
		return "Employee added";
	}


	public List<EmployeeDTO> getAllEmployees() {
		
		// getting all entities from DB 
	    List<Employee> employeeList = employeeRepository.findAll();
	    log.info("The customer payload from repo{}"+  employeeList);
	    
	    
	    List<EmployeeDTO> mylist = employeeList.stream()
	            .map(employee -> this.convertToDto( employee))
	            .collect(Collectors.toList());
	            							   
	    log.info("The customer payload from DTO: {} ", mylist);
	    return mylist;
	}

	


	public EmployeeDTO getEmployee(Integer empId) {
		
	
		Optional<Employee> optionalemployee = employeeRepository.findById(empId);
		
			if (optionalemployee.isPresent()) {
				Employee employee = optionalemployee .get();
		        return convertToDto(employee);
		    } else {
		        // Handle the case where the employee is not found
		        log.warn("Employee with ID " + empId + " not found.");
		        return null; // or throw a custom exception
		    }
	
	}
	
	 public EmployeeDTO convertToDto(Employee employee) {
	    	
		return  EmployeeDTO.builder()
						   .empId(employee.getEmpId())
						   .empName(employee.getEmpName())
						   .age(employee.getAge())
						   .gender(employee.getGender())
						   .salary(employee.getSalary())
						   .deptName(employee.getDeptName())
						   .city(employee.getCity())
						   .build();
	        
	    }


}
