package ru.geekbrains.java2.client;

import ru.geekbrains.java2.client.controller.ClientController;

// базовый класс для поднятия клиента
public class NetworkClient {

    public static void main(String[] args) {
        try {
            ClientController clientController = new ClientController("localhost", 8189);
            clientController.runApplication();
        } catch (Exception e) {
            System.err.println("Failed to connect to server! Please, check you network settings");
            e.printStackTrace();
        }
    }
}