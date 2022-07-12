package com.andersonjunior.traveltur.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andersonjunior.traveltur.dtos.VehicleDto;
import com.andersonjunior.traveltur.enums.Status;
import com.andersonjunior.traveltur.models.Vehicle;
import com.andersonjunior.traveltur.services.VehicleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Veículos", description = "Operações pertecentes aos veículos")
@RequestMapping(value = "/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @ApiOperation(value = "Retorna um veículo através do ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<VehicleDto> findById(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.findById(id);
        VehicleDto vehicleDto = new VehicleDto(vehicle);
        return ResponseEntity.ok().body(vehicleDto);
    }

    @ApiOperation(value = "Retorna todos os veículos")
    @GetMapping
    public ResponseEntity<List<VehicleDto>> findAll(
            @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = true, defaultValue = "10") Integer size) {
        List<Vehicle> vehicles = vehicleService.findAll(page, size);
        List<VehicleDto> vehiclesDto = vehicles.stream().map(vehicle -> new VehicleDto(vehicle))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(vehiclesDto);
    }

    @ApiOperation(value = "Retorna todos os veículos através do status")
    @GetMapping(value = "/status")
    public ResponseEntity<List<VehicleDto>> findByStatus(
            @RequestParam(name = "status", required = true, defaultValue = "ATIVO") Status status,
            @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = true, defaultValue = "10") Integer size) {
        List<Vehicle> vehicles = vehicleService.findByStatus(status, page, size);
        List<VehicleDto> vehiclesDto = vehicles.stream().map(vehicle -> new VehicleDto(vehicle))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(vehiclesDto);
    }

    @ApiOperation(value = "Retorna um veículo através da placa")
    @GetMapping(value = "/name")
    public ResponseEntity<List<VehicleDto>> findByLicensePlate(
            @RequestParam(name = "licensePlate", required = true) String licensePlate,
            @RequestParam(name = "status", required = true, defaultValue = "ATIVO") Status status) {
        List<Vehicle> vehicles = vehicleService.findByLicensePlate(licensePlate, status);
        List<VehicleDto> vehiclesDtos = vehicles.stream().map(vehicle -> new VehicleDto(vehicle))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(vehiclesDtos);
    }

    @ApiOperation(value = "Adiciona um novo veículo")
    @PostMapping
    public ResponseEntity<String> insert(@Valid @RequestBody VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleService.fromDTO(vehicleDto);
        vehicle = vehicleService.insert(vehicle);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(vehicle.getId()).toUri();
        return ResponseEntity.created(uri).body("Veículo cadastrado com sucesso!");
    }

    @ApiOperation(value = "Edita o veículo selecionado")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody VehicleDto vehicleDto, @PathVariable Long id) {
        Vehicle vehicle = vehicleService.fromDTO(vehicleDto);
        vehicleService.update(vehicle);
        return ResponseEntity.ok().body("Veículo atualizado com sucesso!");
    }

    @ApiOperation(value = "Exclui o veículo selecionado")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id) {
        vehicleService.delete(id);
        return ResponseEntity.ok().body("Veículo excluído com sucesso!");
    }

}
