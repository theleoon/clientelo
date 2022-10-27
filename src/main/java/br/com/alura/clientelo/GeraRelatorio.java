package br.com.alura.clientelo;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.service.CategoriaService;
import br.com.alura.clientelo.service.PedidoService;

public class GeraRelatorio {

	private static final Logger logger = LoggerFactory.getLogger(GeraRelatorio.class);
	

	public static void main(String[] args) throws IOException, URISyntaxException {

		List<Pedido> pedidos = new ArrayList<Pedido>();
		
		pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");
		PedidoService pedidoService = new PedidoService();
		CategoriaService categoriaService = new CategoriaService();
		
		Set<String> categorias = categoriaService.getCategorias(Optional.of(pedidos));

		Optional<Integer> totalDePedidosRealizados = pedidoService.getQuantidadeDePedidos(Optional.of(pedidos));
		
		BigDecimal totalDeVendas = pedidoService.getTotalDeVendas(Optional.of(pedidos));
	
		BigDecimal totalDeProdutosVendidos = pedidoService.getTotalDeProdutosVendidos(Optional.of(pedidos));

		Optional<Pedido> pedidoMaisBarato = pedidoService.getPedidoMaisBarato(Optional.of(pedidos));
		Optional<Pedido> pedidoMaisCaro = pedidoService.getPedidoMaisCaro(Optional.of(pedidos));

		logger.info("\n\n ##### RELATÓRIO DE VALORES TOTAIS ##### \n");
		logger.info("TOTAL DE PEDIDOS: {}", totalDePedidosRealizados);
		logger.info("TOTAL DE FATURADO: {}", totalDeVendas);
		logger.info("TOTAL DE PRODUTOS VENDIDOS: {}", totalDeProdutosVendidos);
		logger.info("TOTAL DE CATEGORIAS: {}", categorias.size());
		logger.info("PEDIDO MAIS CARO: {}", pedidoMaisCaro.getTotalPedido());
		logger.info("PEDIDO MAIS BARATO: {}", pedidoMaisBarato.getTotalPedido());
		logger.info("CATEGORIAS: {}", categorias);

		logger.info("\n\n ##### PRODUTO MAIS VENDIDO ##### \n");
		pedidos.sort(Comparator.comparing(Pedido::getQuantidade).reversed());
		
		pedidos.stream().forEach(pedido -> {
			logger.info("PRODUTO: {}", pedido.getProduto());
			logger.info("QUANTIDADE: {} \n", pedido.getQuantidade());
		});
		
		logger.info("\n\n ##### RELATÓRIO DE VENDAS POR CATEGORIA ##### \n");
		pedidos.sort(Comparator.comparing(Pedido::getCategoria));
		
		for (String categoria : categorias) {
			
			BigDecimal montante = BigDecimal.ZERO;
			BigDecimal quantidade = BigDecimal.ZERO;
			
			montante = pedidos.stream()
								.filter(p -> p.getCategoria().equals(categoria))
								.map(p -> p.getTotalPedido())
								.reduce(BigDecimal.ZERO, BigDecimal::add);
			
			quantidade = pedidos.stream()
								.filter(p -> p.getCategoria().equals(categoria))
								.map(p -> new BigDecimal(p.getQuantidade()))
								.reduce(BigDecimal.ZERO, BigDecimal::add);
			
			logger.info("CATEGORIA: {}", categoria);
			logger.info("QUANTIDADE VENDIDA: {}", quantidade);
			logger.info("QUANTIDADE MONTANTE: {} \n", montante);
		};
		
	}

	
	 
}
