package com.mp.shop.controllers;

import com.mp.shop.models.Image;
import com.mp.shop.services.ImageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class GeneralController {
    private final ImageService imageService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "general/index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "О нас");
        return "general/about";
    }

    @GetMapping("/contacts")
    public String contacts(Model model) {
        model.addAttribute("title", "Контакты");
        return "general/contacts";
    }

    @GetMapping("/image/display/{imageId}")
    @ResponseBody
    public void displayImage(@PathVariable Long imageId, HttpServletResponse response) throws IOException {
        Optional<Image>  image=imageService.findByID(imageId);

        response.setContentType("image/jpeg");
        response.setContentType("image/jpg");
        response.setContentType("image/png");
        response.setContentType("image/gif");

        if(image.isPresent())
            response.getOutputStream().write(image.get().getContent());

        response.getOutputStream().close();
    }
}
