package edu.mohamed.restaurantfinder.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "prayer_rooms")
public class PrayerRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100)
    private String name;
    
    @NotBlank(message = "Address is required")
    @Size(max = 200)
    private String address;
    
    private String facilities;
    
    @DecimalMin("-90.0") @DecimalMax("90.0")
    private Double latitude;

    @DecimalMin("-180.0") @DecimalMax("180.0")
    private Double longitude;
    
    @Min(0) @Max(5)
    private int cleanlinessRating = 0;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Default constructor
    public PrayerRoom() {
    }

    // Parameterized constructor
    public PrayerRoom(String name, String address, String facilities,
    		Double latitude, Double longitude) {
        this.name = name;
        this.address = address;
        this.facilities = facilities;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the location of the prayer room.
     * 
     * @return the location as a String.
     */
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

    public String getFacilities() {
        return facilities;
    }

   
	public void setFacilities(String facilities) {
        this.facilities = facilities;
    }
	
	

    public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setCleanlinessRating(int cleanlinessRating) {
		this.cleanlinessRating = cleanlinessRating;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public int getCleanlinessRating() {
		return cleanlinessRating;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	@Override
    public String toString() {
        return "PrayerRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + address + '\'' +
                ", facilities='" + facilities + '\'' +
                '}';
    }
}
