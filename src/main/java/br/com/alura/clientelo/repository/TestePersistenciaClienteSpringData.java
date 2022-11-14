package br.com.alura.clientelo.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.model.Endereco;

public class TestePersistenciaClienteSpringData {
	
	private static final Logger logger = LoggerFactory.getLogger(TestePersistenciaClienteSpringData.class);
	
	private ClienteRepository clienteRepository;
	
	public TestePersistenciaClienteSpringData(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public void valida() {
		
		Cliente clienteJoao = new Cliente("Pedro da Silva", "455.666.444-57", "1155556666", new Endereco("Rua Sem Nome", "22", "Perto da Padoca", "Planalto", "São Bernardo", "São Paulo"));
		clienteRepository.save(clienteJoao);
		
		Cliente clienteHeloisa = new Cliente("Heloisa Ferreira", "455.666.444-88", "1155556666", new Endereco("Rua Sem Nome", "22", "Perto da Padoca", "Planalto", "São Bernardo", "São Paulo"));
		clienteRepository.save(clienteHeloisa);
		
		Cliente clienteHenrique = new Cliente("Henrique Junior", "455.854.444-57", "1155556666", new Endereco("Rua Sem Nome", "22", "Perto da Padoca", "Planalto", "São Bernardo", "São Paulo"));
		clienteRepository.save(clienteHenrique);
		
		logger.info("Cliente cadastrado com sucesso!");

		logger.info("Cliente cadastrado (João) {}", clienteRepository.findById(clienteJoao.getId()));

	}

}
