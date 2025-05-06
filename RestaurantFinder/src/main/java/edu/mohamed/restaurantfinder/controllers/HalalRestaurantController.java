package edu.mohamed.restaurantfinder.controllers;

import edu.mohamed.restaurantfinder.dto.NewHalalRestaurant;
import edu.mohamed.restaurantfinder.exception.ResourceNotFoundException;
import edu.mohamed.restaurantfinder.model.*;
import edu.mohamed.restaurantfinder.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/halal-restaurants")
@Tag(name = "Halal Restaurant API", description = "CRUD operations for Halal Restaurants")
public class HalalRestaurantController {

    private final RestaurantService halalRestaurantService;

    public HalalRestaurantController(RestaurantService halalRestaurantService) {
        this.halalRestaurantService = halalRestaurantService;
    }

    @Operation(summary = "Get all halal restaurants")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @GetMapping
    public ResponseEntity<List<HalalRestaurant>> getAllRestaurants() {
        return ResponseEntity.ok(halalRestaurantService.getAllRestaurants());
    }

    @Operation(summary = "Get a halal restaurant by ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved restaurant")
    @ApiResponse(responseCode = "404", description = "Restaurant not found")
    @GetMapping("/{id}")
    public ResponseEntity<HalalRestaurant> getRestaurantById(@PathVariable Long id) {
    	System.out.println("Received " + id);
    	HalalRestaurant halalRestaurant = halalRestaurantService.getRestaurantById(id);
    	System.out.println("Restaurant found: "+ halalRestaurant);
    	return Optional.of(halalRestaurant)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new halal restaurant")
    @ApiResponse(responseCode = "201", description = "Successfully created restaurant")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @PostMapping
    public ResponseEntity<?> createRestaurant(@Valid @RequestBody HalalRestaurant restaurant, BindingResult bindingResult) {
        // Log the received payload (consider using a proper logger instead of System.out)
        System.out.println("Received " + restaurant);
        
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            // Create a map to hold field errors
            Map<String, String> errors = new HashMap<>();
            
            // Process each field error
            bindingResult.getFieldErrors().forEach(error -> {
                String fieldName = error.getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            
            // Return validation errors with 400 Bad Request status
            return ResponseEntity.badRequest().body(errors);
        }
        
        // If validation passes, proceed with creation
        HalalRestaurant createdRestaurant = halalRestaurantService.createRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRestaurant);
    }

    @Operation(summary = "Update an existing halal restaurant")
    @ApiResponse(responseCode = "200", description = "Successfully updated restaurant")
    @ApiResponse(responseCode = "404", description = "Restaurant not found")
    @PutMapping("/{id}")
    public ResponseEntity<HalalRestaurant> updateRestaurant(@PathVariable Long id, @RequestBody HalalRestaurant restaurant) {
        return halalRestaurantService.updateRestaurant(id, restaurant)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a halal restaurant")
    @ApiResponse(responseCode = "204", description = "Successfully deleted restaurant")
    @ApiResponse(responseCode = "404", description = "Restaurant not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        if (halalRestaurantService.deleteRestaurant(id)) {
            return ResponseEntity.noContent().build();
        }
        throw new ResourceNotFoundException("Restaurant with ID " + id + " not found");
    }
}
