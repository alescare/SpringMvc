package com.example.springmvc.controller;

import com.example.springmvc.entities.Utente;
import com.example.springmvc.service.AutoService;
import com.example.springmvc.service.PrenotazioneService;
import com.example.springmvc.service.UtenteService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
        if (!("".equals(password) || password == null)) utente.setPassword(passwordEncoder.encode(password));
        if (!("".equals(username))) utente.setUsername(username);
        if (!("".equals(email))) utente.setEmail(email);
        utenteService.salvaOAggiornaUtente(utente);
        return "redirect:/login/form_logout";
    }

    @PostMapping(value = "/aggiungi_utente")
    public String aggiungiUtente(@RequestParam("dataNascita") String dataNascita,
                                 @RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("nome") String nome,
                                 @RequestParam("cognome") String cognome,
                                 @RequestParam("email") String email) {

        Utente nuovoUtente = new Utente(nome, cognome, LocalDate.parse(dataNascita),
                username, passwordEncoder.encode(password), email);
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
    public String vaiAPrenotaAuto(Authentication authentication) {
        Utente utente = utenteService.cercaUtentePerUsername(authentication.getName());
        if (prenotazioneService.prenotazioneInCorsoUtente(utente)) return "redirect:/utente/home";
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
