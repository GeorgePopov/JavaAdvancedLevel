package ru.geekbrains.java2.server.networkserver.clienthandler;

import ru.geekbrains.java2.server.networkserver.MyServer;
import ru.geekbrains.java2.server.networkserver.auth.AuthService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

// static import позволяет импортировать определённую константу из класса и обращаться к ней напрямую.
// * означает, что можно обращаться ко всем константу из класса
import static ru.geekbrains.java2.server.networkserver.MessageConstant.*;

public class ClientHandler {

    private final MyServer serverInstance;
    private final AuthService authService;
    private final Socket clientSocket;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private String nickname;

    public ClientHandler(Socket clientSocket, MyServer server) {
        this.clientSocket = clientSocket;
        this.serverInstance = server;
        this.authService = serverInstance.getAuthService();
    }

    public void handle() throws IOException { // логика по обработке клиентского подключения
        inputStream = new DataInputStream(clientSocket.getInputStream());
        outputStream = new DataOutputStream(clientSocket.getOutputStream());

        new Thread(() -> {
            try {
                authentication();
                readMessage();
            } catch (IOException e) {
                System.out.println("Connection has been failed"); // was interrupted
            } finally {
                closeConnection(); // закрытие соединения и отписка от коллекции пользователя
            }
        }).start();
    }

    private void closeConnection() {
        serverInstance.unsubscribe(this); // удаляемся из коллекции
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage() throws IOException {
        while (true) {
            String message = inputStream.readUTF();
            if (message.startsWith(END_CMD)) {
                return;
            }

            serverInstance.broadcastMessage(String.format("%s: %s", nickname, message)); // ! так можно использовать
        }
    }

    private void authentication() throws IOException {
        while (true) {
            String message = inputStream.readUTF();
            // метод startsWith() читает строку, если она начинается с определённого префикса
            if (message.startsWith(AUTH_CMD)) {
                // метод split(regex) разбивает строку на совпадения (которые задаются
                // с помощью регулярных выражений) и возвращает массив строк
                // ("\\s+") в качестве разделителя будет один или более пробелов
                // изучить регулярные выражения
                String[] parts = message.split("\\s+");
                String login = parts[1]; /*parts[0] = AUTH_CMD ("/auth") */
                String password = parts[2];

                String nickname = authService.getNickByLoginAndPassword(login, password);
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
                }
            }
        }
    }

    private void setNickname(String nickname) { // setter privet что бы использовать его внутри
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void sendMessage(String message) throws IOException {
        outputStream.writeUTF(message);
    }
}