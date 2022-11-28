package br.com.alura.clientelo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.repository.CategoriaRepository;
import br.com.alura.clientelo.repository.ClienteRepository;
import br.com.alura.clientelo.repository.SpecificationCliente;
import br.com.alura.clientelo.vo.RelatorioClientePedidosVo;
import br.com.alura.clientelo.vo.RelatorioClientePorMontanteVo;

//@EnableJpaRepositories({"br.com.alura.clientelo.repository"})
//@EntityScan("br.com.alura.clientelo.model")

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
//		new TestePaginacaoClienteSpringData(clienteRepository).valida(2);
//		new TestePaginacaoClienteSpringData(clienteRepository).validaComOrdenacao(2);
		
//		List<RelatorioClientePedidosVo> listaPorMaisPedidos = clienteRepository.listaPorMaisPedidos();
//		listaPorMaisPedidos.forEach(p -> System.out.println(p.getNomeDoCliente()));
		
//		 List<RelatorioClientePorMontanteVo> listaPorMaiorValorGasto = clienteRepository.listaPorMaiorValorGasto();
//		 listaPorMaiorValorGasto.forEach(p -> System.out.println(p.getMontanteGasto()));
		
//		List<Cliente> clientes = clienteRepository
//				.findAll(SpecificationCliente.nome("Pedro da Silva")
//						.and(SpecificationCliente.cpf("455.666.444-57")));
//		
//		clientes.forEach(c -> System.out.println(c.getNome()));
		
	}

	
}