package com.example.springmvc.controller;

import com.example.springmvc.entities.Utente;
import com.example.springmvc.service.UtenteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    private final UtenteService utenteService;

    public IndexController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @GetMapping
    public String getWelcome(Authentication authentication, Model model) {

        if (authentication == null) {
            return "redirect:/login";
        }
        return "redirect:/utente/home";
    }

}