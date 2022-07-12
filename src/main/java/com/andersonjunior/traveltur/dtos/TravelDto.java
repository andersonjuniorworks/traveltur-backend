package com.andersonjunior.traveltur.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.andersonjunior.traveltur.enums.Status;
import com.andersonjunior.traveltur.models.Destination;
import com.andersonjunior.traveltur.models.Travel;
import com.andersonjunior.traveltur.models.User;
import com.andersonjunior.traveltur.models.Vehicle;

import lombok.Data;

@Data
public class TravelDto implements Serializable {

    private Long id;
    private Destination destination;
    private Vehicle vehicle;
    private LocalDateTime departureDate;
    private String departureTime;
    private LocalDateTime returnDate;
    private String returnTime;
    private Status status;
    private User createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public TravelDto(Travel travel) {
        this.id = travel.getId();
        this.destination = travel.getDestination();
        this.vehicle = travel.getVehicle();
        this.departureDate = travel.getDepartureDate();
        this.returnDate = travel.getReturnDate();
        this.departureTime = travel.getDepartureTime();
        this.returnTime = travel.getReturnTime();
    }

}
