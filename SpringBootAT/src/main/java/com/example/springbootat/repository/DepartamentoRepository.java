package com.example.springbootat.repository;

import com.example.springbootat.model.Departamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends MongoRepository<Departamento, String> {
}
