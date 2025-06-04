package com.restaurante.controller;

import com.restaurante.model.Mesa;
import com.restaurante.service.MesaService;

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
            System.out.println("2. Listar Mesas");
            System.out.println("3. Listar Mesas Disponíveis");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> listarDisponiveis();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrar() {
        System.out.print("Número da mesa: ");
        int numero = sc.nextInt();
        System.out.print("Capacidade: ");
        int capacidade = sc.nextInt();
        service.cadastrarMesa(numero, capacidade, true);
        System.out.println("Mesa cadastrada!");
    }

    private void listar() {
        List<Mesa> mesas = service.listarTodas();
        mesas.forEach(System.out::println);
    }

    private void listarDisponiveis() {
        List<Mesa> mesas = service.listarDisponiveis();
        mesas.forEach(System.out::println);
    }
}
