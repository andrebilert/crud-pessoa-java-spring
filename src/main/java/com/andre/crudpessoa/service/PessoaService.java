package com.andre.crudpessoa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.andre.crudpessoa.dto.PessoaDTO;
import com.andre.crudpessoa.dto.mapper.PessoaMapper;
import com.andre.crudpessoa.exception.RecordNotFoundException;
import com.andre.crudpessoa.repository.PessoaRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Validated
@Service
public class PessoaService {
    
    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;

    public PessoaService(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
    }

    public List<PessoaDTO> list() {
        return pessoaRepository.findAll()
        .stream()
        .map(pessoaMapper::toDTO)
        .collect(Collectors.toList());
    }

    public PessoaDTO findById(@PathVariable @NotNull @Positive Long id) {
        return pessoaRepository.findById(id)
        .map(pessoaMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public PessoaDTO create(@Valid @NotNull PessoaDTO pessoa) {
    return pessoaMapper.toDTO(pessoaRepository.save(pessoaMapper.toEntity(pessoa)));
    }

    public PessoaDTO update(@NotNull @Positive Long id, @Valid @NotNull PessoaDTO pessoa){
        return pessoaRepository.findById(id)
            .map(recordFound -> {
                recordFound.setCpf(pessoa.cpf());
                recordFound.setName(pessoa.name());
                recordFound.setDataNascimento(pessoa.dataNascimento());
                return pessoaMapper.toDTO(pessoaRepository.save(recordFound));
            }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull @Positive Long id){
        pessoaRepository.delete(pessoaRepository.findById(id)
        .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
