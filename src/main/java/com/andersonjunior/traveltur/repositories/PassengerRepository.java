package com.andersonjunior.traveltur.repositories;

import com.andersonjunior.traveltur.models.Passenger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
