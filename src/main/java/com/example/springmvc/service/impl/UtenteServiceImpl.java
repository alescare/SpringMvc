package com.example.springmvc.service.impl;

import com.example.springmvc.dao.UtenteDao;
import com.example.springmvc.entities.Utente;
import com.example.springmvc.service.UtenteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("utenteService")
@Transactional
public class UtenteServiceImpl implements UtenteService
{


    private final UtenteDao utenteDao;

    public UtenteServiceImpl(UtenteDao utenteDao) {
        this.utenteDao = utenteDao;
    }

    @Override
    public void salvaOAggiornaUtente(Utente utente) {
        utenteDao.salvaOAggiornaUtente(utente);
    }

    @Override
    public Utente cercaUtentePerCredenziali(String username, String password) {
        return utenteDao.cercaUtentePerCredenziali(username, password);
    }

    @Override
    public Utente cercaUtentePerUsername(String username) {
        return utenteDao.cercaUtentePerUsername(username);
    }

    @Override
    public List<Utente> listaUtenti() {
        return utenteDao.listaUtenti();
    }

    @Override
    public Utente cercaUtentePerId(Long id) {
        return utenteDao.cercaUtentePerId(id);
    }

    @Override
    public void cancellaUtente(Utente utente) {
        utenteDao.cancellaUtente(utente);
    }

    @Override
    public void cancellaUtentePerId(Long id) {
        utenteDao.cancellaUtentePerId(id);
    }
}
