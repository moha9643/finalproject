package edu.mohamed.restaurantfinder.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mohamed.restaurantfinder.model.PrayerRoom;
import edu.mohamed.restaurantfinder.repository.PrayerRoomRepository;
import edu.mohamed.restaurantfinder.services.PrayerRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/prayer-rooms")
@Tag(name = "Prayer Room API", description = "CRUD operations for Prayer Rooms")
public class PrayerRoomController {

    private final PrayerRoomService prayerRoomService;

    public PrayerRoomController(PrayerRoomService prayerRoomService) {
        this.prayerRoomService = prayerRoomService;
    }

    @Operation(summary = "Get all prayer rooms")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @GetMapping
    public ResponseEntity<List<PrayerRoom>> getAllPrayerRooms() {
        return ResponseEntity.ok(prayerRoomService.getAllPrayerRooms());
    }

    @Operation(summary = "Get a prayer room by ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved prayer room")
    @ApiResponse(responseCode = "404", description = "Prayer room not found")
    @GetMapping("/{id}")
    public ResponseEntity<PrayerRoom> getPrayerRoomById(@PathVariable Long id) {
    	PrayerRoom prayerRoom = prayerRoomService.getPrayerRoomById(id);
        return Optional.of(prayerRoom)
        	   .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new prayer room")
    @ApiResponse(responseCode = "201", description = "Successfully created prayer room")
    @PostMapping
    public ResponseEntity<PrayerRoom> createPrayerRoom(@RequestBody PrayerRoom prayerRoom) {
    	System.out.println(prayerRoom.getLatitude());
        return ResponseEntity.status(201).body(prayerRoomService.createPrayerRoom(prayerRoom));
    }

    @Operation(summary = "Update an existing prayer room")
    @ApiResponse(responseCode = "200", description = "Successfully updated prayer room")
    @ApiResponse(responseCode = "404", description = "Prayer room not found")
    @PutMapping("/{id}")
    public ResponseEntity<PrayerRoom> updatePrayerRoom(@PathVariable Long id, @RequestBody PrayerRoom prayerRoom) {
    	PrayerRoom prayerRoomLocal = prayerRoomService.updatePrayerRoom(id, prayerRoom);
        return Optional.of(prayerRoomLocal).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a prayer room")
    @ApiResponse(responseCode = "204", description = "Successfully deleted prayer room")
    @ApiResponse(responseCode = "404", description = "Prayer room not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrayerRoom(@PathVariable Long id) {
        if (prayerRoomService.deletePrayerRoom(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
