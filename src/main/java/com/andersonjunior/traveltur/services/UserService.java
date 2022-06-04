package com.andersonjunior.traveltur.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.traveltur.models.User;
import com.andersonjunior.traveltur.repositories.UserRepository;
import com.andersonjunior.traveltur.services.exceptions.DataIntegrityException;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable).getContent();
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new com.andersonjunior.traveltur.services.exceptions.ObjectNotFoundException(
                "Registro não encontrado na base de dados"));
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
    public void delete(Long id) {
        findById(id);
        if (id != 1) {
            try {
                userRepository.deleteById(id);
            } catch (DataIntegrityException e) {
                throw new DataIntegrityException("Não é possível excluir usuário com chamados vinculados!");
            }
        } else {
            throw new DataIntegrityException("Não é possível excluir o usuário administrador!");
        }
    }

    private void updateData(User newUser, User user) {
        newUser.setFullname(user.getFullname());
        newUser.setEmail(user.getEmail());
        newUser.setProfile(user.getProfile());
    }

}
