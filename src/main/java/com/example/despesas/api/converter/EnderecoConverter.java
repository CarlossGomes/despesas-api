package com.example.despesas.api.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.despesas.api.model.Endereco;
import com.example.despesas.api.model.dtos.EnderecoDTO;

@Component
public class EnderecoConverter {
	
	public Endereco toDtoToEntity(EnderecoDTO enderecoDTO) {
		Endereco endereco = new Endereco();
		BeanUtils.copyProperties(enderecoDTO, endereco);
		return endereco;
	}

	public EnderecoDTO toEntityToDto(Endereco endereco) {
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		BeanUtils.copyProperties(endereco, enderecoDTO);
		return enderecoDTO;
	}

}
