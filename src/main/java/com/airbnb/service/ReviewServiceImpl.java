package com.airbnb.service;

import com.airbnb.dto.CountryDto;
import com.airbnb.dto.ReviewDto;
import com.airbnb.entity.AppUser;
import com.airbnb.entity.Country;
import com.airbnb.entity.Property;
import com.airbnb.entity.Review;
import com.airbnb.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        Review review = mapToEntity(reviewDto);
        Review save = reviewRepository.save(review);
        ReviewDto reviewDto1 = mapToDto(save);
        return reviewDto1;
    }

    @Override
    public Review getByUserAndProperty(AppUser user, Property byId) {
        Review getReviewDetails = reviewRepository.findByUserNameAndProperty(user, byId);
        return getReviewDetails;



    }

    @Override
    public List<ReviewDto> listReviewsOfUser(AppUser user) {
        List<Review> reviewsByUser = reviewRepository.findReviewsByUser(user);
        List<ReviewDto> collect = reviewsByUser.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return collect;
    }

    Review mapToEntity(ReviewDto dto) {
        Review entity = new Review();
//        dto.setId(entity.getId());
        entity.setRating(dto.getRating());
        entity.setDescription(dto.getDescription());
        entity.setAppUser(dto.getAppUser());
        entity.setProperty(dto.getProperty());
        return entity;
    }

    ReviewDto mapToDto(Review entity) {
        ReviewDto dto = new ReviewDto();
        dto.setId(entity.getId());
        dto.setRating(entity.getRating());
        dto.setDescription(entity.getDescription());
        dto.setAppUser(entity.getAppUser());
        dto.setProperty(entity.getProperty());
        return dto;
    }
}
