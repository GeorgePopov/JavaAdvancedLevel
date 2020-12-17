package ru.geekbrains.java2.client.view;

import ru.geekbrains.java2.client.controller.ClientController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

// данный класс отображает компоненты и общается с контроллером
public class ClientChat extends JFrame {

    private JPanel mainPanel;
    private JTextArea chatText;
    private JTextField messageTextField;
    private JButton sendButton;
    private JList<String> usersList;

    private ClientController controller;

    public ClientChat(ClientController controller) {
        this.controller = controller;
        setTitle(controller.getUsername());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        addListeners();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.shutdown();
            }
        });
    }

    private void addListeners() {
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientChat.this.sendMessage();
            }
        });

        messageTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientChat.this.sendMessage();
            }
        });
    }

    // отправляются сообщения обычные и приватные
    private void sendMessage() {
        String message = messageTextField.getText().trim();
        if (message.isEmpty()) {
            return;
        }

        appendOwnMessage(message);

        if (usersList.getSelectedIndex() < 1) { // есть ли выделенное имя в usersList, т.е. равное All или ничего
            controller.sendMessage(message);
        }
        else {
            String username = usersList.getSelectedValue(); // получаем кому хотим отправить сообщение
            controller.sendPrivateMessage(username, message);
        }

        messageTextField.setText(null);
    }

    // данный метод в обёртке SwingUtilities.invokeLater() добавляет сообщения которые нам пришли в окно чата
    public void appendMessage(String message) {
        SwingUtilities.invokeLater(new Runnable() { // в SwingUtilities, оборачиваем invokeLater
            @Override
            public void run() {
                chatText.append(message);
                chatText.append(System.lineSeparator()); // перенос на новую строку
            }
        });
    }

    private void appendOwnMessage(String message) {
        appendMessage("Я: " + message);
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public void updateUsers(List<String> users) { // изменяем графический интерфейс - из отдельного потока
        // можно построить лямбда выражение для Runnable()
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DefaultListModel<String> model = new DefaultListModel<>();
//            model.addAll(users);
                for (String user : users) {
                    model.addElement(user);
                }
                usersList.setModel(model);
            }
        });
    }
}