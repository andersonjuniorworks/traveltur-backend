package com.andersonjunior.traveltur.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.andersonjunior.traveltur.dtos.UserDto;
import com.andersonjunior.traveltur.enums.Status;
import com.andersonjunior.traveltur.models.User;
import com.andersonjunior.traveltur.services.UserService;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Usuário", description = "Operações pertecentes aos usuários")
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Retorna um usuário através do ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        UserDto userDto = new UserDto(user);
        return ResponseEntity.ok().body(userDto);
    }

    @ApiOperation(value = "Retorna todos os usuários")
    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(
            @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = true, defaultValue = "10") Integer size) {
        List<User> users = userService.findAll(page, size);
        List<UserDto> usersDto = users.stream().map(user -> new UserDto(user)).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDto);
    }

    @ApiOperation(value = "Retorna todos os usuários através do status")
    @GetMapping(value = "/status")
    public ResponseEntity<List<UserDto>> findByStatus(
            @RequestParam(name = "status", required = true, defaultValue = "ATIVO") Status status,
            @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = true, defaultValue = "10") Integer size) {
        List<User> users = userService.findByStatus(status, page, size);
        List<UserDto> usersDto = users.stream().map(user -> new UserDto(user)).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDto);
    }

    @ApiOperation(value = "Retorna um usuário através do email")
    @GetMapping(value = "/email")
    public ResponseEntity<List<UserDto>> findByEmail(
            @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "status", required = true, defaultValue = "ATIVO") Status status) {
        List<User> users = userService.findByEmail(email, status);
        List<UserDto> usersDto = users.stream().map(user -> new UserDto(user)).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDto);
    }

    @ApiOperation(value = "Retorna uma lista de usuários através do nome")
    @GetMapping(value = "/name")
    public ResponseEntity<List<UserDto>> findByFullname(
            @RequestParam(name = "fullname", required = true) String fullname,
            @RequestParam(name = "status", required = true, defaultValue = "ATIVO") Status status) {
        List<User> users = userService.findByFullname(fullname, status);
        List<UserDto> usersDto = users.stream().map(user -> new UserDto(user)).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDto);
    }

    @ApiOperation(value = "Adiciona um novo usuário")
    @PostMapping
    public ResponseEntity<String> insert(@Valid @RequestBody User user) {
        user = userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body("Usuário criado com sucesso!");
    }

    @ApiOperation(value = "Edita o usuário selecionado")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody UserDto userDto, @PathVariable Long id) {
        User user = userService.fromDTO(userDto);
        userService.update(user);
        return ResponseEntity.ok().body("Usuário atualizado com sucesso!");
    }

    @ApiOperation(value = "Exclui o usuário selecionado")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().body("Usuário excluído com sucesso!");
    }

}
