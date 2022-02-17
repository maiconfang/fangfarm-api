package com.maif.fangfarm.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.maif.fangfarm.api.v1.MaifLinks;
import com.maif.fangfarm.api.v1.controller.AnimalController;
import com.maif.fangfarm.api.v1.model.AnimalModel;
import com.maif.fangfarm.core.security.MaifSecurity;
import com.maif.fangfarm.domain.model.Animal;

@Component
public class AnimalModelAssembler extends RepresentationModelAssemblerSupport<Animal, AnimalModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MaifLinks maifLinks;
	
	@Autowired
	private MaifSecurity maifSecurity;
	
	public AnimalModelAssembler() {
		super(AnimalController.class, AnimalModel.class);
	}
	
	@Override
	public AnimalModel toModel(Animal animal) {
		AnimalModel animalModel = createModelWithId(animal.getId(), animal);
		modelMapper.map(animal, animalModel);
		
		if (maifSecurity.canConsultAnimals()) {
			animalModel.add(maifLinks.linkToAnimals("animals"));
		}
		
		return animalModel;
	}
	
	@Override
	public CollectionModel<AnimalModel> toCollectionModel(Iterable<? extends Animal> entities) {
		CollectionModel<AnimalModel> collectionModel = super.toCollectionModel(entities);
		
		if (maifSecurity.canConsultAnimals()) {
			collectionModel.add(maifLinks.linkToAnimals());
		}
		
		return collectionModel;
	}
	
}
