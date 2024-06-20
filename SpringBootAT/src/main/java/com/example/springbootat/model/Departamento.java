package com.example.springbootat.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Document(collection = "departamentos")
@Data
public class Departamento {

    @Id
    private String id;
    private String nome;
    private String local;

    @DBRef(lazy = true)
    private List<Funcionario> funcionarios;

    public Departamento() {
    }

    public Departamento(String nome, String local) {
        this.nome = nome;
        this.local = local;
    }
}
