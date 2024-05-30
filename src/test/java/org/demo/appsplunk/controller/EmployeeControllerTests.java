package org.demo.appsplunk.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.demo.appsplunk.dto.EmployeeDTO;

import org.demo.appsplunk.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTests {
	/***********************************************************************************************************************************
	 * @Mock is used to create mock objects of classes or interfaces. @InjectMocks is used to inject mocks into the class under test.
	 * @Mock is applied to fields that need to be mocked. 
	 * @InjectMocks is applied to the instance of the class under test to inject the mocked dependencies.
	 * 
	 * JUnit 5 has a powerful extension model and Mockito recently published one under the group / artifact ID :
	 *   org.mockito : mockito-junit-jupiter
	 *  You can apply the extension by adding @ExtendWith(MockitoExtension.class) to the test class and 
	 *  annotating mocked fields with @Mock. 
	 ***********************************************************************************************************************************/
	 @Mock
	 private EmployeeService service;

	 @InjectMocks
	 private EmployeeController controller;
	  
	 private EmployeeDTO empDTO;
	  
	  @BeforeEach
	  void setUp() {
		  MockitoAnnotations.openMocks(this);
		  empDTO = EmployeeDTO.builder()
				  .id(12)
                  .empName("TestName")
                  .age(45)
                  .city("testCity")
                  .build();
		  
	  }

       @Test
       void should_create_CustomerTest() {
    	   
    	  EmployeeDTO expected = (EmployeeDTO) when(service.saveTheEmp(empDTO)).thenReturn(empDTO);
    	   
    	  ResponseEntity<EmployeeDTO> actual = controller.createCustomer(empDTO);
    	  
    	  assertEquals(expected.getAge(), actual.getBody().getAge());
    	  assertEquals(expected.getEmpName(), actual.getBody().getEmpName());
    	  
       }
       
       @SuppressWarnings("unchecked")
       @Test
       void getCustomerTest() {
    	   
    	   List<EmployeeDTO> empList =  new ArrayList<>();
    	   empList.add(empDTO);
    	   
    	   List<EmployeeDTO> expected = (List<EmployeeDTO>) when(service.getAllEmployees()).thenReturn(empList);
    	   
    	   ResponseEntity<List<EmployeeDTO>> actual = controller.getCustomer();
    	   
    	   assertEquals(expected.get(0).getAge(), actual.getBody().get(0).getAge());
    	   
       } 
       
       @Test
       void getSpecificCustomerTest() {
    	   
    	   EmployeeDTO expected = (EmployeeDTO) when(service.getEmployee(empDTO.getId())).thenReturn(empDTO);
    	   ResponseEntity<EmployeeDTO> actual = controller.getSpecificCustomer(empDTO.getId());
    	   
    	   assertEquals("TestName", actual.getBody().getEmpName());
    	   assertEquals(expected.getEmpName(), actual.getBody().getEmpName());
    	   
    	   
       }
}
