package com.example.registerofaddress.services;

import com.example.registerofaddress.entities.Endereco;
import com.example.registerofaddress.entities.User;
import com.example.registerofaddress.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    Endereco endereco;

    User user;

    @BeforeEach
    void setUp() throws Exception
    {
        MockitoAnnotations.openMocks(this);
        endereco = new Endereco();

        endereco.setCep("01001-000");
        endereco.setNumeroCasa(39);

        user = new User();
        user.setName("Sant");
        user.setCPF(458447L);
        user.setEndereco(endereco);
    }

    @Test
    void createUsers(){
        User user = userService.insert(this.user);
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Praça da Sé",user.getEndereco().getLogradouro());
    }

    @Test
    void listOfUsers(){
        List<User> lists = userService.findAll();
        Assertions.assertNotNull(lists);
        Assertions.assertEquals("01001-000",lists.get(0).getEndereco().getCep());
    }

}
