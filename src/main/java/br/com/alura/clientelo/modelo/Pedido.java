package br.com.alura.clientelo.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {

    private String categoria;
    private String produto;
    private String cliente;

    private BigDecimal preco;
    private BigDecimal totalPedido;
    private int quantidade;

    private LocalDate data;

    public Pedido(String categoria, String produto, String cliente, BigDecimal preco, int quantidade, LocalDate data) {
        this.categoria = categoria;
        this.produto = produto;
        this.cliente = cliente;
        this.preco = preco;
        this.quantidade = quantidade;
        this.totalPedido = this.preco.multiply(new BigDecimal(this.quantidade));
        this.data = data;
    }

    public BigDecimal getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(BigDecimal totalPedido) {
		this.totalPedido = totalPedido;
	}

	public String getCategoria() {
        return categoria;
    }

    public String getProduto() {
        return produto;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "categoria='" + categoria + '\'' +
                ", produto='" + produto + '\'' +
                ", cliente='" + cliente + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }
    
    public String toTotalEProduto() {
        return "R$ " + totalPedido + " (" + produto + ")";
    }
    
    public boolean isMaisBaratoQue(Pedido outroPedido) {
    	return this.totalPedido.compareTo(outroPedido.totalPedido) < 1;
    }

    public boolean isMaisCaroQue(Pedido outroPedido) {
    	return this.totalPedido.compareTo(outroPedido.totalPedido) == 1;
    }

    public BigDecimal getValorTotal() {
    	return totalPedido;
    }

}
