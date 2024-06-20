package com.example.springbootat.controller;

import com.example.springbootat.model.Departamento;
import com.example.springbootat.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping
    public ResponseEntity<List<Departamento>> getAllDepartamentos() {
        return ResponseEntity.ok(departamentoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> getDepartamentoById(@PathVariable String id) {
        return departamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Departamento addDepartamento(@RequestBody Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> updateDepartamento(@PathVariable String id, @RequestBody Departamento departamentoDetails) {
        return departamentoRepository.findById(id)
                .map(existingDepartamento -> {
                    departamentoDetails.setId(existingDepartamento.getId());
                    Departamento updatedDepartamento = departamentoRepository.save(departamentoDetails);
                    return ResponseEntity.ok(updatedDepartamento);
                })
                .orElseGet(() -> {
                    departamentoDetails.setId(id);
                    Departamento newDepartamento = departamentoRepository.save(departamentoDetails);
                    return ResponseEntity.status(HttpStatus.CREATED).body(newDepartamento);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartamento(@PathVariable String id) {
        if (departamentoRepository.existsById(id)) {
            departamentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
