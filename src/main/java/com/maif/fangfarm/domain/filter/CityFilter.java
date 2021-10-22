package com.maif.fangfarm.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CityFilter {

	@ApiModelProperty(example = "1", value = "ID of city search")
	private Long cityId;
	
	@ApiModelProperty(example = "1", value = "Name of city search")
	private String name;
	
	@ApiModelProperty(example = "1", value = "ID for search filter")
	private Long stateId;
	
	
}
