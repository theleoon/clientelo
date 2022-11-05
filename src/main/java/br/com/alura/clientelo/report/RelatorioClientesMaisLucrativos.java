package br.com.alura.clientelo.report;

import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.repository.ClienteDao;

public class RelatorioClientesMaisLucrativos implements Report {
	
	private static final Logger logger = LoggerFactory.getLogger(RelatorioClientesMaisLucrativos.class);
	
	@Override
	public void export() {
		
		ClienteDao clienteDao = new ClienteDao();
		Map<Cliente, BigDecimal> retorno = clienteDao.listaPorMaiorValorGasto();
		
		StringBuilder response = new StringBuilder();
        response.append("##### RELATÓRIO DE CLIENTES MAIS LUCRATIVOS ##### \n");
        response.append("\n");
        
        retorno.forEach((cliente, valorGasto) -> {
        	response.append(String.format("NOME: %s \n", cliente.getNome()));
            response.append(String.format("Nº DE PEDIDOS: %s \n", cliente.getQuantidadeDePedidos()));
            response.append(String.format("MONTANTE GASTO: %s \n", valorGasto));
            response.append("\n");
        });

        response.append("##### FIM DO RELATÓRIO ##### \n");
        logger.info(response.toString()); 
		
	}

}
