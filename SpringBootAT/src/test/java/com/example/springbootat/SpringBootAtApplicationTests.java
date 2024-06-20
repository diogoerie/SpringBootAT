package com.example.springbootat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringBootAtApplicationTests {

    @Autowired
    private MockMvc mockMvc;



    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void whenAdminAccessUsuarioEndpoints_thenOk() throws Exception {
        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/usuarios")
                        .contentType("application/json")
                        .content("{\"nome\":\"Usuario Teste\", \"senha\":\"senhaTeste\", \"papel\":\"USER\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk());

        mockMvc.perform(put("/usuarios/1")
                        .contentType("application/json")
                        .content("{\"nome\":\"Usuario Atualizado\", \"senha\":\"senhaAtualizada\", \"papel\":\"ADMIN\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isOk());
    }
}
