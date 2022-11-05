package br.com.alura.clientelo.report;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Produto;
import br.com.alura.clientelo.repository.ProdutoDao;

public class RelatorioProdutosMaisCarosPorCategoria implements Report {

	private static final Logger logger = LoggerFactory.getLogger(RelatorioProdutosMaisCarosPorCategoria.class);
	
	@Override
	public void export() {
		
		ProdutoDao produtoDao = new ProdutoDao();
		Map<Categoria, List<Produto>> retorno = produtoDao.listaMaisCaroPorCategoria();
		
		StringBuilder response = new StringBuilder();
        response.append("##### RELATÓRIO DE PRODUTOS MAIS CAROS POR CATEGORIA ##### \n");
        response.append("\n");

        retorno.forEach((categoria, produtos) -> {
        	produtos.forEach(p -> {
        		 response.append(String.format("CATEGORIA: %s \n", categoria));
                 response.append(String.format("PRODUTO: %s \n", p.getNome()));
                 response.append(String.format("PREÇO: %s \n", p.getPreco()));
                 response.append("\n");
        	});
        });

        response.append("##### FIM DO RELATÓRIO ##### \n");
        logger.info(response.toString());
	}

}
