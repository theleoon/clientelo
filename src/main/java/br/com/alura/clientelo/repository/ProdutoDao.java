package br.com.alura.clientelo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Produto;

@SuppressWarnings("unchecked")
public class ProdutoDao implements Dao<Produto>{
	
	private static final Logger logger = LoggerFactory.getLogger(ProdutoDao.class);

	private EntityManagerFactory factory;
	private EntityManager em;
	
	public ProdutoDao() {
		factory = Persistence.createEntityManagerFactory("vendas");
		em = factory.createEntityManager();
	}

	@Override
	public Produto buscaPorId(Long id) {
		return em.find(Produto.class, id);
	}

	@Override
	public void cadastra(Produto t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}

	@Override
	public void atualiza(Produto t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
	}

	@Override
	public void remove(Produto t) {
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
	}

	@Override
	public List<Produto> listaTodos() {
		return em.createQuery("select p from Produto p", Produto.class).getResultList();
	}
	
	public List<Produto> listaIndisponiveis() {
		return em.createQuery("select p from Produto p where p.quantidadeEstoque = 0",
				Produto.class)
				.getResultList();
	}
	
	public Map<Categoria, List<Produto>> listaMaisCaroPorCategoria() {
		
		Map<Produto, Categoria> maisCaroPorCategoria = new HashMap<>();
		
		List<Object[]> retorno = em.createQuery("select p, c from Produto p join Categoria c on p.categoria = c group by p.id order by p.preco desc").getResultList();
		
		for (Object[] object : retorno) {
			Produto produto = (Produto) object[0];
			Categoria categoria = (Categoria) object[1];
			maisCaroPorCategoria.put(produto, categoria);
        }
		
		List<Categoria> categorias = new CategoriaDao().listaTodos();
        
        Map<Categoria, List<Produto>> produtosPorCategoria = new HashMap<>();
        categorias.forEach(c -> {
        	maisCaroPorCategoria.forEach((produto, categoria) -> { 
        		if (c.equals(categoria)) produtosPorCategoria.get(c).add(produto);	 
        	});
        });

		return produtosPorCategoria;
	}	
	

}
