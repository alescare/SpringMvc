package com.example.springmvc.controller;

import com.example.springmvc.entities.Utente;
import com.example.springmvc.service.UtenteService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {


    private final UtenteService utenteService;

    private final BCryptPasswordEncoder passwordEncoder;

    public LoginController(UtenteService utenteService, BCryptPasswordEncoder passwordEncoder) {
        this.utenteService = utenteService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String loginGet(Model model) {

        model.addAttribute("utenteLogin", new Utente());
        return "login";
    }

    @PostMapping("/form")
    public String getLoginPost(HttpServletRequest request, HttpServletResponse response) {

        return "redirect:/login/form?logout";
    }

}
