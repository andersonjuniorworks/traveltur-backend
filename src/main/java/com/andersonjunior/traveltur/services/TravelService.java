package com.andersonjunior.traveltur.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.traveltur.models.Travel;
import com.andersonjunior.traveltur.repositories.TravelRespository;
import com.andersonjunior.traveltur.services.exceptions.DataIntegrityException;
import com.andersonjunior.traveltur.services.exceptions.ObjectNotFoundException;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TravelService {

    private final TravelRespository travelRepository;

    public TravelService(TravelRespository travelRepository) {
        this.travelRepository = travelRepository;
    }

    public List<Travel> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return travelRepository.findAll(pageable).getContent();
    }

    public Travel findById(Long id) {
        Optional<Travel> travel = travelRepository.findById(id);
        return travel.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    public Long count() {
        Long count = travelRepository.count();
        return count;
    }

    @Transactional
    public Travel insert(Travel travel) {
        travel.setId(null);
        return travelRepository.save(travel);
    }

    @Transactional
    public Travel update(Travel travel) {
        Travel newTravel = findById(travel.getId());
        updateData(newTravel, travel);
        return travelRepository.save(newTravel);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            travelRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir essa viagem!");
        }
    }

    private void updateData(Travel newTravel, Travel travel) {
        newTravel.setDestination(travel.getDestination());
        newTravel.setDepartureDate(travel.getDepartureDate());
        newTravel.setDepartureTime(travel.getDepartureTime());
        newTravel.setReturnDate(travel.getDepartureDate());
        newTravel.setReturnTime(travel.getReturnTime());
    }

}
