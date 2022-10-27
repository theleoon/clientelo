package br.com.alura.clientelo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.alura.clientelo.modelo.Pedido;

public class CategoriaServiceTest {

	static List<Pedido> pedidos = new ArrayList<>();
	
	CategoriaService categoriaService = new CategoriaService();

	@BeforeAll
	 static void init() {
		pedidos.add(new Pedido("Informática", "iPhone 12", "Leonardo", new BigDecimal(11000), 1, LocalDate.now()));
		pedidos.add(new Pedido("Casa", "Copo de Vidro", "Carolina", new BigDecimal(80), 2, LocalDate.now()));
		pedidos.add(new Pedido("Casa", "Pano de Prato", "Ricardo", new BigDecimal(20), 1, LocalDate.now()));
		pedidos.add(new Pedido("Informática", "Asus Notebook", "Heloisa", new BigDecimal(2000), 1, LocalDate.now()));
	}
	
	@Test
	void deveDevolverTodasAsCategoriasDosPedidos() {
		
		Set<String> categorias = categoriaService.getCategorias(Optional.of(pedidos));
		
		Assert.assertTrue(categorias.contains("Informática"));
		Assert.assertTrue(categorias.contains("Casa"));
	}

}
