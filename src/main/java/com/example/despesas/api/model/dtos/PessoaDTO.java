package com.example.despesas.api.model.dtos;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PessoaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9033959376726027624L;

	private Long codigo;

	@Embedded
	private EnderecoDTO endereco;

	@NotNull
	private String nome;

	@NotNull
	private Boolean ativo;

}
