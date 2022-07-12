package com.andersonjunior.traveltur.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.traveltur.dtos.VehicleDto;
import com.andersonjunior.traveltur.enums.Status;
import com.andersonjunior.traveltur.models.Vehicle;
import com.andersonjunior.traveltur.repositories.VehicleRepository;
import com.andersonjunior.traveltur.services.exceptions.ObjectNotFoundException;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return vehicleRepository.findAll(pageable).getContent();
    }

    public Vehicle findById(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    public List<Vehicle> findByStatus(Status status, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return vehicleRepository.findByStatus(status, pageable);
    }

    public List<Vehicle> findByLicensePlate(String licensePlate, Status status) {
        return vehicleRepository.findByLicensePlateAndStatus(licensePlate, status);
    }

    public Long count() {
        Long count = vehicleRepository.count();
        return count;
    }

    @Transactional
    public Vehicle insert(Vehicle vehicle) {
        vehicle.setId(null);
        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public Vehicle update(Vehicle vehicle) {
        Vehicle newVehicle = findById(vehicle.getId());
        updateData(newVehicle, vehicle);
        return vehicleRepository.save(newVehicle);
    }

    @Transactional
    public Vehicle delete(Long id) {
        Vehicle newVehicle = findById(id);
        newVehicle.setStatus(Status.INATIVO);
        return vehicleRepository.save(newVehicle);
    }

    public Vehicle fromDTO(VehicleDto vehicleDto) {
        return new Vehicle(vehicleDto.getId(), vehicleDto.getType(), vehicleDto.getLicensePlate(),
                vehicleDto.getDescription(), vehicleDto.getBrand(), vehicleDto.getModel(), vehicleDto.getYear(),
                vehicleDto.getStatus(),
                vehicleDto.getCreatedBy(), vehicleDto.getCreatedAt(), vehicleDto.getUpdatedAt());
    }

    private void updateData(Vehicle newVehicle, Vehicle vehicle) {
        newVehicle.setType(vehicle.getType());
        newVehicle.setDescription(vehicle.getDescription());
        newVehicle.setModel(vehicle.getModel());
        newVehicle.setBrand(vehicle.getBrand());
        newVehicle.setYear(vehicle.getYear());
        newVehicle.setCreatedBy(vehicle.getCreatedBy());
        newVehicle.setCreatedAt(vehicle.getCreatedAt());
        newVehicle.setUpdateAt(vehicle.getUpdateAt());
    }

}
