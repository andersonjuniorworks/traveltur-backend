package com.andersonjunior.traveltur.repositories;

import com.andersonjunior.traveltur.enums.Status;
import com.andersonjunior.traveltur.models.Passenger;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    List<Passenger> findByStatus(Status status, Pageable pageable);

    List<Passenger> findByDocumentNumberAndStatus(String documentNumber, Status status);

    List<Passenger> findByFullnameContainingIgnoreCaseAndStatus(String fullname, Status status);

}
