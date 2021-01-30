package com.example.despesas.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.despesas.api.config.property.DespesasApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(DespesasApiProperty.class)
public class DespesasApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DespesasApiApplication.class, args);
	}

}
