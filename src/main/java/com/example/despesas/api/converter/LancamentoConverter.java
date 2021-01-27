package com.example.despesas.api.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.despesas.api.model.Lancamento;
import com.example.despesas.api.model.dtos.LancamentoDTO;

@Component
public class LancamentoConverter {

	@Autowired
	private CategoriaConverter categoriaConverter;

	@Autowired
	private PessoaConverter pessoaConverter;

	public Lancamento toDtoToEntity(LancamentoDTO lancamentoDTO) {
		Lancamento lancamento = new Lancamento();
		BeanUtils.copyProperties(lancamentoDTO, lancamento);
		lancamento.setCategoria(categoriaConverter.toDtoToEntity(lancamentoDTO.getCategoria()));
		lancamento.setPessoa(pessoaConverter.toDtoToEntity(lancamentoDTO.getPessoa().getCodigo()));
		return lancamento;
	}

	public LancamentoDTO toEntityToDto(Lancamento lancamento) {
		LancamentoDTO lancamentoDTO = new LancamentoDTO();
		BeanUtils.copyProperties(lancamento, lancamentoDTO);
		lancamentoDTO.setCategoria(categoriaConverter.toEntityToDto(lancamento.getCategoria()));
		lancamentoDTO.setPessoa(lancamento.getPessoa().getEndereco() == null
				? pessoaConverter.toEntityToDto(lancamento.getPessoa().getCodigo())
				: pessoaConverter.toEntityToDto(lancamento.getPessoa()));
		return lancamentoDTO;
	}

	public LancamentoDTO toEntityToDtoCreate(Lancamento lancamento) {
		LancamentoDTO lancamentoDTO = new LancamentoDTO();
		BeanUtils.copyProperties(lancamento, lancamentoDTO);
		lancamentoDTO.setCategoria(categoriaConverter.toEntityToDto(lancamento.getCategoria()));
		lancamentoDTO.setPessoa(pessoaConverter.toEntityToDto(lancamento.getPessoa().getCodigo()));
		return lancamentoDTO;
	}

	public List<LancamentoDTO> toListToEntityToDto(List<Lancamento> lista) {
		List<LancamentoDTO> listaDTO = new ArrayList<>();
		for (Lancamento lancamento : lista) {
			listaDTO.add(toEntityToDto(lancamento));
		}
		return listaDTO;
	}

}
