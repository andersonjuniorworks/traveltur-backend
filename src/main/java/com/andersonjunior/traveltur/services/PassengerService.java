package com.andersonjunior.traveltur.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.traveltur.dtos.PassengerDto;
import com.andersonjunior.traveltur.enums.Status;
import com.andersonjunior.traveltur.models.Passenger;
import com.andersonjunior.traveltur.repositories.PassengerRepository;
import com.andersonjunior.traveltur.services.exceptions.ObjectNotFoundException;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public List<Passenger> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return passengerRepository.findAll(pageable).getContent();
    }

    public List<Passenger> findByStatus(Status status, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return passengerRepository.findByStatus(status, pageable);
    }

    public Passenger findById(Long id) {
        Optional<Passenger> passenger = passengerRepository.findById(id);
        return passenger.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    public List<Passenger> findByDocument(String documentNumber, Status status) {
        return passengerRepository.findByDocumentNumberAndStatus(documentNumber, status);
    }

    public List<Passenger> findByFullname(String fullname, Status status) {
        return passengerRepository.findByFullnameContainingIgnoreCaseAndStatus(fullname, status);
    }

    public Long count() {
        Long count = passengerRepository.count();
        return count;
    }

    @Transactional
    public Passenger insert(Passenger passenger) {
        passenger.setId(null);
        return passengerRepository.save(passenger);
    }

    @Transactional
    public Passenger update(Passenger passenger) {
        Passenger newPassenger = findById(passenger.getId());
        updateData(newPassenger, passenger);
        return passengerRepository.save(newPassenger);
    }

    @Transactional
    public Passenger delete(Long id) {
        Passenger newPassenger = findById(id);
        newPassenger.setStatus(Status.INATIVO);
        return passengerRepository.save(newPassenger);
    }

    public Passenger fromDTO(PassengerDto passengerDto) {
        return new Passenger(passengerDto.getId(), passengerDto.getDocumentType(), passengerDto.getDocumentNumber(),
                passengerDto.getFullname(), passengerDto.getBirthDate(), passengerDto.getStatus(),
                passengerDto.getCreatedBy(),
                null, null);
    }

    private void updateData(Passenger newPassenger, Passenger passenger) {
        newPassenger.setDocumentType(passenger.getDocumentType());
        newPassenger.setDocumentNumber(passenger.getDocumentNumber());
        newPassenger.setFullname(passenger.getFullname());
        newPassenger.setBirthDate(passenger.getBirthDate());
        newPassenger.setStatus(passenger.getStatus());
    }

}
