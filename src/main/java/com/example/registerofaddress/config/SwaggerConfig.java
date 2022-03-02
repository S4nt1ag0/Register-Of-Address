package com.example.registerofaddress.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {

        Contact contact = new Contact("Gustavo Santiago Sousa",
                "https://github.com/S4nt1ag0",
                "gustavosantiago1018@gmail.com");

        private ApiInfo apiInfo = new ApiInfo("Register of Address",
                "When the user registers with a CPF (primary key), name and a CEP code " +
                "(primary key of address) and house number, an external API is consulted returning the" +
                " address data for that zip code. This creates a new entity in the database.",
                "1.0.0","",contact,"","",new ArrayList<>());


        @Bean
        public Docket apiDocket(){
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.example.registerofaddress"))
                    .paths(PathSelectors.any())
                    .build();
        }
}
