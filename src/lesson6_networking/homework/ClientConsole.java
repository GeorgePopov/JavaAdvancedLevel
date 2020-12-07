package lesson6_networking.homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientConsole {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 9696;

    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        createConnection();

        new Thread(ClientConsole::readMessage).start();
        new Thread(ClientConsole::sendMessage).start();

    }

    private static void createConnection() {
        try {
            Socket clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            inputStream = new DataInputStream(clientSocket.getInputStream());
            outputStream = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage() {
        try {
            while (true) {
                System.out.println("Enter message to server: ");
                String messageToServer = scanner.nextLine();
                outputStream.writeUTF(messageToServer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readMessage() {
        try {
            while (true) {
                String messageFromServer = inputStream.readUTF();
                System.out.println("Message from server: " + messageFromServer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}