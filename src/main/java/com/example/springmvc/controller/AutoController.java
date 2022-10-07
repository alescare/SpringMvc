package com.example.springmvc.controller;

import com.example.springmvc.entities.Auto;
import com.example.springmvc.service.AutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/auto")
public class AutoController {


    private final AutoService autoService;


    public AutoController(AutoService autoService) {
        this.autoService = autoService;
    }

    @GetMapping(value = "/gestione_auto")
    public String gestisciAuto(Model model) {

        model.addAttribute("autoDaModificare", new Auto());
        model.addAttribute("listaAuto", autoService.getListaAuto());

        return "gestioneAuto";
    }

    @PostMapping(value = "/gestione_modifiche_auto")
    public String salvaModifiche(@Valid @ModelAttribute("autoDaModificare") Auto autoDaModificare, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "gestioneAuto";
        }
        autoService.salvaOAggiornaAuto(autoDaModificare);
        return "redirect:/auto/gestione_auto";
    }


    @PostMapping(value = "/elimina_auto/{autoId}")
    public String elimina(@PathVariable("autoId") String autoId) {
        autoService.cancellaAuto(autoService.getAutoPerId(Long.parseLong(autoId)));
        return "redirect:/auto/gestione_auto";
    }

    @GetMapping(value = "/modifica_auto_{autoId}")
    public String modifica(@PathVariable("autoId") String autoId, Model model) {
        model.addAttribute("autoDaModificare", autoService.getAutoPerId(Long.parseLong(autoId)));
        model.addAttribute("listaAuto", autoService.getListaAuto());
        return "gestioneAuto";
    }

    @ModelAttribute("listaAuto")
    public List<Auto> getListaAuto(){
        return autoService.getListaAuto();
    }


}
