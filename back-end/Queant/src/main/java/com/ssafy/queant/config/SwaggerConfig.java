package com.ssafy.queant.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.expression.Sets;
import springfox.documentation.PathProvider;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Server;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.DefaultPathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.HashSet;

import static springfox.documentation.spring.web.paths.Paths.removeAdjacentForwardSlashes;

//http://localhost/swagger-ui.html
//http://localhost/swagger-ui/index.html

@Configuration
public class SwaggerConfig{


	@Bean
	public Docket api() {
		Server localServer = new Server("local", "http://localhost:8000", "for testing", Collections.emptyList(), Collections.emptyList());
		Server testServer = new Server("test", "https://i7a201.p.ssafy.io", "for testing", Collections.emptyList(), Collections.emptyList());
		return new Docket(DocumentationType.OAS_30)
				.servers(testServer, localServer)
				.groupName("Queant")
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.queant.controller"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Queant API")
				.description("<h2>SSAFY Queant API Reference for Developers</h2>" +
						"<img src=\"/api/img/Queant.png\" style=\"width:10px; height:10px;\">"
				)
				.termsOfServiceUrl("https://edu.ssafy.com")
				.license("SSAFY License")
				.licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp").version("1.0").build();
	}

	private class MyPathProvider extends DefaultPathProvider {
		@Override
		public String getOperationPath(String operationPath) {

			operationPath = operationPath + "/api";

			return removeAdjacentForwardSlashes(UriComponentsBuilder.newInstance().replacePath(operationPath)
					.build().toString());
		}
	}

}
