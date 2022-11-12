package br.com.alura.clientelo.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static String persistenceUnitVendas = "vendas";
	
	 public static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory(persistenceUnitVendas).createEntityManager();
	 }

}
