package com.andersonjunior.traveltur.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.traveltur.dtos.DestinationDto;
import com.andersonjunior.traveltur.enums.Status;
import com.andersonjunior.traveltur.models.Destination;
import com.andersonjunior.traveltur.repositories.DestinationRepository;
import com.andersonjunior.traveltur.services.exceptions.ObjectNotFoundException;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public List<Destination> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return destinationRepository.findAll(pageable).getContent();
    }

    public Destination findById(Long id) {
        Optional<Destination> destination = destinationRepository.findById(id);
        return destination.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    public List<Destination> findByStatus(Status status, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return destinationRepository.findByStatus(status, pageable);
    }

    public List<Destination> findByName(String name) {
        return destinationRepository.findByNameContainingIgnoreCase(name);
    }

    public Long count() {
        Long count = destinationRepository.count();
        return count;
    }

    @Transactional
    public Destination insert(Destination destination) {
        destination.setId(null);
        return destinationRepository.save(destination);
    }

    @Transactional
    public Destination update(Destination destination) {
        Destination newDestination = findById(destination.getId());
        updateData(newDestination, destination);
        return destinationRepository.save(newDestination);
    }

    @Transactional
    public Destination delete(Long id) {
        Destination newDestination = findById(id);
        newDestination.setStatus(Status.INATIVO);
        return destinationRepository.save(newDestination);
    }

    public Destination fromDTO(DestinationDto destinationDto) {
        return new Destination(destinationDto.getId(), destinationDto.getName(), destinationDto.getState(),
                destinationDto.getCity(), destinationDto.getStatus(), destinationDto.getCreatedBy(),
                destinationDto.getCreatedAt(), destinationDto.getUpdateAt());
    }

    private void updateData(Destination newDestination, Destination destination) {
        newDestination.setName(destination.getName());
        newDestination.setState(destination.getState());
        newDestination.setCity(destination.getCity());
        newDestination.setCreatedBy(destination.getCreatedBy());
        newDestination.setCreatedAt(destination.getCreatedAt());
        newDestination.setUpdateAt(destination.getUpdateAt());
    }

}
