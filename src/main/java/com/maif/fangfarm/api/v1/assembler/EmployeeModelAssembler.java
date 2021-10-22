package com.maif.fangfarm.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.maif.fangfarm.api.v1.MaifLinks;
import com.maif.fangfarm.api.v1.controller.EmployeeController;
import com.maif.fangfarm.api.v1.model.EmployeeModel;
import com.maif.fangfarm.core.security.MaifSecurity;
import com.maif.fangfarm.domain.model.Employee;

@Component
public class EmployeeModelAssembler extends RepresentationModelAssemblerSupport<Employee, EmployeeModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MaifLinks maifLinks;
	
	@Autowired
	private MaifSecurity maifSecurity;
	
	public EmployeeModelAssembler() {
		super(EmployeeController.class, EmployeeModel.class);
	}
	
	@Override
	public EmployeeModel toModel(Employee employee) {
		EmployeeModel employeeModel = createModelWithId(employee.getId(), employee);
		
		modelMapper.map(employee, employeeModel);
		
		if (maifSecurity.canConsultEmployees()) {
			employeeModel.add(maifLinks.linkToEmployees("employees"));
		}
		
		if (maifSecurity.canConsultCities()) {
			if (employeeModel.getAddress() != null 
					&& employeeModel.getAddress().getCity() != null) {
				employeeModel.getAddress().getCity().add(
						maifLinks.linkToCity(employee.getAddress().getCity().getId()));
			}
		}
		
		return employeeModel;
	}
	
	@Override
	public CollectionModel<EmployeeModel> toCollectionModel(Iterable<? extends Employee> entities) {
		CollectionModel<EmployeeModel> collectionModel = super.toCollectionModel(entities);
		
		if (maifSecurity.canConsultEmployees()) {
			collectionModel.add(maifLinks.linkToEmployees());
		}
		
		return collectionModel;
	}
	
}
