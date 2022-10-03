package com.example.springmvc.controller;

import com.example.springmvc.entities.Utente;
import com.example.springmvc.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    UtenteService utenteService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @RequestMapping(value = "index")
    public String getWelcome(Model model) {

        if (utenteService.cercaUtentePerUsername("admin") == null) {
            Utente admin = new Utente();
            admin.setAdmin(true);
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEmail("");
            admin.setCognome("");
            admin.setNome("");
            admin.setDataNascita(LocalDate.now());
            utenteService.salvaOAggiornaUtente(admin);
        }
        model.addAttribute("utenteLogin", new Utente());
        return "index";
    }

    @RequestMapping
    public String getWelcome2(Model model) {

        return "redirect:/index";
    }

}
