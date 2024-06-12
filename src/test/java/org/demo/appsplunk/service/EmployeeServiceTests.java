package org.demo.appsplunk.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.demo.appsplunk.dto.EmployeeDTO;
import org.demo.appsplunk.entity.Employee;
import org.demo.appsplunk.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/*************************************************************************************************************************************
 *we are mocking the Mapper method . We will stub its behavior in setup
 *Some frequently used Assertions are : assertNull(),assertNotNull(),assertFalse(), assertTrue(), assertNotSame()(don't refer to same 
 *object), assertSame() , assertEquals() ,assertNotEquals() , assertArrayEquals() --> not for List.
 * @author sabaakhtar
 *
 *************************************************************************************************************************************/

@ExtendWith(MockitoExtension.class)
@Slf4j
public class EmployeeServiceTests {
	
	@InjectMocks
    private EmployeeService service;

    @Mock
    private EmployeeRepository employeeRepository;
    // it should be intialized here other wise errors will be encountered 
//    @Mock
//    private ModelMapper modelMapper;

    private EmployeeDTO empDTO;
    private EmployeeDTO empDTO1;
    private Employee employee;
    private Employee employee1;

    @BeforeEach
    void setUp() {
      
        empDTO = EmployeeDTO.builder()
                .empId(12)
                .empName("TestName")
                .age(45)
                .salary(10000L)
                .gender("Male")
                .deptName("ACC")
                .city("testCity")
                .build();
        
        empDTO1 = EmployeeDTO.builder()
                .empId(13)
                .empName("TestName2")
                .age(46)
                .salary(11000L)
                .gender("Female")
                .deptName("IT")
                .city("testCity2")
                .build();
        
        employee = Employee.builder()
                .empId(empDTO.getEmpId())
                .empName(empDTO.getEmpName())
                .age(empDTO.getAge())
                .gender(empDTO.getGender())
                .salary(empDTO.getSalary())
                .deptName(empDTO.getDeptName())
                .city(empDTO.getCity())
                .build();
        
        employee1 = Employee.builder()
                .empId(empDTO1.getEmpId())
                .empName(empDTO1.getEmpName())
                .age(empDTO1.getAge())
                .gender(empDTO1.getGender())
                .salary(empDTO1.getSalary())
                .deptName(empDTO1.getDeptName())
                .city(empDTO1.getCity())
                .build();
    }

	 
	@Test
	public void saveTheEmpOrNot () {
		
		// We don't need to stub employeeRepository.save because it returns nothing except for String message 
		String actual = service.saveTheEmp(empDTO);
        log.info("the employee payload expected {}" + employee);
		assertEquals(employee.getAge(), empDTO.getAge());
        assertEquals("Employee added", actual);
	}

	@Test
	public void get_AllEmployees_as_listOfEmployee(){
		 List<Employee> empListExpected = Arrays.asList(employee, employee1);
	        
	        // Mock the repository response
	        when(employeeRepository.findAll()).thenReturn(empListExpected);

	        // Fetch employees via service
	        List<EmployeeDTO> empListActual = service.getAllEmployees();
	        
	       log.info("The emplist data is "+ empListActual);
	       
	        assertNotNull(empListExpected);
	        assertEquals(2, empListActual.size(), "The size of the employee list should be 2");
            assertEquals(empListExpected.get(0).getAge(), empListActual.get(0).getAge());



	}
	/************************************************************************************************************************************
	 * we are doing parameterized Test here : Some frequently used Parameterized Test Annotations are : @CSVSource, @CSVFileSource
	 * @ValueSource
	 * @EmptySource :Works for String, List, set ,map ,arrays of primitive datatypes (int[], char[] etc.)Objects arrays(String[], 
	 * Integer[])
	 *************************************************************************************************************************************/
	@ParameterizedTest
	@EmptySource
	public void get_AllEmployees_as_listOfEmployee_check_for_nullorEmpty_values(List<Employee> empListExpected){
		assertTrue(empListExpected.isEmpty());
		
	}

	@ParameterizedTest
	@ValueSource(ints = {12, 13})
	public void get_Employee_through_id(Integer empId) {
	    // Mock the repository to return an employee for certain IDs
	    Employee mockEmployee = new Employee(empId, "TestName", 45, 10000L, "Male", "ACC", "testCity");
	    
	    // When findById is called with empId, return an Optional containing mockEmployee
	    when(employeeRepository.findById(empId)).thenReturn(Optional.of(mockEmployee));
	    
	    // Call the method under test
	    EmployeeDTO employeeDTO = service.getEmployee(empId);

	    // Assert that the employeeDTO is not null and has expected values
	    assertNotNull(employeeDTO);
	    assertEquals(empId.intValue(), employeeDTO.getEmpId());
	    assertEquals("TestName", employeeDTO.getEmpName());
	}

	@ParameterizedTest
	@ValueSource(ints = 999)
	public void get_Employee_not_found(Integer id) {
	   
	    
	    // Mock the repository to return an empty Optional when the employee is not found
	    when(employeeRepository.findById(id)).thenReturn(Optional.empty());

	    // Call the method under test
	    EmployeeDTO employeeDTO = service.getEmployee(id);

	    // Assert that the employeeDTO is null (or handle as per your method's logic)
	    assertNull(employeeDTO);
	}
}
