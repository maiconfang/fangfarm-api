package com.maif.fangfarm.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StateFilter {

	@ApiModelProperty(example = "1", value = "ID of state of search")
	private Long stateId;
	
	@ApiModelProperty(example = "Moncton", value = "Name of state of search")
	private String name;
	
	@ApiModelProperty(example = "NB", value = "FS of state of search")
	private String fs;
	
	
}
