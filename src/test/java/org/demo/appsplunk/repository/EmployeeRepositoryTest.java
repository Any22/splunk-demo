package org.demo.appsplunk.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.persistence.GeneratedValue;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.demo.appsplunk.dto.EmployeeDTO;
import org.demo.appsplunk.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
/**************************************************************************************************************************************
 * Use real repository instances for repository testing with @DataJpaTest to interact with the actual database.
 *@DataJpaTest: To test repository methods in isolation from the rest of the application but still with a real database 
 *(typically an in-memory database like H2). 
 *
 *************************************************************************************************************************************/
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository empRepository;

    private EmployeeDTO empDTO;
    private Employee employee;

    @BeforeEach
    void setUp() {
        empDTO = EmployeeDTO.builder()
                .empName("TestName")
                .age(45)
                .salary(10000L)
                .gender("Male")
                .deptName("ACC")
                .city("testCity")
                .build();

        employee = Employee.builder()
                .empName(empDTO.getEmpName())
                .age(empDTO.getAge())
                .gender(empDTO.getGender())
                .salary(empDTO.getSalary())
                .deptName(empDTO.getDeptName())
                .city(empDTO.getCity())
                .build();
        
        // Save the employee to the repository to set up test data
        empRepository.save(employee);
        
    }

    @Test
    @DisplayName("Test findByEmpName returns employee object for existenting name")
    public void testingTocheckFindByEmpNameIsReturningTheEmployeeObject() {
        String eName = "TestName";

        // Find the employee by name
        Employee emp = empRepository.findByEmpName(eName);
        

        // Verify that the employee is found and matches the expected values
        assertNotNull(emp);
       
        assertEquals(empDTO.getEmpName(), emp.getEmpName());
        assertEquals(empDTO.getAge(), emp.getAge());
        assertEquals(empDTO.getSalary(), emp.getSalary());
        assertEquals(empDTO.getGender(), emp.getGender());
        assertEquals(empDTO.getDeptName(), emp.getDeptName());
        assertEquals(empDTO.getCity(), emp.getCity());
    }
    
    @Test
    @DisplayName("Test findByEmpName returns null for non-existent name")
    public void testingToCheckFindByEmpNameReturnsNullForNonExistentName() {
        String nonExistentName = "NonExistentName";
        Employee emp = empRepository.findByEmpName(nonExistentName);
        assertNull(emp);
    }
    
    @Test
    public void testingTocheckFindByIdIsReturningTheEmployeeObject() {
    	//Getting the generated id ( @GeneratedValue) from entity class
    	int eId = employee.getEmpId(); // get the generated id according to the @GeneratedValue in entity class
        Optional<Employee> found = empRepository.findById(eId);
       
        if (found.isPresent()) {
            Employee actual = found.get();
         
            assertNotNull(actual);
            assertEquals(empDTO.getEmpName(), actual.getEmpName());
            assertEquals(empDTO.getAge(), actual.getAge());
            assertEquals(empDTO.getSalary(), actual.getSalary());
            assertEquals(empDTO.getGender(), actual.getGender());
            assertEquals(empDTO.getDeptName(), actual.getDeptName());
            assertEquals(empDTO.getCity(), actual.getCity());
        } else {
        	
        	 fail("Employee not found with id: " + eId);
        }
    }     
    
    @Test
    @DisplayName("Test findById returns empty Optional for non-existent ID")
    public void testingToCheckFindByIdReturnsEmptyOptionalForNonExistentId() {
        int nonExistentId = 9999;
        Optional<Employee> found = empRepository.findById(nonExistentId);
        assertFalse(found.isPresent());
    }
        
    @Test
    @DisplayName("Test repository is empty before saving any employee")
    public void testingToCheckRepositoryIsEmptyBeforeSaving() {
        empRepository.deleteAll(); // Ensure the repository is empty
        assertTrue(empRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Test multiple employees with the same name are handled correctly")
    public void testingToCheckMultipleEmployeesWithSameName() {
        Employee secondEmployee = Employee.builder()
                .empName(empDTO.getEmpName())
                .age(30)
                .salary(20000L)
                .gender("Female")
                .deptName("HR")
                .city("anotherCity")
                .build();

        empRepository.save(secondEmployee);

        // This test assumes the repository handles multiple employees with the same name appropriately
        List<Employee> employees = empRepository.findAll().stream()
                .filter(emp -> emp.getEmpName().equals(empDTO.getEmpName()))
                .collect(Collectors.toList());

        assertEquals(2, employees.size());
    }
}