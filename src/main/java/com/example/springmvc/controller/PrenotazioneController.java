package com.example.springmvc.controller;

import com.example.springmvc.dao.PrenotazioneDao;
import com.example.springmvc.entities.Prenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/prenotazione")
public class PrenotazioneController {

    @Autowired
    PrenotazioneDao prenotazioneDao;

    @GetMapping(value = "/approva")
    public String ApprovaPrenotazioni(Model model){

        model.addAttribute("prenotazioni", prenotazioneDao.getPrenotazioniDaApprovare());
        return "approvaPrenotazioni";
    }

    @GetMapping(value = "/a")
    public String approvaPrenotazioni(Model model){

        return "approvaPrenotazioni";
    }

    @GetMapping(value = "/s")
    public String cancellaPrenotazioni(Model model){

        return "cancellaPrenotazioni";
    }

    @PostMapping(value = "/d")
    public String approva(Model model){

        return "approvaPrenotazioni";
    }

    @PostMapping(value = "/g")
    public String cancella(Model model){

        return "cancellaPrenotazioni";
    }

    @PostMapping(value = "/n")
    public String prenota(Model model){

        return "profilo";
    }

}
