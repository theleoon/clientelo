package br.com.alura.clientelo.report;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.repository.ClienteDao;
import br.com.alura.clientelo.vo.RelatorioClientePedidosVo;

public class RelatorioClientesFieis implements Report {
	
	private static final Logger logger = LoggerFactory.getLogger(RelatorioClientesFieis.class);

	@Override
	public void export() {
		
		 ClienteDao clienteDao = new ClienteDao();
	      List<RelatorioClientePedidosVo> retorno = clienteDao.listaPorMaisPedidos();
		
		 StringBuilder response = new StringBuilder();
	     response.append("##### RELATÓRIO DE CLIENTES FIÉIS ##### \n");
	     response.append("\n");

	     retorno.forEach((obj) -> {
	         response.append(String.format("NOME: %s \n", obj.getNomeDoCliente()));
	         response.append(String.format("Nº DE PEDIDOS: %s \n", obj.getQuantidadeDePedidos()));
	         response.append("\n");
	     });
	
	     response.append("##### FIM DO RELATÓRIO ##### \n");
	    
	     logger.info(response.toString());
	}
	
	public static void main(String[] args) {
		new RelatorioClientesFieis().export();
	}

}
