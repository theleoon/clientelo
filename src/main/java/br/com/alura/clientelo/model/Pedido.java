package br.com.alura.clientelo.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	
	@Column(name = "desconto")
	private BigDecimal desconto;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_desconto")
	private TipoDescontoPedidoEnum tipoDesconto;

	@Column(name = "total_pedido")
    private BigDecimal totalPedido;

    @Column(name = "data")
    private LocalDate data;
    
    /** 
	 * @deprecated Hibernate only */
    public Pedido() {}
 
    public Pedido(List<ItemPedido> itens, Cliente cliente, BigDecimal desconto,
			TipoDescontoPedidoEnum tipoDesconto) {
    	adicionaItens(itens);
		this.cliente = cliente;
		this.desconto = desconto;
		this.tipoDesconto = tipoDesconto;
		this.data = LocalDate.now();
	}

	@Override
    public String toString() {
        return "Pedido{" +
                ", cliente='" + cliente + '\'' +
                ", data=" + data +
                '}';
    }
    
    public boolean isMaisBaratoQue(Pedido outroPedido) {
    	return this.totalPedido.compareTo(outroPedido.totalPedido) < 1;
    }

    public boolean isMaisCaroQue(Pedido outroPedido) {
    	return this.totalPedido.compareTo(outroPedido.totalPedido) == 1;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public TipoDescontoPedidoEnum getTipoDesconto() {
		return tipoDesconto;
	}

	public void setTipoDesconto(TipoDescontoPedidoEnum tipoDesconto) {
		this.tipoDesconto = tipoDesconto;
	}

	public BigDecimal getTotalPedido() {
		return this.totalPedido;
	}
	
	public void updateTotalPedido() {
		this.totalPedido = itens.stream()
				.map(item -> item.getTotal())
				.reduce(BigDecimal.ZERO, BigDecimal::add)
				.subtract(getDesconto());
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public void adicionaItem(ItemPedido item) {
		if (item == null) return;
		this.itens.add(item);
		updateTotalPedido();
	}
	
	public void adicionaItens(List<ItemPedido> itens) {
		if (itens == null) return;
		this.itens.addAll(itens);
		updateTotalPedido();
	}


}
