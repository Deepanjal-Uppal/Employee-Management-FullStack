package com.EmployeeManagement.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.EmployeeManagement.DTO.EmployeeDTO;
import com.EmployeeManagement.Entity.Employee;
import com.EmployeeManagement.Exceptions.ResourceNotFoundException;
import com.EmployeeManagement.Mapper.EmployeeManualMapper;
import com.EmployeeManagement.Repository.EmployeeRepository;
import com.EmployeeManagement.Service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeManualMapper mapper;

	@Override
	public EmployeeDTO create(EmployeeDTO dto) {
		System.out.println("before saving : "+dto);
		if (dto.getEmail() != null && employeeRepository.existsByEmailIgnoreCase(dto.getEmail())) {
			throw new DataIntegrityViolationException("Email Already Exists: " + dto.getEmail());
		}
		Employee entity = mapper.toEntity(dto);
		Employee saved = employeeRepository.save(entity);
		System.out.println("after saving : "+saved);
		return mapper.toDto(saved);
	}

	@Override
public List<EmployeeDTO> getAll() {
    return employeeRepository.findAll()
            .stream()
            .map(mapper::toDto)
            .toList();
}

	@Override
	public EmployeeDTO getById(Long id) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("employee not found with id" + id));
		
		return mapper.toDto(employee);
	}

//	@Override
//	public Page<EmployeeDTO> list(Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public EmployeeDTO updatePut(Long id, EmployeeDTO dto) throws ResourceNotFoundException {
		Employee target = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("employee not found with id" + id));
		
		if (dto.getEmail() != null && employeeRepository.existsByEmailIgnoreCaseAndIdNot(dto.getEmail(), id)) {
			throw new DataIntegrityViolationException("Email Already Exists: " + dto.getEmail());
		}
		mapper.overwrite(target, dto);
		Employee saved = employeeRepository.save(target);

		return mapper.toDto(saved);
	}

	@Override
	public EmployeeDTO updatePatch(Long id, EmployeeDTO dto) throws ResourceNotFoundException {
		Employee target = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("employee not found with id" + id));
		
		if (dto.getEmail() != null && employeeRepository.existsByEmailIgnoreCaseAndIdNot(dto.getEmail(), id)) {
			throw new DataIntegrityViolationException("Email Already Exists: " + dto.getEmail());
		}
		mapper.patch(target, dto); // COPY ONLY AND ONLY NON NULL FIELDS
		Employee saved = employeeRepository.save(target);

		return mapper.toDto(saved);
	}

	@Override
	public void delete(Long id) throws ResourceNotFoundException {
		Employee emp = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
		employeeRepository.delete(emp);
	}

}
