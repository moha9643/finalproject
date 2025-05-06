package edu.mohamed.restaurantfinder.services;

import java.util.List;
import java.util.Optional;

import edu.mohamed.restaurantfinder.model.Review;

public interface ReviewService {
    List<Review> getAllReviews();
    Optional<Review> getReviewById(Long id);
    Review createReview(Review review);
    Optional<Review> updateReview(Long id, Review review);
    boolean deleteReview(Long id);
}
