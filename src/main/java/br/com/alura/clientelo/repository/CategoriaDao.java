package br.com.alura.clientelo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.clientelo.model.Categoria;

public class CategoriaDao implements Dao<Categoria>{
	
	private EntityManagerFactory factory;
	private EntityManager em;
	
	public CategoriaDao() {
		factory = Persistence.createEntityManagerFactory("vendas");
		em = factory.createEntityManager();
	}

	@Override
	public void cadastra(Categoria t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}

	@Override
	public void atualiza(Categoria t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
	}

	@Override
	public List<Categoria> listaTodos() {
		
		return em.createQuery("select f from Categoria f", Categoria.class).getResultList();
		
	}

	@Override
	public Categoria buscaPorId(Long id) {
		return em.find(Categoria.class, id);
	}

	@Override
	public void remove(Categoria t) {
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
	}

}
