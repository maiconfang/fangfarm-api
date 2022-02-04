package com.maif.fangfarm.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.maif.fangfarm.api.v1.MaifLinks;
import com.maif.fangfarm.api.v1.controller.BrandController;
import com.maif.fangfarm.api.v1.model.BrandModel;
import com.maif.fangfarm.core.security.MaifSecurity;
import com.maif.fangfarm.domain.model.Brand;

@Component
public class BrandModelAssembler 
		extends RepresentationModelAssemblerSupport<Brand, BrandModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MaifLinks maifLinks;
	
	@Autowired
	private MaifSecurity maifSecurity;
	
	public BrandModelAssembler() {
		super(BrandController.class, BrandModel.class);
	}
	
	@Override
	public BrandModel toModel(Brand brand) {
		BrandModel brandModel = createModelWithId(brand.getId(), brand);
		
		modelMapper.map(brand, brandModel);
		
		if (maifSecurity.canConsultCities()) {
			brandModel.add(maifLinks.linkToCities("brands"));
		}
		
		if (maifSecurity.canConsultBrands()) {
			brandModel.getModel().add(maifLinks.linkToBrand(brandModel.getModel().getId()));
		}
		
		return brandModel;
	}
	
	@Override
	public CollectionModel<BrandModel> toCollectionModel(Iterable<? extends Brand> entities) {
		CollectionModel<BrandModel> collectionModel = super.toCollectionModel(entities);
		
		if (maifSecurity.canConsultCities()) {
			collectionModel.add(maifLinks.linkToCities());
		}
		
		return collectionModel;
	}
	
}
