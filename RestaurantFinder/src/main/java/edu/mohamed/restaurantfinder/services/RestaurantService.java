package edu.mohamed.restaurantfinder.services;

import edu.mohamed.restaurantfinder.model.HalalRestaurant;
import java.util.List;
import java.util.Optional;

public interface RestaurantService {
	HalalRestaurant addRestaurant(HalalRestaurant restaurant);
    HalalRestaurant getRestaurantById(Long id);
    List<HalalRestaurant> getAllRestaurants();
    Optional<HalalRestaurant> updateRestaurant(Long id, HalalRestaurant restaurant);
    boolean deleteRestaurant(Long id);
	HalalRestaurant createRestaurant(HalalRestaurant restaurant);
}
