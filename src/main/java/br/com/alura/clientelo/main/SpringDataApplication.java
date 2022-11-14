package br.com.alura.clientelo.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.alura.clientelo.repository.CategoriaRepository;
import br.com.alura.clientelo.repository.ClienteRepository;
import br.com.alura.clientelo.repository.TestePersistenciaCategoriaSpringData;
import br.com.alura.clientelo.repository.TestePersistenciaClienteSpringData;

@EnableJpaRepositories({"br.com.alura.clientelo.repository"})
@EntityScan("br.com.alura.clientelo.model")  
@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
	private final CategoriaRepository categoriaRepository;
	
	private final ClienteRepository clienteRepository;

	public SpringDataApplication(CategoriaRepository categoriaRepository, ClienteRepository clienteRepository) {
		this.categoriaRepository = categoriaRepository;
		this.clienteRepository = clienteRepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		new TestePersistenciaCategoriaSpringData(categoriaRepository).valida();
//		new TestePersistenciaClienteSpringData(clienteRepository).valida();
	}

	
}