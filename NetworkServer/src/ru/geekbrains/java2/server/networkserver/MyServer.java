package ru.geekbrains.java2.server.networkserver;

import ru.geekbrains.java2.clent.server.Command;
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
        try (ServerSocket serverSocket = new ServerSocket(port)) { // создаём socket, когда он есть значит можем работать
            System.out.println("Server is running");
            authService.start(); // здесь должен происхотить переход на БД

            //noinspection InfiniteLoopStatement
            while (true) { // основная логика сервера
                System.out.println("Waiting for client connection...");
                Socket clientSocket = serverSocket.accept(); // ожидаем нового подключения от клиента
                System.out.println("Client has been connected");
                ClientHandler handler = new ClientHandler(clientSocket, this); // создаём обработчик на каждого кл-та
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
            authService.stop(); // отключаем БД после оконсания работы сервера
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
    public synchronized void broadcastMessage(Command command) throws IOException {
        for (ClientHandler client : clients) {
            client.sendMessage(command);
        }
    }

    public synchronized void subscribe(ClientHandler clientHandler) throws IOException {
        clients.add(clientHandler);
        List<String> users = getAllUsernames();
        broadcastMessage(Command.updateUsersListCommand(users)); // делаем массовую рассылку обновлённого списка подкл-ний
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) throws IOException {
        clients.remove(clientHandler);
        List<String> users = getAllUsernames();
        broadcastMessage(Command.updateUsersListCommand(users));
    }

    private List<String> getAllUsernames() {
        // !*!*! Реализация с помощью 'IPI'?. Преобразовыаем все элементы данной колекции с
        // помощью метода 'референс' т.е. берём у каждой коллекции getNickname() и это и будет
        // считаться заменой каждого элемента и потом всё что получилась собираем в обычный лист
        /*return clients.stream()*/
//                .map(client -> client.getNickname())
//                .map(ClientHandler::getNickname) // метод референс заменяет лямбда выражение
//                .collect(Collectors.toList());
        List<String> result = new ArrayList<>();
        for (ClientHandler client : clients) {
            result.add(client.getNickname());
        }
        return result;
    }

    public synchronized void sendPrivateMessage(String receiver, Command command) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getNickname().equals(receiver)) {
                client.sendMessage(command);
                return;
            }
        }
    }
}