package com.airbnb.controller;

import com.airbnb.dto.AppUserDto;
import com.airbnb.dto.PropertyDto;
import com.airbnb.dto.ReviewDto;
import com.airbnb.entity.AppUser;

import com.airbnb.entity.Property;
import com.airbnb.entity.Review;
import com.airbnb.service.PropertyService;
import com.airbnb.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1/reviews")
public class ReviewController {

    private ReviewService reviewService;
    private PropertyService propertyService;

    public ReviewController(ReviewService reviewService, PropertyService propertyService) {
        this.reviewService = reviewService;
        this.propertyService = propertyService;
    }


    @PostMapping("/createReview")
    public ResponseEntity<?> createReview(
            @RequestBody ReviewDto reviewDto,
            @AuthenticationPrincipal AppUser user,
            @RequestParam long propertyId
    ) {
        Property byId = propertyService.getById(propertyId);
        Review reviewDetails = reviewService.getByUserAndProperty(user, byId);
        if(reviewDetails != null){
            return new ResponseEntity<>("Review Exists", HttpStatus.CREATED);
        }
        reviewDto.setProperty(byId);
        reviewDto.setAppUser(user);
        ReviewDto review = reviewService.createReview(reviewDto);
        return new ResponseEntity<>(review, HttpStatus.CREATED);

    }

    @GetMapping("/userReviews")
    public ResponseEntity<List<ReviewDto>> listReviewsOfUser(
            @AuthenticationPrincipal AppUser user
    ){
        List<ReviewDto> reviewDtos = reviewService.listReviewsOfUser(user);
        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }

}
