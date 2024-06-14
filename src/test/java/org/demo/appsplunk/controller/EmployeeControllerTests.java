package org.demo.appsplunk.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTests {
	/***********************************************************************************************************************************
	 * @Mock is used to create mock objects of classes or interfaces. @InjectMocks is used to inject mocks into the class under test.
	 * @Mock is applied to fields that need to be mocked. 
	 * @InjectMocks is applied to the instance of the class under test to inject the mocked dependencies.
	 * @WebMvcTest(EmployeeController.class) : Configures a test slice for EmployeeController.
	 * JUnit 5 has a powerful extension model and Mockito recently published one under the group / artifact ID :
	 *   org.mockito : mockito-junit-jupiter
	 *  You can apply the extension by adding @ExtendWith(MockitoExtension.class) to the test class and 
	 *  annotating mocked fields with @Mock. 
	 *  https://medium.com/@husnapoyraz88/spring-data-jpa-unit-test-repository-layer-9e875390645e
	 *  @DataJpaTest : provides a test slice for JPA repositories and initializes an in-memory database (like H2) for testing. However, 
	 *  it does not automatically populate the database with test data.
	 *  Use MockMvc to perform HTTP requests and assertions on the controller.

	 ***********************************************************************************************************************************/
	@Autowired 
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService empService;

	@InjectMocks
	private EmployeeController employeeController;
	  
	private EmployeeDTO empDTO;
	
	private EmployeeDTO empDTO2;
	List<EmployeeDTO> empList = new ArrayList<>();
	 
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
		  empDTO2 = EmployeeDTO.builder()
	                .empId(13)
	                .empName("TestName2")
	                .age(46)
	                .salary(11000L)
	                .gender("Female")
	                .deptName("IT")
	                .city("testCity2")
	                .build();

	       empList.add(empDTO);
	       empList.add(empDTO2);
//	  mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
		  
	  }
      
	  //https://medium.com/javarevisited/restful-api-testing-in-java-with-mockito-controller-layer-f4605f8ffaf3
	  @Test
	    void createEmployeeTest() throws Exception {
		  
	        when(empService.saveTheEmp(any(EmployeeDTO.class))).thenReturn("Employee created successfully");

	        mockMvc.perform(post("/create")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(empDTO)))
	                .andExpect(status().isCreated())
	                .andExpect(content().string("Employee created successfully"));
	    }
	  
	  
	  //Helper method
	  private static String asJsonString(final Object obj) {
		    try {
		        return new ObjectMapper().writeValueAsString(obj);
		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }
		}

       
	  @Test
       void toTestTogetCustomerOrNot() throws Exception {
    	   
    	   List<EmployeeDTO> empList =  new ArrayList<>();
    	   empList.add(empDTO);
    	  
    	   when(empService.getAllEmployees()).thenReturn(empList);
    	   
    	   mockMvc.perform(get("/get-emp")
                   .accept(MediaType.APPLICATION_JSON))                    //specifies the desired format of the response from the server, indicating that the test expects a JSON response.
                   .andExpect(status().isOk())
                   .andExpect(content().json(asJsonString(empList)));
    	   
    	   assertNotNull(empList);
    	   assertEquals (empList.get(0).getCity(), empDTO.getCity());
    	   assertEquals (empList.get(0).getAge(), empDTO.getAge());
    	   assertEquals (empList.get(0).getDeptName(), empDTO.getDeptName());
    	   assertEquals (empList.get(0).getEmpId(), empDTO.getEmpId());
    	   verify(empService, times(1)).getAllEmployees();
       } 
       
	  @ParameterizedTest
	    @ValueSource(ints = {12})
	    void getSpecificCustomerTest(int empId) throws Exception {
	        when(empService.getEmployee(empId)).thenReturn(empDTO);

	        mockMvc.perform(get("/get-emp/{empId}", empId)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().json(asJsonString(empDTO)));
	    }
       
       
}
