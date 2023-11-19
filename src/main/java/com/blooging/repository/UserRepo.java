package com.blooging.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blooging.entities.User;

public interface UserRepo extends JpaRepository<User,Integer>  {
}
