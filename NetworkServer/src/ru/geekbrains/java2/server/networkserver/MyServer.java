package ru.geekbrains.java2.server.networkserver;

import ru.geekbrains.java2.server.networkserver.auth.AuthService;
import ru.geekbrains.java2.server.networkserver.auth.BaseAuthService;
import ru.geekbrains.java2.server.networkserver.clienthandler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {

    // если объект final то нельзя изменить ссылку на этот объект, сам объект изменять можно, у примитивных нельзя
    private final int port;
    private final List<ClientHandler> clients;
    private final AuthService authService;

    // !*!*! конструктор лучше использовать для того, что бы создать сущьность, а не запускать какую-то логику
    public MyServer(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
        this.authService = new BaseAuthService();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) { // создаём socket
            System.out.println("Server is running");
            authService.start();

            //noinspection InfiniteLoopStatement
            while (true) {
                System.out.println("Waiting for client connection...");
                Socket clientSocket = serverSocket.accept(); // дожидаемся подключения
                System.out.println("Client has been connected");
                ClientHandler handler = new ClientHandler(clientSocket, this); // создаём обработчик
                try {
                    handler.handle(); // вызываем обрабатыватель
                } catch (IOException e) {
                    System.err.println("Failed to handle client connection!");
                    clientSocket.close();
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            authService.stop();
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public boolean isNicknameBusy(String nickname) {
        for (ClientHandler client : clients) {
            if (client.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    // чтобы методы broadcastMessage, subscribe и unsubscribe не работали
    // одновременно с коллекцией clients, помечаем их как synchronized
    // в противном случае это может привести к ConcurrentModificationException
    public synchronized void broadcastMessage(String message) throws IOException {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }
}