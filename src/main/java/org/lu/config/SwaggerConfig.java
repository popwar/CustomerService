package org.lu.config;

import java.util.Arrays;

import org.lu.web.BaseController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(BaseController.class
						.getPackage().getName()))
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/")
				.apiInfo(apiInfo())
				.globalOperationParameters(
						Arrays.asList(new ParameterBuilder()
								.name("Authorization").parameterType("header")
								.required(true).description("Oauth2 token")
								.modelRef(new ModelRef("string")).build()));

	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("Notification service REST API",
				"Notification service REST API.", "version 1",
				"Terms of service", new Contact("", "", ""), "License of API",
				"API license URL");
		return apiInfo;
	}

}
