package br.com.alura.clientelo.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.model.Perfil;
import br.com.alura.clientelo.model.Usuario;

public class TestePersistenciaUsuario {
	
	private static final Logger logger = LoggerFactory.getLogger(TestePersistenciaUsuario.class);
	
	private UsuarioRepository usuarioRepository;
	
	public TestePersistenciaUsuario(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public void valida() {
		List<Perfil> perfis = new ArrayList<>();
		perfis.add(new Perfil("Administrador"));
		
		Usuario usuario = new Usuario("alura1", "$2a$10$vnLoEg2tQlxkps2cjkECJeYMoRk37/O98LdR54pVnb8KUjDQ8StJe", perfis);
		
		usuarioRepository.save(usuario);
		
	
		logger.info("Usuario cadastrado {}", usuarioRepository.findByLogin(usuario.getLogin()));

	}

}
