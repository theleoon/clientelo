package br.com.alura.clientelo.controller;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
	@Transactional
	@CacheEvict(allEntries = true, value = "listaDePedidos") // Invalida o Cache pela chave.
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
	@Cacheable(value = "listaDePedidos")
	public Page<PedidoDto> lista(@PageableDefault(size = 2, page = 0, sort = "data", direction = Direction.DESC) Pageable paginacao) {
		// /api/pedidos?page=0&size=2&sort=totalPedido,asc
		return pedidoService.get(paginacao);
	}


}
