package com.maif.fangfarm.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VehicleFilter {

	@ApiModelProperty(example = "1", value = "ID of vehicle search")
	private Long vehicleId;
	
	@ApiModelProperty(example = "Green", value = "Color of vehicle search")
	private String color;
	
	@ApiModelProperty(example = "2020-2022", value = "year of vehicle search")
	private String year;
	
	@ApiModelProperty(example = "MAIF-1233", value = "License Plate of vehicle search")
	private String licensePlate;
	
	@ApiModelProperty(example = "Diesel", value = "Fuel of vehicle search")
	private String fuel;
	
	@ApiModelProperty(example = " Versatile MFWD", value = "Description of vehicle search")
	private String description;
	
	@ApiModelProperty(example = "1", value = "ID of brand for search filter")
	private Long brandId;
	
	
}
