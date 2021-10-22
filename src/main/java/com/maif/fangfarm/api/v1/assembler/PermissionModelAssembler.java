package com.maif.fangfarm.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.maif.fangfarm.api.v1.MaifLinks;
import com.maif.fangfarm.api.v1.model.PermissionModel;
import com.maif.fangfarm.core.security.MaifSecurity;
import com.maif.fangfarm.domain.model.Permission;

@Component
public class PermissionModelAssembler implements RepresentationModelAssembler<Permission, PermissionModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MaifLinks maifLinks;
	
	@Autowired
	private MaifSecurity maifSecurity;

	@Override
	public PermissionModel toModel(Permission permission) {
		PermissionModel permissionModel = modelMapper.map(permission, PermissionModel.class);
		return permissionModel;
	}
	
	@Override
	public CollectionModel<PermissionModel> toCollectionModel(Iterable<? extends Permission> entities) {
		CollectionModel<PermissionModel> collectionModel 
			= RepresentationModelAssembler.super.toCollectionModel(entities);

		if (maifSecurity.canConsultUsserrsGroupsPermissions()) {
			collectionModel.add(maifLinks.linkToPermissions());
		}
		
		return collectionModel;
	}
	
}
