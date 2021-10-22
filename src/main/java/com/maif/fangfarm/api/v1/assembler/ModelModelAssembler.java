package com.maif.fangfarm.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.maif.fangfarm.api.v1.MaifLinks;
import com.maif.fangfarm.api.v1.controller.ModelController;
import com.maif.fangfarm.api.v1.model.ModelModel;
import com.maif.fangfarm.core.security.MaifSecurity;
import com.maif.fangfarm.domain.model.Model;

@Component
public class ModelModelAssembler extends RepresentationModelAssemblerSupport<Model, ModelModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MaifLinks maifLinks;
	
	@Autowired
	private MaifSecurity maifSecurity;
	
	public ModelModelAssembler() {
		super(ModelController.class, ModelModel.class);
	}
	
	@Override
	public ModelModel toModel(Model model) {
		ModelModel modelModel = createModelWithId(model.getId(), model);
		modelMapper.map(model, modelModel);
		
		if (maifSecurity.canConsultModels()) {
			modelModel.add(maifLinks.linkToModel("models"));
		}
		
		return modelModel;
	}
	
	@Override
	public CollectionModel<ModelModel> toCollectionModel(Iterable<? extends Model> entities) {
		CollectionModel<ModelModel> collectionModel = super.toCollectionModel(entities);
		
		if (maifSecurity.canConsultModels()) {
			collectionModel.add(maifLinks.linkToModels());
		}
		
		return collectionModel;
	}
	
}
