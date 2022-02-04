package com.maif.fangfarm.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BrandFilter {

	@ApiModelProperty(example = "1", value = "ID of brand search")
	private Long brandId;
	
	@ApiModelProperty(example = "1", value = "Name of brand search")
	private String name;
	
	@ApiModelProperty(example = "1", value = "ID for search filter")
	private Long modelId;
	
	
}
