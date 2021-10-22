package com.maif.fangfarm.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressInput {

	@ApiModelProperty(example = "E1C 1W2", required = true)
	@NotBlank
	private String zip;
	
	@ApiModelProperty(example = "St. George St", required = true)
	@NotBlank
	private String type;
	
	@ApiModelProperty(example = "251", required = true)
	@NotBlank
	private String number;
	
	@ApiModelProperty(example = "Building")
	private String complement;
	
	@ApiModelProperty(example = "Center", required = true)
	@NotBlank
	private String block;
	
	@Valid
	@NotNull
	private CityIdInput city;
	
}
