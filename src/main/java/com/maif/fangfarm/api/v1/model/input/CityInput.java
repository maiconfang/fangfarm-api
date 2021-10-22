package com.maif.fangfarm.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CityInput {

	@ApiModelProperty(example = "1", required = true)
	private Long id;
	
	@ApiModelProperty(example = "Moncton", required = true)
	@NotBlank
	private String name;
	
	@Valid
	@NotNull
	private StateIdInput state;

}
