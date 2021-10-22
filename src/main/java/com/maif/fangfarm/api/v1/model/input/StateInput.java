package com.maif.fangfarm.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StateInput {

	@ApiModelProperty(example = "1", required = true)
	private Long id;
	
	@ApiModelProperty(example = "New Brunswick", required = true)
	@NotBlank
	private String name;
	
	@ApiModelProperty(example = "NB", required = true)
	@NotBlank
	private String fs;
}
