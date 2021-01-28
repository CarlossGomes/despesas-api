package com.example.despesas.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.despesas.api.converter.LancamentoConverter;
import com.example.despesas.api.model.Lancamento;
import com.example.despesas.api.model.dtos.LancamentoDTO;
import com.example.despesas.api.repository.LancamentoRepository;
import com.example.despesas.api.repository.filter.LancamentoFilter;
import com.example.despesas.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private LancamentoConverter lancamentoConverter;

	@Autowired
	private PessoaService PessoaService;

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

	@Transactional
	public LancamentoDTO salvar(LancamentoDTO lancamentoDTO) throws PessoaInexistenteOuInativaException {
		PessoaService.validarIsAtivo(lancamentoDTO.getPessoa().getCodigo());
		Lancamento lancamento = lancamentoConverter.toDtoToEntity(lancamentoDTO);
		return lancamentoConverter.toEntityToDto(lancamentoRepository.save(lancamento));
	}

	@Transactional
	public Page<LancamentoDTO> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		Page<Lancamento> page = lancamentoRepository.filtrar(lancamentoFilter, pageable);

		return new PageImpl<LancamentoDTO>(lancamentoConverter.toListToEntityToDto(page.getContent()), pageable,
				page.getTotalElements());
	}

	@Transactional
	public void remover(Long codigo) {
		lancamentoRepository.delete(codigo);
	}

}
