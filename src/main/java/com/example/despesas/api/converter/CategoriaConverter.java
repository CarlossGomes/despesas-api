package com.example.despesas.api.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.despesas.api.model.Categoria;
import com.example.despesas.api.model.dtos.CategoriaDTO;

@Component
public class CategoriaConverter {

	public Categoria toDtoToEntity(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();
		BeanUtils.copyProperties(categoriaDTO, categoria);
		return categoria;
	}

	public CategoriaDTO toEntityToDto(Categoria categoria) {
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		BeanUtils.copyProperties(categoria, categoriaDTO);
		return categoriaDTO;
	}

	public List<CategoriaDTO> toListToEntityToDto(List<Categoria> lista) {
		List<CategoriaDTO> listaDTO = new ArrayList<>();
		for (Categoria categoria : lista) {
			listaDTO.add(toEntityToDto(categoria));
		}
		return listaDTO;
	}

	public CategoriaDTO toEntityToDto(String nome) {
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setNome(nome);
		return categoriaDTO;
	}

}
