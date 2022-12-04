package br.com.alura.clientelo.config.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ApiDataSourceHealthIndicator implements HealthIndicator {

	private String message;
	private Integer errorCode;
	private Boolean up = Boolean.FALSE;

	@Override
	public Health health() {

		check(); // health check específico

		if (up.equals(Boolean.FALSE)) {
			return Health
					.down()
					.withDetail("Message", this.message)
					.withDetail("Code", this.errorCode)
					.build();
		}
		
		return Health
				.up()
				.withDetail("Message", this.message)
				.withDetail("Code", this.errorCode)
				.build();
	}

	public void check() {
		// implementar sua lógica para validar
		this.errorCode = 0;
		this.up = Boolean.TRUE;
		this.message = "Api Service OK";

	}

}
