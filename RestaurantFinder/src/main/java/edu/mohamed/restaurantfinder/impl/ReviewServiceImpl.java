package edu.mohamed.restaurantfinder.impl;


import org.springframework.stereotype.Service;

import edu.mohamed.restaurantfinder.model.Review;
import edu.mohamed.restaurantfinder.repository.ReviewRepository;
import edu.mohamed.restaurantfinder.services.ReviewService;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Optional<Review> updateReview(Long id, Review review) {
        return reviewRepository.findById(id).map(existingReview -> {
            existingReview.setRating(review.getRating());
            existingReview.setReviewText(review.getReviewText());
            existingReview.setUser(review.getUser());
            existingReview.setRestaurant(review.getRestaurant());
            return reviewRepository.save(existingReview);
        });
    }

    @Override
    public boolean deleteReview(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

