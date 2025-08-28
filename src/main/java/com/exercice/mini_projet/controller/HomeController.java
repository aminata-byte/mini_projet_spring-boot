package com.exercice.mini_projet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 👉 Accueil : http://localhost:8080/
    @GetMapping("/")
    public String index() {
        return "index"; // retournera templates/index.html
    }
}

