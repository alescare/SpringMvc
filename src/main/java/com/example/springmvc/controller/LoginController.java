package com.example.springmvc.controller;

import com.example.springmvc.entities.Utente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {


    @GetMapping
    public String loginGet(Model model) {

        model.addAttribute("utenteLogin", new Utente());
        return "login";
    }

    @PostMapping("/form")
    public String getLoginPost() {

        return "redirect:/login/form?logout";
    }

}
