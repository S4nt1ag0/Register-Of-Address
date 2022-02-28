package com.example.registerofaddress.repositories;

import com.example.registerofaddress.entities.User;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface UserRepository extends JpaRepository<User,Long> {}
