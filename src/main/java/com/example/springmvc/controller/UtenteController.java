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

    @PutMapping(value = "/modifica_credenziali")
    public String modificaCredenziali(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, Model model) {
        //utente.setPassword(password)
        //utente.setUsername(username)
        //utente.setEmail(email)
        return "redirect:/utente/profilo";
    }

    @PostMapping(value = "/aggiungi_utente")
    public String aggiungiUtente(@ModelAttribute("nuovoUtente") Utente utente, Model model) {
        utenteService.salvaOAggiornaUtente(utente);
        model.addAttribute("nuovoUtente", new Utente());
        return "gestioneUtenti";
    }

    @PostMapping(value = "/cancella_utente_{utenteId}")
    public String cancellaUtente(@PathVariable("utenteId") String id, Model model) {
        utenteService.cancellaUtentePerId(Long.parseLong(id));
        return "redirect:/utente/gestione_utenti";
    }

    @PostMapping(value = "/home")
    public String login(@ModelAttribute("utenteLogin") Utente utente, Model model) {

        Utente utente2 = utenteService.cercaUtentePerCredenziali("admin@mail.com", "admin");
        System.out.println(utente2.getEmail() + utente2.getPassword());
        if (utente2.getId() != null) {
            //model.addAttribute("utenteLogin", utente);
            if (utente2.isAdmin()) {
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
    public String visualizzaProfilo(Model model) {
        //set utente e lista prenotazioni
        return "profilo";
    }

    @GetMapping(value = "/prenota_auto")
    public String vaiAPrenotaAuto(Model model){

        return "redirect:/prenotazione/prenota_auto";
    }

    @GetMapping(value = "/prenotazioni_da_approvare")
    public String vaiAPrenotazioniDaApprovare(Model model){
        return "redirect:/prenotazione/prenotazioni_da_approvare";
    }

    @GetMapping(value = "/prenotazioni_da_cancellare")
    public String vaiAPrenotazioniDaCancellare(Model model){
        return "redirect:/prenotazione/prenotazioni_da_cancellare";
    }

}
