package com.example.despesas.api.repository.lancamento;

import java.util.List;

import com.example.despesas.api.model.Lancamento;
import com.example.despesas.api.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);

}
