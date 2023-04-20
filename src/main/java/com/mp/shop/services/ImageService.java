package com.mp.shop.services;

import com.mp.shop.models.Image;
import com.mp.shop.repo.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageDbRepository;

    public boolean save(Image image){
        imageDbRepository.save(image);
        return true;
    }

    public boolean existByID(Long id){
        return imageDbRepository.existsById(id);
    }

    public Optional<Image> findByID(Long id){
        return imageDbRepository.findById(id);
    }

}