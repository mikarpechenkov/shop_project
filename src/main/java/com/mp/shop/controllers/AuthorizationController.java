package com.mp.shop.controllers;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorizationController {
    @GetMapping("/login")
    public String logIn(Model model){
        model.addAttribute("title","Вход");
        return "authorization/login";
    }

    @GetMapping("/registration")
    public String singIn(Model model){
        model.addAttribute("title", "Регистрация");
        return "authorization/registration";
    }

    @PostMapping("/registration")
    public String createUser(Model model){
        return "";
    }
}
