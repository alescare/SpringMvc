package com.example.springmvc.controller;

import com.example.springmvc.entities.Auto;
import com.example.springmvc.entities.Prenotazione;
import com.example.springmvc.service.AutoService;
import com.example.springmvc.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/prenotazione")
public class PrenotazioneController {

    @Autowired
    PrenotazioneService prenotazioneService;

    @Autowired
    AutoService autoService;

    @GetMapping(value = "/approva")
    public String ApprovaPrenotazioni(Model model) {

        model.addAttribute("prenotazioni", prenotazioneService.getPrenotazioniDaApprovare());
        return "approvaPrenotazioni";
    }

    @GetMapping(value = "/prenota_auto")
    public String vaiAPrenotaAuto(Model model) {
        model.addAttribute("dataInizioPeriodo", null);
        model.addAttribute("dataFinePeriodo", null);
        return "prenotaAuto";
    }

    @GetMapping(value = "/auto_disponibili")
    public String cercaAutoDisponibili(@RequestParam("dataInizioPeriodo") String dataInizioPeriodoForm, @RequestParam("dataFinePeriodo") String dataFinePeriodoForm, Model model) {

        LocalDate dataInizioPeriodo = LocalDate.parse(dataInizioPeriodoForm);
        LocalDate dataFinePeriodo = LocalDate.parse(dataFinePeriodoForm);
        List<Auto> listaAutoDisponibili = autoService.listaAutoDisponibiliNelPeriodo(dataInizioPeriodo, dataFinePeriodo);
        model.addAttribute("dataInizioPeriodo", dataInizioPeriodo);
        model.addAttribute("dataFinePeriodo", dataFinePeriodo);
        model.addAttribute("listaAutoDisponibili", listaAutoDisponibili);

        return "prenotaAuto";
    }

    @PostMapping(value = "/prenota_auto/{autoId}")
    public String prenotazioneAuto(@PathVariable("autoId") String autoId, @RequestParam("dataInizioPeriodo") String dataInizioPeriodo, @RequestParam("dataFinePeriodo") String dataFinePeriodo, Model model) {
        Prenotazione nuovaPrenotazione = new Prenotazione();
        //nuovaPrenotazione.setUtente()
        nuovaPrenotazione.setAuto(autoService.getAutoPerId(Long.parseLong(autoId)));
        nuovaPrenotazione.setDataInizio(LocalDate.parse(dataInizioPeriodo));
        nuovaPrenotazione.setDataFine(LocalDate.parse(dataFinePeriodo));
        prenotazioneService.salvaOAggiornaPrenotazione(nuovaPrenotazione);
        return "redirect:/utente/profilo";
    }


    @GetMapping(value = "/prenotazioni_da_approvare")
    public String prenotazioniDaCancellare(Model model) {

        model.addAttribute("listaPrenotazioniDaApprovare", prenotazioneService.getPrenotazioniDaApprovare());
        return "cancellaPrenotazioni";
    }


    @PostMapping(value = "/approva_prenotazione/{prenotazioneId}")
    public String approvaPrenotazione(@PathVariable("prenotazioneId") String prenotazioneId, Model model) {
        Prenotazione prenotazione = prenotazioneService.getPrenotazione(Long.parseLong(prenotazioneId));
        prenotazione.setApprovata(true);
        prenotazioneService.salvaOAggiornaPrenotazione(prenotazione);
        return "redirect:/prenotazione/prenotazioni_da_approvare";
    }

    @PostMapping(value = "/prenotazioni_da_cancellare/{prenotazioneId}")
    public String cancellaPrenotazione(@PathVariable("prenotazioneId") String prenotazioneId, Model model) {
        Prenotazione prenotazione = prenotazioneService.getPrenotazione(Long.parseLong(prenotazioneId));
        prenotazioneService.cancellaPrenotazione(prenotazione);
        return "cancellaPrenotazioni";
    }


}
