package br.com.alura.clientelo.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.alura.clientelo.model.Cliente;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {
	
	// http://localhost:8080/swagger-ui.html
	
	@Bean
	public Docket clienteloApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors
				.basePackage("br.com.alura.clientelo"))
				.paths(PathSelectors.ant("/**")).build()
				.ignoredParameterTypes(Cliente.class);
	}

}
