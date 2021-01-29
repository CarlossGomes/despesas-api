package com.example.despesas.api.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "permissao")
@EqualsAndHashCode(of = {"codigo"})
public class Permissao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1484608191970211004L;

	@Id
	private Long codigo;

	private String descricao;

}
