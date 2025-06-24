package com.restaurante.service;

import com.restaurante.model.Cliente;
import com.restaurante.repository.ClienteRepository;

import java.util.List;

public class ClienteService {

    private final ClienteRepository repository = new ClienteRepository();

    public void cadastrarCliente(String nome, String email) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }

        Cliente cliente = new Cliente(nome, email);
        repository.salvar(cliente);
    }

    public Cliente buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public List<Cliente> listarTodos() {
        return repository.listarTodos();
    }

    public void atualizarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        repository.atualizar(cliente);
    }

    public void removerCliente(int id) {
        repository.deletar(id);
    }
}