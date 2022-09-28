package com.example.springmvc.service;

import com.example.springmvc.dao.PrenotazioneDao;
import com.example.springmvc.entities.Prenotazione;
import com.example.springmvc.entities.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service("prenotazioneService")
@Transactional
public class PrenotazioneServiceImpl implements PrenotazioneService
{

    @Autowired
    PrenotazioneDao prenotazioneDao;

    @Override
    public void salvaOAggiornaPrenotazione(Prenotazione prenotazione) {
        prenotazioneDao.salvaOAggiornaPrenotazione(prenotazione);
    }

    @Override
    public List<Prenotazione> getPrenotazioni() {
        return prenotazioneDao.getPrenotazioni();
    }

    @Override
    public Prenotazione getPrenotazione(Long id) {
        return prenotazioneDao.getPrenotazione(id);
    }

    @Override
    public List<Prenotazione> getPrenotazioniDaApprovare() {
        return prenotazioneDao.getPrenotazioniDaApprovare();
    }

    @Override
    public List<Prenotazione> getPrenotazioniUtente(Utente utente) {
        return prenotazioneDao.getPrenotazioniUtente(utente);
    }

    @Override
    public boolean prenotazioneInCorsoUtente(Utente utente) {
        return prenotazioneDao.prenotazioneInCorsoUtente(utente);
    }

    @Override
    public void cancellaPrenotazione(Prenotazione prenotazione) {
        prenotazioneDao.cancellaPrenotazione(prenotazione);
    }

    @Override
    public List<Prenotazione> getListaPrenotazioniNelPeriodo(LocalDate dataInizioPeriodo, LocalDate dataFinePeriodo) {
        return prenotazioneDao.getListaPrenotazioniNelPeriodo(dataInizioPeriodo, dataFinePeriodo);
    }
}
