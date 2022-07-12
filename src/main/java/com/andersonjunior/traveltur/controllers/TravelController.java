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

import com.andersonjunior.traveltur.dtos.TravelDto;
import com.andersonjunior.traveltur.models.Travel;
import com.andersonjunior.traveltur.services.TravelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Viagens", description = "Operações pertecentes as viagens")
@RequestMapping(value = "/api/travels")
public class TravelController {

    private final TravelService travelService;

    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }

    @ApiOperation(value = "Retorna uma viagem através do ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<TravelDto> findById(@PathVariable Long id) {
        Travel travel = travelService.findById(id);
        TravelDto travelDto = new TravelDto(travel);
        return ResponseEntity.ok().body(travelDto);
    }

    @ApiOperation(value = "Retorna todas as viagens")
    @GetMapping
    public ResponseEntity<List<TravelDto>> findAll(
            @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = true, defaultValue = "10") Integer size) {
        List<Travel> travels = travelService.findAll(page, size);
        List<TravelDto> travelsDto = travels.stream().map(travel -> new TravelDto(travel))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(travelsDto);
    }

    @ApiOperation(value = "Adiciona uma nova viagem")
    @PostMapping
    public ResponseEntity<String> insert(@Valid @RequestBody TravelDto TravelDto) {
        Travel travel = travelService.fromDTO(TravelDto);
        travel = travelService.insert(travel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(travel.getId()).toUri();
        return ResponseEntity.created(uri).body("Viagem cadastrada com sucesso!");
    }

    @ApiOperation(value = "Edita a viagem selecionada")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody TravelDto TravelDto, @PathVariable Long id) {
        Travel travel = travelService.fromDTO(TravelDto);
        travelService.update(travel);
        return ResponseEntity.ok().body("Viagem atualizada com sucesso!");
    }

    @ApiOperation(value = "Exclui a viagem selecionada")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id) {
        travelService.delete(id);
        return ResponseEntity.ok().body("Viagem excluída com sucesso!");
    }

}
