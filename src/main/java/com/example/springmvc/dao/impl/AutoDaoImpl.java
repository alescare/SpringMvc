package com.example.springmvc.dao.impl;

import com.example.springmvc.dao.AbstractDao;
import com.example.springmvc.dao.AutoDao;
import com.example.springmvc.dao.PrenotazioneDao;
import com.example.springmvc.entities.Auto;
import com.example.springmvc.entities.Prenotazione;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class AutoDaoImpl extends AbstractDao<Auto, Long> implements AutoDao {


    private final PrenotazioneDao prenotazioneDao;

    public AutoDaoImpl(PrenotazioneDao prenotazioneDao) {
        this.prenotazioneDao = prenotazioneDao;
    }

    @Override
    public void salvaOAggiornaAuto(Auto auto) {

        if(auto.getId() == null) {
            super.Inserisci(auto);
        } else {
            super.Aggiorna(auto);
        }

    }

    @Override
    public void cancellaAuto(Auto auto) {
        super.Elimina(auto);
    }

    @Override
    public List<Auto> getListaAuto() {
        return super.SelTutti();
    }

    @Override
    public Auto getAutoPerId(Long id) {
        return super.SelById(id);
    }

    @Override
    public List<Auto> listaAutoDisponibiliNelPeriodo(LocalDate dataInizio, LocalDate dataFine) {

        List<Auto> listaAutoDisponibili = getListaAuto();

        List<Prenotazione> listaPrenotazioni = prenotazioneDao.getListaPrenotazioniNelPeriodo(dataInizio, dataFine);

        for (Prenotazione p : listaPrenotazioni) {
            listaAutoDisponibili.remove(p.getAuto());
        }
         return listaAutoDisponibili;
    }
}
