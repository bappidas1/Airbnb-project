package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Image;
import com.airbnb.entity.Property;
import com.airbnb.service.BucketService;
import com.airbnb.service.ImageService;
import com.airbnb.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
    @RequestMapping("/api/v1/images")
public class ImageController {

    private ImageService imageService;
    private PropertyService propertyService;
    private BucketService bucketService;

    @PostMapping(value = "/upload/file/{bucketName}/property/{propertyId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadFile(
            @RequestParam MultipartFile file,
            @PathVariable String bucketName,
            @PathVariable long propertyId,
            @AuthenticationPrincipal AppUser user
    ){
        String imageUrl = bucketService.uploadFile(file, bucketName);
        Property property = propertyService.getById(propertyId);
        Image img = new Image();
        img.setUrl(imageUrl);
        img.setProperty(property);
        Image savedImage = imageService.insertImage(img);
        return new ResponseEntity<>(savedImage, HttpStatus.OK);
    }
}
