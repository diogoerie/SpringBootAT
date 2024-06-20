package com.example.springbootat.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "funcionarios")
@Data
public class Funcionario {

    @Id
    private String id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private Date dataNascimento;

    @DBRef
    private Departamento departamento;

    public Funcionario() {
    }

    public Funcionario(String nome, String endereco, String telefone, String email, Date dataNascimento, Departamento departamento) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.departamento = departamento;
    }

    public Funcionario(String nome, String endereco, String telefone, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }
}
