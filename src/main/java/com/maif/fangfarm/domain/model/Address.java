package com.maif.fangfarm.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class Address {

	@Column(name = "address_zip")
	private String zip;
	
	@Column(name = "address_type")
	private String type;
	
	@Column(name = "address_number")
	private String number;
	
	@Column(name = "address_complement")
	private String complement;
	
	@Column(name = "address_block")
	private String block;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_city_id")
	private City city;
	
}
