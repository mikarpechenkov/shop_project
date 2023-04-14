package com.mp.shop.services;

import com.mp.shop.models.Image;
import com.mp.shop.repo.FileSystemRepository;
import com.mp.shop.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ImageLocationService {
    @Autowired
    private FileSystemRepository fileSystemRepository;
    @Autowired
    private ImageRepository imageDbRepository;

    public Long save(byte[] bytes, String imageName) throws Exception {
        String location = fileSystemRepository.save(bytes, imageName);

        return imageDbRepository.save(new Image(imageName, location)).getId();
    }

    public FileSystemResource find(Long imageId) {
        Image image = imageDbRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return fileSystemRepository.findInFileSystem(image.getLocation());
    }
}