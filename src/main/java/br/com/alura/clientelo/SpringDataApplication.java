package br.com.alura.clientelo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import br.com.alura.clientelo.repository.CategoriaRepository;
import br.com.alura.clientelo.repository.ClienteRepository;
import br.com.alura.clientelo.repository.UsuarioRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableJpaRepositories({"br.com.alura.clientelo.repository"})
//@EntityScan("br.com.alura.clientelo.model")

@SpringBootApplication
@EnableSwagger2
@EnableSpringDataWebSupport // Spring Data Sort
@EnableCaching
public class SpringDataApplication implements CommandLineRunner {
	
	private final CategoriaRepository categoriaRepository;
	
	private final ClienteRepository clienteRepository;
	
	private final UsuarioRepository usuarioRepository;

	public SpringDataApplication(CategoriaRepository categoriaRepository, ClienteRepository clienteRepository,
			UsuarioRepository usuarioRepository) {
		this.categoriaRepository = categoriaRepository;
		this.clienteRepository = clienteRepository;
		this.usuarioRepository = usuarioRepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		new TestePersistenciaUsuario(this.usuarioRepository).valida();
		
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