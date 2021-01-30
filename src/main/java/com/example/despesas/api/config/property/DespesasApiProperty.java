package com.example.despesas.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.Getter;

@Getter
@ConfigurationProperties("despesas")
public class DespesasApiProperty {
	
	private String originPermitida = "http://localhost:8080";
	
	private final Seguranca seguranca = new Seguranca();

	@Data
	public static class Seguranca {
		private boolean enableHttps;

	}

}
