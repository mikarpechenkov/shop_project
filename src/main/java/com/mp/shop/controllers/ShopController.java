package com.mp.shop.controllers;

import com.mp.shop.models.Product;
import com.mp.shop.services.ImageLocationService;
import com.mp.shop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;

@Controller
@RequiredArgsConstructor
public class ShopController {

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

//        Long productID = productService.save(name, description, new BigDecimal(price));
//        var setOfImageIDs = new HashSet<Long>();
//
//        for (MultipartFile image : images)
//            setOfImageIDs.add(imageLocationService.save(image.getBytes(), image.getOriginalFilename()));
//
//        productService.addPictures(productID, setOfImageIDs);

        return "redirect:/shop/adding_product"; //Изменить
    }
}
