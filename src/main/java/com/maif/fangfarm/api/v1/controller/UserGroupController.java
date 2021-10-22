package com.maif.fangfarm.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maif.fangfarm.api.v1.MaifLinks;
import com.maif.fangfarm.api.v1.assembler.GroupppModelAssembler;
import com.maif.fangfarm.api.v1.model.GroupppModel;
import com.maif.fangfarm.api.v1.openapi.controller.UserGroupControllerOpenApi;
import com.maif.fangfarm.core.security.MaifSecurity;
import com.maif.fangfarm.core.security.CheckSecurity;
import com.maif.fangfarm.domain.model.Usserr;
import com.maif.fangfarm.domain.service.RegisterUsserrService;

@RestController
@RequestMapping(path = "/v1/usserrs/{usserrId}/groups", 
	produces = MediaType.APPLICATION_JSON_VALUE)
public class UserGroupController implements UserGroupControllerOpenApi {

	@Autowired
	private RegisterUsserrService registerUsserr;
	
	@Autowired
	private GroupppModelAssembler groupModelAssembler;
	
	@Autowired
	private MaifLinks maifLinks;
	
	@Autowired
	private MaifSecurity maifSecurity;
	
	@CheckSecurity.UsersGroupPermission.CanConsult
	@Override
	@GetMapping
	public CollectionModel<GroupppModel> list(@PathVariable Long usserrId) {
		Usserr usserr = registerUsserr.findOrFail(usserrId);
		
		CollectionModel<GroupppModel> groupsModel = groupModelAssembler.toCollectionModel(usserr.getGroups())
				.removeLinks();
		
		if (maifSecurity.canEditUsserrsGroupsPermissions()) {
			groupsModel.add(maifLinks.linkToUsserrGropuAssociation(usserrId, "associate"));
			
			groupsModel.getContent().forEach(groupModel -> {
				groupModel.add(maifLinks.linkToUsserrGroupDisfellowshipping(
						usserrId, groupModel.getId(), "disassociate"));
			});
		}
		
		return groupsModel;
	}
	
	@CheckSecurity.UsersGroupPermission.CanEdit
	@Override
	@DeleteMapping("/{groupId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> disassociate(@PathVariable Long usserrId, @PathVariable Long groupId) {
		registerUsserr.disassociateGroup(usserrId, groupId);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.UsersGroupPermission.CanEdit
	@Override
	@PutMapping("/{groupId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associate(@PathVariable Long usserrId, @PathVariable Long groupId) {
		registerUsserr.associateGroup(usserrId, groupId);
		
		return ResponseEntity.noContent().build();
	}

}
