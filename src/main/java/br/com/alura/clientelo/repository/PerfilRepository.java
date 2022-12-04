package br.com.alura.clientelo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.clientelo.model.Perfil;

@Repository
public interface PerfilRepository extends CrudRepository<Perfil, Long>{
	
	List<Perfil> findByNome(String nome);
	

}
