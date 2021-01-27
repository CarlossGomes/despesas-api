package com.example.despesas.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.despesas.api.converter.CategoriaConverter;
import com.example.despesas.api.model.Categoria;
import com.example.despesas.api.model.dtos.CategoriaDTO;
import com.example.despesas.api.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CategoriaConverter categoriaConverter;

	@Transactional
	public List<CategoriaDTO> listar() {
		List<Categoria> lista = categoriaRepository.findAll();
//		if (lista.isEmpty()) {
//			throw new NotFoundException(Mensagens.MSG_ERRO_LISTA_VAZIA);
//		}
		return categoriaConverter.toListToEntityToDto(lista);
	}

	@Transactional
	public CategoriaDTO salvar(CategoriaDTO categoriaDTO) {
		Categoria categoria = categoriaConverter.toDtoToEntity(categoriaDTO);
		return categoriaConverter.toEntityToDto(categoriaRepository.save(categoria));
	}

	@Transactional
	public CategoriaDTO buscarPorCodigo(Long codigo) {
		return categoriaConverter.toEntityToDto(categoriaRepository.findOne(codigo));
	}

}
