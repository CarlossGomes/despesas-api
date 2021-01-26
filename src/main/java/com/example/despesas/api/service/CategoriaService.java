package com.example.despesas.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.despesas.api.model.Categoria;
import com.example.despesas.api.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Transactional
	public List<Categoria> listar() {
		List<Categoria> lista = categoriaRepository.findAll();
//		if (lista.isEmpty()) {
//			throw new NotFoundException(Mensagens.MSG_ERRO_LISTA_VAZIA);
//		}
		return lista;
	}

	@Transactional
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Transactional
	public Categoria buscarPorCodigo(Long codigo) {
		return categoriaRepository.findOne(codigo);
	}

}
