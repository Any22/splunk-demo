package org.demo.appsplunk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private int id;
    private String empName;
    private int age;
    private long salary;
    private String gender;
    private String deptName;
    private String city;   
}
