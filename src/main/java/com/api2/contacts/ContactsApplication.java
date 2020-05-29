package com.api2.contacts;

import com.api2.contacts.controller.PersonController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
// enable swagger
@EnableSwagger2
public class ContactsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactsApplication.class, args);
	}

	// code snippet taken from Swagger documentation in order to customize the API documentation:
	@Bean
	public Docket swaggerConfiguration() {
		// return a prepared docket instance
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/contacts/*"))
				//.apis(RequestHandlerSelectors.basePackage("http://localhost:8080/"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo("Contacts API", "Sample API to insert contacts", "version 1.0", "Free to use",
				new springfox.documentation.service.Contact("Konstantinos Dimou", "http://www.dinosdimou.site",
						"dinos.dimou@gmail.com"), "API License", "http://dinosdimou.site",
				Collections.emptyList());
	}
}
