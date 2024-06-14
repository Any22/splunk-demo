package org.demo.appsplunk.dto;

import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

		 
		
		 
	 }
	 
	 @Test
	 @DisplayName("Test Should pass if the field values contain the correct values accroding to their data type and every filed must have some value")
	 void shouldGetTheCorrectValues_EmployeeDTO() {
		 
		 empDTO = EmployeeDTO.builder()
	                .empId(12)
	                .empName("TestName")
	                .age(45)
	                .salary(10000L)
	                .gender("Male")
	                .deptName("ACC")
	                .city("testCity")
	                .build();
		
		 
	 assertNotNull(empDTO.getDeptName());
	 assertEquals("Male",empDTO.getGender() );
	 assertEquals(45,empDTO.getAge());
	 assertNotEquals("",empDTO.getAge());
	 assertEquals("ACC",empDTO.getDeptName());
	
	 assertNotNull(empDTO.getDeptName(), "Department name should not be null"); 
     assertNotEquals("", empDTO.getDeptName(), "Department name should not be empty");
     
     assertNotNull(empDTO.getGender(), "Gender should not be null");
     assertNotEquals("", empDTO.getGender(), "Gender should not be empty");

     assertTrue(empDTO.getAge() > 0, "Age should be greater than 0");
     assertFalse(empDTO.getAge() < 0, "Age should not be lesser than 0");

     assertNotNull(empDTO.getEmpName(), "Employee name should not be null");
     assertNotEquals("", empDTO.getEmpName(), "Employee name should not be empty");

     assertNotNull(empDTO.getCity(), "City should not be null"); 
     assertNotEquals("", empDTO.getCity(), "City should not be empty");

     assertTrue(empDTO.getSalary() > 0, "Salary should be greater than 0");
     assertTrue(empDTO.getEmpId() > 0, "Employee ID should be greater than 0");


		 
	 }
	 
	 @Test
	    @DisplayName("Test with negative age")
	    void shouldGetTheCorrectValues_EmployeeDTO_NegativeAge() {
	        empDTO = EmployeeDTO.builder()
	                .empId(12)
	                .empName("TestName")
	                .age(-1)
	                .salary(10000L)
	                .gender("Male")
	                .deptName("ACC")
	                .city("testCity")
	                .build();

	        assertTrue(empDTO.getAge() < 0, "Age should not be lesser than 0");
	    }
	 
	 @Test
	    @DisplayName("Test with zero salary")
	    void shouldGetTheCorrectValues_EmployeeDTO_ZeroSalary() {
	        empDTO = EmployeeDTO.builder()
	                .empId(12)
	                .empName("TestName")
	                .age(45)
	                .salary(0)
	                .gender("Male")
	                .deptName("ACC")
	                .city("testCity")
	                .build();

	        assertTrue(empDTO.getSalary() == 0, "Salary should not be zero");
	    }

	 @Test
	    @DisplayName("Test with zero employee ID")
	    void shouldGetTheCorrectValues_EmployeeDTO_ZeroEmpId() {
	        empDTO = EmployeeDTO.builder()
	                .empId(0)
	                .empName("TestName")
	                .age(45)
	                .salary(10000L)
	                .gender("Male")
	                .deptName("ACC")
	                .city("testCity")
	                .build();

	        assertTrue(empDTO.getEmpId() == 0, "Employee ID should not be zero");
	    }
	 

	 @Test
	    @DisplayName("Test with incorrect gender")
	    void shouldGetIncorrectGenderValues_EmployeeDTO() {
	        empDTO = EmployeeDTO.builder()
	                .empId(0)
	                .empName("TestName")
	                .age(45)
	                .salary(10000L)
	                .gender("AlphaMale")
	                .deptName("ACC")
	                .city("testCity")
	                .build();

	        assertFalse(empDTO.getEmpName().equals("AlphaMale"), "Employee gender should be a string only");
	    }
	 
	 @Test
	    @DisplayName("Test with incorrect city")
	    void shouldGetIncorrectCityName_EmployeeDTO() {
	        empDTO = EmployeeDTO.builder()
	                .empId(0)
	                .empName("TestName")
	                .age(45)
	                .salary(10000L)
	                .gender("AlphaMale")
	                .deptName("ACC")
	                .city("testCity232")
	                .build();

	        assertTrue(empDTO.getCity().equals("testCity232"),"Employee Name should be a string only");
	    }

}
