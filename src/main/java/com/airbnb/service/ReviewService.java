package com.airbnb.service;

import com.airbnb.dto.ReviewDto;
import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.entity.Review;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);

    Review getByUserAndProperty(AppUser user, Property byId);

    List<ReviewDto> listReviewsOfUser(AppUser user);
}
