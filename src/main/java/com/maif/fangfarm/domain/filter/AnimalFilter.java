package com.maif.fangfarm.domain.filter;

import java.time.OffsetDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnimalFilter {

	@ApiModelProperty(example = "1", value = "ID of animal of search")
	private Long animalId;
	
	@ApiModelProperty(example = "Amora", value = "Name of animal of search")
	private String name;
	
	@ApiModelProperty(example = "IDEN-0548752", value = "Identification of animal of search")
	private String identification;
	
	@ApiModelProperty(example = "Female", value = "Sex of animal of search")
	private String sex;
	
	@ApiModelProperty(example = "5", value = "Age of animal of search")
	private Integer age;
	
	@ApiModelProperty(example = "Maine Coon", value = "Breed of animal of search")
	private String breed;

	@ApiModelProperty(example = "2016-11-01", value = "Date of birthday of animal of search")
	private OffsetDateTime dtBirthday;
	
	
}
