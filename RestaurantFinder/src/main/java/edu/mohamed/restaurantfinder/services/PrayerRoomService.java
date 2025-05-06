package edu.mohamed.restaurantfinder.services;

import edu.mohamed.restaurantfinder.model.PrayerRoom;
import java.util.List;
import java.util.Optional;

public interface PrayerRoomService {
    PrayerRoom getPrayerRoomById(Long id);
    List<PrayerRoom> getAllPrayerRooms();
    PrayerRoom updatePrayerRoom(Long id, PrayerRoom prayerRoom);
    boolean deletePrayerRoom(Long id);
	PrayerRoom createPrayerRoom(PrayerRoom prayerRoom);
}
