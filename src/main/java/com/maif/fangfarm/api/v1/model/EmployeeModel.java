package com.maif.fangfarm.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "employees")
@Setter
@Getter
public class EmployeeModel extends RepresentationModel<EmployeeModel> {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Alexander")
	private String name;
	
	@ApiModelProperty(example = "8.749.577-8")
	private String rg;
	
	@ApiModelProperty(example = "87.495.744-44")
	private String cpf;
	
	@ApiModelProperty(example = "test@mail.com")
	private String email;
	
	private AddressModel address;
	
	
}
