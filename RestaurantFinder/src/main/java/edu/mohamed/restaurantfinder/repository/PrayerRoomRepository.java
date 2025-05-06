package edu.mohamed.restaurantfinder.repository;

import edu.mohamed.restaurantfinder.model.PrayerRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrayerRoomRepository extends JpaRepository<PrayerRoom, Long> {
    List<PrayerRoom> findByAddress(String address);
    
    @Query(value = "SELECT * FROM prayer_rooms " +
 	       "WHERE ((ABS(latitude - :lat) + ABS(longitude - :lon)) * 111.12 <= :radius )",
 	       nativeQuery = true)
    List<PrayerRoom> findNearbyPrayerRooms(@Param("lat") Double latitude,
                                         @Param("lon") Double longitude,
                                         @Param("radius") Double radiusInKm);
}
