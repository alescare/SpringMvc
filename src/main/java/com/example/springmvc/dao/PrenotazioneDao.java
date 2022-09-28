package com.example.springmvc.dao;

import com.example.springmvc.entities.Prenotazione;
import com.example.springmvc.entities.Utente;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneDao {

    public void salvaOAggiornaPrenotazione(Prenotazione prenotazione);

    public List<Prenotazione> getPrenotazioni();

    public Prenotazione getPrenotazione(Long id);

    public List<Prenotazione> getPrenotazioniDaApprovare();

    public List<Prenotazione> getPrenotazioniUtente(Utente utente);

    public boolean prenotazioneInCorsoUtente(Utente utente);

    public void cancellaPrenotazione(Prenotazione prenotazione);


    public List<Prenotazione> getListaPrenotazioniNelPeriodo(LocalDate dataInizioPeriodo, LocalDate dataFinePeriodo);
}
