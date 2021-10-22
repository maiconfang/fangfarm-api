package com.maif.fangfarm.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsserrFilter {

	@ApiModelProperty(example = "1", value = "User Id of user of search")
	private Long usserrId;
	
	@ApiModelProperty(example = "1", value = "Name of user of search")
	private String name;
	
	@ApiModelProperty(example = "1", value = "E-mail of user of search")
	private String email;
	
	
}
