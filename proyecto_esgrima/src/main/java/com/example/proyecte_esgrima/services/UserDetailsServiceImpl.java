package com.example.proyecte_esgrima.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.proyecte_esgrima.model.Usuari;
import com.example.proyecte_esgrima.repository.UserLoginRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	 @Autowired
	   private UserLoginRepository repo;
	   @Override
	   public UserDetails loadUserByUsername(String username)
	           throws UsernameNotFoundException {
<<<<<<< HEAD
	       UserLogin user = repo.findByUsername(username)
	               .orElseThrow(() -> new UsernameNotFoundException("Usuari no trobat"));
=======
	       Usuari user = repo.findByUsername(username)
	               .orElseThrow(() ->
	                       new UsernameNotFoundException("Usuari no trobat"));
>>>>>>> branch 'main' of https://github.com/hely123-lab/Proyecta__Esgrima.git
	       return new org.springframework.security.core.userdetails.User(
	               user.getUsername(),
	               user.getPassword(),
	               new ArrayList<>()
	       );
	   }

}
