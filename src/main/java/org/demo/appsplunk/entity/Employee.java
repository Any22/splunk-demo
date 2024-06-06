package org.demo.appsplunk.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="emp_record")
public class Employee {
	//approach from hibernate 
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_record_seq")
	@SequenceGenerator(name = "emp_record_seq", sequenceName = "emp_record_seq", allocationSize = 1)
	@Column(name="emp_Id" ,nullable=false)
	private int empId;
	@Column(name="emp_name" ,nullable=false)
    private String empName;
	
    private int age;
    private long salary;
    private String gender;
    
    @Column(name="dept_name" ,nullable=false)
    private String deptName;
    
    private String city;   
}
