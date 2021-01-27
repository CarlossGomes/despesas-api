package com.example.despesas.api.model.dtos;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class EnderecoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5643642205275323466L;
	
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
}
