package br.com.alura.clientelo.report;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.dao.PedidoDao;
import br.com.alura.clientelo.model.Produto;

public class RelatorioProdutosMaisVendidos implements Report {
	
	private static final Logger logger = LoggerFactory.getLogger(RelatorioProdutosMaisVendidos.class);

	@Override
	public void export() {
		
		PedidoDao pedidoDao = new PedidoDao();
		Map<Produto, Long> produtoMaisVendido = pedidoDao.listaProdutosMaisVendidos();
		
		StringBuilder response = new StringBuilder();
	    response.append("##### RELATÓRIO DE PRODUTOS MAIS VENDIDOS ##### \n");
	    response.append("\n");
		
		produtoMaisVendido.forEach((pedido, quantidade) -> {
			response.append(String.format("PRODUTO: %s \n", pedido.getNome()));
			response.append(String.format("QUANTIDADE: %s \n", quantidade));
		});
		
		logger.info(response.toString());
	}
	
	

}
