package com.restaurante.service;

import com.restaurante.model.Administrador;
import com.restaurante.repository.AdministradorRepository;

import java.util.List;

public class AdministradorService {

    private final AdministradorRepository repository = new AdministradorRepository();

    public void cadastrarAdministrador(String nome, String email) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }

        Administrador admin = new Administrador(nome, email);
        repository.salvar(admin);
    }

    public Administrador buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public List<Administrador> listarTodos() {
        return repository.listarTodos();
    }

    public void atualizar(Administrador admin) {
        if (admin == null) {
            throw new IllegalArgumentException("Administrador não pode ser nulo");
        }
        repository.atualizar(admin);
    }

    public void remover(int id) {
        repository.deletar(id);
    }
}