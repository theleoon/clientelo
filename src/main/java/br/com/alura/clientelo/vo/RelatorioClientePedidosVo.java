package br.com.alura.clientelo.vo;

public class RelatorioClientePedidosVo {

	private String nomeDoCliente;
	private Long quantidadeDePedidos;

	public RelatorioClientePedidosVo(String nomeDoCliente, Long quantidadeDePedidos) {
		this.nomeDoCliente = nomeDoCliente;
		this.quantidadeDePedidos = quantidadeDePedidos;
	}

	public Long getQuantidadeDePedidos() {
		return quantidadeDePedidos;
	}

	public String getNomeDoCliente() {
		return nomeDoCliente;
	}

}
