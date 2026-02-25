package com.example.proyecte_esgrima.security;

import com.example.proyecte_esgrima.repository.UsuariRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Carrega l'usuari des de MongoDB per email per a l'autenticació de Spring Security.
 * Ara retorna el Usuari real (que implementa UserDetails), incloent els rols correctament.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuariRepository usuariRepository;

    public UserDetailsServiceImpl(UsuariRepository usuariRepository) {
        this.usuariRepository = usuariRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuariRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuari no trobat amb email: " + email));
    }
}
