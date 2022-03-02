package com.example.registerofaddress;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
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
@OpenAPIDefinition(info = @Info(
        title ="Register of Address",
        version = "1.0.0",
        description = "When the user registers with a CPF (primary key), name and a CEP" +
        "(primary key of address) and house number, an external API is consulted returning the" +
        "address data for that zip code. This creates a new entity in the database.",
        contact = @Contact(
                name = "Gustavo Santiago Sousa",
                email = "gustavosantiago1018@gmail.com",
                url = "https://github.com/S4nt1ag0"
        )
    )
)
public class RegisterOfAddressApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterOfAddressApplication.class, args);
    }
}
