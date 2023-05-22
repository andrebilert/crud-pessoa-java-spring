package com.andre.crudpessoa.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.andre.crudpessoa.dto.ContatoDTO;
import com.andre.crudpessoa.dto.PessoaDTO;
import com.andre.crudpessoa.dto.mapper.PessoaMapper;
import com.andre.crudpessoa.model.Contato;
import com.andre.crudpessoa.model.Pessoa;


public class CreatePessoaServiceTest {
    @Test
    public void whenCreateNewPessoaReturnSuccess() {
        
        Pessoa pessoa = new Pessoa();
			pessoa.setName("Andre");
			pessoa.setCpf("08299999909");
			pessoa.setDataNascimento(LocalDate.of(1993,9,28));

			Contato contato1 = new Contato();
			contato1.setNameContato("Mykaelly");
			contato1.setEmailContato("myka@gmail.com");
			contato1.setPhoneContato("44999999999");
			contato1.setPessoa(pessoa);
			pessoa.getContatos().add(contato1);

			Contato contato2 = new Contato();
			contato2.setNameContato("Luana");
			contato2.setEmailContato("luana@gmail.com");
			contato2.setPhoneContato("43999999999");
			contato2.setPessoa(pessoa);
			pessoa.getContatos().add(contato2);

            PessoaDTO pessoaDTOExpected = new PessoaDTO(
                1L,
                "Andre",
                "08299999909",
                LocalDate.of(1993,9,28),
                Arrays.asList(
                    new ContatoDTO(1L, "Mykaelly", "44999999999", "myka@gmail.com"),
                    new ContatoDTO(2L, "Luana", "43999999999", "luana@gmail.com")
                )
            );
            
            PessoaMapper pessoaMapper = new PessoaMapper();

            PessoaDTO pessoaDTO = pessoaMapper.toDTO(pessoa);
            
            assertEquals(pessoaDTOExpected, pessoaDTO);
    }
}
