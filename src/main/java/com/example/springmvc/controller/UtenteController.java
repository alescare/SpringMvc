package com.example.springmvc.controller;


import com.example.springmvc.entities.Prenotazione;
import com.example.springmvc.entities.Utente;
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
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/utente")
public class UtenteController {

    private final UtenteService utenteService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final PrenotazioneService prenotazioneService;

    public UtenteController(UtenteService utenteService, BCryptPasswordEncoder passwordEncoder, PrenotazioneService prenotazioneService) {
        this.utenteService = utenteService;
        this.passwordEncoder = passwordEncoder;
        this.prenotazioneService = prenotazioneService;
    }

    //MODELATTRIBUTE

    @ModelAttribute("utenteLogin")
    public Utente utenteLogin(Authentication authentication){

        return  utenteService.cercaUtentePerUsername(authentication.getName());
    }
    @ModelAttribute("listaPrenotazioni")
    public Set<Prenotazione> listaPrenotazioniUtente(Authentication authentication){

        return utenteLogin(authentication).getPrenotazioni();
    }

    @ModelAttribute("listaUtenti")
    public List<Utente> listaUtenti(){

        return utenteService.listaUtenti();
    }

    //GET

    @RequestMapping(value = "/home")
    public String home() {

        return "home";
    }


    @GetMapping(value = "/gestione_utenti")
    public String gestioneUtenti(Model model) {
        model.addAttribute("nuovoUtente", new Utente());
        model.addAttribute("listaUtenti", this.listaUtenti());
        return "gestioneUtenti";
    }

    @GetMapping(value = "/gestione_auto")
    public String gestioneAuto() {

        return "redirect:/auto/gestione_auto";
    }


    @GetMapping(value = "/profilo")
    public String visualizzaProfilo() {

        return "profilo";
    }

    @GetMapping(value = "/prenota_auto")
    public String vaiAPrenotaAuto(Authentication authentication, Model model) {
        Utente utente = this.utenteLogin(authentication);
        if (prenotazioneService.prenotazioneInCorsoUtente(utente)){
            model.addAttribute("prenotazioneInCorsoMsg", "Hai già una prenotazione in corso");
            return home();
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

    //POST

    @PostMapping(value = "/modifica_credenziali")
    public String modificaCredenziali(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, Authentication authentication) {

        Utente utente = this.utenteLogin(authentication);
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
        return this.visualizzaProfilo();
    }

    @PostMapping(value = "/aggiungi_utente")
    public String aggiungiUtente(@Valid @ModelAttribute("nuovoUtente") Utente nuovoUtente, BindingResult bindingResul, Model model) {

        if(bindingResul.hasErrors()){
            return "gestioneUtenti";
        }
        nuovoUtente.setPassword(passwordEncoder.encode(nuovoUtente.getPassword()));
        utenteService.salvaOAggiornaUtente(nuovoUtente);
        return this.gestioneUtenti(model);
    }

    @PostMapping(value = "/cancella_utente/{utenteId}")
    public String cancellaUtente(@PathVariable("utenteId") String id, Model model) {
        utenteService.cancellaUtentePerId(Long.parseLong(id));
        return this.gestioneUtenti(model);
    }


}
