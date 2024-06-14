package org.demo.appsplunk.controller;

import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.demo.appsplunk.dto.EmployeeDTO;
import org.demo.appsplunk.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@PostMapping(value = "/create", consumes = APPLICATION_JSON_VALUE )
	  public ResponseEntity<String> createCustomer(@RequestBody EmployeeDTO employeeDTO ){
		
	    log.info("Creating Employee: The request payload {}"+ employeeDTO);
	String status =   service.saveTheEmp(employeeDTO);
	    
	 return new ResponseEntity<>(status,HttpStatus.CREATED);
		
	}
	
	
	 @GetMapping(value = "/get-emp", produces= APPLICATION_JSON_VALUE )
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
	 
	 
	 @GetMapping(value = "/get-emp/{empId}", produces= APPLICATION_JSON_VALUE )
	    public ResponseEntity<EmployeeDTO> getSpecificCustomer(@PathVariable int empId)  {
		 
	       try { 
	    	   	  EmployeeDTO customerDto = service.getEmployee(empId);
	        
	    	   		if ( null == customerDto) {
		        	
		        	log.info("May not have Customer regsitered against the id: "+ empId);
		            
		        } 
	    	   		log.info("The resposne payload {}"+ customerDto);
	    	   		
		        return new ResponseEntity<>(customerDto, HttpStatus.OK);
		        
	       } catch (Exception ex) {
			   log.error(ex.getMessage());
	    	   throw ex;
		   }
	        
	    }

}

