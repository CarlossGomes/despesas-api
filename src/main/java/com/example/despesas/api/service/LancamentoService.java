package com.example.despesas.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.despesas.api.converter.LancamentoConverter;
import com.example.despesas.api.model.Lancamento;
import com.example.despesas.api.model.dtos.LancamentoDTO;
import com.example.despesas.api.repository.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private LancamentoConverter lancamentoConverter;

	@Transactional
	public List<LancamentoDTO> listar() {
		List<Lancamento> lista = lancamentoRepository.findAll();
		return lancamentoConverter.toListToEntityToDto(lista);
	}

	@Transactional
	public LancamentoDTO buscarPorCodigo(Long codigo) {
		Lancamento lancamento = lancamentoRepository.findOne(codigo);
		if (lancamento == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return lancamentoConverter.toEntityToDto(lancamento);
	}

	public LancamentoDTO salvar(LancamentoDTO lancamentoDTO) {
		Lancamento lancamento = lancamentoConverter.toDtoToEntity(lancamentoDTO);
		return lancamentoConverter.toEntityToDto(lancamentoRepository.save(lancamento));
	}

}
