package com.example.registerofaddress.repositories;

import com.example.registerofaddress.entities.Endereco;
import com.example.registerofaddress.entities.User;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
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

    @Autowired
    EnderecoRepository enderecoRepository;

    static boolean recordsCreated = false;

    @BeforeEach
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
        user1.setCPF("45847");
        user1.setName("Thai");
        endereco.setCep("40015970");
        endereco.setNumeroCasa(39);
        user1.setEndereco(endereco);

        User user2 = new User();
        user2.setCPF("845472");
        user2.setName("Sant");
        user2.setEndereco(endereco);

        enderecoRepository.save(endereco);

        userRepository.save(user1);
        userRepository.save(user2);
        recordsCreated = true;
    }
    @Test
    @Order(1)
    void findUserById()
    {
        String CPF = "845472";
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
        String CPF = "45847";
        Optional<User> opUser = userRepository.findById(CPF);
        User user = opUser.get();
        user.setName("John");
        userRepository.save(user);
        Optional<User> opUser2 = userRepository.findById(CPF);
        User user2 = opUser2.get();
        assertNotNull(user2);
        assertEquals(user2.getName(),"John");
    }

    @Test
    @Order(4)
    void deleteUserById()
    {
        String CPF = "45847";
        userRepository.deleteById(CPF);
        Optional<User> opUser = userRepository.findById(CPF);
        User user = opUser.orElse(null);
        Assertions.assertNull(user);
    }

}
