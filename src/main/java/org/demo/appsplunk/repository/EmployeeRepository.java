package org.demo.appsplunk.repository;
import org.demo.appsplunk.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

	

}
