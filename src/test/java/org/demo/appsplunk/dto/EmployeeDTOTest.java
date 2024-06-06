package org.demo.appsplunk.dto;

import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
//https://programmingtechie.com/2020/10/16/spring-boot-testing-tutorial-unit-testing-with-junit-5-and-mockito/
@ExtendWith(MockitoExtension.class)
public class EmployeeDTOTest {
	
	private EmployeeDTO empDTO;
	
	 @BeforeEach
	  void setUp() {
		 
		 MockitoAnnotations.openMocks(this);
		
		 
	 }
	 
	 @Test
	 @DisplayName("Test Should pass if the field values contain the correct values accroding to their data type")
	 void shouldGetTheCorrectValues_EmployeeDTO() {
		 empDTO = EmployeeDTO.builder()
				.empId(12)
                .empName("TestName")
                .age(45)
                .city("testCity")
                .build();
		 
//		 when(empDTO.getDeptName()).thenReturn(anyString());
//		 when(empDTO.getAge()).thenReturn(anyInt());
//		 when(empDTO.getSalary()).thenReturn(anyLong());
//		 when(empDTO.getGender()).thenReturn(anyString());
//		 when(empDTO.getCity()).thenReturn(anyString());
		 
	 assertEquals(empDTO.getDeptName(),anyString());
		verify(empDTO.getDeptName());
		verify(empDTO.getAge());
		verify(empDTO.getSalary());
		verify(empDTO.getGender());
		verify(empDTO.getCity());
		 
	 }
	 
	

}
