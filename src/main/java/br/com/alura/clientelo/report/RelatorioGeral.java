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

public class RelatorioGeral implements Report {
	
	private static final Logger logger = LoggerFactory.getLogger(RelatorioGeral.class);
	
	private BigDecimal totalDePedidosRealizados;
	private BigDecimal totalDeVendas;
	private BigDecimal totalDeProdutosVendidos;
	private BigDecimal totalDeCategorias;
	private Pedido pedidoMaisCaro;
	private Pedido pedidoMaisBarato;
	
	private Set<String> categorias;
	
	public RelatorioGeral(Optional<List<Pedido>> pedidos) {
		
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
			
            StringBuilder response = new StringBuilder();
            response.append("##### RELATÓRIO DE VALORES TOTAIS ##### \n");
            response.append("\n");

            response.append(String.format("TOTAL DE PEDIDOS REALIZADOS: %s\n", getTotalDePedidosRealizados()));
            response.append(String.format("TOTAL DE PRODUTOS VENDIDOS: %s\n", getTotalDeProdutosVendidos()));
            response.append(String.format("TOTAL DE CATEGORIAS: %s\n", getTotalDeCategorias()));
            response.append(String.format("MONTANTE DE VENDAS: %s\n", getTotalDeVendas()));
//            response.append(String.format("PEDIDO MAIS BARATO: %s (%s)\n", getPedidoMaisBarato().toTotalEProduto()));
//            response.append(String.format("PEDIDO MAIS CARO: %s (%s)\n\n", getPedidoMaisCaro().toTotalEProduto()));
                           
            response.append("### FIM DO RELATÓRIO ###");
            response.append("\n");
            
            logger.info(response.toString());
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
