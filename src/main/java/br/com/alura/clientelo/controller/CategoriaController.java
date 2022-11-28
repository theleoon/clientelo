package br.com.alura.clientelo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.clientelo.dto.CategoriaDto;
import br.com.alura.clientelo.form.CategoriaForm;
import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoriaController.class);
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/{id}")
	@ResponseBody
	public CategoriaDto get(@PathVariable("id") Long id) {
		return categoriaService.get(id);
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDto> cadastro(@Valid CategoriaForm categoriaForm, BindingResult result, UriComponentsBuilder uriBuilder) {

		if (result.hasFieldErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		Categoria novaCategoria = categoriaForm.toEntity();
		categoriaService.cadastrar(novaCategoria);
		
		URI uri = uriBuilder.path("/api/categorias/{id}").buildAndExpand(novaCategoria.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoriaDto(novaCategoria));
	}
	
	@GetMapping
	@ResponseBody
	public List<CategoriaDto> lista() {
		return categoriaService.getCategorias();
	}

}
