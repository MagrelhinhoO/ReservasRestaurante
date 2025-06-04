package com.restaurante.controller;

import com.restaurante.model.Administrador;
import com.restaurante.service.AdministradorService;

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
            System.out.println("2. Listar Administradores");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

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
        service.cadastrarAdministrador(nome, email);
        System.out.println("Administrador cadastrado!");
    }

    private void listar() {
        List<Administrador> admins = service.listarTodos();
        admins.forEach(System.out::println);
    }
}
