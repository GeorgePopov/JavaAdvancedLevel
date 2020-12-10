package lesson7_networkchat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

// класс обработчик, отвечает за общение сервера с каждым отдельным клиентом
// класс аутентифицирует клиента и получает от него сообщения
public class ClientHandler {

    private MyServer myServer; // ссылка на сервер к которому он прикреплён, для возможности обращаться к его методам
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private String name; // если это поле пусто клиент считается не авторизированным

    public String getName() {
        return name;
    }

    public ClientHandler(MyServer server, Socket socket) {
        try {
            this.myServer = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.name = "";
            new Thread(() -> { // при старте создаётся отдельный поток, читающий все сообщения от клиента
                try {
                    authentication(); // цикл аутентификации
                    readMessage(); // цикл обмена обычными сообщениями
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            throw  new RuntimeException("Проблемы при создании обработчика клиента");
        }
    }

    private void authentication() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith("/auth")) {
                String[] parts = str.split("\\s+");
                String nick = myServer.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                if (nick != null) {
                    if (!myServer.isNickBusy(nick)) {
                        sendMsg("/authOk " + nick); // отсылаем клиенту сообщение об успешной авторизации
                        name = nick;
                        myServer.broadcastMsg(name + " зашёл в чат");
                        myServer.subscribe(this); // подписываем этого клиента на рассылку чата и выходим
                        return;
                    } else {
                        System.out.println("Учётная запись уже используется");
                    }
                } else {
                    System.out.println("Неверные логин/пароль");
                }
            }
        }
    }

    private void readMessage() throws IOException {
        while (true) {
            String strFromClient = in.readUTF();
            System.out.println("от " + name + ": " + strFromClient);
            if (strFromClient.equals("/end")) {
                return;
            }
            myServer.broadcastMsg(name + ": " + strFromClient);
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        myServer.unsubscribe(this);
        myServer.broadcastMsg(name + " вышел из чата");
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}