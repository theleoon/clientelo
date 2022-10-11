package br.com.alura.clientelo;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//Feito em sala de aula
public class GeraRelatorioRaiz {

	private static final Logger logger = LoggerFactory.getLogger(GeraRelatorioRaiz.class);

	public static <R> void main(String[] args) throws IOException, URISyntaxException {

		List<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");

		Integer totalDePedidosRealizados = pedidos.size();

		BigDecimal totalDeVendas = pedidos.stream()
				.map(pedido -> pedido.getTotalPedido())
				.reduce(BigDecimal.ZERO,BigDecimal::add);

		BigDecimal totalDeProdutosVendidos = pedidos.stream()
				.map(pedido -> new BigDecimal(pedido.getQuantidade()))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		Set<String> categorias = pedidos.stream().map(pedido -> pedido.getCategoria()).collect(Collectors.toSet());

		pedidos.sort(Comparator.comparing(Pedido::getTotalPedido));
		Pedido pedidoMaisBarato = pedidos.get(0);
		Pedido pedidoMaisCaro = pedidos.get(pedidos.size() - 1);

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

		pedidos.forEach(pedido -> {
			logger.info("PRODUTO: {}", pedido.getProduto());
			logger.info("QUANTIDADE: {} \n", pedido.getQuantidade());
		});

		logger.info("\n\n ##### RELATÓRIO DE VENDAS POR CATEGORIA ##### \n");
		pedidos.sort(Comparator.comparing(Pedido::getCategoria));
		for (String categoria : categorias) {
			
			BigDecimal montante = BigDecimal.ZERO;
			BigDecimal quantidade = BigDecimal.ZERO;
			
			for (Pedido pedido : pedidos) {
				if (pedido == null) break;
				
				if (pedido.getCategoria().equals(categoria)) {
					montante = montante.add(pedido.getTotalPedido());
					quantidade = quantidade.add(new BigDecimal(pedido.getQuantidade()));
				}
			}

			logger.info("CATEGORIA: {}", categoria);
			logger.info("QUANTIDADE VENDIDA: {}", quantidade);
			logger.info("QUANTIDADE MONTANTE: {} \n", montante);
		}
		
		categorias.stream().forEach(categoria -> {
			
			List<Pedido> pedidosDaCategoria = pedidos.stream()
					.filter(pedido -> pedido.getCategoria().equals(categoria)).collect(Collectors.toList());

			BigDecimal montante = pedidosDaCategoria.stream()
					.map(pedido -> pedido.getTotalPedido())
					.reduce(BigDecimal.ZERO, BigDecimal::add);

			BigDecimal quantidade = pedidosDaCategoria.stream()
					.map(p -> new BigDecimal(p.getQuantidade()))
					.reduce(BigDecimal.ZERO, BigDecimal::add);

			logger.info("CATEGORIA: {}", categoria);
			logger.info("QUANTIDADE VENDIDA: {}", quantidade);
			logger.info("QUANTIDADE MONTANTE: {} \n", montante);
			
		});
		



	}
}
