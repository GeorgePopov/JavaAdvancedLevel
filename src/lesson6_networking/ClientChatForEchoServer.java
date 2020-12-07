package lesson6_networking;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientChatForEchoServer {

    private JPanel mainPanel;
    private JList<String> usersList;
    private JTextField messageTextField;
    private JButton sendButton;
    private JTextArea chatText;

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    public static final int CONNECTION_TIMEOUT = 10000;

    public ClientChatForEchoServer(JFrame frame) {
        addListeners(frame);
        initServerConnection();
    }

    private void initServerConnection() {
        try {
            // создаём сокет
            socket = new Socket("localHost", EchoServer.SERVER_PORT); // EchoServer.SERVER_PORT, код расшаринный
//            socket = new Socket("127.0.0.1", EchoServer.SERVER_PORT);
            // создание сервера с таймером подключения, через 10сек подключение упадёт
//            socket = new Socket();
//            socket.connect(new InetSocketAddress("192.168.1.68", EchoServer.SERVER_PORT), CONNECTION_TIMEOUT);


            // получаем из сокета inputStream и outputStream, оборачиваем их
            inputStream = new DataInputStream(socket.getInputStream()); // читаем сообщения
            outputStream = new DataOutputStream(socket.getOutputStream()); // отправляем сообщения
            processMessagesFromServer();
        } catch (IOException e) {
            showErrorMessage(e.getMessage());
        }
    }

    /**
     * Метод выводит отдельное окно сообщением об ошибке
     */
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * В этом методе, мы создаём отдельный поток в котором мы читаем сообщения с нашего сервера, метод
     * readUTF() блокирующий, и если не сделать отдельный поток, все клиентское приложение зависнит, и
     * мы не сможем ввести и отправить сообщения нашему серверу, т.к. мы в ожидании получения сообщения
     */
    private void processMessagesFromServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // оборачиваем чтение сообщение в try/catch, т.к. это отдельный от графического интерфейса поток
                try {
                    while (true) {
                        String messageFormServer = inputStream.readUTF();
                        // оборачиваем appendMessage() в SwingUtilities.invokeLater(), поскольку все компоненты
                        // графического интерфейса должны обрабатываться в потоке графического интерфейса, т.к.
                        // здесь мы работаем в отдельном потоке. Можно написать в одну строчку
                        SwingUtilities.invokeLater(() -> {
                            appendMessage("Server", messageFormServer);
                        });
                    }
                } catch (IOException e) {
                    System.err.println("The connection to the server was closed!");
                }
            }
        }).start();
    }

    private void addListeners(JFrame frame) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    // делаем проверку, что бы не упастьс NullPointerException (NPE)
                    if (outputStream != null) {
                        // отправляем серверу сообщение о закрытии
                        outputStream.writeUTF("/end");
                        // закрываем подключение, inputStream, outputStream и окно приложения методом windowClosed()
                        socket.close();
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        sendButton.addActionListener(e -> ClientChatForEchoServer.this.sendMessage());
        messageTextField.addActionListener(e -> sendMessage());
    }

    private void sendMessage() {
        String message = messageTextField.getText().trim();
        if (message.isEmpty()) {
            return;
        }

        appendOwnMessage(message);

        try {
            outputStream.writeUTF(message);
        } catch (IOException e) {
            showErrorMessage(e.getMessage());
            e.printStackTrace();
        }

        messageTextField.setText(null);
    }

    private void appendMessage(String sender, String messageFormServer /*message*/) {
        String formattedMessage = String.format("%s: %s%n", sender, messageFormServer);
        chatText.append(formattedMessage);
    }

    private void appendOwnMessage(String message) {
        appendMessage("I", message);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setContentPane(new ClientChatForEchoServer(frame).mainPanel);
        frame.setTitle("Client Chat");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
//    new Thread - это анонимный класс, вот так делается конструктор для анонимного класса:
//    private String name;
//    {
//        name = "Harry";
//    }
}