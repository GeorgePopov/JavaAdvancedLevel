package lesson7_networkchat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// в классе хранится список подключённых клиентов, функционал управления соединения с клиентом и рассылкой сообщений
public class MyServer {

    private static final int PORT = 9898;
    private List<ClientHandler> clients;
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            authService = new BaseAuthService();
            authService.start();
            clients = new ArrayList<>();
            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept(); // клиент подключился
                System.out.println("Клтент подключился");
                new ClientHandler(this, socket); // создался новый объект -  обработчик общения сервера с клиентом
            }
        } catch (IOException e) {
            System.out.println("Ошибка в работе сервера");
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler client : clients) {
            if (client.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void broadcastMsg(String msg) { // блокирует возможность подключения под одним ником
        for (ClientHandler client : clients) {
            client.sendMsg(msg);
        }
    }

    public synchronized void subscribe(ClientHandler client) { // при подключении и авторизации добавляем клиента
        clients.add(client);
    }

    public synchronized void unsubscribe(ClientHandler client) { // при отключении удаляем клиента
        clients.remove(client);
    }
}