package br.com.alura.clientelo.report;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.service.CategoriaService;
import br.com.alura.clientelo.service.PedidoService;

public class RelatorioPedidoConsolidado implements Report {
	
	private static final Logger logger = LoggerFactory.getLogger(RelatorioPedidoConsolidado.class);
	
	private BigDecimal totalDePedidosRealizados;
	private BigDecimal totalDeVendas;
	private BigDecimal totalDeProdutosVendidos;
	private BigDecimal totalDeCategorias;
	private Pedido pedidoMaisCaro;
	private Pedido pedidoMaisBarato;
	
	private Set<String> categorias;
	
	public RelatorioPedidoConsolidado(Optional<List<Pedido>> pedidos) {
		
		PedidoService pedidoService = new PedidoService();
		CategoriaService categoriaService = new CategoriaService();
		
		this.totalDePedidosRealizados = pedidoService.getTotalDePedidosRealizados(pedidos);
		this.totalDeVendas = pedidoService.getTotalDeVendas(pedidos);
		this.totalDeProdutosVendidos = pedidoService.getTotalDeProdutosVendidos(pedidos);
		this.categorias = categoriaService.getCategorias(pedidos);
		this.totalDeCategorias = new BigDecimal(categorias.size());
		this.pedidoMaisCaro = pedidoService.getPedidoMaisCaro(pedidos).orElse(null);
		this.pedidoMaisBarato = pedidoService.getPedidoMaisBarato(pedidos).orElse(null);
		
	}

	@Override
	public void export() {
		logger.info("\n\n ##### RELATÓRIO DE VALORES TOTAIS ##### \n");
		logger.info("TOTAL DE PEDIDOS: {}", getTotalDePedidosRealizados());
		logger.info("TOTAL DE PRODUTOS VENDIDOS: {}", getTotalDeProdutosVendidos());
		logger.info("TOTAL DE CATEGORIAS: {}", getTotalDeCategorias());
		logger.info("MONTANTE DE VENDAS: {}", getTotalDeVendas());
		logger.info("PEDIDO MAIS BARATO: {}", getPedidoMaisBarato().toTotalEProduto());
		logger.info("PEDIDO MAIS CARO: {}", getPedidoMaisCaro().toTotalEProduto());
		logger.info("CATEGORIAS: {}", getCategorias());
		
	}

	public String getTotalDePedidosRealizados() {
		return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(totalDePedidosRealizados.setScale(2, RoundingMode.HALF_DOWN));
	}

	public String getTotalDeVendas() {
		return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(totalDeVendas.setScale(2, RoundingMode.HALF_DOWN));
	}

	public String getTotalDeProdutosVendidos() {
		return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(totalDeProdutosVendidos.setScale(2, RoundingMode.HALF_DOWN));
	}

	public BigDecimal getTotalDeCategorias() {
		return totalDeCategorias;
	}

	public Pedido getPedidoMaisCaro() {
		return pedidoMaisCaro;
	}

	public Pedido getPedidoMaisBarato() {
		return pedidoMaisBarato;
	}

	public Set<String> getCategorias() {
		return categorias;
	}

}
