package br.com.alura.clientelo.dao.testes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.dao.ClienteDao;
import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.model.Endereco;

public class TestePersistenciaCliente {
	
	private static final Logger logger = LoggerFactory.getLogger(TestePersistenciaCliente.class);
	
	public static void main(String[] args) {
		
		ClienteDao clienteDao = new ClienteDao();
		Cliente clienteJoao = new Cliente("Pedro da Silva", "455.666.444-57", "1155556666", new Endereco("Rua Sem Nome", "22", "Perto da Padoca", "Planalto", "São Bernardo", "São Paulo"));
		clienteDao.cadastra(clienteJoao);
		
		Cliente clienteHeloisa = new Cliente("Heloisa Ferreira", "455.666.444-88", "1155556666", new Endereco("Rua Sem Nome", "22", "Perto da Padoca", "Planalto", "São Bernardo", "São Paulo"));
		clienteDao.cadastra(clienteHeloisa);
		
		Cliente clienteHenrique = new Cliente("Henrique Junior", "455.854.444-57", "1155556666", new Endereco("Rua Sem Nome", "22", "Perto da Padoca", "Planalto", "São Bernardo", "São Paulo"));
		clienteDao.cadastra(clienteHenrique);
		
		logger.info("Cliente cadastrado com sucesso!");

		logger.info("Cliente cadastrado {}", clienteDao.buscaPorId(clienteJoao.getId()));

	}

}
