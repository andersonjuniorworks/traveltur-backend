package com.andersonjunior.traveltur.repositories;

import com.andersonjunior.traveltur.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
