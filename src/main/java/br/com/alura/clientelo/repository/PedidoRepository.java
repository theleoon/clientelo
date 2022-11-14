package br.com.alura.clientelo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.clientelo.model.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

}
