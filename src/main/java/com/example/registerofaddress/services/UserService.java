package com.example.registerofaddress.services;
import com.example.registerofaddress.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(String CPF);


    User insert(User user);

    void deleteById(String CPF);

    User update(String CPF, User user);
}
