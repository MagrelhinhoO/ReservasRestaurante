package com.restaurante.controller;

import com.restaurante.model.Cliente;
import com.restaurante.service.ClienteService;
import com.restaurante.utils.InputHandler;

import java.util.List;
import java.util.Scanner;

public class ClienteController {

    private final ClienteService service = new ClienteService();
    private final Scanner sc = new Scanner(System.in);

    public void menuCliente() {
        int opcao;
        do {
            System.out.println("\n--- MENU CLIENTE ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Atualizar Cliente");
            System.out.println("5. Remover Cliente");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            opcao = InputHandler.getIntInput(sc);

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> listarClientes();
                case 3 -> buscarPorId();
                case 4 -> atualizarCliente();
                case 5 -> removerCliente();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarCliente() {
        System.out.println("\n--- NOVO CLIENTE ---");
        System.out.print("Nome: ");
        String nome = InputHandler.getNonEmptyInput(sc, "Nome");
        System.out.print("Email: ");
        String email = InputHandler.getEmailInput(sc);

        service.cadastrarCliente(nome, email);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void listarClientes() {
        List<Cliente> clientes = service.listarTodos();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            clientes.forEach(System.out::println);
        }
    }

    private void buscarPorId() {
        System.out.print("Digite o ID: ");
        int id = InputHandler.getIntInput(sc);
        Cliente cliente = service.buscarPorId(id);

        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    private void atualizarCliente() {
        System.out.print("Digite o ID do cliente: ");
        int id = InputHandler.getIntInput(sc);
        Cliente cliente = service.buscarPorId(id);

        if (cliente != null) {
            System.out.print("Novo nome: ");
            cliente.setNome(InputHandler.getNonEmptyInput(sc, "Nome"));
            System.out.print("Novo email: ");
            cliente.setEmail(InputHandler.getEmailInput(sc));

            service.atualizarCliente(cliente);
            System.out.println("Cliente atualizado!");
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    private void removerCliente() {
        System.out.print("Digite o ID do cliente: ");
        int id = InputHandler.getIntInput(sc);
        service.removerCliente(id);
        System.out.println("Cliente removido!");
    }
}