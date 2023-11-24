package com.blooging.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blooging.entities.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer>  {

    Optional<User> findByEmail(String email);

}
