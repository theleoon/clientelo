package br.com.alura.clientelo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.modelo.Pedido;

public class CategoriaService {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoriaService.class);
	
	public Set<String> getCategorias(Optional<List<Pedido>> pedidos) {
		return pedidos.orElse(new ArrayList<Pedido>())
				.stream()
				.map(pedido -> pedido.getCategoria())
				.collect(Collectors.toSet());
	}

}
