package com.example.despesas.api.model.dtos;

import java.io.Serializable;
import java.util.List;

import com.example.despesas.api.model.Permissao;

import lombok.Data;

@Data
public class UsuarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7168665204196776027L;

	private Long codigo;

	private String nome;

	private String email;

	private String senha;

	private List<Permissao> permissoes;

}
