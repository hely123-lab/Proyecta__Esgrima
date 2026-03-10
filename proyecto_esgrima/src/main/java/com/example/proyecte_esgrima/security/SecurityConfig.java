package com.example.proyecte_esgrima.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.proyecte_esgrima.model.enums.Role;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	   @Autowired
	   private JwtAuthenticationFilter jwtFilter;
	   
	   @Bean
	   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	       http
	           .csrf(csrf -> csrf.disable())
	           .authorizeHttpRequests(auth -> auth
	               .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
	               // Pistes: GET lliure, creació/modificació/esborrat només ADMIN
	                .requestMatchers(HttpMethod.GET, "/api/pistes/**").authenticated()
	                .requestMatchers(HttpMethod.POST, "/api/pistes/**").hasRole("ADMIN")
	                .requestMatchers(HttpMethod.PUT,  "/api/pistes/**").hasRole("ADMIN")
	                .requestMatchers(HttpMethod.DELETE,"/api/pistes/**").hasRole("ADMIN")
	                // Usuaris: llistar tots i canviar rols/eliminar, només ADMIN
	                .requestMatchers(HttpMethod.GET,   "/api/usuaris").hasRole("ADMIN")
	                .requestMatchers(HttpMethod.PUT,   "/api/usuaris/{id}/rol").hasRole("ADMIN")
	                .requestMatchers(HttpMethod.DELETE,"/api/usuaris/{id}").hasRole("ADMIN")
	                // La resta requereix autenticació
	               .anyRequest().authenticated()
	           )
	           .sessionManagement(session ->
	               session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	           )
	           .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	       return http.build();
	   }
	   @Bean
	   public PasswordEncoder passwordEncoder() {
	       return new BCryptPasswordEncoder();
	   }
	   
	   @Bean
	   public AuthenticationManager authenticationManager(
	           AuthenticationConfiguration config) throws Exception {
	       return config.getAuthenticationManager();
	   }


}
