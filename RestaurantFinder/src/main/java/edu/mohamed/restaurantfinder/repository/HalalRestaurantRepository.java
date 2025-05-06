package edu.mohamed.restaurantfinder.repository;

import edu.mohamed.restaurantfinder.model.HalalRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HalalRestaurantRepository extends JpaRepository<HalalRestaurant, Long> {
    List<HalalRestaurant> findByCity(String city);
    List<HalalRestaurant> findByCuisineType(String cuisineType);
    // Add to HalalRestaurantRepository.java
    @Query(value = "SELECT * FROM halal_restaurants " +
    	       "WHERE ((ABS(latitude - :lat) + ABS(longitude - :lon)) * 111.12 <= :radius )" +
    	       "ORDER BY rating DESC", 
    	       nativeQuery = true)
    	List<HalalRestaurant> findNearbyRestaurants(
    	    @Param("lat") Double latitude,
    	    @Param("lon") Double longitude,
    	    @Param("radius") Double radiusInKm
    	);
}

