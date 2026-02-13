package com.EmployeeManagement.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmployeeDTO {

	// Keep ID present in DTO only to see ID in responses and we will keep it null
	// while creation
	private Long id;

	@NotBlank(message = "Employee First Name is mandatory")
	@Size(max = 30)
	private String firstName;

	@NotBlank(message = "Employee Last Name is mandatory")
	@Size(max = 50)
	private String lastName;

	@NotBlank(message = "Email is mandatory")
	@Email(message = "Enter a valid email address")
	private String email;

	@NotBlank(message = "Phone number is mandatory")
	@Pattern(regexp = "^\\d{10}$", message = "Phone number must be of 10 digits")
	private String phoneNumber;

	@Min(value = 18, message = "Age should not be less than 18")
	@Max(value = 65, message = "Age should not be greater than 65")
	private Integer age;

	private LocalDate dateOfJoining;

	@NotNull(message = "Department is mandatory")
	private String department;

	public EmployeeDTO() {

	}

	public EmployeeDTO(Long id,
			@NotBlank(message = "Employee First Name is mandatory") @Size(max = 30) String firstName,
			@NotBlank(message = "Employee Last Name is mandatory") @Size(max = 50) String lastName,
			@NotBlank @Email String email,
			@Pattern(regexp = "^\\d{10}$", message = "Phnoe number must be of 10 digits") String phoneNumber,
			@NotNull @Min(18) @Max(65) Integer age, @NotNull LocalDate dateOfJoining, @NotNull String department) {
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", age=" + age + ", dateOfJoining=" + dateOfJoining + ", department="
				+ department + "]";
	}

}
