package com.andersonjunior.traveltur.repositories;

import com.andersonjunior.traveltur.enums.Status;
import com.andersonjunior.traveltur.models.Travel;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRespository extends JpaRepository<Travel, Long> {

    List<Travel> findByStatus(Status status, Pageable pageable);

    @Query("SELECT travel FROM Travel travel WHERE travel.destination.name = :destinationName ORDER BY travel.createdAt ASC")
    List<Travel> findByDestination(String destinationName, Pageable pageable);

    List<Travel> findByDepartureDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
