package br.com.alura.clientelo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.clientelo.dto.ClienteDto;
import br.com.alura.clientelo.dto.PedidoDto;
import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.repository.PedidoRepository;

@Component
public class PedidoService {

	private static final Logger logger = LoggerFactory.getLogger(PedidoService.class);

	@Autowired
	private PedidoRepository pedidoRepository;

	public Optional<Integer> getQuantidadeDePedidos(Optional<List<Pedido>> pedidos) {
		return Optional.of(pedidos.get().size());
	}

	public BigDecimal getTotalDeVendas(Optional<List<Pedido>> pedidos) {
		return pedidos.orElse(new ArrayList<Pedido>()).stream().map(pedido -> pedido.getTotalPedido())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getTotalDeProdutosVendidos(Optional<List<Pedido>> pedidos) {
		return pedidos.orElse(new ArrayList<Pedido>()).stream().map(pedido -> new BigDecimal(pedido.getQuantidade()))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public Optional<Pedido> getPedidoMaisBarato(Optional<List<Pedido>> pedidos) {
		Optional<Pedido> pedido = Optional.empty();

		if (pedidos.isPresent()) {
			pedidos.get().sort(Comparator.comparing(Pedido::getTotalPedido));
			pedido = Optional.of(pedidos.get().get(0));
		}

		return pedido;
	}

	public Optional<Pedido> getPedidoMaisCaro(Optional<List<Pedido>> pedidos) {
		Optional<Pedido> pedido = Optional.empty();

		if (pedidos.isPresent()) {
			pedidos.get().sort(Comparator.comparing(Pedido::getTotalPedido));
			pedido = Optional.of(pedidos.get().get(pedidos.get().size() - 1));
		}

		return pedido;
	}

	public BigDecimal getTotalDePedidosRealizados(Optional<List<Pedido>> pedidos) {

		if (pedidos.isPresent()) {
			return new BigDecimal(pedidos.get().size());
		}

		return BigDecimal.ZERO;
	}

	public void cadastrar(Pedido obj) {
		pedidoRepository.save(obj);
	}

	public List<PedidoDto> get() {
		List<Pedido> objs = new ArrayList<>();
		pedidoRepository.findAll().forEach(objs::add);

		return PedidoDto.converter(objs);
	}

	public PedidoDto get(Long id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		Pedido pedido = obj.orElse(new Pedido());

		return new PedidoDto(pedido);
	}

}
