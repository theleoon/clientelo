package br.com.alura.exemplos;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class ExemploJava8 {
	
	public static void main(String[] args) {
		
		LocalDate hoje = LocalDate.now();
		System.out.println("Hoje: " + hoje);
		
		LocalTime horario = LocalTime.parse("15:58", DateTimeFormatter.ofPattern("HH:mm"));
		System.out.println(horario);
		
		String hojeFormatado = hoje.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.println(hojeFormatado);
		
		
		LocalDate vencimentoDaFatura = LocalDate.of(2022, 12, 30);
		System.out.println("Sua fatura vence em " + vencimentoDaFatura);
		
		Period periodo = Period.between(hoje, vencimentoDaFatura);
		
		System.out.println(periodo.getMonths() + " mês e " + periodo.getDays() + " dias para o vencimento");
		
//		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		System.out.println(hoje.format(formatador));
//		
//		LocalDateTime agora = LocalDateTime.now();
//		System.out.println("Agora: " + agora);
		
	}

}
