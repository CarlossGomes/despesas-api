package com.example.despesas.api.model.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoriaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2733038345428874904L;

	private Long codigo;

	@NotNull
	@Size(min = 3, max = 20)
	private String nome;

}
