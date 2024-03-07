package com.maif.fangfarm.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeFilter {

	@ApiModelProperty(example = "1", value = "ID of employee of search")
	private Long employeeId;
	
	@ApiModelProperty(example = "Maicon", value = "Name of employee of search")
	private String name;
	
	@ApiModelProperty(example = "87455883", value = "RG of employee of search")
	private String rg;
	
	@ApiModelProperty(example = "548421844", value = "CPF of employee of search")
	private String cpf;
	
	@ApiModelProperty(example = "test@mail.com", value = "E-Mail of employee of search")
	private String email;
	
	
}
