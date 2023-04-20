package com.mp.shop.services;

import com.mp.shop.models.Image;
import com.mp.shop.models.Product;
import com.mp.shop.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageService imageService;

    public boolean save(Product product) {
        for (Image image : product.getPictures())
            if (image.getId() == null || !imageService.existByID(image.getId()))
                imageService.save(image);
        productRepository.save(product);
        return true;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
