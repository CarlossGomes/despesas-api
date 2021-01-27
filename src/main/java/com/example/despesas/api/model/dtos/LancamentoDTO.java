package com.example.despesas.api.model.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.despesas.api.model.TipoLancamento;

import lombok.Data;

@Data
public class LancamentoDTO implements Serializable{
;

	/**
	 * 
	 */
	private static final long serialVersionUID = 6046893584313315619L;

	private Long codigo;

	private String descricao;

	private LocalDate dataVencimento;

	private LocalDate dataPagamento;

	private BigDecimal valor;

	private TipoLancamento tipo;

	private CategoriaDTO categoria;

	private PessoaDTO pessoa;

}
