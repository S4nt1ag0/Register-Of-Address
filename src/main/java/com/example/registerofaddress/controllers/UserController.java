package com.example.registerofaddress.controllers;

import com.example.registerofaddress.entities.User;
import com.example.registerofaddress.services.UserService;
import com.example.registerofaddress.services.exceptions.DatabaseException;
import com.example.registerofaddress.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API do ViaCEP) em uma
 * interface simples e coesa (API REST).
 *
 * @author S4nt1ag0
 */

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{CPF}")
    public ResponseEntity<User> findbyId(@PathVariable String CPF){
        User user = userService.findById(CPF);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping()
    public ResponseEntity<User> insert(@RequestBody User user){
        user = userService.insert(user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/{CPF}")
    public ResponseEntity<Void> deleteById(@PathVariable String CPF){
        try {
            userService.deleteById(CPF);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(CPF);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{CPF}")
    public ResponseEntity<User> update(@PathVariable String CPF, @RequestBody User user){
        try{
        user = userService.update(CPF,user);
        return ResponseEntity.ok().body(user);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(CPF);
        }
    }
}
