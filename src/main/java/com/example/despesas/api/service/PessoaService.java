package com.example.despesas.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.despesas.api.model.Pessoa;
import com.example.despesas.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Transactional
	public List<Pessoa> listar() {
		List<Pessoa> lista = pessoaRepository.findAll();
		return lista;
	}

	@Transactional
	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@Transactional
	public Pessoa buscarPorCodigo(Long codigo) {
		return pessoaRepository.findOne(codigo);
	}

}
