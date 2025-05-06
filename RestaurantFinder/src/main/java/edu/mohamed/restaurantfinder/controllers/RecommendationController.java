package edu.mohamed.restaurantfinder.controllers;

import edu.mohamed.restaurantfinder.services.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/recommendations")
@Tag(name = "Recommendation API", description = "Location-based recommendations")
public class RecommendationController {

 private final RecommendationService recommendationService;

 public RecommendationController(RecommendationService recommendationService) {
     this.recommendationService = recommendationService;
 }

 @Operation(summary = "Get nearby recommendations")
 @ApiResponse(responseCode = "200", description = "Successfully retrieved recommendations")
 @GetMapping
 public ResponseEntity<Map<String, Object>> getRecommendations(
         @RequestParam @Parameter(description = "Latitude") Double lat,
         @RequestParam @Parameter(description = "Longitude") Double lon,
         @RequestParam(defaultValue = "5.0") @Parameter(description = "Search radius in kilometers") Double radius) {
     
     if (lat == null || lon == null) {
    	 System.out.println((lat == null) + " " + (lon == null));
         return ResponseEntity.badRequest().build();
     }
     Map<String, Object> result = recommendationService.getRecommendations(lat, lon, radius);
     System.out.println(result);
     
     return ResponseEntity.ok(
    		 result
     );
 }
}
