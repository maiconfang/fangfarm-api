package com.maif.fangfarm.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.maif.fangfarm.api.v1.model.EmployeeModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("EmployeesModel")
@Data
public class EmployeesModelOpenApi {

	private EmployeesEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("StatesEmbeddedModel")
	@Data
	public class EmployeesEmbeddedModelOpenApi {
		
		private List<EmployeeModel> employees;
		
	}
	
}
