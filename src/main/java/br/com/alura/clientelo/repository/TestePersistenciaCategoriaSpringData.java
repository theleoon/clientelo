package br.com.alura.clientelo.repository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.model.Categoria;

public class TestePersistenciaCategoriaSpringData {

	private static final Logger logger = LoggerFactory.getLogger(TestePersistenciaCategoriaSpringData.class);

	private CategoriaRepository categoriaRepo;

	public TestePersistenciaCategoriaSpringData(CategoriaRepository categoriaRepo) {
		this.categoriaRepo = categoriaRepo;
	}

	public void valida() {

		Categoria categoriaCelular = new Categoria("CELULARES");
		Categoria categoriaInformatica = new Categoria("INFORMÁTICA");
		Categoria categoriaMoveis = new Categoria("MÓVEIS");
		Categoria categoriaLivros = new Categoria("LIVROS");
		Categoria categoriaAutomotiva = new Categoria("AUTOMOTIVA");

		categoriaRepo.save(categoriaCelular);
		categoriaRepo.save(categoriaInformatica);
		categoriaRepo.save(categoriaMoveis);
		categoriaRepo.save(categoriaLivros);
		categoriaRepo.save(categoriaAutomotiva);

		logger.info("Categoria cadastrada com sucesso!");

		// BUSCAR
		Optional<Categoria> categoria = categoriaRepo.findById(1l);

		logger.info("Categoria cadastrada: {} ", categoria.get().getNome());

	}

}
