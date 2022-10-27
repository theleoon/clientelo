package br.com.alura.clientelo.main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.ProcessadorDeCsv;
import br.com.alura.clientelo.modelo.Pedido;
import br.com.alura.clientelo.service.RelatorioService;

public class GeraRelatorio {

	private static final Logger logger = LoggerFactory.getLogger(GeraRelatorio.class);
	
	public static void main(String[] args) throws IOException, URISyntaxException {

		List<Pedido> pedidos = new ArrayList<Pedido>();
		
		pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");
		
		RelatorioService relatorioService = new RelatorioService(Optional.of(pedidos));
		relatorioService.relatorioConsolidadoDePedidos();
	
	}
}
