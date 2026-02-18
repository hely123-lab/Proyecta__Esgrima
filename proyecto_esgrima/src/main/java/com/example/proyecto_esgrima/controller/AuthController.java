package com.example.proyecto_esgrima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecte_esgrima.model.UserLogin;
import com.example.proyecte_esgrima.model.dto.LoginRequest;
import com.example.proyecte_esgrima.model.dto.RegisterRequest;
import com.example.proyecto_esgrima.repository.UserLoginRepository;
import com.example.proyecto_esgrima.security.JwtUtil;


@RestController
@RequestMapping("/auth")
public class AuthController {
   @Autowired
   private AuthenticationManager authenticationManager;
   @Autowired
   private JwtUtil jwtUtil;
   @Autowired
   private UserLoginRepository userRepo;
   @Autowired
   private PasswordEncoder passwordEncoder;



   @PostMapping("/login")
   public ResponseEntity<String> login(@RequestBody LoginRequest request) {
	   Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       request.getUsername(),
                       request.getPassword()
               )
       );
       
	   UserDetails userDetails = (UserDetails) authentication.getPrincipal();
       String token = jwtUtil.generateToken(userDetails);
       return ResponseEntity.ok(token);
   }
   
   @PostMapping("/register")
   public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
       if (userRepo.findByUsername(request.getUsername()).isPresent()) {
           return ResponseEntity.badRequest()
                   .body("Aquest usuari ja existeix");
       }
       UserLogin user = new UserLogin();
       user.setUsername(request.getUsername());
       user.setPassword(passwordEncoder.encode(request.getPassword()));
       user.setRole(request.getRole());
       userRepo.save(user);
       return ResponseEntity.ok("Usuari registrat correctament");
   }
}

