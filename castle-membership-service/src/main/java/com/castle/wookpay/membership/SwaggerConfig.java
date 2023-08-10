package com.castle.wookpay.membership;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;


@OpenAPIDefinition(
		info = @Info(title = "Money Service API",
				description = "Money Service api명세",
				version = "v1"))
@RequiredArgsConstructor
@Configuration
@SecurityScheme(
		type = SecuritySchemeType.HTTP,
		scheme = "bearer",
		bearerFormat = "JWT",
		name = "Bearer Authentication"
)
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi chatOpenApi() {
		String[] paths = {"/membership/**"};

		return GroupedOpenApi.builder()
				.group("Money Service API")
				.pathsToMatch(paths)
				.build();
	}
}