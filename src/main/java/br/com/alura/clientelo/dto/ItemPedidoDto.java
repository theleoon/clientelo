package br.com.alura.clientelo.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.clientelo.model.ItemPedido;
import br.com.alura.clientelo.model.TipoDescontoProdutoEnum;

public class ItemPedidoDto {

	private Long id;

	private ProdutoDto produto;

	private BigDecimal precoUnitario;

	private int quantidade;

	private BigDecimal desconto;

	private TipoDescontoProdutoEnum tipoDesconto;

	public ItemPedidoDto(ItemPedido itemPedido) {
		this.id = itemPedido.getId();
		this.produto = new ProdutoDto(itemPedido.getProduto());
		this.precoUnitario = itemPedido.getPrecoUnitario();
		this.quantidade = itemPedido.getQuantidade();
		this.desconto = itemPedido.getDesconto();
		this.tipoDesconto = itemPedido.getTipoDesconto();
	}

	public Long getId() {
		return id;
	}

	public ProdutoDto getProduto() {
		return produto;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public TipoDescontoProdutoEnum getTipoDesconto() {
		return tipoDesconto;
	}

	public static List<ItemPedidoDto> converter(List<ItemPedido> itens) {
		List<ItemPedidoDto> retorno = new ArrayList<>();
		itens.forEach(i -> retorno.add(new ItemPedidoDto(i)));
		return retorno;
	}

}
