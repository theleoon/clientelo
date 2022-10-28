package br.com.alura.clientelo.reader;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.clientelo.model.Pedido;

public class PedidoReader implements FileReader<Pedido> {
	
	private URL fileUrl = ClassLoader.getSystemResource("pedidos.json");

	@Override
	public List<Pedido> readFromJson(URL url) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<Pedido> pedidos = new ArrayList<>();
		
		try {
			
			pedidos = Arrays.asList(objectMapper.readValue(fileUrl, Pedido[].class));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pedidos;
	}

	@Override
	public List<Pedido> readFromCsv(URL url) {
		// TODO Auto-generated method stub
		return null;
	}

}
