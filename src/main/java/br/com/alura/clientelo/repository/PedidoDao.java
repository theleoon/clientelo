package br.com.alura.clientelo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
import br.com.alura.clientelo.util.JPAUtil;

public class PedidoDao implements Dao<Pedido>{

	private EntityManager em = JPAUtil.getEntityManager();

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
		return em.createQuery("select p from Pedido p JOIN FETCH p.cliente", Pedido.class).getResultList();
	}

	public Map<Produto, Long> listaProdutosMaisVendidos() {
		
		// TODO precisa ser por ItemPedido > Produto

	    Map<Produto, Long> retorno = new HashMap<>();
	    
	    String jpql = "select p, c from Pedido p join Produto prd on p.categoria = c group by p.id order by p.preco desc";
		
		List<Object[]> consulta = em.createQuery(jpql).getResultList();
		
//			for (Object[] object : consulta) {
//				Produto produto = (Produto) object[0];
//				Categoria categoria = (Categoria) object[1];
//				maisCaroPorCategoria.put(produto, categoria);
//	        }

		return retorno;
	}

}
