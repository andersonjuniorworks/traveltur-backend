package com.andersonjunior.traveltur.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.traveltur.models.PassengerList;
import com.andersonjunior.traveltur.repositories.PassengerListRepository;
import com.andersonjunior.traveltur.services.exceptions.DataIntegrityException;
import com.andersonjunior.traveltur.services.exceptions.ObjectNotFoundException;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerListService {

    private final PassengerListRepository passengerListRepository;

    public PassengerListService(PassengerListRepository passengerListRepository) {
        this.passengerListRepository = passengerListRepository;
    }

    public List<PassengerList> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return passengerListRepository.findAll(pageable).getContent();
    }

    public PassengerList findById(Long id) {
        Optional<PassengerList> passengerList = passengerListRepository.findById(id);
        return passengerList.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    public Long count() {
        Long count = passengerListRepository.count();
        return count;
    }

    @Transactional
    public PassengerList insert(PassengerList passengerList) {
        passengerList.setId(null);
        return passengerListRepository.save(passengerList);
    }

    @Transactional
    public PassengerList update(PassengerList passengerList) {
        PassengerList newPassengerList = findById(passengerList.getId());
        updateData(newPassengerList, passengerList);
        return passengerListRepository.save(newPassengerList);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            passengerListRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir essa lista!");
        }
    }

    private void updateData(PassengerList newPassengerList, PassengerList passengerList) {
        newPassengerList.setTravel(passengerList.getTravel());
        newPassengerList.setPassenger(passengerList.getPassenger());
        newPassengerList.setSeat(passengerList.getSeat());
    }

}
