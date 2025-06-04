package com.restaurante.controller;

import com.restaurante.model.Cliente;
import com.restaurante.service.ClienteService;

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
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrar() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        service.cadastrarCliente(nome, email);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void listar() {
        List<Cliente> clientes = service.listarTodos();
        clientes.forEach(System.out::println);
    }
}
