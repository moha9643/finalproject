package edu.mohamed.restaurantfinder.impl;

import edu.mohamed.restaurantfinder.exception.ResourceNotFoundException;
import edu.mohamed.restaurantfinder.model.HalalRestaurant;
import edu.mohamed.restaurantfinder.repository.HalalRestaurantRepository;
import edu.mohamed.restaurantfinder.services.RestaurantService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final HalalRestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(HalalRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public HalalRestaurant addRestaurant(HalalRestaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public HalalRestaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Restaurant with ID " + id + " not found"));
    }

    @Override
    public List<HalalRestaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<HalalRestaurant> updateRestaurant(Long id, HalalRestaurant restaurant) {
    	HalalRestaurant restaurantLocal = restaurantRepository.findById(id)
            .map(existing -> {
                existing.setName(restaurant.getName());
                existing.setAddress(restaurant.getAddress());
                existing.setCuisineType(restaurant.getCuisineType());
                existing.setRating(restaurant.getRating());
                return restaurantRepository.save(existing);
            }).orElseThrow(() -> new ResourceNotFoundException("Restaurant with ID " + id + " not found"));
    	return Optional.of(restaurantLocal);
    }

    @Override
    public boolean deleteRestaurant(Long id) {
    	 if (!restaurantRepository.existsById(id)) {
             throw new ResourceNotFoundException("Restaurant with ID " + id + " not found");
         }
         restaurantRepository.deleteById(id);
         return true;
    }

	@Override
	public HalalRestaurant createRestaurant(HalalRestaurant restaurant) {
		 // Reset ID if it's 0 to force insert
	    if (restaurant.getRestaurantId() != null && restaurant.getRestaurantId() == 0L) {
	        restaurant.setRestaurantId(null);
	    }
	    restaurant.setCreatedAt(LocalDateTime.now());
	    return restaurantRepository.save(restaurant);
    }
}

