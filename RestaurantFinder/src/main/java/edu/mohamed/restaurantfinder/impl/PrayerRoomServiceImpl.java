package edu.mohamed.restaurantfinder.impl;

import edu.mohamed.restaurantfinder.exception.ResourceNotFoundException;
import edu.mohamed.restaurantfinder.model.PrayerRoom;
import edu.mohamed.restaurantfinder.repository.PrayerRoomRepository;
import edu.mohamed.restaurantfinder.services.PrayerRoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrayerRoomServiceImpl implements PrayerRoomService {

    private final PrayerRoomRepository prayerRoomRepository;

    public PrayerRoomServiceImpl(PrayerRoomRepository prayerRoomRepository) {
        this.prayerRoomRepository = prayerRoomRepository;
    }

    @Override
    public List<PrayerRoom> getAllPrayerRooms() {
        return prayerRoomRepository.findAll();
    }

    @Override
    public PrayerRoom getPrayerRoomById(Long id) {
        return prayerRoomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prayer room not found with id: " + id));
        
    }

    @Override
    public PrayerRoom createPrayerRoom(PrayerRoom prayerRoom) {
    	 // Reset ID if it's 0 to force insert
	    if (prayerRoom.getId() != null && prayerRoom.getId() == 0L) {
	    	prayerRoom.setId(null);
	    }
        return prayerRoomRepository.save(prayerRoom);
    }

    @Override
    public PrayerRoom updatePrayerRoom(Long id, PrayerRoom prayerRoomDetails) {
        PrayerRoom prayerRoom = prayerRoomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prayer room not found with id: " + id));

        prayerRoom.setName(prayerRoomDetails.getName());
        prayerRoom.setAddress(prayerRoomDetails.getAddress());
        prayerRoom.setFacilities(prayerRoomDetails.getFacilities());

        return prayerRoomRepository.save(prayerRoom);
    }

    @Override
    public boolean deletePrayerRoom(Long id) {
        if (prayerRoomRepository.existsById(id)) {
            prayerRoomRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


