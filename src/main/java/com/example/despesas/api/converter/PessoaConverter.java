package com.example.despesas.api.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.despesas.api.model.Pessoa;
import com.example.despesas.api.model.dtos.PessoaDTO;

@Component
public class PessoaConverter {

	@Autowired
	private EnderecoConverter enderecoConverter;

	public Pessoa toDtoToEntity(PessoaDTO pessoaDTO) {
		Pessoa pessoa = new Pessoa();
		BeanUtils.copyProperties(pessoaDTO, pessoa);
		pessoa.setEndereco(enderecoConverter.toDtoToEntity(pessoaDTO.getEndereco()));
		return pessoa;
	}

	public PessoaDTO toEntityToDto(Pessoa pessoa) {
		PessoaDTO pessoaDTO = new PessoaDTO();
		BeanUtils.copyProperties(pessoa, pessoaDTO);
		pessoaDTO.setEndereco(enderecoConverter.toEntityToDto(pessoa.getEndereco()));
		return pessoaDTO;
	}

	public List<PessoaDTO> toListToEntityToDto(List<Pessoa> lista) {
		List<PessoaDTO> listaDTO = new ArrayList<>();
		for (Pessoa pessoa : lista) {
			listaDTO.add(toEntityToDto(pessoa));
		}
		return listaDTO;
	}

	public Pessoa toDtoToEntity(Long codigo) {
		Pessoa pessoa = new Pessoa();
		pessoa.setCodigo(codigo);
		return pessoa;
	}

	public PessoaDTO toEntityToDto(Long codigo) {
		PessoaDTO pessoaDTO = new PessoaDTO();
		pessoaDTO.setCodigo(codigo);
		return pessoaDTO;
	}

}
