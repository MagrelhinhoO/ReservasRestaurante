package com.restaurante.controller;

import com.restaurante.model.Mesa;
import com.restaurante.service.MesaService;
import com.restaurante.utils.InputHandler;

import java.util.List;
import java.util.Scanner;

public class MesaController {

    private final MesaService service = new MesaService();
    private final Scanner sc = new Scanner(System.in);

    public void menuMesa() {
        int opcao;
        do {
            System.out.println("\n--- MENU MESA ---");
            System.out.println("1. Cadastrar Mesa");
            System.out.println("2. Listar Todas");
            System.out.println("3. Listar Disponíveis");
            System.out.println("4. Buscar por ID");
            System.out.println("5. Liberar Mesa");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            opcao = InputHandler.getIntInput(sc);

            switch (opcao) {
                case 1 -> cadastrarMesa();
                case 2 -> listarTodas();
                case 3 -> listarDisponiveis();
                case 4 -> buscarPorId();
                case 5 -> liberarMesa();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarMesa() {
        System.out.println("\n--- NOVA MESA ---");
        System.out.print("Número: ");
        int numero = InputHandler.getIntInput(sc);
        System.out.print("Capacidade: ");
        int capacidade = InputHandler.getIntInput(sc);

        service.cadastrarMesa(numero, capacidade, true);
        System.out.println("Mesa cadastrada com sucesso!");
    }

    private void listarTodas() {
        List<Mesa> mesas = service.listarTodas();
        if (mesas.isEmpty()) {
            System.out.println("Nenhuma mesa cadastrada.");
        } else {
            mesas.forEach(System.out::println);
        }
    }

    private void listarDisponiveis() {
        List<Mesa> mesas = service.listarDisponiveis();
        if (mesas.isEmpty()) {
            System.out.println("Nenhuma mesa disponível.");
        } else {
            mesas.forEach(System.out::println);
        }
    }

    private void buscarPorId() {
        System.out.print("Digite o ID: ");
        int id = InputHandler.getIntInput(sc);
        Mesa mesa = service.buscarPorId(id);

        if (mesa != null) {
            System.out.println(mesa);
        } else {
            System.out.println("Mesa não encontrada!");
        }
    }

    private void liberarMesa() {
        System.out.print("Digite o ID da mesa: ");
        int id = InputHandler.getIntInput(sc);
        service.liberarMesa(id);
        System.out.println("Mesa liberada!");
    }
}