package com.andersonjunior.traveltur.repositories;

import com.andersonjunior.traveltur.models.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
