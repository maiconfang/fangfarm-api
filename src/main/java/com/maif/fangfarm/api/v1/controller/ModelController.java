package com.maif.fangfarm.api.v1.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
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

import com.maif.fangfarm.api.v1.assembler.ModelInputDisassembler;
import com.maif.fangfarm.api.v1.assembler.ModelModelAssembler;
import com.maif.fangfarm.api.v1.model.ModelModel;
import com.maif.fangfarm.api.v1.model.input.ModelInput;
import com.maif.fangfarm.api.v1.openapi.controller.ModelControllerOpenApi;
import com.maif.fangfarm.core.data.PageWrapper;
import com.maif.fangfarm.core.data.PageableTranslator;
import com.maif.fangfarm.core.security.CheckSecurity;
import com.maif.fangfarm.domain.filter.ModelFilter;
import com.maif.fangfarm.domain.model.Model;
import com.maif.fangfarm.domain.repository.ModelRepository;
import com.maif.fangfarm.domain.service.RegisterModelService;
import com.maif.fangfarm.infrastructure.repository.spec.ModelSpecs;

@RestController
@RequestMapping(path = "/v1/models", produces = MediaType.APPLICATION_JSON_VALUE)
public class ModelController implements ModelControllerOpenApi {

	@Autowired
	private ModelRepository modelRepository;

	@Autowired
	private RegisterModelService registerModel;

	@Autowired
	private ModelModelAssembler modelModelAssembler;

	@Autowired
	private ModelInputDisassembler modelInputDisassembler;

	@Autowired
	private PagedResourcesAssembler<Model> pagedResourcesAssembler;

	@CheckSecurity.Models.CanConsult
	@Override
	@GetMapping
	public PagedModel<ModelModel> list(ModelFilter filter, Pageable pageable) {

		Pageable pageableTranslate = translatePageable(pageable);
		Page<Model> modelsPage = null;

		if (filter.getName() != null) {
			modelsPage = modelRepository.findAll(ModelSpecs.withName(filter), pageableTranslate);
		} else
			modelsPage = modelRepository.findAllByOrderByNameAsc(pageable);

		modelsPage = new PageWrapper<>(modelsPage, pageable);

		return pagedResourcesAssembler.toModel(modelsPage, modelModelAssembler);
	}

	@CheckSecurity.Models.CanConsult
	@Override
	@GetMapping("/{modelId}")
	public ModelModel find(@PathVariable Long modelId) {
		Model model = registerModel.findOrFail(modelId);

		return modelModelAssembler.toModel(model);
	}

	@CheckSecurity.Models.CanEdit
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ModelModel add(@RequestBody @Valid ModelInput modelInput) {
		Model model = modelInputDisassembler.toDomainObject(modelInput);

		model = registerModel.save(model);

		return modelModelAssembler.toModel(model);
	}

	@CheckSecurity.Models.CanEdit
	@Override
	@PutMapping("/{modelId}")
	public ModelModel update(@PathVariable Long modelId, @RequestBody @Valid ModelInput modelInput) {
		Model modelCorrent = registerModel.findOrFail(modelId);

		modelInputDisassembler.copyToDomainObject(modelInput, modelCorrent);

		modelCorrent = registerModel.save(modelCorrent);

		return modelModelAssembler.toModel(modelCorrent);
	}

	@CheckSecurity.Models.CanEdit
	@Override
	@DeleteMapping("/{modelId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long modelId) {
		registerModel.delete(modelId);
	}

	private Pageable translatePageable(Pageable apiPageable) {
		var mapping = Map.of("id", "code", "name", "name");

		return PageableTranslator.translate(apiPageable, mapping);
	}

}
