package br.com.alura.clientelo.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PedidoTest {
	
	static Pedido pedidoBarato;
	static Pedido pedidoCaro;
	static Pedido pedidoComMaisUnidades;
	static Pedido pedidoComUmaUnidade;
	
	@BeforeAll
	static void init() {
		pedidoBarato = new Pedido("Informática", "Mouse Pad", "Leonardo", new BigDecimal(21), 1, LocalDate.now());
		pedidoCaro = new Pedido("Informática", "iPhone 12", "Ricardo", new BigDecimal(11000), 1, LocalDate.now());
		pedidoComUmaUnidade = new Pedido("Informática", "Mouse Gamer", "Luciana", new BigDecimal(200), 1, LocalDate.now());
		pedidoComMaisUnidades = new Pedido("Informática", "Mouse Gamer", "Luciana", new BigDecimal(200), 3, LocalDate.now());
	}
	
	@Test
	void deveValidarSePedidoEMaisBarato() {
		
		boolean result = pedidoBarato.isMaisBaratoQue(pedidoCaro);
		
		Assert.assertTrue(result);
	}
	
	@Test
	void deveValidarSePedidoEMaisCaro() {
		
		boolean result = pedidoBarato.isMaisCaroQue(pedidoCaro);
		
		Assert.assertFalse(result);
	}
	
	@Test
	void deveValidarTotalDoPedido() {
		
		BigDecimal result = pedidoComUmaUnidade.getValorTotal();
		
		Assert.assertEquals(new BigDecimal(200), result);
	}
	
	@Test
	void deveValidarTotalDoPedidoComMaisDeUmItem() {
		
		BigDecimal result = pedidoComMaisUnidades.getValorTotal();
		
		Assert.assertEquals(new BigDecimal(600), result);
	}

}
