package com.example.despesas.api.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.despesas.api.converter.PessoaConverter;
import com.example.despesas.api.model.Pessoa;
import com.example.despesas.api.model.dtos.PessoaDTO;
import com.example.despesas.api.repository.PessoaRepository;
import com.example.despesas.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaConverter pessoaConverter;

	@Transactional
	public Page<PessoaDTO> filtrar(String nome, Pageable pageable) {
		Page<Pessoa> lista = null;
		if (nome != null) {
			lista = pessoaRepository.findByNomeContains(nome, pageable);
		} else {
			lista = pessoaRepository.findAll(pageable);
		}
		return new PageImpl<PessoaDTO>(pessoaConverter.toListToEntityToDto(lista.getContent()), pageable,
				lista.getTotalElements());
	}

	@Transactional
	public PessoaDTO salvar(PessoaDTO pessoaDTO) {
		Pessoa pessoa = pessoaConverter.toDtoToEntity(pessoaDTO);
		return pessoaConverter.toEntityToDto(pessoaRepository.save(pessoa));
	}

	@Transactional
	public PessoaDTO buscarPorCodigo(Long codigo) {
		Pessoa pessoa = pessoaRepository.findOne(codigo);
		if (pessoa == null) {
			throw new EmptyResultDataAccessException(1);
		}

		return pessoaConverter.toEntityToDto(pessoa);
	}

	@Transactional
	public void remover(Long codigo) {
		pessoaRepository.delete(codigo);
	}

	@Transactional
	public PessoaDTO atualizar(Long codigo, PessoaDTO pessoaDTO) {
		PessoaDTO pessoaSalva = buscarPorCodigo(codigo);
		BeanUtils.copyProperties(pessoaDTO, pessoaSalva, "codigo");
		pessoaRepository.save(pessoaConverter.toDtoToEntity(pessoaSalva));
		return pessoaSalva;
	}

	public void atualizaPropriedadeAtivo(Long codigo, Boolean ativo) {
		PessoaDTO pessoaDTO = buscarPorCodigo(codigo);
		pessoaDTO.setAtivo(ativo);
		pessoaRepository.save(pessoaConverter.toDtoToEntity(pessoaDTO));
	}

	public void validarIsAtivo(Long codigo) throws PessoaInexistenteOuInativaException {
		Pessoa pessoa = pessoaRepository.findOne(codigo);
		if (pessoa == null || pessoa.getAtivo() == false) {
			throw new PessoaInexistenteOuInativaException();
		}
	}

}
