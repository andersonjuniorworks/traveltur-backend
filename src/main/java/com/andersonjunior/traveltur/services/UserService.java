package com.andersonjunior.traveltur.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.traveltur.dtos.UserDto;
import com.andersonjunior.traveltur.enums.Status;
import com.andersonjunior.traveltur.models.User;
import com.andersonjunior.traveltur.repositories.UserRepository;
import com.andersonjunior.traveltur.services.exceptions.ObjectNotFoundException;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    public List<User> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable).getContent();
    }

    public List<User> findByStatus(Status status, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByStatus(status, pageable);
    }

    public List<User> findByEmail(String email, Status status) {
        return userRepository.findByEmailAndStatus(email, status);
    }

    public List<User> findByFullname(String fullname, Status status) {
        return userRepository.findByFullnameContainingIgnoreCaseAndStatus(fullname, status);
    }

    public Long count() {
        Long count = userRepository.count();
        return count;
    }

    @Transactional
    public User insert(User user) {
        user.setId(null);
        return userRepository.save(user);
    }

    @Transactional
    public User update(User user) {
        User newUser = findById(user.getId());
        updateData(newUser, user);
        return userRepository.save(newUser);
    }

    @Transactional
    public User delete(Long id) {
        User newUser = findById(id);
        newUser.setStatus(Status.INATIVO);
        return userRepository.save(newUser);
    }

    public User fromDTO(UserDto userDto) {
        return new User(userDto.getId(), userDto.getFullname(), userDto.getEmail(), null,
                userDto.getProfile(), userDto.getStatus(),
                null, null);
    }

    private void updateData(User newUser, User user) {
        newUser.setFullname(user.getFullname());
        newUser.setEmail(user.getEmail());
        newUser.setProfile(user.getProfile());
        newUser.setStatus(user.getStatus());
    }

}
