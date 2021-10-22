package com.maif.fangfarm.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeInput {
	
	@ApiModelProperty(example = "1", required = true)
	private Long id;

	@ApiModelProperty(example = "Alexander", required = true)
	@NotBlank
	private String name;
	
	@ApiModelProperty(example = "8.478.488-8", required = true)
	@NotBlank
	private String rg;
	
	@ApiModelProperty(example = "547.942.447-22", required = true)
	@NotBlank
	private String cpf;
	
	@Valid
	@NotNull
	private AddressInput address;
	
}
