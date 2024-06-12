package org.demo.appsplunk.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.demo.appsplunk.dto.EmployeeDTO;
import org.demo.appsplunk.entity.Employee;
import org.demo.appsplunk.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
@Slf4j
@RequiredArgsConstructor
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
	 *  https://medium.com/@husnapoyraz88/spring-data-jpa-unit-test-repository-layer-9e875390645e
	 *  @DataJpaTest : provides a test slice for JPA repositories and initializes an in-memory database (like H2) for testing. However, 
	 *  it does not automatically populate the database with test data.
	 ***********************************************************************************************************************************/
	 @Mock
	 private EmployeeService service;

	 @InjectMocks
	 private EmployeeController controller;
	 

	 private final MockMvc mockMvc;
	 private EmployeeDTO empDTO;
	
	 
	  @BeforeEach
	  void setUp() {
		  
	  }
      
	  //https://medium.com/javarevisited/restful-api-testing-in-java-with-mockito-controller-layer-f4605f8ffaf3
       @Test
       void should_create_CustomerTest() throws Exception {
    	   
    	//   given(service.saveTheEmp((ArgumentMatchers.any())).will
 	  
       }
       
//       @SuppressWarnings("unchecked")
//       @Test
//       void getCustomerTest() {
//    	   
//    	   List<EmployeeDTO> empList =  new ArrayList<>();
//    	   empList.add(empDTO);
//    	  
//    	   when(service.getAllEmployees()).thenReturn(empList);
//    	  
//    	   
//    	   ResponseEntity<List<EmployeeDTO>> actual = controller.getCustomer();
//    	   
//    	   assertEquals(empList.get(0).getAge(), actual.getBody().get(0).getAge());
//    	   
//       } 
//       
//       
//       
//       @ParameterizedTest
//       @ValueSource(ints = {12})
//       void getSpecificCustomerTest(int empId) {
//    	   
//    	   when(service.getEmployee(empDTO.getEmpId())).thenReturn(empDTO);
//    	
//    	   ResponseEntity<EmployeeDTO> actual = controller.getSpecificCustomer(empId);
//    	   
//    	   assertEquals("TestName", actual.getBody().getEmpName());
//    	   assertEquals(empDTO.getEmpName(), actual.getBody().getEmpName());
//    	   
//    	   
//       }
}
