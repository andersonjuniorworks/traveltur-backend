package com.andersonjunior.traveltur.repositories;

import com.andersonjunior.traveltur.enums.Status;
import com.andersonjunior.traveltur.models.User;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByStatus(Status status, Pageable pageable);

    List<User> findByEmailAndStatus(String email, Status status);

    List<User> findByFullnameContainingIgnoreCaseAndStatus(String fullname, Status status);

}
