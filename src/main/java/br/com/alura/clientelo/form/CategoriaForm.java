package br.com.alura.clientelo.form;

import javax.validation.constraints.NotEmpty;

import br.com.alura.clientelo.model.Categoria;

public class CategoriaForm {
	
	@NotEmpty
	private String nome;
	
	public Categoria toEntity() {
		return new Categoria(this.getNome());
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
