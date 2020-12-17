package ru.geekbrains.java2.client.model;

import ru.geekbrains.java2.clent.server.Command;
import ru.geekbrains.java2.clent.server.command.AuthCommand;
import ru.geekbrains.java2.clent.server.command.ErrorCommand;
import ru.geekbrains.java2.clent.server.command.MessageCommand;
import ru.geekbrains.java2.clent.server.command.UpdateUsersListCommand;
import ru.geekbrains.java2.client.controller.AuthEvent;
import ru.geekbrains.java2.client.controller.ClientController;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.function.Consumer;

public class NetworkService { // класс отвечает за логику соединения с сервером

    private final String host;
    private final int port;
    private final ClientController controller;
    private Socket socket;
    // если на сервере создали сначала in потом out, здесь нужно сделать наоборот les8, 1:07h
    // делается это для того, что когда сервер что то отправляет то клиент должен уже уметь читать и наоборот
    private ObjectOutputStream out;
    private ObjectInputStream in;

    // messageHandler это логика объяснения из контроллера, что он должен сделать когда придёт сообщение
    // этот базовый интерфейс можно использовать для описания лямбда выражений типа (clientChat::appendMessage)
    private Consumer<String> messageHandler; // обёртка для входящего сообщения (что бы его в лямбда использовать)
    private AuthEvent successfulAuthEvent;

    public NetworkService(String host, int port, ClientController controller) {
        this.host = host;
        this.port = port;
        this.controller = controller;
    }

    public void connect() throws IOException { // логика подключение и новый поток по чтению сообщений от сервера
        socket = new Socket(host, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        runReadThread();
    }

    private void runReadThread() { // метод после подключения переподменят наши окна с одного на другое
        new Thread(() -> {
            while (true) { // в бесконечном цикле ожидаем команду и обрабатываем её
                try {
                    Command command = (Command) in.readObject();
                    processCommand(command);
                } catch (IOException e) {
                    System.out.println("Поток на чтение был прерван!");
                    return;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void processCommand(Command command) {
        switch (command.getType()) {
            case AUTH: {                    // !*!*! можно в блоке делать
                processAuthCommand(command);
                break;
            }
            case MESSAGE: {
                processMessageCommand(command);
                break;
            }
            case AUTH_ERROR:
            case ERROR: {
                processErrorCommand(command);
                break;
            }
            case UPDATE_USERS_LIST: {
                UpdateUsersListCommand data = (UpdateUsersListCommand) command.getData();
                List<String> users = data.getUsers();
                controller.updateUsersList(users); // говорим контроллеру, что нужно обновить список
                break;
            }
            default:
                System.err.println("Unknown type of command: " + command.getType());
        }
    }

    private void processErrorCommand(Command command) {
        ErrorCommand data = (ErrorCommand) command.getData();
        // методы по ошибкам теперь находятся в окнах, а контроллер является руководителем,
        // кто и что должен сделать в нашей ситуации
        controller.showErrorMessage(data.getErrorMessage()); // контроллеру что нужно показать сообщение об ошибке
    }

    private void processMessageCommand(Command command) { // если коменда о сообщении
        MessageCommand data = (MessageCommand) command.getData();
        if (messageHandler != null) {
            String message = data.getMessage();
            String username = data.getUsername();
            if (username != null) {
                message = username + ": " + message; // если сообщение есть, сформировываем его
            }
            messageHandler.accept(message); // говорим 'обработчику сообщений' обработать сообщение
        }
    }

    private void processAuthCommand(Command command) { // здесь обрабатывается логика по успешной аутен-ции
        AuthCommand data = (AuthCommand) command.getData();
        String nickname = data.getUsername();
        successfulAuthEvent.authIsSuccessful(nickname); // если аутен-ция успешно прошла, вот никнейм
    }

    public void sendCommand(Command command) throws IOException { // отправляет обычное сообщение на сервер
        out.writeObject(command);
    }

    public void setMessageHandler(Consumer<String> messageHandler) {
        this.messageHandler = messageHandler;
    }

    // сетим здесь объект который реализует интерфейс AuthEvent и метод с успешно пройденной аутентификацией
    public void setSuccessfulAuthEvent(AuthEvent successfulAuthEvent) { // сетим объект который реализует этот интерфейс
        this.successfulAuthEvent = successfulAuthEvent;
    }

    public void close() {
        try {
            sendCommand(Command.endCommand());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}