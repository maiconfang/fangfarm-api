package com.maif.fangfarm.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GroupppInput {

	@ApiModelProperty(example = "1", required = true)
	private Long id;
	
	@ApiModelProperty(example = "Manager", required = true)
	@NotBlank
	private String name;
	
}
