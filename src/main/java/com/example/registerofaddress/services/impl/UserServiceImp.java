package com.example.registerofaddress.services.impl;

import com.example.registerofaddress.entities.Endereco;
import com.example.registerofaddress.entities.User;
import com.example.registerofaddress.repositories.EnderecoRepository;
import com.example.registerofaddress.repositories.UserRepository;
import com.example.registerofaddress.services.EnderecoService;
import com.example.registerofaddress.services.UserService;
import com.example.registerofaddress.services.exceptions.ResourceNotFoundException;
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
    public User findById(String CPF){
        Optional<User> obj = userRepository.findById(CPF);
        return obj.orElseThrow(() -> new ResourceNotFoundException(CPF));
    }

    @Override
    public User insert(User user){
        return saveUser(user);
    }

    @Override
    public void deleteById(String CPF){
        userRepository.deleteById(CPF);
    }

    @Override
    public User update(String CPF, User user){
        User entity = findById(CPF);
        entity.setName(user.getName());
        entity.getEndereco().setCep(user.getEndereco().getCep());
        entity.getEndereco().setNumeroCasa(user.getEndereco().getNumeroCasa());
        User updated = this.saveUser(entity);
        return updated;

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
