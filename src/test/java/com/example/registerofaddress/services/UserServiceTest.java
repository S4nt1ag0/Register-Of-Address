package com.example.registerofaddress.services;

import com.example.registerofaddress.entities.Endereco;
import com.example.registerofaddress.entities.User;
import com.example.registerofaddress.repositories.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    Endereco endereco = new Endereco();

    User user = new User();

}
