package br.com.alura.clientelo.dto;

import java.math.BigDecimal;

import br.com.alura.clientelo.model.Produto;

public class ProdutoDto {

	private Long id;

	private String nome;

	private BigDecimal preco;

	private String descricao;

	private Integer quantidadeEstoque;

	private CategoriaDto categoria;

	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		this.descricao = produto.getDescricao();
		this.quantidadeEstoque = produto.getQuantidadeEstoque();
		this.categoria = new CategoriaDto(produto.getCategoria());
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public CategoriaDto getCategoria() {
		return categoria;
	}

}
