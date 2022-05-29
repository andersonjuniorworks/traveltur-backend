package com.andersonjunior.traveltur.repositories;

import com.andersonjunior.traveltur.models.Destination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

}
