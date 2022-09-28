package com.example.springmvc.controller;

import com.example.springmvc.entities.Utente;
import com.example.springmvc.service.AutoService;
import com.example.springmvc.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private AutoService autoService;

    @PostMapping(value = "/home")
    public String login(@ModelAttribute("utenteLogin") Utente utente, Model model) {

        utente = utenteService.cercaUtentePerCredenziali(utente.getEmail(), utente.getPassword());
        if (utente.getId() != null) {
            model.addAttribute("utenteLogin", utente);
            if (utente.isAdmin()) {
                return "superUserHome";
            }
            return "customerHome";
        }
        return "index";

    }

    @GetMapping(value = "/home")
    public String home(@ModelAttribute("utenteLogin") Utente utente, Model model) {
        if (utente.isAdmin()) {
            return "redirect:/utente/superUserHome";
        }
        return "redirect:/utente/customerHome";
    }

    @GetMapping(value = "/gestione_utenti")
    public String gestisciUtenti(Model model) {

        model.addAttribute("listaUtenti", utenteService.listaUtenti());
        return "gestioneUtenti";
    }

    @GetMapping(value = "/gestione_auto")
    public String gestioneAuto(Model model) {
        model.addAttribute("listaAuto", autoService.getListaAuto());
        return "redirect:/auto/gestione_auto";
    }

    @GetMapping(value = "/profilo")
    public String visualizzaProfiloGet(Model model) {
        return "profilo";
    }

    @PostMapping(value = "/profilo")
    public String modificaCredenziali(@ModelAttribute("utenteLogin") Utente utente, Model model) {
        utenteService.salvaOAggiornaUtente(utente);
        return "profilo";
    }


    @GetMapping(value = "/")
    public String esci(Model model) {
        return "redirect:/";
    }



    @PostMapping(value = "/gestione_utenti")
    public String aggiungi(Model model) {

        return "gestioneUtenti";
    }

    @PostMapping(value = "/homel")
    public String entra(Model model) {

        return "customerHome";
    }

    @PostMapping(value = "/gestione_utenti2")
    public String cancella(Model model) {

        return "gestioneUtenti";
    }

}
