package com.example.springmvc.service.impl;

import com.example.springmvc.dao.AutoDao;
import com.example.springmvc.entities.Auto;
import com.example.springmvc.service.AutoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service("autoService")
@Transactional
public class AutoServiceImpl implements AutoService {

    private final AutoDao autoDao;

    public AutoServiceImpl(AutoDao autoDao) {
        this.autoDao = autoDao;
    }

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
