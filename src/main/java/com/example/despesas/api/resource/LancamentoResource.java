package com.example.despesas.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.despesas.api.event.RecursoCriadoEvent;
import com.example.despesas.api.model.dtos.LancamentoDTO;
import com.example.despesas.api.repository.filter.LancamentoFilter;
import com.example.despesas.api.service.LancamentoService;
import com.example.despesas.api.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public Page<LancamentoDTO> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoService.filtrar(lancamentoFilter, pageable);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<LancamentoDTO> buscarPorCodigo(@PathVariable Long codigo) {
		return ResponseEntity.ok(lancamentoService.buscarPorCodigo(codigo));
	}

	@PostMapping
	public ResponseEntity<LancamentoDTO> criar(@Valid @RequestBody LancamentoDTO lancamentoDTO,
			HttpServletResponse response) throws PessoaInexistenteOuInativaException {
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoDTO.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoService.salvar(lancamentoDTO));
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		lancamentoService.remover(codigo);
	}

}
