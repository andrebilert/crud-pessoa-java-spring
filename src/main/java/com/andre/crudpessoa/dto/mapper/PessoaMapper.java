package com.andre.crudpessoa.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.andre.crudpessoa.dto.ContatoDTO;
import com.andre.crudpessoa.dto.PessoaDTO;
import com.andre.crudpessoa.model.Pessoa;

@Component
public class PessoaMapper {

    public PessoaDTO toDTO(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }
        List<ContatoDTO> contatos = pessoa.getContatos()
        .stream()
        .map(contato -> new ContatoDTO(contato.getId(), contato.getNameContato(), contato.getEmailContato(), contato.getPhoneContato()))
        .collect(Collectors.toList());
        return new PessoaDTO(pessoa.getId(), pessoa.getName(), pessoa.getCpf(), pessoa.getDataNascimento(), contatos);
    }

    public Pessoa toEntity(PessoaDTO pessoaDTO) {

        if (pessoaDTO == null) {
            return null;
        }

        Pessoa pessoa = new Pessoa();
        if(pessoaDTO.id() != null){
            pessoa.setId(pessoaDTO.id());
        }
        pessoa.setName(pessoaDTO.name());
        pessoa.setCpf(pessoaDTO.cpf());
        pessoa.setDataNascimento(pessoaDTO.dataNascimento());
        pessoa.setStatus("Ativo");
        return pessoa;
    }
}
