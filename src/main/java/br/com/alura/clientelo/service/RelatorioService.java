package br.com.alura.clientelo.service;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.Pedido;
import br.com.alura.clientelo.PedidoServiceTest;

public class RelatorioService {
	
	private static final Logger logger = LoggerFactory.getLogger(RelatorioService.class);
	
	public void displayRelatorioValorTotal() {
		logger.info("\n\n ##### RELATÓRIO DE VALORES TOTAIS ##### \n");
		logger.info("TOTAL DE PEDIDOS: {}", totalDePedidosRealizados);
		logger.info("TOTAL DE FATURADO: {}", totalDeVendas);
		logger.info("TOTAL DE PRODUTOS VENDIDOS: {}", totalDeProdutosVendidos);
		logger.info("TOTAL DE CATEGORIAS: {}", categorias.size());
		logger.info("PEDIDO MAIS CARO: {}", pedidoMaisCaro.getTotalPedido());
		logger.info("PEDIDO MAIS BARATO: {}", pedidoMaisBarato.getTotalPedido());
		logger.info("CATEGORIAS: {}", categorias);
	}
	
	public void displayRelatorioProdutoMaisVendido() {
		logger.info("\n\n ##### PRODUTO MAIS VENDIDO ##### \n");
		pedidos.sort(Comparator.comparing(Pedido::getQuantidade).reversed());
		
		pedidos.stream().forEach(pedido -> {
			logger.info("PRODUTO: {}", pedido.getProduto());
			logger.info("QUANTIDADE: {} \n", pedido.getQuantidade());
		});
	}

}
