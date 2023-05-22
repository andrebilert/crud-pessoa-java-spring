package com.andre.crudpessoa;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.andre.crudpessoa.model.Contato;
import com.andre.crudpessoa.model.Pessoa;
import com.andre.crudpessoa.repository.PessoaRepository;

@SpringBootApplication
public class CrudPessoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudPessoaApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(PessoaRepository pessoaRepository) {
		return args -> {
			pessoaRepository.deleteAll();

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

			pessoaRepository.save(pessoa);
			//Fazer DTO e ajustar Mapper para Contato
		};
	}
}