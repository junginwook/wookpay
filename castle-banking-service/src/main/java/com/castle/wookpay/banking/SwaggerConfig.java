package com.castle.wookpay.banking;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
		info = @Info(title = "Banking Service API",
				description = "Banking Service api명세",
				version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi chatOpenApi() {
		String[] paths = {"/banking/**"};

		return GroupedOpenApi.builder()
				.group("Banking Service API")
				.pathsToMatch(paths)
				.addOpenApiCustomizer(buildSecurityOpenApi("local"))
				.build();
	}

	private OpenApiCustomizer buildSecurityOpenApi(String active) {
		SecurityScheme securityScheme = new SecurityScheme()
				.name("Authorization")
				.type(SecurityScheme.Type.HTTP)
				.in(SecurityScheme.In.HEADER)
				.bearerFormat("JWT")
				.scheme("bearer");

		return OpenApi -> OpenApi
				.addSecurityItem(new SecurityRequirement().addList("jwt token"))
				.getComponents().addSecuritySchemes("jwt token", securityScheme);
	}
}