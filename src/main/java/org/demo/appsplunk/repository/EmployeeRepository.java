package org.demo.appsplunk.repository;
import org.demo.appsplunk.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

	Employee findByEmpName(String empName);

}
