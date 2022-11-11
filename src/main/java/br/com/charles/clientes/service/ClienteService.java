package br.com.charles.clientes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.charles.clientes.domain.Cliente;

@Service
public class ClienteService {

	private Long nextId;

	private List<Cliente> clientes;

	public ClienteService() {
		nextId = 1l;
		clientes = new ArrayList<Cliente>();
	}

	public Cliente create(Cliente cliente) {
		cliente.setId(nextId);
		nextId++;
		clientes.add(cliente);
		return cliente;
	}

	public List<Cliente> findAll() {
		return clientes;
	}

	public Cliente findById(Long id) {
		Cliente cliente = new Cliente(id);
		int index = clientes.indexOf(cliente);
		if (index != -1) {
			return clientes.get(index);
		}
		
		return null;
	}

	public Cliente update(Long id, Cliente cliente) {
		Cliente persistido = findById(id);
		if (persistido == null) {
			return null;
		}

		persistido.setTel(cliente.getTel());
		persistido.setEnd(cliente.getEnd());
		persistido.setNumero(cliente.getNumero());
		persistido.setCidade(cliente.getCidade());
		persistido.setEstado(cliente.getEstado());

		return persistido;
	}

	public void delete(Long id) {
		/*
		 * for (Cliente cliente : clientes) { if (cliente.getId().equals(id)) {
		 * clientes.remove(cliente); } }
		 */

		for (int i = 0; i < clientes.size(); i++) {
			Cliente c = clientes.get(i);
			if (c.getId().equals(id)) {
				clientes.remove(i);
			}
		}

	}

}
