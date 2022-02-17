package com.maif.fangfarm.api.v1.model;

import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "animals")
@Setter
@Getter
public class AnimalModel extends RepresentationModel<AnimalModel> {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Amora")
	private String name;
	
	@ApiModelProperty(example = "IDEN-0548752")
	private String identification;
	
	@ApiModelProperty(example = "Female")
	private String sex;
	
	@ApiModelProperty(example = "5")
	private Integer age;
	
	@ApiModelProperty(example = "Maine Coon")
	private String breed;

	@ApiModelProperty(example = "2016-11-01")
	private OffsetDateTime dtBirthday;

	
}
