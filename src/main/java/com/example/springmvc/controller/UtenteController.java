package com.example.springmvc.controller;


import com.example.springmvc.entities.Utente;
import com.example.springmvc.service.AutoService;
import com.example.springmvc.service.PrenotazioneService;
import com.example.springmvc.service.UtenteService;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/utente")
public class UtenteController {

    private final UtenteService utenteService;

    private final AutoService autoService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final PrenotazioneService prenotazioneService;

    public UtenteController(UtenteService utenteService, AutoService autoService, BCryptPasswordEncoder passwordEncoder, PrenotazioneService prenotazioneService) {
        this.utenteService = utenteService;
        this.autoService = autoService;
        this.passwordEncoder = passwordEncoder;
        this.prenotazioneService = prenotazioneService;
    }

    @PostMapping(value = "/modifica_credenziali")
    public String modificaCredenziali(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, Authentication authentication) {

        Utente utente = utenteService.cercaUtentePerUsername(authentication.getName());
        boolean usernameModificato = false;
        if (StringUtils.isNotEmpty(password)){
            utente.setPassword(passwordEncoder.encode(password));
        }
        if (StringUtils.isNotEmpty(username) && !(utente.getUsername().equals(username))){
            utente.setUsername(username);
            usernameModificato = true;
        }
        if (StringUtils.isNotEmpty(email)){
            utente.setEmail(email);
        }
        utenteService.salvaOAggiornaUtente(utente);
        if(usernameModificato){
            return "redirect:/login/form_logout";
        }
        return "redirect:/utente/profilo";
    }

    @PostMapping(value = "/aggiungi_utente")
    public String aggiungiUtente(@Valid @ModelAttribute("nuovoUtente") Utente nuovoUtente, BindingResult bindingResul, Model model) {

        if(bindingResul.hasErrors()){
            model.addAttribute("listaUtenti", utenteService.listaUtenti());
            return "gestioneUtenti";
        }
        nuovoUtente.setPassword(passwordEncoder.encode(nuovoUtente.getPassword()));
        utenteService.salvaOAggiornaUtente(nuovoUtente);
        return "redirect:/utente/gestione_utenti";
    }


    @RequestMapping(value = "/home")
    public String home(Authentication authentication, Model model) {
        model.addAttribute("utenteLogin", utenteService.cercaUtentePerUsername(authentication.getName()));
        return "home";
    }

    @PostMapping(value = "/cancella_utente/{utenteId}")
    public String cancellaUtente(@PathVariable("utenteId") String id) {
        utenteService.cancellaUtentePerId(Long.parseLong(id));
        return "redirect:/utente/gestione_utenti";
    }

    @GetMapping(value = "/gestione_utenti")
    public String gestioneUtenti(Model model) {
        model.addAttribute("nuovoUtente", new Utente());
        model.addAttribute("listaUtenti", utenteService.listaUtenti());
        return "gestioneUtenti";
    }

    @GetMapping(value = "/gestione_auto")
    public String gestioneAuto(Model model) {
        model.addAttribute("listaAuto", autoService.getListaAuto());
        return "redirect:/auto/gestione_auto";
    }


    @GetMapping(value = "/profilo")
    public String visualizzaProfilo(Authentication authentication, Model model) {

        Utente utenteLogin = utenteService.cercaUtentePerUsername(authentication.getName());
        model.addAttribute("utenteLogin", utenteLogin);
        model.addAttribute("listaPrenotazioni", utenteLogin.getPrenotazioni());

        return "profilo";
    }

    @GetMapping(value = "/prenota_auto")
    public String vaiAPrenotaAuto(Authentication authentication, Model model) {
        Utente utente = utenteService.cercaUtentePerUsername(authentication.getName());
        if (prenotazioneService.prenotazioneInCorsoUtente(utente)){
            model.addAttribute("prenotazioneInCorsoMsg", "Hai già una prenotazione in corso");
            return home(authentication, model);
        }
        return "redirect:/prenotazione/prenota_auto";
    }

    @GetMapping(value = "/prenotazioni_da_approvare")
    public String vaiAPrenotazioniDaApprovare() {
        return "redirect:/prenotazione/prenotazioni_da_approvare";
    }

    @GetMapping(value = "/prenotazioni_da_cancellare")
    public String vaiAPrenotazioniDaCancellare() {
        return "redirect:/prenotazione/prenotazioni_da_cancellare";
    }

}
