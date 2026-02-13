package com.EmployeeManagement.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.EmployeeManagement.Entity.Employee;
import com.EmployeeManagement.Entity.Employee.Department;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	//we will write methods so that duplicates email, phone number will not be allowed to enter
	//and while retrieving all records, we will make sure data will be printed using order in Ascending order.
	boolean existsByEmailIgnoreCase(String email);
	
	boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id); //id should not match an existing id
	
	Optional<Employee> findByEmailIgnoreCase(String email);
	
	List<Employee> findByDepartment(Department department);

}
