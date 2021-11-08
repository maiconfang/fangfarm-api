package com.maif.fangfarm.api.v1.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maif.fangfarm.api.ResourceUriHelper;
import com.maif.fangfarm.api.v1.assembler.EmployeeInputDisassembler;
import com.maif.fangfarm.api.v1.assembler.EmployeeModelAssembler;
import com.maif.fangfarm.api.v1.model.EmployeeModel;
import com.maif.fangfarm.api.v1.model.input.EmployeeInput;
import com.maif.fangfarm.api.v1.openapi.controller.EmployeeControllerOpenApi;
import com.maif.fangfarm.core.data.PageWrapper;
import com.maif.fangfarm.core.data.PageableTranslator;
import com.maif.fangfarm.core.security.CheckSecurity;
import com.maif.fangfarm.domain.filter.EmployeeFilter;
import com.maif.fangfarm.domain.model.Employee;
import com.maif.fangfarm.domain.repository.EmployeeRepository;
import com.maif.fangfarm.domain.service.RegisterEmployeeService;
import com.maif.fangfarm.infrastructure.repository.spec.EmployeeSpecs;

@RestController
@RequestMapping(path = "/v1/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController implements EmployeeControllerOpenApi {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RegisterEmployeeService registerEmployee;
	
	@Autowired
	private EmployeeModelAssembler employeeModelAssembler;
	
	@Autowired
	private EmployeeInputDisassembler employeeInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Employee> pagedResourcesAssembler;
	
	@CheckSecurity.Employees.CanConsult
	@Override
	@GetMapping
	public PagedModel<EmployeeModel> list(EmployeeFilter filter, Pageable pageable) {
		
		Pageable pageableTranslate = translatePageable(pageable);
		Page<Employee> employeesPage = null;
		
		if(filter.getName()!=null ) {
			employeesPage = employeeRepository.findAll(EmployeeSpecs.withName(filter), pageableTranslate);
		}
		else
			employeesPage = employeeRepository.findAll(pageable);
		
		employeesPage = new PageWrapper<>(employeesPage, pageable);
		
		return pagedResourcesAssembler.toModel(employeesPage, employeeModelAssembler);
	}
	
	@CheckSecurity.Employees.CanConsult
	@Override
	@GetMapping("/{employeeId}")
	public EmployeeModel find(@PathVariable Long employeeId) {
		Employee employee = registerEmployee.findOrFail(employeeId);
		
		return employeeModelAssembler.toModel(employee);
	}
	
	@CheckSecurity.Employees.CanConsult
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EmployeeModel add(@RequestBody @Valid EmployeeInput employeeInput) {
			Employee employee = employeeInputDisassembler.toDomainObject(employeeInput);
			
			employee = registerEmployee.save(employee);
			
			EmployeeModel employeeModel = employeeModelAssembler.toModel(employee);
			
			ResourceUriHelper.addUriInResponseHeader(employeeModel.getId());
			
			return employeeModel;
	}
	
	@CheckSecurity.Employees.CanConsult
	@Override
	@PutMapping("/{employeeId}")
	public EmployeeModel update(@PathVariable Long employeeId,
			@RequestBody @Valid EmployeeInput employeeInput) {

			Employee employeeCurrent = registerEmployee.findOrFail(employeeId);
			
			employeeInputDisassembler.copyToDomainObject(employeeInput, employeeCurrent);
			
			employeeCurrent = registerEmployee.save(employeeCurrent);
			
			return employeeModelAssembler.toModel(employeeCurrent);
		
	}
	
	@CheckSecurity.Employees.CanConsult
	@Override
	@DeleteMapping("/{employeeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long employeeId) {
		registerEmployee.delete(employeeId);	
	}
	
	private Pageable translatePageable(Pageable apiPageable) {
		var mapping = Map.of(
				"id", "code",
				"name", "name",
				"rg", "rg",
				"cpf", "cpf" 
			);
		
		return PageableTranslator.translate(apiPageable, mapping);
	}
	
	
}
