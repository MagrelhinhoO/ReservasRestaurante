package com.restaurante.controller;

import com.restaurante.model.Administrador;
import com.restaurante.model.Funcionario;
import com.restaurante.model.Gerente;
import com.restaurante.service.AdministradorService;
import com.restaurante.utils.InputHandler;

import java.util.List;
import java.util.Scanner;

public class AdministradorController {

    private final AdministradorService service = new AdministradorService();
    private final Scanner sc = new Scanner(System.in);

    public void menuAdministrador() {
        int opcao;
        do {
            System.out.println("\n--- MENU ADMINISTRADOR ---");
            System.out.println("1. Cadastrar Administrador");
            System.out.println("2. Cadastrar Funcionário");
            System.out.println("3. Cadastrar Gerente");
            System.out.println("4. Listar Todos");
            System.out.println("5. Buscar por ID");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            opcao = InputHandler.getIntInput(sc);

            switch (opcao) {
                case 1 -> cadastrarAdministrador();
                case 2 -> cadastrarFuncionario();
                case 3 -> cadastrarGerente();
                case 4 -> listarTodos();
                case 5 -> buscarPorId();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarAdministrador() {
        System.out.println("\n--- NOVO ADMINISTRADOR ---");
        System.out.print("Nome: ");
        String nome = InputHandler.getNonEmptyInput(sc, "Nome");
        System.out.print("Email: ");
        String email = InputHandler.getEmailInput(sc);

        service.cadastrarAdministrador(nome, email);
        System.out.println("Administrador cadastrado com sucesso!");
    }

    private void cadastrarFuncionario() {
        System.out.println("\n--- NOVO FUNCIONÁRIO ---");
        System.out.print("Nome: ");
        String nome = InputHandler.getNonEmptyInput(sc, "Nome");
        System.out.print("Email: ");
        String email = InputHandler.getEmailInput(sc);
        System.out.print("Cargo: ");
        String cargo = InputHandler.getNonEmptyInput(sc, "Cargo");

        // Implemente no service um método específico para Funcionario
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private void cadastrarGerente() {
        System.out.println("\n--- NOVO GERENTE ---");
        System.out.print("Nome: ");
        String nome = InputHandler.getNonEmptyInput(sc, "Nome");
        System.out.print("Email: ");
        String email = InputHandler.getEmailInput(sc);
        System.out.print("Setor: ");
        String setor = InputHandler.getNonEmptyInput(sc, "Setor");

        // Implemente no service um método específico para Gerente
        System.out.println("Gerente cadastrado com sucesso!");
    }

    private void listarTodos() {
        List<Administrador> admins = service.listarTodos();
        if (admins.isEmpty()) {
            System.out.println("Nenhum administrador cadastrado.");
        } else {
            admins.forEach(System.out::println);
        }
    }

    private void buscarPorId() {
        System.out.print("Digite o ID: ");
        int id = InputHandler.getIntInput(sc);
        Administrador admin = service.buscarPorId(id);

        if (admin != null) {
            System.out.println(admin);
        } else {
            System.out.println("Administrador não encontrado!");
        }
    }
}