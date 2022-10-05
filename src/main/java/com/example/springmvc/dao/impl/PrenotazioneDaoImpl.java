package com.example.springmvc.dao.impl;

import com.example.springmvc.dao.AbstractDao;
import com.example.springmvc.dao.PrenotazioneDao;
import com.example.springmvc.entities.Prenotazione;
import com.example.springmvc.entities.Utente;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PrenotazioneDaoImpl extends AbstractDao<Prenotazione, Long> implements PrenotazioneDao {

    public void salvaOAggiornaPrenotazione(Prenotazione prenotazione) {

        if (prenotazione.getId() == null) {
            super.Inserisci(prenotazione);
        } else {
            super.Aggiorna(prenotazione);
        }
    }

    public List<Prenotazione> getPrenotazioni() {
        return super.SelTutti();
    }

    public Prenotazione getPrenotazione(Long id) {
        return super.SelById(id);
    }

    public List<Prenotazione> getPrenotazioniDaApprovare() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Prenotazione> queryDefinition = cb.createQuery(Prenotazione.class);

        Root<Prenotazione> recordset = queryDefinition.from(Prenotazione.class);

        queryDefinition.select(recordset).
                where(cb.equal(recordset.get("approvata"), false));

        List<Prenotazione> prenotazioni = entityManager.createQuery(queryDefinition).getResultList();

        entityManager.clear();

        return prenotazioni;
    }

    public List<Prenotazione> getPrenotazioniUtente(Utente utente) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Prenotazione> queryDefinition = cb.createQuery(Prenotazione.class);

        Root<Prenotazione> recordset = queryDefinition.from(Prenotazione.class);

        queryDefinition.select(recordset).
                where(cb.equal(recordset.get("utente"), utente));

        List<Prenotazione> prenotazioni = entityManager.createQuery(queryDefinition).getResultList();

        entityManager.clear();

        return prenotazioni;
    }

    public boolean prenotazioneInCorsoUtente(Utente utente) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Prenotazione> queryDefinition = cb.createQuery(Prenotazione.class);

        Root<Prenotazione> recordset = queryDefinition.from(Prenotazione.class);

        Predicate whereClause = cb.and(
                cb.equal(recordset.get("utente"), utente),
                cb.greaterThanOrEqualTo(recordset.get("dataFine"), LocalDate.now()));

        queryDefinition.select(recordset).
                where(whereClause);

        List<Prenotazione> prenotazioni = entityManager.createQuery(queryDefinition).getResultList();

        entityManager.clear();

        return !(prenotazioni.isEmpty());
    }

    public void cancellaPrenotazione(Prenotazione prenotazione) {
        super.Elimina(prenotazione);
    }

    public List<Prenotazione> getListaPrenotazioniNelPeriodo(LocalDate dataInizioPeriodo, LocalDate dataFinePeriodo) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Prenotazione> queryDefinition = cb.createQuery(Prenotazione.class);

        Root<Prenotazione> recordset = queryDefinition.from(Prenotazione.class);

        Predicate whereClause = cb.or(
                cb.and(cb.lessThanOrEqualTo(recordset.get("dataInizio"), dataInizioPeriodo), cb.greaterThanOrEqualTo(recordset.get("dataFine"), dataFinePeriodo)),
                cb.between(recordset.get("dataInizio"),dataInizioPeriodo, dataFinePeriodo),
                cb.between(recordset.get("dataFine"),dataInizioPeriodo, dataFinePeriodo));

        queryDefinition.select(recordset).
                where(whereClause);

        List<Prenotazione> prenotazioni = entityManager.createQuery(queryDefinition).getResultList();

        entityManager.clear();

        return prenotazioni;
    }

}
