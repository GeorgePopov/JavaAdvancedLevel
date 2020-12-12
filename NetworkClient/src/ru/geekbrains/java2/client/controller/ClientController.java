package ru.geekbrains.java2.client.controller;

import ru.geekbrains.java2.client.model.NetworkService;
import ru.geekbrains.java2.client.view.AuthDialog;
import ru.geekbrains.java2.client.view.ClientChat;

import javax.swing.*;
import java.io.IOException;
import java.util.function.Consumer;

public class ClientController {

    private final NetworkService networkService;
    private final AuthDialog authDialog;
    private final ClientChat clientChat;
    private String nickname;

    public ClientController(String serverHost, int serverPort) {
        // в конструкторе просто инициализируем поля, явное подключение не делаем
        // создаём базовую сущность которая отвечает за логику соединения с сервером
        this.networkService = new NetworkService(serverHost, serverPort, this); // можно в конструктор передавать этот же класс
        this.authDialog = new AuthDialog(this); // взаранее создаём окна
        this.clientChat = new ClientChat(this);
    }

    public void runApplication() throws IOException {
        connectToServer(); // подключение к серверу
        runAuthProcess();
    }

    private void runAuthProcess() {
        networkService.setSuccessfulAuthEvent(new AuthEvent() {
            @Override
            public void authIsSuccessful(String nickname) {
                ClientController.this.setUserName(nickname);
                ClientController.this.openChat();
            }
        });
        authDialog.setVisible(true);
    }

    private void openChat() {
        authDialog.dispose(); // уничтожает диалог об аутентификации
        // здесь обработанное входящее сообщение добавляем в окно чата
        networkService.setMessageHandler(new Consumer<String>() {
            @Override
            public void accept(String message) {
                clientChat.appendMessage(message);
            }
        }); // задаём messageHandler
        clientChat.setVisible(true); // делаем окно чата видимым
    }

    private void setUserName(String nickname) { // запоминаем nickname
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                clientChat.setTitle(nickname);
            }
        });
        this.nickname = nickname;
    }

    private void connectToServer() throws IOException {
        try {
            networkService.connect();
        } catch (Exception e) {
            System.err.println("Failed to establish server connection");
            throw e;
        }
    }

    public void sendAuthMessage(String login, String pass) throws IOException {
        networkService.sendAuthMessage(login, pass); // отправкой сообщения занимается networkService
    }

    public void sendMessage(String message) {
        try {
            networkService.sendMessage(message); // отправкой сообщения занимается networkService
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to send message!");
            e.printStackTrace();
        }
    }

    public void shutdown() {
        networkService.close();
    }

    public String getUsername() {
        return nickname;
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(authDialog, message);
    }
}