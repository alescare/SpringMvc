package com.example.springmvc.service;

import com.example.springmvc.entities.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UtenteService utenteService;

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

        UserBuilder builder = null;

        builder = User.withUsername(utente.getUsername());
        builder.password(utente.getPassword());

        String tipoUtente = utente.isAdmin() ? "ROLE_ADMIN" : "ROLE_USER";

        builder.authorities(tipoUtente);

        return builder.build();
    }

}
