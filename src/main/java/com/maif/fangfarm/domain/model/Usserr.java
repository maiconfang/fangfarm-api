package com.maif.fangfarm.domain.model;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usserr {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dtCreate;
	
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dtUpdate;
	
	
	@ManyToMany
	@JoinTable(name = "usserr_grouppp", joinColumns = @JoinColumn(name = "usserr_id"),
			inverseJoinColumns = @JoinColumn(name = "grouppp_id"))
	private Set<Grouppp> groups = new HashSet<>();
	
	public boolean removeGroup(Grouppp group) {
		return getGroups().remove(group);
	}
	
	public boolean addGroup(Grouppp group) {
		return getGroups().add(group);
	}
	
	public boolean isNew() {
		return getId() == null;
	}
	
}
