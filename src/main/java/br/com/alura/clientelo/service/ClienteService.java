package br.com.alura.clientelo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.clientelo.dto.ClienteDto;
import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.repository.ClienteRepository;

@Component
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);
	
	public void cadastrar(Cliente obj) {
		
		if (obj == null) return;
		
		clienteRepository.save(obj);
	}

	public List<ClienteDto> get() {
		
		List<Cliente> objs = new ArrayList<>();
		clienteRepository.findAll().forEach(objs::add);
		
		return ClienteDto.converter(objs);
	}

	public ClienteDto get(Long id) {
		
		Optional<Cliente> obj = clienteRepository.findById(id);
		
		Cliente cliente = obj.orElse(new Cliente());
		
		return ClienteDto.converter(cliente);
	}

}
