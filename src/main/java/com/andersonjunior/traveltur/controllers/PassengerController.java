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

import com.andersonjunior.traveltur.dtos.PassengerDto;
import com.andersonjunior.traveltur.enums.Status;
import com.andersonjunior.traveltur.models.Passenger;
import com.andersonjunior.traveltur.services.PassengerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Passageiros", description = "Operações pertecentes aos passageiros")
@RequestMapping(value = "/api/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @ApiOperation(value = "Retorna um passageiro através do ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<PassengerDto> findById(@PathVariable Long id) {
        Passenger passenger = passengerService.findById(id);
        PassengerDto passengerDto = new PassengerDto(passenger);
        return ResponseEntity.ok().body(passengerDto);
    }

    @ApiOperation(value = "Retorna todos os passageiros")
    @GetMapping
    public ResponseEntity<List<PassengerDto>> findAll(
            @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = true, defaultValue = "10") Integer size) {
        List<Passenger> passengers = passengerService.findAll(page, size);
        List<PassengerDto> passengersDto = passengers.stream().map(passenger -> new PassengerDto(passenger))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(passengersDto);
    }

    @ApiOperation(value = "Retorna todos os passageiros através do status")
    @GetMapping(value = "/status")
    public ResponseEntity<List<PassengerDto>> findByStatus(
            @RequestParam(name = "status", required = true, defaultValue = "ATIVO") Status status,
            @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = true, defaultValue = "10") Integer size) {
        List<Passenger> passengers = passengerService.findByStatus(status, page, size);
        List<PassengerDto> passengersDto = passengers.stream().map(passenger -> new PassengerDto(passenger))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(passengersDto);
    }

    @ApiOperation(value = "Retorna uma lista de passageiros através do número do documento")
    @GetMapping(value = "/document")
    public ResponseEntity<List<PassengerDto>> findByDocument(
            @RequestParam(name = "document", required = true) String document,
            @RequestParam(name = "status", required = true, defaultValue = "ATIVO") Status status) {
        List<Passenger> passengers = passengerService.findByDocument(document, status);
        List<PassengerDto> passengersDto = passengers.stream().map(passenger -> new PassengerDto(passenger))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(passengersDto);
    }

    @ApiOperation(value = "Retorna uma lista de passageiros através do nome")
    @GetMapping(value = "/name")
    public ResponseEntity<List<PassengerDto>> findByFullname(
            @RequestParam(name = "fullname", required = true) String fullname,
            @RequestParam(name = "status", required = true, defaultValue = "ATIVO") Status status) {
        List<Passenger> passengers = passengerService.findByFullname(fullname, status);
        List<PassengerDto> passengersDto = passengers.stream().map(passenger -> new PassengerDto(passenger))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(passengersDto);
    }

    @ApiOperation(value = "Adiciona um novo passageiro")
    @PostMapping
    public ResponseEntity<String> insert(@Valid @RequestBody PassengerDto passengerDto) {
        Passenger passenger = passengerService.fromDTO(passengerDto);
        passenger = passengerService.insert(passenger);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(passenger.getId()).toUri();
        return ResponseEntity.created(uri).body("Passageiro cadastrado com sucesso!");
    }

    @ApiOperation(value = "Edita o usuário selecionado")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody PassengerDto passengerDto, @PathVariable Long id) {
        Passenger passenger = passengerService.fromDTO(passengerDto);
        passengerService.update(passenger);
        return ResponseEntity.ok().body("Passageiro atualizado com sucesso!");
    }

    @ApiOperation(value = "Exclui o usuário selecionado")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id) {
        passengerService.delete(id);
        return ResponseEntity.ok().body("Passageiro excluído com sucesso!");
    }

}
