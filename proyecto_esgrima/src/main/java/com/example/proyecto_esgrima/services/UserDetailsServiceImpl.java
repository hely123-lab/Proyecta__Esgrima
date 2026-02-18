package com.example.proyecto_esgrima.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.proyecte_esgrima.model.UserLogin;
import com.example.proyecto_esgrima.repository.UserLoginRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	 @Autowired
	   private UserLoginRepository repo;
	   @Override
	   public UserDetails loadUserByUsername(String username)
	           throws UsernameNotFoundException {
	       UserLogin user = repo.findByUsername(username)
	               .orElseThrow(() ->
	                       new UsernameNotFoundException("Usuari no trobat"));
	       return new org.springframework.security.core.userdetails.User(
	               user.getUsername(),
	               user.getPassword(),
	               new ArrayList<>()
	       );
	   }

}
