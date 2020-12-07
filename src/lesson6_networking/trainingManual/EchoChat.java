package lesson6_networking.trainingManual;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoChat {
    private static Socket socket = null;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9595)) {
            System.out.println("Срвер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String message = inputStream.readUTF();
                if ("/end".equals(message)) {
                    break;
                }
                outputStream.writeUTF("Echo: " + message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}