package com.example.despesas.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.despesas.api.event.RecursoCriadoEvent;
import com.example.despesas.api.model.dtos.CategoriaDTO;
import com.example.despesas.api.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
	public List<CategoriaDTO> listar() {
		return categoriaService.listar();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
	public ResponseEntity<CategoriaDTO> criar(@Valid @RequestBody CategoriaDTO categoriaDTO,
			HttpServletResponse response) {
		CategoriaDTO categoriaSalva = categoriaService.salvar(categoriaDTO);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaDTO.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
	public ResponseEntity<?> buscarPorCodigo(@PathVariable Long codigo) {
		return ResponseEntity.ok(categoriaService.buscarPorCodigo(codigo));
		// return categoriaDTO != null ? ResponseEntity.ok(categoriaDTO) :
		// ResponseEntity.noContent().build();
	}

}
