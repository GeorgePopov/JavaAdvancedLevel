package ru.geekbrains.java2.client.controller;

import ru.geekbrains.java2.clent.server.Command;
import ru.geekbrains.java2.client.model.NetworkService;
import ru.geekbrains.java2.client.view.AuthDialog;
import ru.geekbrains.java2.client.view.ClientChat;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

// класс диспечер связывает графическую (view) часть и
public class ClientController {

    private final NetworkService networkService;
    private final AuthDialog authDialog;
    private final ClientChat clientChat;
    private String nickname;

    public static final String ALL_USERS_LIST_ITEM = "All";

    public ClientController(String serverHost, int serverPort) {
        // в конструкторе создаём все экземпляры для общения по сети
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

    // в данном кодде говориться нетворксервису, что когда аутентификация у тебя пройдет
    // успешно, вот те действия которые ты должен сделать. Это делается для того что бы
    // не спарвишать в бесконечном цикле у нетворксервиса 'прошла ли аутнтификация'
    private void runAuthProcess() {
        networkService.setSuccessfulAuthEvent(new AuthEvent() { // самописанный Listener, задаём событие
            @Override
            public void authIsSuccessful(String nickname) { // выполняем в случае успешной аутентификации
                ClientController.this.setUserName(nickname);
                ClientController.this.openChat(); // чат откроется не сразу, а после успешной аутен-ции
            }
        });
        authDialog.setVisible(true);
    }

    private void openChat() {
        authDialog.dispose(); // уничтожает диалог об аутентификации
        // здесь обработанное входящее сообщение добавляем в окно чата
        // задаём MessageHandler, и говорим, что толжен делать networkService, когда получиль успешное сообщение
        networkService.setMessageHandler(new Consumer<String>() {
            @Override
            public void accept(String message) {
                clientChat.appendMessage(message); // вызывает код переданный ему ClientController-лером
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
        } catch (IOException e) {
            System.err.println("Failed to establish server connection");
            throw e;
        }
    }

    public void sendAuthMessage(String login, String pass) throws IOException {
        sendCommand(Command.authCommand(login, pass));
    }

    public void sendMessage(String message) {
        sendCommand(Command.broadcastMessageCommand(message));
    }

    public void sendCommand(Command command) {
        try {
            networkService.sendCommand(command);
        } catch (IOException e) {
            showErrorMessage(e.getMessage());
        }
    }

    public void shutdown() {
        networkService.close();
    }

    public String getUsername() {
        return nickname;
    }

    public void showErrorMessage(String errorMessage) { // какое окно активено, там и показываем сообщение об ошибке
        if (clientChat.isActive()) {
            clientChat.showError(errorMessage);
        }
        else if (authDialog.isActive()) {
            authDialog.showError(errorMessage);
        }
        System.err.println(errorMessage);
    }

    public void sendPrivateMessage(String username, String message) {
        sendCommand(Command.privatMessageCommand(username, message));
    }

    public void updateUsersList(List<String> users) {
        users.remove(nickname);
        users.add(0, ALL_USERS_LIST_ITEM);
        clientChat.updateUsers(users); // обновляем список у клиент чата
    }
}