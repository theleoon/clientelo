package br.com.alura.clientelo.vo;

import java.math.BigDecimal;

public class RelatorioClientePorMontanteVo {

	private String nomeCliente;
	private BigDecimal montanteGasto;
	private Long quantidadeDePedidos;

	public RelatorioClientePorMontanteVo(String nomeCliente, BigDecimal montanteGasto, Long quantidadeDePedidos) {
		this.nomeCliente = nomeCliente;
		this.montanteGasto = montanteGasto;
		this.quantidadeDePedidos = quantidadeDePedidos;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public BigDecimal getMontanteGasto() {
		return montanteGasto;
	}

	public Long getQuantidadeDePedidos() {
		return quantidadeDePedidos;
	}

}
