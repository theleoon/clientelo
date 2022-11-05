package br.com.alura.clientelo.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.model.Cliente;

@SuppressWarnings("unchecked")
public class ClienteDao implements Dao<Cliente>{
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteDao.class);
	
	
	private EntityManagerFactory factory;
	private EntityManager em;
	
	public ClienteDao() {
		factory = Persistence.createEntityManagerFactory("vendas");
		em = factory.createEntityManager();
	}

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
		return em.createQuery("select f from Cliente f", Cliente.class).getResultList();
	}
	
	public List<Cliente> listaPorNome(String nome) {
		
		if (nome.isEmpty()) throw new RuntimeException();
		
		return em.createQuery("select c from Cliente c where c.nome = :nome",
				Cliente.class)
				.setParameter("nome", nome)
				.getResultList();
	}

	public Map<Cliente, Long> listaPorMaisPedidos() {
		
		Map<Cliente, Long> clienteQuantidadeDePedidos = new HashMap<>();
		
		List<Object[]> retorno = em.createQuery("select c, count(p) from Cliente c join Pedido p on p.cliente = c group by c.id").getResultList();
		
		for (Object[] object : retorno) {
			
			Cliente cliente = (Cliente) object[0];
			Long quantidadeDePedidos = (Long) Long.valueOf(String.valueOf(object[1]));
			logger.info("listaPorMaisPedidos {}, Total: {} ", cliente, quantidadeDePedidos);
			clienteQuantidadeDePedidos.put(cliente, quantidadeDePedidos);
        }

		return clienteQuantidadeDePedidos;
	}

	public Map<Cliente, BigDecimal> listaPorMaiorValorGasto() {
		// select c.nome, sum(p.total_pedido) from cliente c join pedido p on p.cliente_id = c.id group by c.id;
		
		Map<Cliente, BigDecimal> clienteValorGasto = new HashMap<>();
		
		 List<Object[]> retorno = em.createQuery("select c, sum(p.totalPedido) from Cliente c join Pedido p on p.cliente = c group by c.id").getResultList();
		
		for (Object[] object : retorno) {
			
			Cliente cliente = (Cliente) object[0];
			BigDecimal totalGasto = (BigDecimal) new BigDecimal(String.valueOf(object[1]));
			logger.info("ListaPorMaiorValorGasto {}, Total: {} ", cliente, totalGasto);
			clienteValorGasto.put(cliente, totalGasto);
        }

		return clienteValorGasto;
	}	
	
}
