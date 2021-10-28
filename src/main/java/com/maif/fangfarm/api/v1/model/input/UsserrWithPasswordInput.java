package com.maif.fangfarm.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsserrWithPasswordInput extends UsserrInput {

	@ApiModelProperty(example = "123", required = true)
	@NotBlank (message = "{usserrWithPasswordInput.password.not.blank}")
	private String password;
	
}
