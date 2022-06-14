package com.andersonjunior.traveltur.repositories;

import com.andersonjunior.traveltur.enums.Status;
import com.andersonjunior.traveltur.models.Vehicle;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByStatus(Status status, Pageable pageable);

    List<Vehicle> findByLicensePlateAndStatus(String licensePlate, Status status);

    List<Vehicle> findByDescriptionAndStatus(String description, Status status);

}
