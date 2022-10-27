package br.com.alura.clientelo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.modelo.Pedido;

public class PedidoServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(PedidoServiceTest.class);

	static List<Pedido> pedidos = new ArrayList<>();

	PedidoService pedidoService = new PedidoService();

	@BeforeAll
	 static void init() {
		pedidos.add(new Pedido("Informática", "iPhone 12", "Leonardo", new BigDecimal(11000), 1, LocalDate.now()));
		pedidos.add(new Pedido("Casa", "Copo de Vidro", "Carolina", new BigDecimal(80), 2, LocalDate.now()));
		pedidos.add(new Pedido("Casa", "Pano de Prato", "Ricardo", new BigDecimal(20), 1, LocalDate.now()));
		pedidos.add(new Pedido("Informática", "Asus Notebook", "Heloisa", new BigDecimal(2000), 1, LocalDate.now()));
	}

	@Test
	void deveRetornarPedidoComMaiorValor() {
		Optional<Pedido> pedidoObtido = pedidoService.getPedidoMaisCaro(Optional.of(pedidos));
		Assert.assertEquals(new BigDecimal(11000), pedidoObtido.get().getTotalPedido());
	}
	
	@Test
	void deveRetornarPedidoComMenorValor() {
		Optional<Pedido> pedidoObtido = pedidoService.getPedidoMaisBarato(Optional.of(pedidos));
		Assert.assertEquals(new BigDecimal(20), pedidoObtido.get().getTotalPedido());
	}

	@Test
	void deveFalharAoNaoRetornarUmPedidoValido() {
		Optional<Pedido> pedidoObtido = pedidoService.getPedidoMaisBarato(Optional.empty());
		Assert.assertFalse(pedidoObtido.isPresent());
	}

}
