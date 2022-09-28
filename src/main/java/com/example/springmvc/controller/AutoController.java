package com.example.springmvc.controller;

import com.example.springmvc.entities.Auto;
import com.example.springmvc.entities.Prenotazione;
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




    @GetMapping(value = "/gestione_auto")
    public String gestisciAuto(Model model) {

        model.addAttribute("autoDaModificare", new Auto());
        model.addAttribute("listaAuto", autoService.getListaAuto());

        return "gestioneAuto";
    }

    @PostMapping(value = "/gestione_modifiche_auto")
    public String salvaModifiche(@ModelAttribute("autoDaModificare") Auto autoDaModificare, @RequestParam("azione") String azione, Model model) {
        autoService.salvaOAggiornaAuto(autoDaModificare);
        model.addAttribute("listaAuto", autoService.getListaAuto());
        return "redirect:/auto/gestione_auto";
    }


    @PostMapping(value = "/elimina_auto_{autoId}")
    public String elimina(@PathVariable("autoId") String autoId, Model model) {
        autoService.cancellaAuto(autoService.getAutoPerId(Long.parseLong(autoId)));
        model.addAttribute("listaAuto", autoService.getListaAuto());
        return "gestioneAuto";
    }

    @GetMapping(value = "/modifica_auto_{autoId}")
    public String modifica(@PathVariable("autoId") String autoId, Model model) {
        model.addAttribute("autoDaModificare", autoService.getAutoPerId(Long.parseLong(autoId)));
        model.addAttribute("listaAuto", autoService.getListaAuto());
        return "gestioneAuto";
    }


}
