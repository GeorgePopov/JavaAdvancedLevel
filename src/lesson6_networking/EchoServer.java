package lesson6_networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static final int SERVER_PORT = 8189; // со значений 8080 можно использовать все порты

    public static void main(String[] args) {

        System.out.println("Start echo-server");

        try {
            // создаём сокет по определённому потру, он умеет только принимать подключения
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("The server is running, waiting for a connection...");
            // метод accept() блокирует приложение до тех пор пока клиент не подключиться
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            // оборачиваем getInputStream() в готовую реализацию new DataInputStream(),
            // т.к. InputStream, в котором находится метод getInputStream(), является абстрактным классом
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            try {
                while (true) {
                    // метод readUTF(), явлется блокирующим методом по аналогии с accept()
                    String message = inputStream.readUTF(); // чтение сообщения (из массива байт в строку)
                    System.out.println("From client: " + message);
                    if ("/end".equals(message)) {
                        break;
                    }
                    outputStream.writeUTF("Echo: " + message); // отправка сообщения
                }
            } catch (IOException e) {
                System.out.println("The connection was closed!");
            }

            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Port " + SERVER_PORT + " is already used!");
            e.printStackTrace();
        }
    }
// можно задать таймаут сразу после создания сокета, если
// подключение не произошло, он выключится и бросится исключение
// serverSocket.setSoTimeout(10)
// тоже самое можно использовать и с методом readUTF()
}