package com.maif.fangfarm.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.maif.fangfarm.api.v1.MaifLinks;
import com.maif.fangfarm.api.v1.controller.UsserrController;
import com.maif.fangfarm.api.v1.model.UsserrModel;
import com.maif.fangfarm.core.security.MaifSecurity;
import com.maif.fangfarm.domain.model.Usserr;

@Component
public class UsserrModelAssembler 
		extends RepresentationModelAssemblerSupport<Usserr, UsserrModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MaifLinks maifLinks;
	
	@Autowired
	private MaifSecurity maifSecurity;
	
	public UsserrModelAssembler() {
		super(UsserrController.class, UsserrModel.class);
	}
	
	@Override
	public UsserrModel toModel(Usserr usserr) {
		UsserrModel usserrModel = createModelWithId(usserr.getId(), usserr);
		modelMapper.map(usserr, usserrModel);
		
		if (maifSecurity.canConsultUsserrsGroupsPermissions()) {
			usserrModel.add(maifLinks.linkToUsserrs("users"));
			
			usserrModel.add(maifLinks.linkToGroupsUsserr(usserr.getId(), "groups-user"));
		}
		
		return usserrModel;
	}
	
	@Override
	public CollectionModel<UsserrModel> toCollectionModel(Iterable<? extends Usserr> entities) {
		CollectionModel<UsserrModel> collectionModel = super.toCollectionModel(entities);
		
		if (maifSecurity.canConsultUsserrsGroupsPermissions()) {
			collectionModel.add(maifLinks.linkToUsserrs());
		}
		
		return collectionModel;
	}
	
}
