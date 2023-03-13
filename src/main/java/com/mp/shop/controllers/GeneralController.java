package com.mp.shop.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("title", "Главная страница");
        return "general/index";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("title", "О нас");
        return "general/about";
    }

    @GetMapping("/contacts")
    public String contacts(Model model){
        model.addAttribute("title","Контакты");
        return "general/contacts";
    }
}
