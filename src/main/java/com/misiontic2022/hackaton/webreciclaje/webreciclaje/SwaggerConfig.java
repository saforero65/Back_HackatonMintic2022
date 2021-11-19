package com.misiontic2022.hackaton.webreciclaje.webreciclaje;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket apiDocket() {
		//RECORDAR CAMBIAR EL PAQUETE BASE AL PAQUETE BO DE CADA UNO
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.misiontic2022.hackaton.webreciclaje.webreciclaje.controller"))
				.paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Api Rest Spring Boot-Angular-MongoDB", 
	      "This is the  access point from the frond end of the group 31, we are working for the point 12 of the hackaton", 
	      "API TOS", 
	      "Terms of service", 
	      new Contact("Santiago Moreno y Juan Salas", "https://github.com/saforero65/Back_HackatonMintic2022", "jdsalasc@unal.edu.co"), 
	      "License of API", "API license URL", Collections.emptyList());
	}

 
}
