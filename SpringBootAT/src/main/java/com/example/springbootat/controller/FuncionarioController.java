package com.example.springbootat.controller;

import com.example.springbootat.model.Funcionario;
import com.example.springbootat.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public ResponseEntity<List<Funcionario>> getAllFuncionarios() {
        return ResponseEntity.ok(funcionarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable String id) {
        return funcionarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Funcionario addFuncionario(@RequestBody Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable String id, @RequestBody Funcionario funcionarioDetails) {
        return funcionarioRepository.findById(id)
                .map(existingFuncionario -> {
                    funcionarioDetails.setId(existingFuncionario.getId());
                    Funcionario updatedFuncionario = funcionarioRepository.save(funcionarioDetails);
                    return ResponseEntity.ok(updatedFuncionario);
                })
                .orElseGet(() -> {
                    funcionarioDetails.setId(id);
                    Funcionario newFuncionario = funcionarioRepository.save(funcionarioDetails);
                    return ResponseEntity.status(HttpStatus.CREATED).body(newFuncionario);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable String id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
