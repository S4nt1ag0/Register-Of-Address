package com.example.registerofaddress.repositories;

import com.example.registerofaddress.entities.Endereco;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface EnderecoRepository extends JpaRepository<Endereco,String> {}
