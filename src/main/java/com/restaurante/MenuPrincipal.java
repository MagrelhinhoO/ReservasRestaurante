package com.restaurante;

import com.restaurante.controller.AdministradorController;
import com.restaurante.controller.ClienteController;
import com.restaurante.controller.MesaController;
import com.restaurante.controller.ReservaController;

import java.util.Scanner;

public class MenuPrincipal {

    public static void main(String[] args) {
        ClienteController clienteController = new ClienteController();
        AdministradorController administradorController = new AdministradorController();
        MesaController mesaController = new MesaController();
        ReservaController reservaController = new ReservaController();

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== SISTEMA DE RESERVAS DE RESTAURANTE =====");
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Administradores");
            System.out.println("3. Gerenciar Mesas");
            System.out.println("4. Gerenciar Reservas");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> clienteController.menuCliente();
                case 2 -> administradorController.menuAdministrador();
                case 3 -> mesaController.menuMesa();
                case 4 -> reservaController.menuReserva();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        sc.close();
    }
}
