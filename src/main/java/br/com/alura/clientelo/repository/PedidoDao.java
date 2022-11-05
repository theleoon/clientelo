package br.com.alura.clientelo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;

public class PedidoDao implements Dao<Pedido>{

	private EntityManagerFactory factory;
	private EntityManager em;
	
	public PedidoDao() {
		factory = Persistence.createEntityManagerFactory("vendas");
		em = factory.createEntityManager();
	}

	@Override
	public Pedido buscaPorId(Long id) {
		return em.find(Pedido.class, id);
	}

	@Override
	public void cadastra(Pedido t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}

	@Override
	public void atualiza(Pedido t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
	}

	@Override
	public void remove(Pedido t) {
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
	}

	@Override
	public List<Pedido> listaTodos() {
		return em.createQuery("select f from Pedido f", Pedido.class).getResultList();
	}

	public Map<Produto, Long> listaProdutosMaisVendidos() {
		
		// TODO

	    Map<Produto, Long> retorno = new HashMap<>();
		
		List<Object[]> consulta = em.createQuery("select p, c from Pedido p join Produto prd on p.categoria = c group by p.id order by p.preco desc").getResultList();
		
//			for (Object[] object : consulta) {
//				Produto produto = (Produto) object[0];
//				Categoria categoria = (Categoria) object[1];
//				maisCaroPorCategoria.put(produto, categoria);
//	        }

		return retorno;
	}

}
