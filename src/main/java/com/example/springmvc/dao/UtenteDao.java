package com.example.springmvc.dao;

import com.example.springmvc.entities.Utente;

import java.util.List;

public interface UtenteDao {

    public void salvaOAggiornaUtente(Utente utente);

    public Utente cercaUtentePerCredenziali(String email, String password);


    public List<Utente> listaUtenti();


    public Utente cercaUtentePerId(Long id);


    public void cancellaUtente(Utente utente);

    public void cancellaUtentePerId(Long id);

}
