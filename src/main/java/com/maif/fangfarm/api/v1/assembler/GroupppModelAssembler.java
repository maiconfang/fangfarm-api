package com.maif.fangfarm.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.maif.fangfarm.api.v1.MaifLinks;
import com.maif.fangfarm.api.v1.controller.GroupController;
import com.maif.fangfarm.api.v1.model.GroupppModel;
import com.maif.fangfarm.core.security.MaifSecurity;
import com.maif.fangfarm.domain.model.Grouppp;

@Component
public class GroupppModelAssembler extends RepresentationModelAssemblerSupport<Grouppp, GroupppModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MaifLinks maifLinks;
	
	@Autowired
	private MaifSecurity maifSecurity;
	
	public GroupppModelAssembler() {
		super(GroupController.class, GroupppModel.class);
	}
	
	@Override
	public GroupppModel toModel(Grouppp group) {
		GroupppModel groupModel = createModelWithId(group.getId(), group);
		modelMapper.map(group, groupModel);
		
		if (maifSecurity.canConsultUsserrsGroupsPermissions()) {
			groupModel.add(maifLinks.linkToGroups("grouppps"));
			
			groupModel.add(maifLinks.linkToGroupPermissions(group.getId(), "permissions"));
		}
		
		return groupModel;
	}
	
	@Override
	public CollectionModel<GroupppModel> toCollectionModel(Iterable<? extends Grouppp> entities) {
		CollectionModel<GroupppModel> collectionModel = super.toCollectionModel(entities);
		
		if (maifSecurity.canConsultUsserrsGroupsPermissions()) {
			collectionModel.add(maifLinks.linkToGroups());
		}
		
		return collectionModel;
	}
	
}
