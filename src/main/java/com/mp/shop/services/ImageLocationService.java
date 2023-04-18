package com.mp.shop.services;

import com.mp.shop.models.Image;
import com.mp.shop.repo.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageLocationService {
    private final ImageRepository imageDbRepository;

    public boolean save(Image image){
        imageDbRepository.save(image);
        return true;
    }

}