package br.com.alura.clientelo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.clientelo.model.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long>{
	
	List<Categoria> findByNome(String nome);
	
	List<Categoria> findByAtiva(Boolean ativa);

}
