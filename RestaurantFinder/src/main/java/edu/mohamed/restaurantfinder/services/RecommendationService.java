package edu.mohamed.restaurantfinder.services;

import edu.mohamed.restaurantfinder.model.HalalRestaurant;
import edu.mohamed.restaurantfinder.model.PrayerRoom;
import edu.mohamed.restaurantfinder.repository.HalalRestaurantRepository;
import edu.mohamed.restaurantfinder.repository.PrayerRoomRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecommendationService {

 private final HalalRestaurantRepository restaurantRepository;
 private final PrayerRoomRepository prayerRoomRepository;

 public RecommendationService(HalalRestaurantRepository restaurantRepository, 
                            PrayerRoomRepository prayerRoomRepository) {
     this.restaurantRepository = restaurantRepository;
     this.prayerRoomRepository = prayerRoomRepository;
 }

 public Map<String, Object> getRecommendations(Double latitude, Double longitude, Double radius) {
     Map<String, Object> recommendations = new HashMap<>();
     
     List<HalalRestaurant> restaurants = restaurantRepository.findNearbyRestaurants(
         latitude, longitude, radius);
     
     List<PrayerRoom> prayerRooms = prayerRoomRepository.findNearbyPrayerRooms(
         latitude, longitude, radius);
     System.out.println("Hit count: " + prayerRooms.size());
     
     recommendations.put("restaurants", restaurants);
     recommendations.put("prayerRooms", prayerRooms);
     
     return recommendations;
 }
}
