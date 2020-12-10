package ru.geekbrains.java2.client.model;

import ru.geekbrains.java2.client.controller.AuthEvent;
import ru.geekbrains.java2.client.controller.ClientController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

public class NetworkService {

    private final String host;
    private final int port;
    private ClientController controller;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    // этот базовый интерфейс можно использовать для описания лямбда выражений типа (clientChat::appendMessage)
    private Consumer<String> messageHandler; // обёртка для входящего сообщения (что бы его в лямбда использовать)
    private AuthEvent successfulAuthEvent;
    private String nickname;

    public NetworkService(String host, int port, ClientController controller) {
        this.host = host;
        this.port = port;
        this.controller = controller;
    }

    public void connect() throws IOException {
        socket = new Socket(host, port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        runReadThread();
    }

    private void runReadThread() { // метод после подключения переподменят наши окна с одного на другое
        new Thread(() -> {
            while (true) {
                try {
                    String message = in.readUTF();
                    if (message.startsWith("/authOk")) { // сообщение с сервера об успешной аутентификации
                        String[] messageParts = message.split("\\s+", 2); // разбиваем с-щение на 2 части
                        nickname = messageParts[1]; // достаём и запоминаем nickname
                        successfulAuthEvent.authIsSuccessful(nickname);
                    }
                    else if (messageHandler != null) {  // обрабатывая оборачиваем сообщение:
                        messageHandler.accept(message); // вызываем обработчик сообщений, у него метод принять
                    }
                    else {
                        controller.showErrorMessage(message);
                    }
                } catch (IOException e) {
                    System.out.println("Поток на чтение был прерван!");
                    return;
                }
            }
        }).start();
    }

    public void sendAuthMessage(String login, String password) throws IOException {
        out.writeUTF(String.format("/auth %s %s", login, password)); // отправляет сообщение об аутентификации на сервер
    }

    public void sendMessage(String message) throws IOException { // отправляет обычное сообщение на сервер
        out.writeUTF(message);
    }

    public void setMessageHandler(Consumer<String> messageHandler) {
        this.messageHandler = messageHandler;
    }
    // сетим здесь объект который реализует интерфейс AuthEvent и метод с успешно пройденной аутентификацией

    public void setSuccessfulAuthEvent(AuthEvent successfulAuthEvent) {
        this.successfulAuthEvent = successfulAuthEvent;
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}