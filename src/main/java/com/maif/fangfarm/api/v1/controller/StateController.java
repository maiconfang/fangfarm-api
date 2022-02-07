package com.maif.fangfarm.api.v1.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maif.fangfarm.api.v1.assembler.StateInputDisassembler;
import com.maif.fangfarm.api.v1.assembler.StateModelAssembler;
import com.maif.fangfarm.api.v1.model.StateModel;
import com.maif.fangfarm.api.v1.model.input.StateInput;
import com.maif.fangfarm.api.v1.openapi.controller.StateControllerOpenApi;
import com.maif.fangfarm.core.data.PageWrapper;
import com.maif.fangfarm.core.data.PageableTranslator;
import com.maif.fangfarm.core.security.CheckSecurity;
import com.maif.fangfarm.domain.filter.StateFilter;
import com.maif.fangfarm.domain.model.State;
import com.maif.fangfarm.domain.repository.StateRepository;
import com.maif.fangfarm.domain.service.RegisterStateService;
import com.maif.fangfarm.infrastructure.repository.spec.StateSpecs;

@RestController
@RequestMapping(path = "/v1/states", produces = MediaType.APPLICATION_JSON_VALUE)
public class StateController implements StateControllerOpenApi {

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private RegisterStateService registerState;
	
	@Autowired
	private StateModelAssembler stateModelAssembler;
	
	@Autowired
	private StateInputDisassembler stateInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<State> pagedResourcesAssembler;
	
	
	@CheckSecurity.States.CanConsult
	@Override
	@GetMapping
	public PagedModel<StateModel> list(StateFilter filter, Pageable pageable) {
		
		Pageable pageableTranslate = translatePageable(pageable);
		Page<State> statesPage = null;
		
		if(filter.getName()!=null || filter.getFs()!=null ) {
			statesPage = stateRepository.findAll(StateSpecs.withFilter(filter), pageableTranslate);
		}
		else
			statesPage = stateRepository.findAll(pageable);
		
		statesPage = new PageWrapper<>(statesPage, pageable);
		
		return pagedResourcesAssembler.toModel(statesPage, stateModelAssembler);
	}
	
	@CheckSecurity.States.CanConsult
	@GetMapping("/noPagination")
	public CollectionModel<StateModel> listNoPagination(StateFilter filter) {
		
		List<State> allStates;
		
		if(filter.getName()!=null || filter.getFs()!=null ) {
			allStates = stateRepository.findAll(StateSpecs.withFilter(filter));
		}
		else
			allStates = stateRepository.findAll();
		
		return stateModelAssembler.toCollectionModel(allStates);
	}
	
	@CheckSecurity.States.CanConsult
	@Override
	@GetMapping("/{stateId}")
	public StateModel find(@PathVariable Long stateId) {
		State state = registerState.findOrFail(stateId);
		
		return stateModelAssembler.toModel(state);
	}
	
	@CheckSecurity.States.CanEdit
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public StateModel add(@RequestBody @Valid StateInput stateInput) {
		State state = stateInputDisassembler.toDomainObject(stateInput);
		
		state = registerState.save(state);
		
		return stateModelAssembler.toModel(state);
	}
	
	@CheckSecurity.States.CanEdit
	@Override
	@PutMapping("/{stateId}")
	public StateModel update(@PathVariable Long stateId, @RequestBody @Valid StateInput stateInput) {
		State currentState = registerState.findOrFail(stateId);
		
		stateInputDisassembler.copyToDomainObject(stateInput, currentState);
		
		currentState = registerState.save(currentState);
		
		return stateModelAssembler.toModel(currentState);
	}
	
	@CheckSecurity.States.CanEdit
	@Override
	@DeleteMapping("/{stateId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long stateId) {
		
		State state = registerState.findOrFail(stateId);
		String nameState = state.getName();
		
		registerState.delete(stateId, nameState);	
	}
	
	private Pageable translatePageable(Pageable apiPageable) {
		var mapping = Map.of(
				"id", "code",
				"name", "name"
			);
		
		return PageableTranslator.translate(apiPageable, mapping);
	}
	
}
