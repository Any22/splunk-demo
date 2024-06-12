package org.demo.appsplunk.dto;

import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
//https://programmingtechie.com/2020/10/16/spring-boot-testing-tutorial-unit-testing-with-junit-5-and-mockito/
@ExtendWith(MockitoExtension.class)
public class EmployeeDTOTest {

	private EmployeeDTO empDTO;
	
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
		
		 
	 }
	 
	 @Test
	 @DisplayName("Test Should pass if the field values contain the correct values accroding to their data type and every filed must have some value")
	 void shouldGetTheCorrectValues_EmployeeDTO() {
		 
	 assertNotNull(empDTO.getDeptName());
	 assertNotEquals("",empDTO.getGender() );
	 assertNotEquals(0,empDTO.getAge());
	 assertNotEquals("",empDTO.getAge());
	 assertNotEquals(0,empDTO.getDeptName());


		 
	 }
	 
	

}
