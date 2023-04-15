package com.mp.shop.services;

import com.mp.shop.models.Product;
import com.mp.shop.repo.ImageRepository;
import com.mp.shop.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageLocationService imageLocationService;
    @Autowired
    private ImageRepository imageRepository;

    //Сохранение товара без изображения
    public Long save(String name, String description, BigDecimal price) {
        return productRepository.save(new Product(name, description, price)).getId();
    }

    public boolean addPictures(Long productID, Set<Long> pictureIDs) {
        Product product = productRepository.getReferenceById(productID);

        pictureIDs.forEach(pictureID ->
                product.getPictures().add(imageRepository.getReferenceById(pictureID))
        );

        return true;
    }
}
