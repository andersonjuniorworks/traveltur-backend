package com.andersonjunior.traveltur.repositories;

import com.andersonjunior.traveltur.models.Travel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRespository extends JpaRepository<Travel, Long> {

}
