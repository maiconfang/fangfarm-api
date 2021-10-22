package com.maif.fangfarm.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.maif.fangfarm.api.v1.MaifLinks;
import com.maif.fangfarm.api.v1.controller.StateController;
import com.maif.fangfarm.api.v1.model.StateModel;
import com.maif.fangfarm.core.security.MaifSecurity;
import com.maif.fangfarm.domain.model.State;

@Component
public class StateModelAssembler 
		extends RepresentationModelAssemblerSupport<State, StateModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MaifLinks maifLinks;
	
	@Autowired
	private MaifSecurity maifSecurity;
	
	public StateModelAssembler() {
		super(StateController.class, StateModel.class);
	}
	
	@Override
	public StateModel toModel(State state) {
		StateModel stateModel = createModelWithId(state.getId(), state);
		modelMapper.map(state, stateModel);
		
		if (maifSecurity.canConsultStates()) {
			stateModel.add(maifLinks.linkToState("states"));
		}
		
		return stateModel;
	}
	
	@Override
	public CollectionModel<StateModel> toCollectionModel(Iterable<? extends State> entities) {
		CollectionModel<StateModel> collectionModel = super.toCollectionModel(entities);
		
		if (maifSecurity.canConsultStates()) {
			collectionModel.add(maifLinks.linkToStates());
		}
		
		return collectionModel;
	}
	
}
