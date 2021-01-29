package com.example.despesas.api.model.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class PermissaoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2407387112667934300L;

	private Long codigo;

	private String descricao;

}
