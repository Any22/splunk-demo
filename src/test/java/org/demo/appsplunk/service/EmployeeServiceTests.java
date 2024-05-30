package org.demo.appsplunk.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.demo.appsplunk.dto.EmployeeDTO;
import org.demo.appsplunk.entity.Employee;
import org.demo.appsplunk.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
/*************************************************************************************************************************************
 *we are mocking the Mapper method . We will stub its behavior in setup
 * @author sabaakhtar
 *
 */
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
//	@Mock
//	private ModelMapper modelMapper;
	
	@InjectMocks
	private EmployeeService service;
	
	private EmployeeDTO empDTO;

	private Employee  employee ;

	
	
	 @BeforeEach
	  void setUp() {
		  MockitoAnnotations.openMocks(this);
		 
		  empDTO = EmployeeDTO.builder()
				  .id(12)
                  .empName("TestName")
                  .age(45)
                  .city("testCity")
                  .build();
		  
//		  when(modelMapper.map(empDTO, Employee.class)).thenReturn(employee);
//	      when(modelMapper.map(employee, EmployeeDTO.class)).thenReturn(empDTO);
		  
	  }
	 
	@Test
	public void save_The_Emp_orNot (EmployeeDTO empDTO) {
		   
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeDTO result = service.saveTheEmp(empDTO);
        
        assertEquals(empDTO.getEmpName(), result.getEmpName());
        assertEquals(empDTO.getAge(), result.getAge());
        assertEquals(empDTO.getCity(), result.getCity());
	}

	

}
