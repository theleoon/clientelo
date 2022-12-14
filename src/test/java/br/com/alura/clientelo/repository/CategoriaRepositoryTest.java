package br.com.alura.clientelo.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.alura.clientelo.model.Categoria;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CategoriaRepositoryTest {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Test
	public void deveriaCarregarUmaCategoriaPeloId() {
		
		Categoria novaCategoria = new Categoria("Celulares");
		
		categoriaRepository.save(novaCategoria);
		
		Optional<Categoria> categoria = categoriaRepository.findById(novaCategoria.getId());
		Assert.assertNotNull(categoria.get());
		Assert.assertEquals(categoria.get().getId(), novaCategoria.getId());
	}

}
