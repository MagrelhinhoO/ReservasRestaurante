package com.restaurante.service;

import com.restaurante.model.Administrador;
import com.restaurante.repository.AdministradorRepository;

import java.util.List;

public class AdministradorService {

    private final AdministradorRepository repository = new AdministradorRepository();

    public void cadastrarAdministrador(String nome, String email) {
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
        repository.atualizar(admin);
    }

    public void remover(int id) {
        repository.deletar(id);
    }
}
