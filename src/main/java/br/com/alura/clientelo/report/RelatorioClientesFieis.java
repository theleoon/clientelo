package br.com.alura.clientelo.report;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.repository.ClienteDao;

public class RelatorioClientesFieis implements Report {
	
	private static final Logger logger = LoggerFactory.getLogger(RelatorioClientesFieis.class);

	@Override
	public void export() {
		
		 ClienteDao clienteDao = new ClienteDao();
	     Map<Cliente, Long> retorno = clienteDao.listaPorMaisPedidos();
		
		 StringBuilder response = new StringBuilder();
	     response.append("##### RELATÓRIO DE CLIENTES FIÉIS ##### \n");
	     response.append("\n");

	     retorno.forEach((cliente, quantidade) -> {
	         response.append(String.format("NOME: %s \n", cliente.getNome()));
	         response.append(String.format("Nº DE PEDIDOS: %s \n", quantidade));
	         response.append("\n");
	     });
	
	     response.append("##### FIM DO RELATÓRIO ##### \n");
	    
	     logger.info(response.toString());
	}

}
