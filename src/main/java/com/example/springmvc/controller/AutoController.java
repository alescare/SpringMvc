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

    //MODELATTRIBUTE
    @ModelAttribute("listaAuto")
    public List<Auto> getListaAuto() {

        return autoService.getListaAuto();
    }

    //GET
    @GetMapping(value = "/gestione_auto")
    public String gestisciAuto(Model model) {

        if(!(model.containsAttribute("autoDaModificare"))){
            model.addAttribute("autoDaModificare", new Auto());
        }
        model.addAttribute("listaAuto", this.getListaAuto());
        return "gestioneAuto";
    }

    @GetMapping(value = "/modifica_auto_{autoId}")
    public String modifica(@PathVariable("autoId") String autoId, Model model) {

        model.addAttribute("autoDaModificare", autoService.getAutoPerId(Long.parseLong(autoId)));
        return gestisciAuto(model);
    }

    //POST

    @PostMapping(value = "/gestione_modifiche_auto")
    public String salvaModifiche(@Valid @ModelAttribute("autoDaModificare") Auto autoDaModificare, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "gestioneAuto";
        }
        autoService.salvaOAggiornaAuto(autoDaModificare);
        return gestisciAuto(model);
    }

    @PostMapping(value = "/elimina_auto/{autoId}")
    public String elimina(@PathVariable("autoId") String autoId, Model model) {

        autoService.cancellaAuto(autoService.getAutoPerId(Long.parseLong(autoId)));
        return gestisciAuto(model);
    }

}
