package com.EmployeeManagement.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Employee { //Domain Entity, Domain Model, Entity, Model, POJO
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // this line is reponsible to create unique ids for every record in db
	private Long id;
	
	//@NotBlank(message = "Employee First Name cannot be blank") // this validation is both for null and blank
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;
	
	//@NotBlank(message = "Last Name is mandatory") // this validation is both for null and blank
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;
	
	//@Email(message = "Email should be valid")
	@Column(unique = true, nullable = false)
	private String email;
	
	//@Pattern(regexp = "^\\d{10}$", message = "Phnoe number must be of 10 digits")
	@Column(name = "phone_number", length = 10, unique = true)
	private String phoneNumber;
	
	//@Min(value = 18, message = "Age should not be less than 18")
	//@Max(value = 65, message = "Age should not be greater than 65")
	@Column(nullable = false)
	private Integer age;
	
	@Column(name = "date_of_joining", nullable = false)
	private LocalDate dateOfJoining;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private Department department;
	
	public enum Department{
		HR, IT, FINANCE, SALES, MARKETING, ADMIN
	}

	public Employee() {
		
	}

	public Employee(Long id, @NotBlank(message = "Employee First Name cannot be blank") String firstName,
			@NotBlank(message = "Last Name is mandatory") String lastName,
			@Email(message = "Email should be valid") String email,
			@Pattern(regexp = "^\\d{10}$", message = "Phone number must be of 10 digits") String phoneNumber,
			@Min(value = 18, message = "Age should not be less than 18") @Max(value = 65, message = "Age should not be greater than 65") Integer age,
			LocalDate dateOfJoining, Department department) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.dateOfJoining = dateOfJoining;
		this.department = department;
	}

	@PrePersist
public void setDateOfJoiningAutomatically() {
    this.dateOfJoining = LocalDate.now();
}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
