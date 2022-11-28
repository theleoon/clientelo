package br.com.alura.clientelo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.clientelo.dto.CategoriaDto;
import br.com.alura.clientelo.dto.ClienteDto;
import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.repository.CategoriaRepository;

@Component
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CategoriaService.class);
	
	public void cadastrar(Categoria novaCategoria) {
		
		if (novaCategoria == null) return;
		
		categoriaRepository.save(novaCategoria);
	}

	public List<CategoriaDto> getCategorias() {
		
		List<Categoria> categorias = new ArrayList<>();
		categoriaRepository.findAll().forEach(categorias::add);
		
		return CategoriaDto.converter(categorias);
	}

	public CategoriaDto get(Long id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		
		Categoria categoria = obj.orElse(new Categoria());
		
		return CategoriaDto.converter(categoria);
	}
	


}
