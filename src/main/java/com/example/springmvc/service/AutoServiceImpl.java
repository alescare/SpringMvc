package com.example.springmvc.service;

import com.example.springmvc.dao.AutoDao;
import com.example.springmvc.entities.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AutoServiceImpl implements AutoService {

    @Autowired
    AutoDao autoDao;

    @Override
    public void salvaOAggiornaAuto(Auto auto) {
        autoDao.salvaOAggiornaAuto(auto);
    }

    @Override
    public void cancellaAuto(Auto auto) {
        autoDao.cancellaAuto(auto);
    }

    @Override
    public List<Auto> getListaAuto() {
        return autoDao.getListaAuto();
    }

    @Override
    public Auto getAutoPerId(Long id) {
        return autoDao.getAutoPerId(id);
    }

    @Override
    public List<Auto> listaAutoDisponibiliNelPeriodo(LocalDate dataInizio, LocalDate dataFine) {
        return autoDao.listaAutoDisponibiliNelPeriodo(dataInizio, dataFine);
    }
}
