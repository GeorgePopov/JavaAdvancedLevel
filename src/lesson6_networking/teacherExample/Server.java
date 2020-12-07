package lesson6_networking.teacherExample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// Как обычно очень интересный пример
public class Server {
    public static final int DEFAULT_PORT = 8189;

    // пример костомизации сервера, передаём аргумент в массив args, les7, 3min30sec
    public static void main(String[] args) throws IOException {
        int port = DEFAULT_PORT;
        if (args.length != 0) {
            port = Integer.parseInt(args[0]);
        }
        new Server().start(port);
    }

    public void start(int port) throws IOException {
        ServerSocket socket = null;
        Socket clientSocket = null;
        Thread inputThread = null;
        try {
            socket = new ServerSocket(port);
            System.out.println("Server started");
            clientSocket = socket.accept();
            System.out.println("Client connected");
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            // create a separate thread for reading messages
            inputThread = runInputThread(in);
            runOutputLoop(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputThread != null) // если имеется объект потока inputThread
                inputThread.interrupt();
            if (clientSocket != null)
                clientSocket.close();
            if (socket != null)
                socket.close();
        }
    }

    private void runOutputLoop(DataOutputStream out) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String message = scanner.next(); // !*!*!
            out.writeUTF(message);
            if (message.equals("/end"))
                break;
        }
    }

    private Thread runInputThread(DataInputStream in) {
        Thread thread = new Thread(() -> { // создаём экземпляр потока
            // пока наш поток не прерван, в базовом представлении поток не прерван
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String message = in.readUTF();
                    System.out.println("From client: " + message);
                    if (message.equals("/end"))
                        System.exit(0); // !*!*! принудительно завершаем приложение
                } catch (IOException e) {
                    System.out.println("Connection was closed");
                    break;
                }
            }
        });
        thread.start();
        return thread;
    }
}