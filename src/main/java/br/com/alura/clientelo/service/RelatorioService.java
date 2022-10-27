package br.com.alura.clientelo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.modelo.Pedido;
import br.com.alura.clientelo.modelo.RelatorioPedidoConsolidado;

public class RelatorioService {
	
	private static final Logger logger = LoggerFactory.getLogger(RelatorioService.class);
	
	private RelatorioPedidoConsolidado relatorioPedidoConsolidado;
	
	public RelatorioService(Optional<List<Pedido>> pedidos) {
		this.relatorioPedidoConsolidado = new RelatorioPedidoConsolidado(pedidos);
	}

	public void relatorioConsolidadoDePedidos() {
		logger.info("\n\n ##### RELATÓRIO DE VALORES TOTAIS ##### \n");
		logger.info("TOTAL DE PEDIDOS: {}", relatorioPedidoConsolidado.getTotalDePedidosRealizados());
		logger.info("TOTAL DE PRODUTOS VENDIDOS: {}", relatorioPedidoConsolidado.getTotalDeProdutosVendidos());
		logger.info("TOTAL DE CATEGORIAS: {}", relatorioPedidoConsolidado.getTotalDeCategorias());
		logger.info("MONTANTE DE VENDAS: {}", relatorioPedidoConsolidado.getTotalDeVendas());
		logger.info("PEDIDO MAIS BARATO: {}", relatorioPedidoConsolidado.getPedidoMaisBarato().toTotalEProduto());
		logger.info("PEDIDO MAIS CARO: {}", relatorioPedidoConsolidado.getPedidoMaisCaro().toTotalEProduto());
		logger.info("CATEGORIAS: {}", relatorioPedidoConsolidado.getCategorias());
	}
	
	
	
//	public void displayRelatorioProdutoMaisVendido() {
//		logger.info("\n\n ##### PRODUTO MAIS VENDIDO ##### \n");
//		pedidos.sort(Comparator.comparing(Pedido::getQuantidade).reversed());
//		
//		pedidos.stream().forEach(pedido -> {
//			logger.info("PRODUTO: {}", pedido.getProduto());
//			logger.info("QUANTIDADE: {} \n", pedido.getQuantidade());
//		});
//	}

}
