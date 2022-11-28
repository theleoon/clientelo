package br.com.alura.clientelo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.clientelo.dto.ClienteDto;
import br.com.alura.clientelo.dto.PedidoDto;
import br.com.alura.clientelo.form.PedidoForm;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/{id}")
	@ResponseBody
	public PedidoDto get(@PathVariable("id") Long id) {
		return pedidoService.get(id);
	}
	
	@PostMapping
	public ResponseEntity<PedidoDto> cadastro(@Valid PedidoForm form, BindingResult result, UriComponentsBuilder uriBuilder) {

		if (result.hasFieldErrors()) {
			logger.info("Erro no formulário");
		}
		
		Pedido obj = form.toEntity();
		pedidoService.cadastrar(obj);
		
		URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(new PedidoDto(obj));
	}
	
	@GetMapping
	@ResponseBody
	public List<PedidoDto> lista() {
		return pedidoService.get();
	}


}
