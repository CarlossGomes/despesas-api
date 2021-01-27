package com.example.despesas.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.despesas.api.converter.PessoaConverter;
import com.example.despesas.api.model.Pessoa;
import com.example.despesas.api.model.dtos.PessoaDTO;
import com.example.despesas.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaConverter pessoaConverter;

	@Transactional
	public List<PessoaDTO> listar() {
		List<Pessoa> lista = pessoaRepository.findAll();
		return pessoaConverter.toListToEntityToDto(lista);
	}

	@Transactional
	public PessoaDTO salvar(PessoaDTO pessoaDTO) {
		Pessoa pessoa = pessoaConverter.toDtoToEntity(pessoaDTO);
		return pessoaConverter.toEntityToDto(pessoaRepository.save(pessoa));
	}

	@Transactional
	public PessoaDTO buscarPorCodigo(Long codigo) {
		return pessoaConverter.toEntityToDto(pessoaRepository.findOne(codigo));
	}

	@Transactional
	public void remover(Long codigo) {
		pessoaRepository.delete(codigo);
	}

}
