package br.com.alura.clientelo.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.alura.clientelo.model.Cliente;

public class TestePaginacaoClienteSpringData {
	
	private static final Logger logger = LoggerFactory.getLogger(TestePaginacaoClienteSpringData.class);
	
	private ClienteRepository clienteRepository;
	
	public TestePaginacaoClienteSpringData(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public void valida(int pagina) {
		
		Pageable pageable = PageRequest.of(pagina, 2, Sort.unsorted());
		
		Page<Cliente> clientes = clienteRepository.findAll(pageable);

		logger.info("Total de páginas {}", clientes);
		logger.info("Página atual {}", clientes.getNumber());
		logger.info("Número de elementos na página {}", clientes.getNumberOfElements());
		
		clientes.get().forEach(c -> logger.info("Objeto: {}", c));
	}
	
	public void validaComOrdenacao(int pagina) {
		
		Pageable pageable = PageRequest.of(pagina, 2, Sort.by(Sort.Direction.ASC, "nome"));
		
		Page<Cliente> clientes = clienteRepository.findAll(pageable);

		logger.info("Total de páginas {}", clientes);
		logger.info("Página atual {}", clientes.getNumber());
		logger.info("Número de elementos na página {}", clientes.getNumberOfElements());
		
		clientes.get().forEach(c -> logger.info("Objeto: {}", c));
	}

}
