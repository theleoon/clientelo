package br.com.alura.clientelo.dao;

import java.util.List;

public interface Dao<T> {
	
	public T buscaPorId(Long id);

	public void cadastra(T t);

	public void atualiza(T t);
	
	public void remove(T t);

	public List<T> listaTodos();


}
