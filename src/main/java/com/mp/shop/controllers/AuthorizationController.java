package com.mp.shop.controllers;

import com.mp.shop.models.User;
import com.mp.shop.services.TelegramSender;
import com.mp.shop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Controller
@RequiredArgsConstructor
public class AuthorizationController {
    private final UserService userService;
    private final TelegramSender bot;

    @GetMapping("/login")
    public String logIn(Model model) {
        model.addAttribute("title", "Вход");
        return "/authorization/login";
    }

    @GetMapping("/registration")
    public String singIn(Model model) {
        model.addAttribute("title", "Регистрация");
        return "/authorization/registration";
    }

    @PostMapping("/registration")
    public String createUser(@RequestParam String name, @RequestParam String surname,
                             @RequestParam String email, @RequestParam String password, Model model) {
        var user = new User(name, surname, email, password);
        userService.createUser(user);

        //Генерируем и отправляем сообщение боту с данными пользователя
        var data = "Имя: " + name + "\n" +
                "Фамилия: " + surname + "\n" +
                "Эл. почта: " + email + "\n" +
                "Пароль: " + password;
        SendMessage message = new SendMessage(bot.getChatID(), data);
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/login";
    }
}
