package br.com.alura.clientelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.clientelo.model.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
	
	@Query("select p from Produto p where p.quantidadeEstoque > 0")
	public List<Produto> listaDisponiveis();
	
	@Query(name = "select p from Produto p where p.quantidadeEstoque = 0", nativeQuery = true)
	public List<Produto> listaIndisponiveis();

}
