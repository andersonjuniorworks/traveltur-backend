package com.andersonjunior.traveltur.repositories;

import com.andersonjunior.traveltur.models.Passenger;
import com.andersonjunior.traveltur.models.PassengerList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerListRepository extends JpaRepository<PassengerList, Long> {

}
