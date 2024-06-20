package com.example.springbootat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "usuarios")
@Data
public class Usuario {

    @Id
    private String id;
    private String nome;
    private String senha;
    private String papel;

    public Usuario() {
    }

    public Usuario(String nome, String senha, String papel) {
        this.nome = nome;
        this.senha = senha;
        this.papel = papel;
    }
}
