package ru.geekbrains.java2.server.networkserver.clienthandler;

import ru.geekbrains.java2.clent.server.Command;
import ru.geekbrains.java2.clent.server.command.AuthCommand;
import ru.geekbrains.java2.clent.server.command.BroadcastMessageCommand;
import ru.geekbrains.java2.clent.server.command.PrivateMessageCommand;
import ru.geekbrains.java2.server.networkserver.MyServer;
import ru.geekbrains.java2.server.networkserver.auth.AuthService;

import java.io.*;
import java.net.Socket;

// static import позволяет импортировать определённую константу из класса и обращаться к ней напрямую.
// * означает, что можно обращаться ко всем константу из класса
import static ru.geekbrains.java2.server.networkserver.MessageConstant.*;

public class ClientHandler {

    private final MyServer serverInstance;
    private final AuthService authService;
    private final Socket clientSocket;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private String nickname;

    public ClientHandler(Socket clientSocket, MyServer server) {
        this.clientSocket = clientSocket;
        this.serverInstance = server;
        this.authService = serverInstance.getAuthService();
    }

    // в этом методе открываем потоки на чтение и запись и создаём отдельный поток в котором занимаемся подключением
    public void handle() throws IOException { // логика по обработке клиентского подключения
//        inputStream = new DataInputStream((clientSocket.getInputStream())); // меняем, что бы работать с объектами
        inputStream = new ObjectInputStream(clientSocket.getInputStream());
        outputStream = new ObjectOutputStream(clientSocket.getOutputStream());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ClientHandler.this.authentication();
                    ClientHandler.this.readMessage();
                } catch (IOException e) {
                    System.out.println("Connection has been failed"); // was interrupted
                } finally {
                    ClientHandler.this.closeConnection(); // закрытие соединения и отписка от коллекции пользователя
                }
            }
        }).start();
    }

    private void closeConnection() {
        try {
            serverInstance.unsubscribe(this); // удаляемся из коллекции
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage() throws IOException {
        while (true) {
            Command command = readCommand();
            if (command == null) {
                continue;
            }
            switch (command.getType()) {
                case END:
                    return;
                case BROADCAST_MESSAGE:
                    BroadcastMessageCommand data = (BroadcastMessageCommand) command.getData();
                    serverInstance.broadcastMessage(Command.messageCommand(nickname, data.getMessage()));

                    break;
                case PRIVATE_MESSAGE:
                    PrivateMessageCommand privateMessageCommand = (PrivateMessageCommand) command.getData();
                    String receiver = privateMessageCommand.getReceiver();
                    String message = privateMessageCommand.getMessage();
                    serverInstance.sendPrivateMessage(receiver, Command.messageCommand(nickname, message));
                    break;
                default:
                    String errorMessage = "Unknown type of command: " + command.getType();
                    System.err.println(errorMessage);
                    sendMessage(Command.errorCommand(errorMessage));
            }
            // старый код
            /*if (message.startsWith(END_CMD)) {
                return;
            }
            else if (message.startsWith(PRIVATE_MSG_CMD)) {
                String[] parts = message.split("\\s+", 3);
                String username = parts[1];
                String privateMessage = parts[2];
                serverInstance.sendPrivateMessage(username, buildMessage(privateMessage));
            }
            else {
                serverInstance.broadcastMessage(buildMessage(message));
//            serverInstance.broadcastMessage(String.format("%s: %s", nickname, message)); // ! так можно использовать
            }*/
        }
    }

    private String buildMessage(String message) { // переводит наше сообщение в String.format
        return String.format("%s: %s", nickname, message); // ! так можно использовать
    }

    private void authentication() throws IOException {
        while (true) {
            Command command = readCommand();
            if (command == null) {
                continue;
            }
            switch (command.getType()) {
                case AUTH: {
                    if (processAuthCommand(command)) {
                        return;
                    }
                    break;
                }
                default:
                    String errorMessage = "Illegal command for authentication: " + command.getType();
                    System.err.println(errorMessage);
                    sendMessage(Command.errorCommand(errorMessage));
            }
        }
    }

    private Command readCommand() throws IOException {
        try {
            return (Command) inputStream.readObject();
        } catch (ClassNotFoundException e) {
            String errorMessage = "Unknown type of object from client!";
            System.err.println(errorMessage);
            e.printStackTrace();
            sendMessage(Command.errorCommand(errorMessage));
            return null;
        }
    }

    private boolean processAuthCommand(Command command) throws IOException {
        AuthCommand authCommand = (AuthCommand) command.getData();
        String login = authCommand.getLogin();
        String password = authCommand.getPassword();

        String nickname = authService.getNickByLoginAndPassword(login, password);
        if (nickname == null) {
            sendMessage(Command.authErrorCommand("Учётная запись уже используется!"));
        }
        else if (serverInstance.isNicknameBusy(nickname)) { // проверяем есть ли такой пользователь уже
            sendMessage(Command.authErrorCommand("Неверные логин/пароль!"));
        }
        else {
            authCommand.setUsername(nickname); // запоминаем username, что бы отправить его клиенту
            sendMessage(command);
            setNickname(nickname); // запоминаем nickname, для подключения, для выборки приватных сообщений
            // массовая рассылка всем пользователям
            serverInstance.broadcastMessage(Command.messageCommand(null, nickname + " Защёл в чат!"));
            serverInstance.subscribe(this); // подписываем данного клиента в нашу коллекцию
            return true;
        }
        return false;
    }
    // старый код
            /*// метод startsWith() читает строку, если она начинается с определённого префикса
            if (message.startsWith(AUTH_CMD)) {
                // метод split(regex) разбивает строку на совпадения (которые задаются
                // с помощью регулярных выражений) и возвращает массив строк
                // ("\\s+") в качестве разделителя будет один или более пробелов
                // изучить регулярные выражения
                String[] parts = message.split("\\s+");
                String login = parts[1]; *//*parts[0] = AUTH_CMD ("/auth") *//*
                String password = parts[2];*/
            // перенесен в новое исполнение
            /*String nickname = authService.getNickByLoginAndPassword(login, password);
                if (nickname == null) {
                    sendMessage("Неверные логин/пароль!");
                }
                else if (serverInstance.isNicknameBusy(nickname)) { // проверяем есть ли такой пользователь уже
                    sendMessage("Неверные логин/пароль!");
                }
                else {
                    sendMessage(String.format("%s %s", AUTH_SUCCESSFUL_CMD, nickname)); // соб-е клиенту о прой-ной auth
                    setNickname(nickname); // запоминаем nickname
                    serverInstance.broadcastMessage(nickname + " Защёл в чат!"); // массовая рассылка всем пользователям
                    serverInstance.subscribe(this); // подписываем данного клиента в нашу коллекцию
                    break;
                }*/

    private void setNickname(String nickname) { // setter privet что бы использовать его внутри
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void sendMessage(Command command) throws IOException {
        outputStream.writeObject(command);
    }
}