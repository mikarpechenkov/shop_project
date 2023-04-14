package com.mp.shop.services;

import com.mp.shop.models.Image;
import com.mp.shop.models.Product;
import com.mp.shop.repo.FileSystemRepository;
import com.mp.shop.repo.ImageRepository;
import com.mp.shop.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
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
        return productRepository.save(new Product(name, description, price, (Set<Image>) null)).getId();
    }

    /* Сохранение товар с одной картинкой
     * В данной версии сайта у большинства товаров будет только одна картинка
     */
    public Long save(String name, String description, BigDecimal price, byte[] imageBytes, String imageName) throws Exception {
        Image image = imageRepository.getReferenceById(imageLocationService.save(imageBytes, imageName));

        return productRepository.save(new Product(name, description, price, image)).getId();
    }

    public boolean addPictures(Long productID, Set<Long> pictureIDs) {
        Product product = productRepository.getReferenceById(productID);

        pictureIDs.forEach(pictureID ->
                product.getPictures().add(imageRepository.getReferenceById(pictureID))
        );

        return true;
    }
}
