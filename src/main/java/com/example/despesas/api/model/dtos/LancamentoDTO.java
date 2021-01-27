package com.example.despesas.api.model.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

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

	@NotNull
	private String descricao;

	@NotNull
	private LocalDate dataVencimento;

	private LocalDate dataPagamento;

	@NotNull
	private BigDecimal valor;

	@NotNull
	private TipoLancamento tipo;

	@NotNull
	private CategoriaDTO categoria;

	@NotNull
	private PessoaDTO pessoa;

}
