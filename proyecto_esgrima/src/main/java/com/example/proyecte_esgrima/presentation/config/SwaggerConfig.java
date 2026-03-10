package com.example.proyecte_esgrima.presentation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * 
 * Configuracion de Swagger con OpenAPI.
 *
 * Aqui esta la documentacion general de la API i el sistema de autenticacion
 * JWT para que Swagger muestre el boton "Autorize" i pertime provar ciertos
 * endpoints directamente desde el navegador.
 */
@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				// Información general que aparece en la cabecera de la página de Swagger
				.info(new Info().title("API del projecte aplicació esgrima").version("1.0")
						.description("Documentació de l'api feta pel projecte, de l'aplicació d'esgrima"))
				// Definimos el esquema de seguridad con el nombre "bearerAuth".
				.components(new Components().addSecuritySchemes("bearerAuth",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
								.description("Introdueix el token JWT obtingut al login")))
				// La aplicamos globalmente a todos los endpoints
				.addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
	}
}
