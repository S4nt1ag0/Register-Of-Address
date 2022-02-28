package com.example.registerofaddress;

import com.example.registerofaddress.entities.Endereco;
import com.example.registerofaddress.entities.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest
@ExtendWith(SpringExtension.class)
public class TestIntController {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testInt() throws Exception{
        this.mvc.perform(MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"name\": \"Sant\",\n" +
                        "  \"endereco\": {\n" +
                        "    \"cep\": \"01001-000\",\n" +
                        "    \"numeroCasa\": 39\n" +
                        "  },\n" +
                        "  \"cpf\": 4455874\n" +
                        "}")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.logradouro").value("Praça da Sé"));

    }

}
