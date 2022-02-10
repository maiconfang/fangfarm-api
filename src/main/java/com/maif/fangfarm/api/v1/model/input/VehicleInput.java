package com.maif.fangfarm.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VehicleInput {

	@ApiModelProperty(example = "1", required = true)
	private Long id;
	
	@ApiModelProperty(example = "Green")
	private String color;
	
	@ApiModelProperty(example = "2021-2022", required = true)
	@NotBlank
	private String year;
	
	@ApiModelProperty(example = "MAIF-123", required = true)
	@NotBlank
	private String licensePlate;
	
	@ApiModelProperty(example = "Diesel")
	private String fuel;
	
	@ApiModelProperty(example = "Versatile MFWD")
	private String description;
	
	@Valid
	@NotNull
	private BrandIdInput brand;

}
