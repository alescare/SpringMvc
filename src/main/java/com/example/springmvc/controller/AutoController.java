package com.example.springmvc.controller;

import com.example.springmvc.entities.Auto;
import com.example.springmvc.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/auto")
public class AutoController {

    @Autowired
    AutoService autoService;


    @GetMapping(value = "/cerca")
    public String prenotaAuto(Model model) {
        return "prenotaAuto";
    }

    @GetMapping(value = "/gestione_auto")
    public String gestisciAuto(Model model) {

        model.addAttribute("autoDaModificare", new Auto());
        model.addAttribute("listaAuto", autoService.getListaAuto());

        return "gestioneAuto";
    }

    @PostMapping(value = "/gestione_auto")
    public String salvaModifiche(@ModelAttribute("autoDaModificare") Auto autoDaModificare, @RequestParam("azione") String azione, Model model) {
        if ("elimina".equals(azione)) {
            autoService.cancellaAuto(autoDaModificare);
        } else {
            autoService.salvaOAggiornaAuto(autoDaModificare);
        }
        model.addAttribute("listaAuto", new Auto());
        return "gestioneAuto";
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

    @PostMapping(value = "/gestione")
    public String elimina(Model model) {

        return "gestioneAuto";
    }

    @GetMapping(value = "/modifica_auto/{autoId}")
    public String modifica(@PathVariable("autoId") String autoId, Model model) {
        model.addAttribute("autoDaModificare", autoService.getAutoPerId(Long.parseLong(autoId)));
        model.addAttribute("listaAuto", autoService.getListaAuto());
        return "gestioneAuto";
    }


}
