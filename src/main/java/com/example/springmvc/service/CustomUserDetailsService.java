package com.example.springmvc.service;

import com.example.springmvc.entities.Utente;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UtenteService utenteService;

    public CustomUserDetailsService(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        if (username == null) {
            throw new UsernameNotFoundException("Deve essere inserito lo username");
        }

        Utente utente = utenteService.cercaUtentePerUsername(username);

        if (utente == null) {
            throw new UsernameNotFoundException("Utente non Trovato!!");
        }

        UserBuilder builder;

        builder = User.withUsername(utente.getUsername());
        builder.password(utente.getPassword());

        String tipoUtente = utente.isAdmin() ? "ROLE_ADMIN" : "ROLE_USER";

        builder.authorities(tipoUtente);

        return builder.build();
    }

}
