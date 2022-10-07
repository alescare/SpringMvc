package com.example.springmvc.controller;

import com.example.springmvc.entities.Utente;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {


    @GetMapping
    public String getWelcome(Authentication authentication, Model model) {

        if (authentication == null) {
            return loginGet(model);
        }
        return "redirect:/utente/home";
    }

    @GetMapping("login")
    public String loginGet(Model model) {

        model.addAttribute("utenteLogin", new Utente());
        return "login";
    }

    @PostMapping("login/form")
    public String getLoginPost() {

        return "redirect:/login/form?logout";
    }

}