package com.restaurante;

import com.restaurante.controller.RestauranteController;

public class MainApp {
    public static void main(String[] args) {
        RestauranteController controller = new RestauranteController();
        controller.iniciar();
    }
}
