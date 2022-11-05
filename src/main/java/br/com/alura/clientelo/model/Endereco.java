package br.com.alura.clientelo.model;

import javax.persistence.Column;

public class Endereco {
	
	@Column(name = "rua", nullable = false, length = 100)
	private String rua;
	
	@Column(name = "numero", nullable = false, length = 10)
	private String numero;
	
	@Column(name = "complemento", nullable = true, length = 100)
	private String complemento;
	
	@Column(name = "bairro", nullable = false, length = 100)
	private String bairro;
	
	@Column(name = "cidade", nullable = false, length = 60)
	private String cidade;
	
	@Column(name = "estado", nullable = false, length = 60)
	private String estado;
	
	public Endereco(String rua, String numero, String complemento, String bairro, String cidade, String estado) {
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}
	
	public Endereco() {}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}