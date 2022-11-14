package br.com.alura.clientelo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.clientelo.model.ItemPedido;

@Repository
public interface ItemPedidoRepository extends CrudRepository<ItemPedido, Long>{

}
