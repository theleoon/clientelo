package br.com.alura.clientelo.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class ExemploJava8 {
	
	public static void main(String[] args) {
		
		LocalDate hoje = LocalDate.now();
		System.out.println("Hoje: " + hoje);
		
		LocalDate vencimentoDaFatura = LocalDate.of(2022, 10, 30);
		System.out.println("Sua fatura vence em " + vencimentoDaFatura);
		
		Period periodo = Period.between(hoje, vencimentoDaFatura);
		
		System.out.println(periodo.getMonths() + " mês e " + periodo.getDays() + " dias para o vencimento");
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println(hoje.format(formatador));
		
		LocalDateTime agora = LocalDateTime.now();
		System.out.println("Agora: " + agora);
		
	}

}
