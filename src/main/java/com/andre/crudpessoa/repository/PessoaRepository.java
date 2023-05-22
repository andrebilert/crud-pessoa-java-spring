package com.andre.crudpessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andre.crudpessoa.model.Pessoa;

public interface PessoaRepository extends JpaRepository <Pessoa, Long>{
    
}
