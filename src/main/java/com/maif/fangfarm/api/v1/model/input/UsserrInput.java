package com.maif.fangfarm.api.v1.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsserrInput {

	@ApiModelProperty(example = "1", required = true)
	private Long id;
	
	@ApiModelProperty(example = "Maicon Alexander", required = true)
	@NotBlank
	private String name;
	
	@ApiModelProperty(example = "maicon.alexander@fangfarm.com.br", required = true)
	@NotBlank
	@Email
	private String email;
	
}
