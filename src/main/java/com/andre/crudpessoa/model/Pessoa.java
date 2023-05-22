package com.andre.crudpessoa.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@SQLDelete(sql = "UPDATE Pessoa SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 2, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotBlank
    @NotNull
    @Length(min = 11, max = 11)
    @Column(length = 11, nullable = false)
    private String cpf;

    @NotNull
    @Past
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(length = 200, nullable = false)
    private LocalDate dataNascimento;

    @NotNull
    @Length(max = 7)
    @Pattern(regexp = "Ativo|Inativo")
    @Column(length = 100, nullable = false)
    private String status = "Ativo";

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "pessoa")
    private List<Contato> contatos = new ArrayList<>();

}
