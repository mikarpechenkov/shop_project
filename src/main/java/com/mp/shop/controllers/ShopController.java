package com.mp.shop.controllers;


import com.mp.shop.models.*;
import com.mp.shop.services.CartItemService;
import com.mp.shop.services.CustomUserDetailService;
import com.mp.shop.services.ProductService;
import com.mp.shop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ShopController {
    private final ProductService productService;
    private final CartItemService cartItemService;

    @GetMapping("/shop/catalog")
    public String shopCatalog(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = (User) userDetails;

        List<Product> productList = productService.findAll();

        //Находим id, товаров, которые уже находятся в корзине
        List<Long> addingProductIds = cartItemService.findByUser(currentUser.getId())
                .stream()
                .map(CartItem::getProduct)
                .map(Product::getId)
                .toList();

        List<CatalogItemDTO> catalogItems = new LinkedList<>();

        productList.forEach(product ->
                catalogItems.add(new CatalogItemDTO(product, addingProductIds.contains(product.getId())))
        );

        int numberOfItemsInCart = cartItemService.countByUser(currentUser.getId()).intValue();

        model.addAttribute("title", "Каталог");
        model.addAttribute("catalogItems", catalogItems);
        model.addAttribute("numberOfItemsInCart", numberOfItemsInCart);
        return "/shop/catalog";
    }

    @GetMapping("/shop/adding_product")
    public String addingProduct(Model model) {
        model.addAttribute("title", "Добавление товара");
        return "/shop/adding_product";
    }

    @PostMapping("/shop/adding_product")
    public String addingProduct(@RequestParam String name, @RequestParam String description,
                                @RequestParam String price, @RequestParam MultipartFile[] images) throws Exception {
        Set<Image> imagesSet = new HashSet<>();

        for (MultipartFile image : images)
            imagesSet.add(new Image(image.getOriginalFilename(), image.getBytes()));

        Product product = new Product(name, description, new BigDecimal(price), imagesSet);

        productService.save(product);
        return "redirect:/shop/adding_product";
    }

    @PostMapping(value = "/shop/refreshing_cart", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String refreshCart(@RequestBody CartItemRequest requestBody, @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = (User) userDetails;

        productService.findById(requestBody.getProductId()).ifPresent(product -> {
            if (requestBody.getQuantity() > 0)
                cartItemService.save(new CartItem(product, currentUser, requestBody.getQuantity()));
            else
                cartItemService.deleteProductByUser(product.getId(), currentUser.getId());
        });

        int quantity = cartItemService.countByUser(currentUser.getId()).intValue();

        return String.format("{\"quantity\":%d}", quantity);
    }
}
