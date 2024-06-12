package org.demo.appsplunk.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.persistence.GeneratedValue;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.demo.appsplunk.dto.EmployeeDTO;
import org.demo.appsplunk.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@DataJpaTest
@Slf4j
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
        log.info("Saved employee: {}", employee);
    }

    @Test
    public void testingTocheckFindByEmpNameIsReturningTheEmployeeObject() {
        String eName = "TestName";

        // Find the employee by name
        Employee emp = empRepository.findByEmpName(eName);
        log.info("the emp from Repo is: {}", emp);
       

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
    public void testingTocheckFindByIdIsReturningTheEmployeeObject() {
    	 int eId = employee.getEmpId(); // get the generated id according to the @GeneratedValue
        Optional<Employee> found = empRepository.findById(eId);
        log.info("found: {}", found);
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
        	log.info("Employee not found with id: {}", eId);
        	 fail("Employee not found with id: " + eId);
        }
    }     
        

}