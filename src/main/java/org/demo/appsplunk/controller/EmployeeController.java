package org.demo.appsplunk.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.demo.appsplunk.dto.EmployeeDTO;
import org.demo.appsplunk.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor

public class EmployeeController {
	
	private final EmployeeService service;
	
	@PostMapping(value = "/create", consumes = {"application/json"})
	  public ResponseEntity<String> createCustomer(@RequestBody EmployeeDTO employeeDTO ){
		
	    log.info("Creating Employee: The request payload {}"+ employeeDTO);
	    service.saveTheEmp(employeeDTO);
	    
	 return new ResponseEntity<>("Data created for : "+ employeeDTO.getEmpName(), HttpStatus.CREATED);
		
	}
	
	
	 @GetMapping(produces= APPLICATION_JSON_VALUE, value = "/get-emp")
	    public ResponseEntity<List<EmployeeDTO>> getCustomer()  {
		 
	       try { 
	    	   	  List<EmployeeDTO> customerDto = service.getAllEmployees();
	        
	    	   		if (customerDto.isEmpty() || null == customerDto) {
		        	
		        	log.info("the data dto is empty or null");
		            
		        } 
	    	   		log.info("The resposne payload {}"+ customerDto);
		        return new ResponseEntity<>(customerDto, HttpStatus.OK);
	       } catch (Exception ex) {
			   log.error(ex.getMessage());
	    	   throw ex;
		   }
	        
	    }

}
