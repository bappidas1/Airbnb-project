package com.airbnb.service;

import com.airbnb.entity.Image;
import com.airbnb.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService{

    private ImageRepository imageRepository;

    @Override
    public Image insertImage(Image img) {
        return imageRepository.save(img);
    }
}
