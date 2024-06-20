package com.example.springbootat.database;

import com.example.springbootat.model.Departamento;
import com.example.springbootat.model.Funcionario;
import com.example.springbootat.model.Usuario;
import com.example.springbootat.repository.DepartamentoRepository;
import com.example.springbootat.repository.FuncionarioRepository;
import com.example.springbootat.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;


@SpringBootApplication
@Component
public class Database implements CommandLineRunner {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public static void main(String[] args) {
        SpringApplication.run(Database.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Departamento dept = new Departamento();
        dept.setNome("TI");
        dept.setLocal("Belo Horizonte");
        departamentoRepository.save(dept);

        Departamento dept1 = new Departamento();
        dept1.setNome("Recursos Humanos");
        dept1.setLocal("Rio de Janeiro");
        departamentoRepository.save(dept1);

        Departamento dept2 = new Departamento();
        dept2.setNome("Expedição");
        dept2.setLocal("Bahia");
        departamentoRepository.save(dept2);

        Funcionario func = new Funcionario();
        func.setNome("Maria da Silva");
        func.setEndereco("Rua despedidos, 7123");
        func.setTelefone("3134354889");
        func.setEmail("maria.silva@gmail.com");
        func.setDataNascimento(new Date());
        func.setDepartamento(dept);
        funcionarioRepository.save(func);

        Funcionario func1 = new Funcionario();
        func1.setNome("João Marcelo");
        func1.setEndereco("Rua despedidos, 1515");
        func1.setTelefone("245245245");
        func1.setEmail("joao.marcelo@gmail.com");
        func1.setDataNascimento(new Date());
        func1.setDepartamento(dept1);
        funcionarioRepository.save(func1);

        Funcionario func2 = new Funcionario();
        func2.setNome("Maraia de Maraisa");
        func2.setEndereco("Rua dos Aprovados, 1818");
        func2.setTelefone("05454325454");
        func2.setEmail("Maraia.Maraisa@gmail.com");
        func2.setDataNascimento(new Date());
        func2.setDepartamento(dept2);
        funcionarioRepository.save(func2);
    }


}