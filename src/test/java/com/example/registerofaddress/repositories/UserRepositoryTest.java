package com.example.registerofaddress.repositories;

import com.example.registerofaddress.entities.Endereco;
import com.example.registerofaddress.entities.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    static boolean recordsCreated = false;

    @BeforeTestClass
    public void configure() throws Exception
    {
        if (!recordsCreated)
        {
            createRecords();
        }
    }

    private void createRecords()
    {
        User user1 = new User();
        Endereco endereco = new Endereco();
        user1.setCPF(45847L);
        user1.setName("Thai");
        endereco.setCep("40015970");
        endereco.setNumeroCasa(39);
        user1.setEndereco(endereco);

        User user2 = new User();
        user2.setCPF(845472L);
        user2.setName("Sant");
        user2.setEndereco(endereco);

        userRepository.save(user1);
        userRepository.save(user2);
        recordsCreated = true;
    }
    @Test
    @Order(1)
    void findUserById()
    {
        Long CPF = 845472L;
        Optional<User> opUser = userRepository.findById(CPF);
        User user = opUser.get();
        assertNotNull(user);
        assertEquals(user.getName(),"Sant");
    }

    @Test
    @Order(2)
    void listUsers()
    {
        List<User> list = userRepository.findAll();
        assertNotNull(list);
        assertEquals(list.get(0).getName(), "Thai");
        assertEquals(list.get(1).getName(), "Sant");
    }

    @Test
    @Order(3)
    void updateUserById()
    {
        Long CPF = 458447L;
        Optional<User> opUser = userRepository.findById(CPF);
        User user = opUser.get();
        user.setName("John");
        userRepository.save(user);
        Optional<User> opUser2 = userRepository.findById(CPF);
        User user2 = opUser.get();
        Assertions.assertNull(user2);
        assertEquals(user.getName(),"John");
    }

    @Test
    @Order(4)
    void deleteUserById()
    {
        Long CPF = 458447L;
        userRepository.deleteById(CPF);
        Optional<User> opUser = userRepository.findById(CPF);
        User user = opUser.get();
        Assertions.assertNull(user);
    }

}
