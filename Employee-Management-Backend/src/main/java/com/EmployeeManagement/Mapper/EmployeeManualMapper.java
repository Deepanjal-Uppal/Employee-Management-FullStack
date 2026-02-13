package com.EmployeeManagement.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.EmployeeManagement.DTO.EmployeeDTO;
import com.EmployeeManagement.Entity.Employee;

@Component
public class EmployeeManualMapper {

    public Employee toEntity(EmployeeDTO dto) {
        if (dto == null) return null;

        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setAge(dto.getAge());
        employee.setDateOfJoining(dto.getDateOfJoining());
        // if (dto.getDepartment() != null) {
        //     employee.setDepartment(Employee.Department.valueOf(dto.getDepartment()));
        // }
        if (dto.getDepartment() == null || dto.getDepartment().isBlank()) {
    throw new IllegalArgumentException("Department is required");
}
employee.setDepartment(Employee.Department.valueOf(dto.getDepartment()));
        return employee;
    }

    public EmployeeDTO toDto(Employee employee) {
        if (employee == null) return null;

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setPhoneNumber(employee.getPhoneNumber());
        dto.setDateOfJoining(employee.getDateOfJoining());
        dto.setAge(employee.getAge());
        if (employee.getDepartment() != null) {
            dto.setDepartment(employee.getDepartment().name());
        }

        return dto;
    }

    public List<EmployeeDTO> toDtoList(List<Employee> list) {
        return list == null ? List.of() :
                list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void overwrite(Employee target, EmployeeDTO dto) {
        if (target == null || dto == null) return;

        target.setFirstName(dto.getFirstName());
        target.setLastName(dto.getLastName());
        target.setEmail(dto.getEmail());
        target.setPhoneNumber(dto.getPhoneNumber());
        target.setAge(dto.getAge());
        target.setDateOfJoining(dto.getDateOfJoining());

        if (dto.getDepartment() != null) {
            target.setDepartment(Employee.Department.valueOf(dto.getDepartment()));
        }
    }

    public void patch(Employee target, EmployeeDTO dto) {
        if (target == null || dto == null) return;

        if (dto.getFirstName() != null) target.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) target.setLastName(dto.getLastName());
        if (dto.getEmail() != null) target.setEmail(dto.getEmail());
        if (dto.getPhoneNumber() != null) target.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getAge() != null) target.setAge(dto.getAge());
        if (dto.getDateOfJoining() != null) target.setDateOfJoining(dto.getDateOfJoining());
        if (dto.getDepartment() != null)
            target.setDepartment(Employee.Department.valueOf(dto.getDepartment()));
    }
}
