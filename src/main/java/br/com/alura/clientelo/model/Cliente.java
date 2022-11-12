package br.com.alura.clientelo.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false, length = 120)
	private String nome;
	
	@Column(name = "cpf", nullable = false, length = 20)
	private String cpf;
	
	@Column(name = "telefone", nullable = false, length = 30)
	private String telefone;
	
	@Embedded
	private Endereco endereco;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<Pedido> pedidos;
	
	public Cliente(String nome, String cpf, String telefone, Endereco endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
	}
	
	/** 
	 * @deprecated Hibernate only */
	public Cliente() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRua() {
		return endereco.getRua();
	}

	public void setRua(String rua) {
		this.endereco.setRua(rua);
	}

	public String getNumero() {
		return endereco.getNumero();
	}

	public void setNumero(String numero) {
		this.endereco.setNumero(numero);
	}

	public String getComplemento() {
		return endereco.getComplemento();
	}

	public void setComplemento(String complemento) {
		this.endereco.setComplemento(complemento);
	}

	public String getBairro() {
		return endereco.getBairro();
	}

	public void setBairro(String bairro) {
		this.endereco.setBairro(bairro);
	}

	public String getCidade() {
		return endereco.getCidade();
	}

	public void setCidade(String cidade) {
		this.endereco.setCidade(cidade);
	}

	public String getEstado() {
		return endereco.getEstado();
	}

	public void setEstado(String estado) {
		this.endereco.setEstado(estado);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + "]";
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public BigDecimal getQuantidadeDePedidos() {
		return BigDecimal.valueOf(pedidos.size());
	}

	public BigDecimal getMontanteGasto() {
		return this.pedidos.stream().map(pedido -> pedido.getTotalPedido()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
}
