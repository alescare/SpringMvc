package com.example.springmvc.dao;

import com.example.springmvc.entities.Auto;
import com.example.springmvc.entities.Prenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class AutoDaoImpl extends AbstractDao<Auto, Long> implements AutoDao{

    @Autowired
    PrenotazioneDao prenotazioneDao;

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
        System.out.println(listaAutoDisponibili.size());

        List<Prenotazione> listaPrenotazioni = prenotazioneDao.getListaPrenotazioniNelPeriodo(dataInizio, dataFine);
        System.out.println(listaPrenotazioni);

        for (Prenotazione p : listaPrenotazioni) {
            listaAutoDisponibili.remove(p.getAuto());
        }
        System.out.println(listaAutoDisponibili.size());
        return listaAutoDisponibili;
    }
}
