package com.maif.fangfarm.core.springfox;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;
import com.maif.fangfarm.api.exceptionhandler.Problem;
import com.maif.fangfarm.api.v1.model.CityModel;
import com.maif.fangfarm.api.v1.model.StateModel;
import com.maif.fangfarm.api.v1.model.GroupppModel;
import com.maif.fangfarm.api.v1.model.PermissionModel;
import com.maif.fangfarm.api.v1.model.UsserrModel;
import com.maif.fangfarm.api.v1.openapi.model.CitiesModelOpenApi;
import com.maif.fangfarm.api.v1.openapi.model.StatesModelOpenApi;
import com.maif.fangfarm.api.v1.openapi.model.GroupsModelOpenApi;
import com.maif.fangfarm.api.v1.openapi.model.LinksModelOpenApi;
import com.maif.fangfarm.api.v1.openapi.model.PageableModelOpenApi;
import com.maif.fangfarm.api.v1.openapi.model.PermissoesModelOpenApi;
import com.maif.fangfarm.api.v1.openapi.model.UsserrsModelOpenApi;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDocketV1() {
		var typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("V1")
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.maif.fangfarm.api"))
					.paths(PathSelectors.ant("/v1/**"))
					.build()
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
				.globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
				.globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
				.globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
				.additionalModels(typeResolver.resolve(Problem.class))
				.ignoredParameterTypes(ServletWebRequest.class,
						URL.class, URI.class, URLStreamHandler.class, Resource.class,
						File.class, InputStream.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.directModelSubstitute(Links.class, LinksModelOpenApi.class)
				
				
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, CityModel.class),
						CitiesModelOpenApi.class))
				
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, StateModel.class),
						StatesModelOpenApi.class))
				
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, GroupppModel.class),
						GroupsModelOpenApi.class))
				
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, PermissionModel.class),
						PermissoesModelOpenApi.class))
				
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, UsserrModel.class),
						UsserrsModelOpenApi.class))
				
				.securitySchemes(Arrays.asList(securityScheme()))
				.securityContexts(Arrays.asList(securityContext()))
				
				.apiInfo(apiInfoV1())
				.tags(new Tag("Cities", "Manager the cities"),
						new Tag("Groups", "Manager the groups of users"),
						new Tag("States", "Manager the states"),
						new Tag("Users", "Manager the users"),
						new Tag("Permissions", "Manger the permissions"));
	}
	
	private SecurityScheme securityScheme() {
		return new OAuthBuilder()
				.name("MaifFarm")
				.grantTypes(grantTypes())
				.scopes(scopes())
				.build();
	}
	
	private SecurityContext securityContext() {
		var securityReference = SecurityReference.builder()
				.reference("MaifFarm")
				.scopes(scopes().toArray(new AuthorizationScope[0]))
				.build();
		
		return SecurityContext.builder()
				.securityReferences(Arrays.asList(securityReference))
				.forPaths(PathSelectors.any())
				.build();
	}
	
	private List<GrantType> grantTypes() {
		return Arrays.asList(new ResourceOwnerPasswordCredentialsGrant("/oauth/token"));
	}
	
	private List<AuthorizationScope> scopes() {
		return Arrays.asList(new AuthorizationScope("READ", "Access of reading"),
				new AuthorizationScope("WRITE", "Access of writing"));
	}
	
	private List<ResponseMessage> globalGetResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Internal server error")
					.responseModel(new ModelRef("Problem"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Resource has no representation that could be accepted by the consumer")
					.build()
			);
	}
	
	private List<ResponseMessage> globalPostPutResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.BAD_REQUEST.value())
					.message("Invalid request (customer error)")
					.responseModel(new ModelRef("Problem"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Internal server error")
					.responseModel(new ModelRef("Problem"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Resource has no representation that could be accepted by the consumer")
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
					.message("Request refused because the body is in an unsupported format")
					.responseModel(new ModelRef("Problem"))
					.build()
			);
	}
	
	private List<ResponseMessage> globalDeleteResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.BAD_REQUEST.value())
					.message("Invalid request (customer error)")
					.responseModel(new ModelRef("Problem"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno no servidor")
					.responseModel(new ModelRef("Problema"))
					.build()
			);
	}
	
	private ApiInfo apiInfoV1() {
		return new ApiInfoBuilder()
				.title("MaifFarm API")
				.description("API that manager the farm")
				.version("1")
				.contact(new Contact("Maif", "https://www.gomaif.com", "contact@gomaif.com"))
				.build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
}
