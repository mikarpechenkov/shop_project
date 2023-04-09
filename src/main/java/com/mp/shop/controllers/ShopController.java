package com.mp.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {

    @GetMapping("/shop/catalog")
    public String shopCatalog(Model model){
        model.addAttribute("title", "Каталог");
            return "/shop/catalog";
    }
}
