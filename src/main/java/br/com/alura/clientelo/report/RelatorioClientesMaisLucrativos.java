package br.com.alura.clientelo.report;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.repository.ClienteDao;
import br.com.alura.clientelo.vo.RelatorioClientePorMontanteVo;

public class RelatorioClientesMaisLucrativos implements Report {

	private static final Logger logger = LoggerFactory.getLogger(RelatorioClientesMaisLucrativos.class);

	@Override
	public void export() {

		ClienteDao clienteDao = new ClienteDao();
		List<RelatorioClientePorMontanteVo> retorno = clienteDao.listaPorMaiorValorGasto();

		StringBuilder response = new StringBuilder();
		response.append("##### RELATÓRIO DE CLIENTES MAIS LUCRATIVOS ##### \n");
		response.append("\n");

		retorno.forEach(obj -> {
			response.append(String.format("NOME: %s \n", obj.getNomeCliente()));
			response.append(String.format("Nº DE PEDIDOS: %s \n", obj.getQuantidadeDePedidos()));
			response.append(String.format("MONTANTE GASTO: %s \n", obj.getMontanteGasto()));
			response.append("\n");
		});

		response.append("##### FIM DO RELATÓRIO ##### \n");
		logger.info(response.toString());

	}
	
	public static void main(String[] args) {
		
		new RelatorioClientesMaisLucrativos().export();
		
	}

}
