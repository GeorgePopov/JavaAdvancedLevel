package lesson6_networking.homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 1. Написать консольный вариант клиент\серверного приложения, в котором пользователь может писать сообщения,
 * как на клиентской стороне, так и на серверной. Т.е. если на клиентской стороне написать «Привет», нажать Enter,
 * то сообщение должно передаться на сервер и там отпечататься в консоли. Если сделать то же самое на серверной стороне,
 * то сообщение передается клиенту и печатается у него в консоли. Есть одна особенность, которую нужно учитывать:
 * клиент или сервер может написать несколько сообщений подряд. Такую ситуацию необходимо корректно обработать.
 * *ВАЖНО! * Сервер общается только с одним клиентом, т.е. не нужно
 * запускать цикл, который будет ожидать второго/третьего/n-го клиентов.
 */
// Kак правильно в main вызвать новый поток, а в нём вызвать метод, или в main вызвать метод а в нём новый поток?
    // вариант решения не совсем верный
public class ServerConsole {
    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
            createConnection();
        new Thread(() -> readMessage()).start();
        new Thread(() -> sendMessage()).start();
    }

    private static void createConnection() {
        try {
            ServerSocket serverSocket = new ServerSocket(9696);
            System.out.println("The server is running, waiting for a connection ...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connection");
            inputStream = new DataInputStream(clientSocket.getInputStream());
            outputStream = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage() {
        try {
            while (true) {
                System.out.println("Enter message to client: ");
                String messageToClient = scanner.nextLine();
                outputStream.writeUTF(messageToClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readMessage() {
        try {
            while (true) {
                String messageFromClient = inputStream.readUTF();
                System.out.println("Message from client: " + messageFromClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}