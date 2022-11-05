package br.com.alura.clientelo.repository.testes;

import java.math.BigDecimal;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.model.Endereco;
import br.com.alura.clientelo.model.ItemPedido;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
import br.com.alura.clientelo.model.TipoDescontoPedidoEnum;
import br.com.alura.clientelo.model.TipoDescontoProdutoEnum;
import br.com.alura.clientelo.repository.CategoriaDao;
import br.com.alura.clientelo.repository.ClienteDao;
import br.com.alura.clientelo.repository.PedidoDao;
import br.com.alura.clientelo.repository.ProdutoDao;

public class TestePersistenciaPedido {
	
	public static void main(String[] args) {
		
		Categoria novaCategoria = new Categoria("Celular");
		new CategoriaDao().cadastra(novaCategoria);
		
		ProdutoDao produtoDao = new ProdutoDao();
		Produto novoProdutoSamsung = new Produto("Samsung S12", new BigDecimal("2000"), "Celular Samsung", 2, novaCategoria);
		Produto novoProdutoApple = new Produto("Apple iPhone 12", new BigDecimal("8000"), "Celular Apple", 2, novaCategoria);
		produtoDao.cadastra(novoProdutoApple);
		produtoDao.cadastra(novoProdutoSamsung);
		
		ClienteDao clienteDao = new ClienteDao();
		Cliente clienteJoao = new Cliente("João da Silva", "455.666.444-57", "1155556666", new Endereco("Rua Sem Nome", "22", "Perto da Padoca", "Planalto", "São Bernardo", "São Paulo"));
		clienteDao.cadastra(clienteJoao);
		
		PedidoDao pedidoDao = new PedidoDao();
		Pedido novoPedidoJoao = new Pedido(null, clienteJoao, BigDecimal.ZERO, TipoDescontoPedidoEnum.NENHUM);
		ItemPedido celularApple = new ItemPedido(novoProdutoApple, novoPedidoJoao, 2, BigDecimal.valueOf(200), TipoDescontoProdutoEnum.PROMOCAO);
		novoPedidoJoao.adicionaItem(celularApple);
		pedidoDao.cadastra(novoPedidoJoao);
		
	}

}
