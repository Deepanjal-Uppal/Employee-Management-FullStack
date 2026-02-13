package com.EmployeeManagement.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.EmployeeManagement.DTO.EmployeeDTO;
import com.EmployeeManagement.Exceptions.ResourceNotFoundException;
import com.EmployeeManagement.Service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

//Add these 2 imports
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;

@WebMvcTest(controllers = EmployeeController.class)
//@WebMvcTest is a springboot test slice annotation specifically designed for
//testing Spring Mvc web controllers in isolation.
//Why specify (controllers = EmployeeController.class)?
//Only bring up the EmployeeController and the beans it directly depends on for the MVC Configuration.
public class EmployeeControllerTest {

	// Mock Mvc is used to perform HTTP requests like Get or Post in tests without
	// starting actual server
	@Autowired
	private MockMvc mvc;

	// create a mock service class so that this test class can control the behaviour
	@MockBean
	private EmployeeService service;

	// used to convert java object to JSON and back(needed for sending request
	// bodies in TEST)
	@Autowired
	private ObjectMapper objectMapper;

	private EmployeeDTO dto(Long id) { // This is a helper method for testing purpose
		EmployeeDTO d = new EmployeeDTO();
		d.setId(id);
		d.setFirstName("ABC");
		d.setLastName("XYZ");
		d.setEmail("abc" + id + ".xyz@example.com");
		d.setPhoneNumber("98765432" + String.format("%02d", id));
		d.setAge(30);
		d.setDateOfJoining(LocalDate.of(2023, 5, 15));
		d.setDepartment("IT");
		return d;
	}

	@Test
	void create_should_return_201_and_body() throws Exception {
		EmployeeDTO in = dto(null); // input data initially set as null
		EmployeeDTO out = dto(101L); // should return an employee with id 101, email: abc101.xyz@example.com, phone:
										// 98765432101
		// below is stub service method + simulating wha service should do and what db
		// should return
		when(service.create(any(EmployeeDTO.class))).thenReturn(out);

		// POST request sent here
		mvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(out))). // converts DTO to JSON
				andExpect(status().isCreated()) // 201 status expected
				.andExpect(jsonPath("$.id", is(101))); // response body JSON with id 101
	}

	@Test
	void getById_should_return_200() throws Exception {
		when(service.getById(1L)).thenReturn(dto(1L));

		mvc.perform(get("/api/employees/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.email", is("abc1.xyz@example.com")));
	}

	@Test
	void getById_should_return_404_on_missing() throws Exception {
		Mockito.doThrow(new ResourceNotFoundException("Employee not found with id: 999")).when(service).getById(999L);
		mvc.perform(get("/api/employees/999")).andExpect(status().isNotFound()); // requires a @RestControllerAdvice
																					// mapping 404
	}

	@Test
	void updatePut_should_return_200() throws Exception{
		EmployeeDTO in =dto(null);
		in.setEmail("new@example.com");
		when(service.updatePut(eq(10L), any(EmployeeDTO.class))).thenReturn(dto(10L));
		
		mvc.perform(put("/api/employees/10").contentType(MediaType.APPLICATION_JSON)
										   .content(objectMapper.writeValueAsBytes(in)))
										   .andExpect(status().isOk())
										   .andExpect(jsonPath("$.id", is(10)));
		
	}

}
