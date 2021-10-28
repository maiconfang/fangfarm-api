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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maif.fangfarm.api.v1.assembler.UsserrInputDisassembler;
import com.maif.fangfarm.api.v1.assembler.UsserrModelAssembler;
import com.maif.fangfarm.api.v1.model.UsserrModel;
import com.maif.fangfarm.api.v1.model.input.PasswordInput;
import com.maif.fangfarm.api.v1.model.input.UsserrWithPasswordInput;
import com.maif.fangfarm.api.v1.model.input.UsserrInput;
import com.maif.fangfarm.api.v1.openapi.controller.UsserrControllerOpenApi;
import com.maif.fangfarm.core.data.PageWrapper;
import com.maif.fangfarm.core.data.PageableTranslator;
import com.maif.fangfarm.core.security.CheckSecurity;
import com.maif.fangfarm.domain.filter.UsserrFilter;
import com.maif.fangfarm.domain.model.Usserr;
import com.maif.fangfarm.domain.repository.UsserrRepository;
import com.maif.fangfarm.domain.service.RegisterUsserrService;
import com.maif.fangfarm.infrastructure.repository.spec.UsserrSpecs;

@RestController
@RequestMapping(path = "/v1/usserrs", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsserrController implements UsserrControllerOpenApi {

	@Autowired
	private UsserrRepository usserrRepository;

	@Autowired
	private RegisterUsserrService registerUsserr;

	@Autowired
	private UsserrModelAssembler usserrModelAssembler;

	@Autowired
	private UsserrInputDisassembler usserrInputDisassembler;

	@Autowired
	private PagedResourcesAssembler<Usserr> pagedResourcesAssembler;

	@CheckSecurity.UsersGroupPermission.CanConsult
	@Override
	@GetMapping
	public PagedModel<UsserrModel> list(UsserrFilter filter, Pageable pageable) {

		Pageable pageableTranslate = translatePageable(pageable);
		Page<Usserr> usserrsPage = null;

		if (filter.getName() != null || filter.getEmail() != null) {
			usserrsPage = usserrRepository.findAll(UsserrSpecs.withNameOrEmailOrAnd(filter), pageableTranslate);
		} else
			usserrsPage = usserrRepository.findAll(pageable);

		usserrsPage = new PageWrapper<>(usserrsPage, pageable);

		return pagedResourcesAssembler.toModel(usserrsPage, usserrModelAssembler);
	}

	@CheckSecurity.UsersGroupPermission.CanConsult
	@Override
	@GetMapping("/{usserrId}")
	public UsserrModel find(@PathVariable Long usserrId) {
		Usserr usserr = registerUsserr.findOrFail(usserrId);

		return usserrModelAssembler.toModel(usserr);
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsserrModel add(@RequestBody @Valid UsserrWithPasswordInput usserrInput) {
		Usserr usserr = usserrInputDisassembler.toDomainObject(usserrInput);
		usserr = registerUsserr.save(usserr);

		return usserrModelAssembler.toModel(usserr);
	}

	@CheckSecurity.UsersGroupPermission.CanEditUser
	@Override
	@PutMapping("/{usserrId}")
	public UsserrModel update(@PathVariable Long usserrId, @RequestBody @Valid UsserrInput usserrInput) {
		Usserr usserrCorrent = registerUsserr.findOrFail(usserrId);
		usserrInputDisassembler.copyToDomainObject(usserrInput, usserrCorrent);
		usserrCorrent = registerUsserr.save(usserrCorrent);

		return usserrModelAssembler.toModel(usserrCorrent);
	}

	@CheckSecurity.UsersGroupPermission.CanEditPropertyPassword
	@Override
	@PutMapping("/{usserrId}/password")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterPassword(@PathVariable Long usserrId, @RequestBody @Valid PasswordInput password) {
		registerUsserr.editPassword(usserrId, password.getCurrentPassword(), password.getNewPassword());
	}

	private Pageable translatePageable(Pageable apiPageable) {
		var mapping = Map.of("id", "code", "login", "login", "email", "e-mail");

		return PageableTranslator.translate(apiPageable, mapping);
	}

}
