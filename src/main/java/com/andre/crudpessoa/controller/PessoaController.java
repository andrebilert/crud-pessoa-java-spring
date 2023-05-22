package com.andre.crudpessoa.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.andre.crudpessoa.dto.PessoaDTO;
import com.andre.crudpessoa.service.PessoaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Validated
@RestController
@RequestMapping("/")
public class PessoaController {
    
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    
    @GetMapping
    public @ResponseBody List<PessoaDTO> list() {
        return pessoaService.list();
    }

    @GetMapping("/{id}")
    public PessoaDTO findById(@PathVariable @NotNull @Positive Long id) {
        return pessoaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PessoaDTO create(@RequestBody @Valid @NotNull PessoaDTO pessoa) {
        return pessoaService.create(pessoa);
    }

    @PutMapping("/{id}")
    public PessoaDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull PessoaDTO pessoa){
        return pessoaService.update(id, pessoa);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        pessoaService.delete(id); 
    }

}
