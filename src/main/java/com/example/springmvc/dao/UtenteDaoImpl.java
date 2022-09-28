package com.example.springmvc.dao;

import com.example.springmvc.entities.Auto;
import com.example.springmvc.entities.Utente;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class UtenteDaoImpl extends AbstractDao<Utente, Long> implements UtenteDao {


    @Override
    public void salvaOAggiornaUtente(Utente utente) {

        if (utente.getId() == null) {
            super.Inserisci(utente);
        } else {
            super.Aggiorna(utente);
        }
    }

    @Override
    public Utente cercaUtentePerCredenziali(String email, String password) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Utente> queryDefinition = cb.createQuery(Utente.class);

        Root<Utente> recordset = queryDefinition.from(Utente.class);

        Predicate whereClause = cb.and(
                cb.equal(recordset.<String>get("email"), email),
                cb.equal(recordset.<String>get("password"), password));

        queryDefinition.select(recordset).
                where(whereClause);

        Utente utente = entityManager.createQuery(queryDefinition).getSingleResult();

        entityManager.clear();

        return utente;
    }

    @Override
    public List<Utente> listaUtenti() {
        return super.SelTutti();
    }

    @Override
    public Utente cercaUtentePerId(Long id) {
        return super.SelById(id);
    }

    @Override
    public void cancellaUtente(Utente utente) {
        super.Elimina(utente);
    }

    @Override
    public void cancellaUtentePerId(Long id) {
        super.EliminaById(id);
    }
}
