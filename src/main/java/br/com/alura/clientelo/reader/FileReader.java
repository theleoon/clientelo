package br.com.alura.clientelo.reader;

import java.net.URL;
import java.util.List;

public interface FileReader<T> {

	List<T> readFromJson(URL url);
	
	List<T> readFromCsv(URL url);

}
