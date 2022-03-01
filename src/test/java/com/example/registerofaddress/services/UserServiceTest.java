package com.example.registerofaddress.services;

import com.example.registerofaddress.entities.Endereco;
import com.example.registerofaddress.entities.User;
import com.example.registerofaddress.repositories.EnderecoRepository;
import com.example.registerofaddress.repositories.UserRepository;
import com.example.registerofaddress.services.exceptions.ResourceNotFoundException;
import com.example.registerofaddress.services.impl.UserServiceImp;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    Endereco endereco;

    User user;

    @BeforeEach
    void setUp() throws Exception
    {
        endereco = new Endereco();

        endereco.setCep("01001-000");
        endereco.setNumeroCasa(39);

        user = new User();
        user.setName("Sant");
        user.setCPF(458447L);
        user.setEndereco(endereco);
    }

    @Test
    @Order(1)
    void createUsers(){
        User user = userService.insert(this.user);
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Praça da Sé",user.getEndereco().getLogradouro());
    }

    @Test
    @Order(2)
    void listOfUsers(){
        List<User> lists = userService.findAll();
        Assertions.assertNotNull(lists);
        Assertions.assertEquals("01001-000",lists.get(0).getEndereco().getCep());
    }

    @Test
    @Order(3)
    void getUser(){
        User user = userService.findById(458447L);
        Assertions.assertNotNull(user);
        Assertions.assertEquals("01001-000",user.getEndereco().getCep());
    }


    @Test
    @Order(4)
    void updateUser(){
        User user = userService.findById(458447L);
        user.getEndereco().setCep("40015-970");
        user = userService.update(458447L,user);
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Praça da Inglaterra",user.getEndereco().getLogradouro());
    }

    @Test
    @Order(5)
    void deleteUser(){
        userService.deleteById(458447L);
        try {
            User user = userService.findById(458447L);
            Assertions.assertNull(user);
        }catch (ResourceNotFoundException e){
            Assertions.assertEquals(e.getClass(),ResourceNotFoundException.class);
        }

    }

}
