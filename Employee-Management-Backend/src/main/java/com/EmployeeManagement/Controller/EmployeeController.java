package com.EmployeeManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeManagement.DTO.EmployeeDTO;
import com.EmployeeManagement.Exceptions.ResourceNotFoundException;
import com.EmployeeManagement.Service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<EmployeeDTO> create(@Valid @RequestBody EmployeeDTO employeeDto){
		EmployeeDTO savedEmployeeDTO = employeeService.create(employeeDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployeeDTO);
	}

	@GetMapping
public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
    return ResponseEntity.ok(employeeService.getAll());
}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@Valid @PathVariable("id") long id) throws ResourceNotFoundException{
		EmployeeDTO employee = employeeService.getById(id);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<EmployeeDTO> deleteEmployeeById(@Valid @PathVariable("id") long id) throws ResourceNotFoundException{
		employeeService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") long id, @Valid @RequestBody EmployeeDTO employeedto) throws ResourceNotFoundException{
		EmployeeDTO emp = employeeService.updatePut(id, employeedto);
		return ResponseEntity.status(HttpStatus.OK).body(emp);
		
	}

}
