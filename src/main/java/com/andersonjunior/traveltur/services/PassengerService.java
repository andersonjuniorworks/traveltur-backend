package com.andersonjunior.traveltur.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.traveltur.models.Passenger;
import com.andersonjunior.traveltur.repositories.PassengerRepository;
import com.andersonjunior.traveltur.services.exceptions.DataIntegrityException;
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

    public Passenger findById(Long id) {
        Optional<Passenger> passenger = passengerRepository.findById(id);
        return passenger.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
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
    public void delete(Long id) {
        findById(id);
        try {
            passengerRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir essa viagem!");
        }
    }

    private void updateData(Passenger newPassenger, Passenger passenger) {
        newPassenger.setCpf(passenger.getCpf());
        newPassenger.setFullname(passenger.getFullname());
        newPassenger.setBirthDate(passenger.getBirthDate());
    }

}
