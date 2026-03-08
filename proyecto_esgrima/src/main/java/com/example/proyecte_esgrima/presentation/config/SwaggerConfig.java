package com.example.proyecte_esgrima.presentation.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig {
	
	 @Bean
	   public OpenAPI customOpenAPI() {
	       return new OpenAPI()
	               .info(new Info()
	                       .title("API del projecte aplicació esgrima ")
	                       .version("1.0")
	                       .description("Documentació de l'api feta pel projecte, de l'aplicació d'esgrima"));
	   }


}
