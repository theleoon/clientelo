package br.com.alura.clientelo.dao.testes;

import java.math.BigDecimal;

import br.com.alura.clientelo.dao.CategoriaDao;
import br.com.alura.clientelo.dao.ClienteDao;
import br.com.alura.clientelo.dao.PedidoDao;
import br.com.alura.clientelo.dao.ProdutoDao;
import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.model.Endereco;
import br.com.alura.clientelo.model.ItemPedido;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
import br.com.alura.clientelo.model.TipoDescontoPedidoEnum;
import br.com.alura.clientelo.model.TipoDescontoProdutoEnum;

public class TestePersistenciaPedido {
	
	public static void main(String[] args) {
		
		ProdutoDao produtoDao = new ProdutoDao();
		Produto novoProduto1 = produtoDao.buscaPorId(1l);
		Produto novoProduto2 = produtoDao.buscaPorId(2l);
		
		ClienteDao clienteDao = new ClienteDao();
		Cliente clienteJoao =  clienteDao.buscaPorId(1l);
		Cliente cliente2 =  clienteDao.buscaPorId(2l);
		Cliente cliente3 =  clienteDao.buscaPorId(3l);
		
		PedidoDao pedidoDao = new PedidoDao();
		Pedido novoPedidoJoao = new Pedido(null, clienteJoao, BigDecimal.ZERO, TipoDescontoPedidoEnum.NENHUM);
		ItemPedido itemPedido1 = new ItemPedido(novoProduto1, novoPedidoJoao, 2, BigDecimal.valueOf(200), TipoDescontoProdutoEnum.PROMOCAO);
		ItemPedido itemPedido2 = new ItemPedido(novoProduto2, novoPedidoJoao, 2, BigDecimal.valueOf(200), TipoDescontoProdutoEnum.PROMOCAO);
		novoPedidoJoao.adicionaItem(itemPedido1);
		novoPedidoJoao.adicionaItem(itemPedido2);
		pedidoDao.cadastra(novoPedidoJoao);
		
		Pedido novoPedido2 = new Pedido(null, cliente2, BigDecimal.TEN, TipoDescontoPedidoEnum.FIDELIDADE);
		ItemPedido itemPedido3 = new ItemPedido(novoProduto1, novoPedido2, 2, BigDecimal.valueOf(200), TipoDescontoProdutoEnum.PROMOCAO);
		ItemPedido itemPedido4 = new ItemPedido(novoProduto2, novoPedido2, 2, BigDecimal.valueOf(200), TipoDescontoProdutoEnum.PROMOCAO);
		novoPedido2.adicionaItem(itemPedido3);
		novoPedido2.adicionaItem(itemPedido4);
		pedidoDao.cadastra(novoPedido2);
		
		Pedido novoPedido3 = new Pedido(null, cliente3, BigDecimal.ZERO, TipoDescontoPedidoEnum.NENHUM);
		ItemPedido itemPedido5 = new ItemPedido(novoProduto1, novoPedido3, 2, BigDecimal.valueOf(200), TipoDescontoProdutoEnum.PROMOCAO);
		ItemPedido itemPedido6 = new ItemPedido(novoProduto2, novoPedido3, 2, BigDecimal.valueOf(200), TipoDescontoProdutoEnum.PROMOCAO);
		novoPedido3.adicionaItem(itemPedido5);
		novoPedido3.adicionaItem(itemPedido6);
		pedidoDao.cadastra(novoPedido3);
		
	}

}
