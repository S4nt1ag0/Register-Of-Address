package com.example.registerofaddress.services.impl;

import com.example.registerofaddress.entities.Endereco;
import com.example.registerofaddress.entities.User;
import com.example.registerofaddress.repositories.EnderecoRepository;
import com.example.registerofaddress.repositories.UserRepository;
import com.example.registerofaddress.services.EnderecoService;
import com.example.registerofaddress.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id){
        Optional<User> obj = userRepository.findById(id);
        return obj.get();
    }

    @Override
    public User insert(User user){
        return saveUser(user);
    }

    @Override
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public User update(Long id, User user){
        Optional<User> entity = userRepository.findById(id);
        if(entity.isPresent()){
            User userdb = entity.get();
            userdb.setName(user.getName());
            return userRepository.save(userdb);
        }
        return null;
    }

    private User saveUser(User user){
        String cep = user.getEndereco().getCep();
        int number = user.getEndereco().getNumeroCasa();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(()->{
            Endereco novo = enderecoService.consultarCep(cep);
            novo.setNumeroCasa(number);
            enderecoRepository.save(novo);
            return novo;
        });
        user.setEndereco(endereco);
        userRepository.save(user);
        return user;
    }
}
