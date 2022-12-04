package br.com.alura.clientelo.config.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiDependencyHealthIndicator implements HealthIndicator {
	
	private String message;
	private Integer errorCode;
	private Boolean up = Boolean.FALSE;

	@Override
	public Health health() {

		try {
			check();
		} catch (Exception ex) {
			this.errorCode = 1;
			this.message = "Api Internal Error";
			this.up = Boolean.FALSE;
		}

		if (up.equals(Boolean.FALSE)) {
			return Health.down()
					.withDetail("Message", this.message)
					.withDetail("Code", this.errorCode)
					.build();
		}
		
		return Health.up()
				.withDetail("Message", this.message)
				.withDetail("Code", this.errorCode)
				.build();
	}

	public void check() {
		String url = "http://google.com";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		
		if (response == null) {
			throw new RuntimeException();
		}

		switch (response.getStatusCode().value()) {
			case 200:
				
				this.up = Boolean.TRUE;
				this.message = "Service OK";
				this.errorCode = 0;

				break;

			case 404:
				
				this.up = Boolean.FALSE;
				this.message = "Service Not Found";
				this.errorCode = 404;

				break;

			case 500:
				
				this.up = Boolean.FALSE;
				this.message = "Service Internal Error";
				this.errorCode = 500;

				break;

			default:
				break;
		}
	}

}
