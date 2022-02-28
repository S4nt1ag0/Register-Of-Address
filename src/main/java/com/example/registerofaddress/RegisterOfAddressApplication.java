package com.example.registerofaddress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Projeto Spring Boot.
 * Os seguintes módulos foram selecionados:
 * - Spring Data JPA
 * - Spring Web
 * - H2 Database
 * - OpenFeign
 *
 * @author S4nt1ag0
 */

@EnableFeignClients
@SpringBootApplication
public class RegisterOfAddressApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterOfAddressApplication.class, args);
    }
/*
    TODO::
    IMPLEMENTS EXCEPTIONS ok
    ADD UNIT TESTS AND INTEGRATION TESTS (MAYBE ONLY INTEGRATION TETSES ARE POSSIBLE)
    CHANGE DATABASE H2 to POSTGRELSS
    IMPROVE DOCUMENTATION (maybe rewatch video of Swagger)
*/
}
