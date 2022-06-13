package com.andersonjunior.traveltur.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andersonjunior.traveltur.dtos.DestinationDto;
import com.andersonjunior.traveltur.models.Destination;
import com.andersonjunior.traveltur.services.DestinationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Destino", description = "Operações pertecentes aos destinos")
@RequestMapping(value = "/api/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @ApiOperation(value = "Retorna um destino através do ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<DestinationDto> findById(@PathVariable Long id) {
        Destination destination = destinationService.findById(id);
        DestinationDto destinationDto = new DestinationDto(destination);
        return ResponseEntity.ok().body(destinationDto);
    }

    @ApiOperation(value = "Retorna todos os destinos")
    @GetMapping
    public ResponseEntity<List<DestinationDto>> findAll(
            @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = true, defaultValue = "10") Integer size) {
        List<Destination> destinations = destinationService.findAll(page, size);
        List<DestinationDto> destinationsDto = destinations.stream().map(destination -> new DestinationDto(destination))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(destinationsDto);
    }

    @ApiOperation(value = "Retorna uma lista de destinos através do nome")
    @GetMapping(value = "/name")
    public ResponseEntity<List<DestinationDto>> findByName(
            @RequestParam(name = "fullname", required = true) String name) {
        List<Destination> destinations = destinationService.findByName(name);
        List<DestinationDto> destinationsDto = destinations.stream().map(destination -> new DestinationDto(destination))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(destinationsDto);
    }

    @ApiOperation(value = "Adiciona um novo destino")
    @PostMapping
    public ResponseEntity<String> insert(@Valid @RequestBody DestinationDto destinationDto) {
        Destination destination = destinationService.fromDTO(destinationDto);
        destination = destinationService.insert(destination);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(destination.getId()).toUri();
        return ResponseEntity.created(uri).body("Destino cadastrado com sucesso!");
    }

    @ApiOperation(value = "Edita o destino selecionado")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody DestinationDto destinationDto, @PathVariable Long id) {
        Destination destination = destinationService.fromDTO(destinationDto);
        destinationService.update(destination);
        return ResponseEntity.ok().body("Destino atualizado com sucesso!");
    }

}
