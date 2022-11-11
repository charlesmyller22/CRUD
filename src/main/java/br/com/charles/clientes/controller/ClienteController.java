package br.com.charles.clientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.charles.clientes.domain.Cliente;
import br.com.charles.clientes.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService service;

	@PostMapping
	public ResponseEntity<Cliente> create(@RequestBody(required = true) Cliente cliente) {
		if (cliente == null) {
			return ResponseEntity.badRequest().build();
		}

		cliente = service.create(cliente);

		return ResponseEntity.ok(cliente);
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> all() {
		List<Cliente> clientes = service.findAll();
		return ResponseEntity.ok(clientes);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> getById(@PathVariable Long id) {
		Cliente cliente = service.findById(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(cliente);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody(required = true) Cliente cliente) {
		ResponseEntity<Cliente> response = getById(id);
		if (HttpStatus.NOT_FOUND.equals(response.getStatusCode())) {
			return response;
		}
		
		Cliente atualizado = service.update(id, cliente);
		return ResponseEntity.ok(atualizado);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable Long id) {
		ResponseEntity<Cliente> response = getById(id);
		if (HttpStatus.NOT_FOUND.equals(response.getStatusCode())) {
			return response;
		}

		service.delete(id);

		return ResponseEntity.ok().build();
	}

}
