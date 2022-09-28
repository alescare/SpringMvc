package com.example.springmvc.controller;

import com.example.springmvc.entities.Utente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value = "index")
    public String getWelcome(Model model){

        model.addAttribute("utenteLogin", new Utente());
        return "index";
    }

    @RequestMapping
    public String getWelcome2(Model model){

        model.addAttribute("utenteLogin", new Utente());
        return "index";
    }

}
