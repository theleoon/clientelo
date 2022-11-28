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
import br.com.alura.clientelo.form.ClienteForm;
import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/{id}")
	@ResponseBody
	public ClienteDto getCliente(@PathVariable("id") Long id) {
		return clienteService.get(id);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDto> cadastro(@Valid ClienteForm form, BindingResult result, UriComponentsBuilder uriBuilder) {

		if (result.hasFieldErrors()) {
			logger.info("Erro no formulário");
		}
		
		Cliente obj = form.toEntity();
		clienteService.cadastrar(obj);
		
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(obj));
	}
	
	@GetMapping
	@ResponseBody
	public List<ClienteDto> lista() {
		return clienteService.get();
	}

}
