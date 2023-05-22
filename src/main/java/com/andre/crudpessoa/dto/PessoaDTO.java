package com.andre.crudpessoa.dto;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record PessoaDTO(
    @JsonProperty("_id") 
    Long id, 

    @NotBlank 
    @NotNull 
    @Length(min = 2, max = 100) 
    String name, 

    @NotBlank
    @NotNull
    @Length(min = 11, max = 11)
    @CPF(message = "CPF inválido.")
    String cpf, 
    
    @NotNull
    @Past
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataNascimento,
    
    List<ContatoDTO> contatos) {
}
