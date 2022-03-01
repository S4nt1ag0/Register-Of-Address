package com.example.registerofaddress.resources;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
@ExtendWith(SpringExtension.class)
public class UserResourceTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testCreation() throws Exception{
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

    @Test
    public void testList() throws Exception{
        this.mvc.perform(MockMvcRequestBuilders
                        .get("/users")
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.logradouro").value("Praça da Sé"));

    }

    @Test
    public void testFindUserByID() throws Exception{
        this.mvc.perform(MockMvcRequestBuilders
                        .get("/users")
                        .param("id","4455874")
                ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.logradouro").value("Praça da Sé"));

    }
}
