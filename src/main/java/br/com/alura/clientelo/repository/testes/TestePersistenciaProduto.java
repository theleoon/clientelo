package br.com.alura.clientelo.repository.testes;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Produto;
import br.com.alura.clientelo.repository.CategoriaDao;
import br.com.alura.clientelo.repository.ProdutoDao;

public class TestePersistenciaProduto {
	
	private static final Logger logger = LoggerFactory.getLogger(TestePersistenciaProduto.class);
	
	public static void main(String[] args) {
		
		CategoriaDao categoriaDao = new CategoriaDao();
		
		ProdutoDao produtoDao = new ProdutoDao();
		Produto novoProdutoSamsung = new Produto("Samsung S12", new BigDecimal("2000"), "Celular Samsung", 2, categoriaDao.buscaPorId(1L));
		Produto novoProdutoApple = new Produto("Apple iPhone 12", new BigDecimal("8000"), "Celular Apple", 2, categoriaDao.buscaPorId(1L));
		produtoDao.cadastra(novoProdutoApple);
		produtoDao.cadastra(novoProdutoSamsung);
		
		Produto novoProdutoCategoriaInformatica1 = new Produto("Notebook Samsung", new BigDecimal("4600"), "Notebook Samsung 13 polegadas", 2, categoriaDao.buscaPorId(2L));
		Produto novoProdutoCategoriaInformatica2 = new Produto("Notebook Dell", new BigDecimal("6200"), "Notebook Dell 15 polegadas", 2, categoriaDao.buscaPorId(2L));
		produtoDao.cadastra(novoProdutoCategoriaInformatica1);
		produtoDao.cadastra(novoProdutoCategoriaInformatica2);
		
		Produto novoProdutoCategoriaLivros1 = new Produto("Clean Architecture", new BigDecimal("36"), "Impresso", 2, categoriaDao.buscaPorId(4L));
		Produto novoProdutoCategoriaLivros2 = new Produto("Implementing Domain-Driven Design", new BigDecimal("6200"), "Impresso atualizado", 2, categoriaDao.buscaPorId(4L));
		produtoDao.cadastra(novoProdutoCategoriaLivros1);
		produtoDao.cadastra(novoProdutoCategoriaLivros2);
		
		logger.info("Produtos cadastrados com sucesso!");

	}

}
