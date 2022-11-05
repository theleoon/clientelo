package br.com.alura.clientelo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.clientelo.model.ItemPedido;

public class ItemPedidoDao {

	private EntityManagerFactory factory;
	private EntityManager em;
	
	public ItemPedidoDao() {
		factory = Persistence.createEntityManagerFactory("vendas");
		em = factory.createEntityManager();
	}

	public ItemPedido buscaPorId(Long id) {
		return em.find(ItemPedido.class, id);
	}

	public void atualiza(ItemPedido t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
	}

	public void remove(ItemPedido t) {
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
	}

	public List<ItemPedido> listaTodos() {
		return em.createQuery("select f from ItemPedido f", ItemPedido.class).getResultList();
	}

}
