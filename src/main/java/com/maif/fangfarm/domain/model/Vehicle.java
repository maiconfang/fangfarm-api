package com.maif.fangfarm.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.maif.fangfarm.core.validation.Groups;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Vehicle {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String color;
	
	@NotBlank
	@Column(nullable = false)
	private String year;
	
	@NotBlank
	@Column(nullable = false)
	private String licensePlate;
	
	@Column(nullable = false)
	private String fuel;
	
	@Column(nullable = false)
	private String description;
	
	@Valid
	@ConvertGroup(from = Default.class, to = Groups.BrandId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Brand brand;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dtCreate;
	
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dtUpdate;

}