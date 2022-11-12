package br.com.alura.clientelo.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false, length = 160)
	private String nome;
	
	@Column(name = "preco", nullable = false)
	private BigDecimal preco;
	
	@Column(name = "descricao", nullable = true)
	private String descricao;
	
	@Column(name = "quantidade_estoque")
	private Integer quantidadeEstoque;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Categoria categoria;
	
	/** 
	 * @deprecated Hibernate only */
	public Produto() {}
	
	public Produto(String nome, BigDecimal preco, String descricao, Integer quantidadeEstoque, Categoria categoria) {
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.quantidadeEstoque = quantidadeEstoque;
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", descricao=" + descricao
				+ ", quantidadeEstoque=" + quantidadeEstoque + ", categoria=" + categoria + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}
	
	
	
}
