package com.example.springmvc.controller;

import com.example.springmvc.entities.Auto;
import com.example.springmvc.entities.Prenotazione;
import com.example.springmvc.service.AutoService;
import com.example.springmvc.service.PrenotazioneService;
import com.example.springmvc.service.UtenteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/prenotazione")
public class PrenotazioneController {


    private final PrenotazioneService prenotazioneService;

    private final AutoService autoService;

    private final UtenteService utenteService;

    public PrenotazioneController(PrenotazioneService prenotazioneService, AutoService autoService, UtenteService utenteService) {
        this.prenotazioneService = prenotazioneService;
        this.autoService = autoService;
        this.utenteService = utenteService;
    }

    //MODELATTRIBUTE
    @ModelAttribute("listaPrenotazioniDaApprovare")
    public List<Prenotazione> getPrenotazioniDaApprovare(){
        return prenotazioneService.getPrenotazioniDaApprovare();
    }
    @ModelAttribute("listaPrenotazioni")
    public List<Prenotazione> getPrenotazioni(){

        return prenotazioneService.getPrenotazioni();
    }


    //GET

    @GetMapping(value = "/prenota_auto")
    public String vaiAPrenotaAuto(Model model) {
        model.addAttribute("dataInizioPeriodo", null);
        model.addAttribute("dataFinePeriodo", null);
        return "prenotaAuto";
    }

    @GetMapping(value = "/prenotazioni_da_approvare")
    public String prenotazioniDaApprovare(Model model) {

        model.addAttribute("listaPrenotazioniDaApprovare", this.getPrenotazioniDaApprovare());
        return "approvaPrenotazioni";
    }

    @GetMapping(value = "/prenotazioni_da_cancellare")
    public String prenotazioniDaCancellare(Model model) {

        model.addAttribute("listaPrenotazioni", this.getPrenotazioni());
        return "cancellaPrenotazioni";
    }

    //POST

    @PostMapping(value = "/auto_disponibili")
    public String cercaAutoDisponibili(@RequestParam("dataInizioPeriodo") String dataInizioPeriodoForm, @RequestParam("dataFinePeriodo") String dataFinePeriodoForm, Model model) {

        LocalDate dataInizioPeriodo = LocalDate.parse(dataInizioPeriodoForm);
        LocalDate dataFinePeriodo = LocalDate.parse(dataFinePeriodoForm);
        List<Auto> listaAutoDisponibili = autoService.listaAutoDisponibiliNelPeriodo(dataInizioPeriodo, dataFinePeriodo);
        model.addAttribute("dataInizioPeriodo", dataInizioPeriodo);
        model.addAttribute("dataFinePeriodo", dataFinePeriodo);
        model.addAttribute("listaAutoDisponibili", listaAutoDisponibili);

        return "prenotaAuto";
    }

    @PostMapping(value = "/approva_prenotazione/{prenotazioneId}")
    public String approvaPrenotazione(@PathVariable("prenotazioneId") String prenotazioneId, Model model) {
        Prenotazione prenotazione = prenotazioneService.getPrenotazione(Long.parseLong(prenotazioneId));
        prenotazione.setApprovata(true);
        prenotazioneService.salvaOAggiornaPrenotazione(prenotazione);
        return this.prenotazioniDaApprovare(model);
    }

    @PostMapping(value = "/prenotazioni_da_cancellare/{prenotazioneId}")
    public String cancellaPrenotazione(@PathVariable("prenotazioneId") String prenotazioneId, Model model) {
        Prenotazione prenotazione = prenotazioneService.getPrenotazione(Long.parseLong(prenotazioneId));
        prenotazioneService.cancellaPrenotazione(prenotazione);
        return this.prenotazioniDaCancellare(model);
    }

    @PostMapping(value = "/prenota_auto/{autoId}")
    public String prenotazioneAuto(@PathVariable("autoId") String autoId, @RequestParam("dataInizioPeriodo") String dataInizioPeriodo, @RequestParam("dataFinePeriodo") String dataFinePeriodo, Authentication authentication) {
        Prenotazione nuovaPrenotazione = new Prenotazione();
        nuovaPrenotazione.setUtente(utenteService.cercaUtentePerUsername(authentication.getName()));
        nuovaPrenotazione.setAuto(autoService.getAutoPerId(Long.parseLong(autoId)));
        nuovaPrenotazione.setDataInizio(LocalDate.parse(dataInizioPeriodo));
        nuovaPrenotazione.setDataFine(LocalDate.parse(dataFinePeriodo));
        prenotazioneService.salvaOAggiornaPrenotazione(nuovaPrenotazione);
        return "redirect:/utente/profilo";
    }


}
