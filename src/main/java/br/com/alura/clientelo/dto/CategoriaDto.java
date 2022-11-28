package br.com.alura.clientelo.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.clientelo.model.Categoria;

public class CategoriaDto {
	
	private Long id;
	private String nome;
	private Boolean ativa;
	
	public CategoriaDto(String nome) {
		this.nome = nome;
	}

	public CategoriaDto(Categoria categoria) {
		this.nome = categoria.getNome();
		this.id = categoria.getId();
		this.ativa = categoria.getAtiva();
	}

	public CategoriaDto(String nome, Long id, Boolean ativa) {
		this.nome = nome;
		this.id = id;
		this.ativa = ativa;
	}

	public static Categoria converter(CategoriaDto categoriaDto) {
		return new Categoria(categoriaDto.getNome(), categoriaDto.getId(), categoriaDto.getAtiva());
	}
	
	public static CategoriaDto converter(Categoria obj) {
		return new CategoriaDto(obj.getNome(), obj.getId(), obj.getAtiva());
	}
	
	public static List<CategoriaDto> converter(List<Categoria> categorias) {
		List<CategoriaDto> categoriasDto = new ArrayList<>();
		categorias.forEach(c -> {
			categoriasDto.add(new CategoriaDto(c.getNome(), c.getId(), c.getAtiva()));
		});
		
		return categoriasDto;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}

	
	
}
