package com.maif.fangfarm.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Employee {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String name;
	
	@NotBlank
	@Column(nullable = false)
	private String rg;
	
	@NotBlank
	@Column(nullable = false)
	private String cpf;
	
	@NotBlank
	@Column(nullable = false)
	private String email;
	
	@Embedded
	private Address address;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dtCreate;
	
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dtUpdate;

}