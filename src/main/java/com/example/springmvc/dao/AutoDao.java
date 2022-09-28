package com.example.springmvc.dao;

import com.example.springmvc.entities.Auto;

import java.time.LocalDate;
import java.util.List;

public interface AutoDao {

    public void salvaOAggiornaAuto(Auto auto);

    public void cancellaAuto(Auto auto);

    public List<Auto> getListaAuto();

    public Auto getAutoPerId(Long id);

    public List<Auto> listaAutoDisponibiliNelPeriodo(LocalDate dataInizio, LocalDate dataFine);
}
