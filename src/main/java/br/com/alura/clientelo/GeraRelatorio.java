package br.com.alura.clientelo;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeraRelatorio {

	private static final Logger logger = LoggerFactory.getLogger(GeraRelatorio.class);

	public static void main(String[] args) throws IOException, URISyntaxException {

		List<Pedido> pedidos = new ArrayList<Pedido>();
		Set<String> categorias = new HashSet<>();
		pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");

		Integer totalDePedidosRealizados = pedidos.size();
		BigDecimal totalDeVendas = BigDecimal.ZERO;
		BigDecimal totalDeProdutosVendidos = BigDecimal.ZERO;
		Pedido pedidoMaisBarato = null;
		Pedido pedidoMaisCaro = null;

		for (Pedido pedido : pedidos) {
			if (pedido == null)
				break;
			totalDeVendas = totalDeVendas.add(pedido.getTotalPedido());
			totalDeProdutosVendidos = totalDeProdutosVendidos.add(new BigDecimal(pedido.getQuantidade()));
		}

		for (Pedido pedido : pedidos) {
			if (pedido == null) 
				break;
			categorias.add(pedido.getCategoria());
		}

		pedidos.sort(Comparator.comparing(Pedido::getTotalPedido));
		pedidoMaisBarato = pedidos.get(0);
		pedidoMaisCaro = pedidos.get(pedidos.size() - 1);

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
		for (Pedido pedido : pedidos) {
			logger.info("PRODUTO: {}", pedido.getProduto());
			logger.info("QUANTIDADE: {} \n", pedido.getQuantidade());
		}
		
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

	}
}
