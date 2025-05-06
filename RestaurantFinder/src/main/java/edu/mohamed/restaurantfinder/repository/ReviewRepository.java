package edu.mohamed.restaurantfinder.repository;

import edu.mohamed.restaurantfinder.model.Review;
import edu.mohamed.restaurantfinder.model.HalalRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRestaurant(HalalRestaurant restaurant);
}