package com.maif.fangfarm.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressModel {

	@ApiModelProperty(example = "E1C 1W2")
	private String zip;
	
	@ApiModelProperty(example = "St. George St")
	private String type;
	
	@ApiModelProperty(example = "251")
	private String number;
	
	@ApiModelProperty(example = "Building")
	private String complement;
	
	@ApiModelProperty(example = "Center")
	private String block;
	
	private CitySummaryModel city;
	
}
