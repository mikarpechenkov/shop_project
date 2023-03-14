package com.mp.shop.controllers;

import com.mp.shop.models.User;
import com.mp.shop.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String logIn(Model model) {
        model.addAttribute("title", "Вход");
        return "authorization/login";
    }

    @GetMapping("/registration")
    public String singIn(Model model) {
        model.addAttribute("title", "Регистрация");
        return "authorization/registration";
    }

    @PostMapping("/registration")
    public String createUser(@RequestParam String name, @RequestParam String surname,
                             @RequestParam String email, @RequestParam String password, Model model) {
        var user = new User(name, surname, email, password);
        userRepository.save(user);
        return "redirect:/login";
    }
}
