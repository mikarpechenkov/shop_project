package com.mp.shop.controllers;


import com.mp.shop.models.Image;
import com.mp.shop.models.Product;
import com.mp.shop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ShopController {
    private final ProductService productService;

    @GetMapping("/shop/catalog")
    public String shopCatalog(Model model) {
        model.addAttribute("title", "Каталог");
        return "/shop/catalog";
    }

    @GetMapping("/shop/adding_product")
    public String addingProduct(Model model) {
        model.addAttribute("title", "Добавление товара");
        return "/shop/adding_product";
    }

    @PostMapping("/shop/adding_product")
    public String addingProduct(@RequestParam String name, @RequestParam String description,
                                @RequestParam String price, @RequestParam MultipartFile[] images, Model model) throws Exception {
        Set<Image> imagesSet = new HashSet<>();

        for (MultipartFile image : images)
            imagesSet.add(new Image(image.getOriginalFilename(), image.getBytes()));

        Product product = new Product(name, description, new BigDecimal(price), imagesSet);

        productService.save(product);
        return "redirect:/shop/adding_product"; //Изменить
    }
}
