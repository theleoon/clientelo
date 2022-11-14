package br.com.alura.clientelo.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.util.JPAUtil;
import br.com.alura.clientelo.vo.RelatorioClientePedidosVo;
import br.com.alura.clientelo.vo.RelatorioClientePorMontanteVo;

@SuppressWarnings("unchecked")
public class ClienteDao implements Dao<Cliente>{
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteDao.class);
	
	private EntityManager em = JPAUtil.getEntityManager();

	@Override
	public Cliente buscaPorId(Long id) {
		return em.find(Cliente.class, id);
	}

	@Override
	public void cadastra(Cliente t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}

	@Override
	public void atualiza(Cliente t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
	}

	@Override
	public void remove(Cliente t) {
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
	}

	@Override
	public List<Cliente> listaTodos() {
		return em.createQuery("select c from Cliente c", Cliente.class).getResultList();
	}
	
	public List<Cliente> listaTodosComLazy() {
		return em.createQuery("select c from Cliente c JOIN FETCH c.pedidos", Cliente.class).getResultList();
	}
	
	public static void main(String[] args) {
		
//		new ClienteDao().listaTodos();
		new ClienteDao().listaTodosComLazy();
		
	}
	
	public List<Cliente> listaPorNome(String nome) {
		
		if (nome.isEmpty()) throw new RuntimeException();
		
		String jpql = "select c from Cliente c where c.nome = :nome";
		
		return em.createQuery(jpql, Cliente.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public Cliente buscaPorNomeCpf(String nome, String cpf) {
		
		if ((nome != null && nome.isEmpty()) && (cpf != null && cpf.isEmpty())) throw new RuntimeException();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> query = builder.createQuery(Cliente.class);
		Root<Cliente> from = query.from(Cliente.class);
		Predicate filtros = builder.and();
		
		if (nome != null && !nome.isEmpty()) filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
			
		if (cpf != null && !cpf.isEmpty()) filtros = builder.and(filtros, builder.equal(from.get("cpf"), cpf));
		
		query.where(filtros);
		
		return em.createQuery(query).getSingleResult();
	}
	
//	public static void main(String[] args) {
//		logger.info(new ClienteDao().buscaPorNomeCpf("Henrique Junior", "455.854.444-57").toString());
//	}
	
	public List<RelatorioClientePedidosVo> listaPorMaisPedidos() {
		
		String jpql = "select new "+ RelatorioClientePedidosVo.class.getName() +" (c.nome, count(p)) from Cliente c join Pedido p on p.cliente = c group by c.id";
		
		return em.createQuery(jpql, RelatorioClientePedidosVo.class).getResultList();
	}
	
	
	public List<RelatorioClientePorMontanteVo> listaPorMaiorValorGasto() {
		// select c.nome, sum(p.total_pedido) from cliente c join pedido p on p.cliente_id = c.id group by c.id;

		String jpql = "select new " + RelatorioClientePorMontanteVo.class.getName() + "(c.nome, sum(p.totalPedido) as soma, count(p.id)) from Cliente c join Pedido p on p.cliente = c group by c.id order by soma DESC";
		
		return em.createQuery(jpql, RelatorioClientePorMontanteVo.class).getResultList();
	}	
	
}
