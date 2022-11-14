package br.com.alura.clientelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.vo.RelatorioClientePedidosVo;
import br.com.alura.clientelo.vo.RelatorioClientePorMontanteVo;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>  {
	
	List<Cliente> findByNome(String nome);
	
	Cliente findByCpf(String cpf);
	
	@Query("select new br.com.alura.clientelo.vo.RelatorioClientePedidosVo (c.nome, count(p)) from Cliente c join Pedido p on p.cliente = c group by c.id")
    List<RelatorioClientePedidosVo> listaPorMaisPedidos();
	
	@Query("select new br.com.alura.clientelo.vo.RelatorioClientePorMontanteVo(c.nome, sum(p.totalPedido) as soma, count(p.id)) from Cliente c join Pedido p on p.cliente = c group by c.id order by soma DESC")
	List<RelatorioClientePorMontanteVo> listaPorMaiorValorGasto();	
	

}
