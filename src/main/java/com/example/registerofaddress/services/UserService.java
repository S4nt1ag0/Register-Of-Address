package com.example.registerofaddress.services;
import com.example.registerofaddress.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);


    User insert(User user);

    void deleteById(Long id);

    User update(Long id, User user);
}
