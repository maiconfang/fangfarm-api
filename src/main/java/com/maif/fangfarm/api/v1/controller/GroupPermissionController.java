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
import com.maif.fangfarm.api.v1.assembler.PermissionModelAssembler;
import com.maif.fangfarm.api.v1.model.PermissionModel;
import com.maif.fangfarm.api.v1.openapi.controller.GroupPermissionControllerOpenApi;
import com.maif.fangfarm.core.security.MaifSecurity;
import com.maif.fangfarm.core.security.CheckSecurity;
import com.maif.fangfarm.domain.model.Grouppp;
import com.maif.fangfarm.domain.service.RegisterGroupService;

@RestController
@RequestMapping(path = "/v1/groups/{groupId}/permissions",
		produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupPermissionController implements GroupPermissionControllerOpenApi {

	@Autowired
	private RegisterGroupService registerGroup;
	
	@Autowired
	private PermissionModelAssembler permissionModelAssembler;
	
	@Autowired
	private MaifLinks maifLinks;
	
	@Autowired
	private MaifSecurity maifSecurity;
	
	@CheckSecurity.UsersGroupPermission.CanConsult
	@Override
	@GetMapping
	public CollectionModel<PermissionModel> list(@PathVariable Long groupId) {
		Grouppp group = registerGroup.findOrFail(groupId);
		
		CollectionModel<PermissionModel> permissionsModel 
			= permissionModelAssembler.toCollectionModel(group.getPermissions())
				.removeLinks();
		
		permissionsModel.add(maifLinks.linkToGrupoPermissions(groupId));
		
		if (maifSecurity.canEditUsserrsGroupsPermissions()) {
			permissionsModel.add(maifLinks.linkToGroupPermissionAssociation(groupId, "association"));
		
			permissionsModel.getContent().forEach(permissionModel -> {
				permissionModel.add(maifLinks.linkToGropuPermissionDisassociate(
						groupId, permissionModel.getId(), "disassociation"));
			});
		}
		
		return permissionsModel;
	}
	
	@CheckSecurity.UsersGroupPermission.CanEdit
	@Override
	@DeleteMapping("/{permissionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> disassociate(@PathVariable Long groupId, @PathVariable Long permissionId) {
		registerGroup.disassociatePermission(groupId, permissionId);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.UsersGroupPermission.CanEdit
	@Override
	@PutMapping("/{permissionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associate(@PathVariable Long groupId, @PathVariable Long permissionId) {
		registerGroup.associatePermission(groupId, permissionId);
		
		return ResponseEntity.noContent().build();
	}

}
