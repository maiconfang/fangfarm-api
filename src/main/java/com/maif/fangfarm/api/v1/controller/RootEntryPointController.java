package com.maif.fangfarm.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maif.fangfarm.api.v1.MaifLinks;
import com.maif.fangfarm.core.security.MaifSecurity;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

	@Autowired
	private MaifLinks maifLinks;
	
	@Autowired
	private MaifSecurity maifSecurity;
	
	@GetMapping
	public RootEntryPointModel root() {
		var rootEntryPointModel = new RootEntryPointModel();
		
		if (maifSecurity.canConsultUsserrsGroupsPermissions()) {
			rootEntryPointModel.add(maifLinks.linkToGroups("groups"));
			rootEntryPointModel.add(maifLinks.linkToUsserrs("ussers"));
			rootEntryPointModel.add(maifLinks.linkToPermissions("permissions"));
		}
		
		if (maifSecurity.canConsultStates()) {
			rootEntryPointModel.add(maifLinks.linkToState("states"));
		}
		
		if (maifSecurity.canConsultCities()) {
			rootEntryPointModel.add(maifLinks.linkToCities("cities"));
		}
		
		return rootEntryPointModel;
	}
	
	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
	}
	
}
