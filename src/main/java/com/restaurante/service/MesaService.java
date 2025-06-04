package com.restaurante.service;

import com.restaurante.model.Mesa;
import com.restaurante.repository.MesaRepository;

import java.util.List;

public class MesaService {

    private final MesaRepository repository = new MesaRepository();

    public void cadastrarMesa(int numero, int capacidade, boolean disponivel) {
        Mesa mesa = new Mesa(numero, capacidade, disponivel);
        repository.salvar(mesa);
    }

    public List<Mesa> listarTodas() {
        return repository.listarTodas();
    }

    public List<Mesa> listarDisponiveis() {
        return repository.listarDisponiveis();
    }

    public Mesa buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public void atualizarMesa(Mesa mesa) {
        repository.atualizar(mesa);
    }

    public void removerMesa(int id) {
        repository.deletar(id);
    }
}
