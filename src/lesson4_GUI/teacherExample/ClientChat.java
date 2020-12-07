package lesson4_GUI.teacherExample;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientChat {
    private JPanel mainPanel;
    private JList usersList;
    private JTextArea chatText;
    private JTextField messageTextField;
    private JButton sendButton;

    public ClientChat() {
        addListeners();
    }

    private void addListeners() {
//        sendButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                sendMessage();
//            }
//        });
        sendButton.addActionListener(e -> ClientChat.this.sendMessage());
        messageTextField.addActionListener(e -> sendMessage());
    }

    private void sendMessage() {
        // метод trim() удаляет пробелы слева и справа нашей строки, оставляя
        // только нужное содержимое, что бы не передовать сообщение из пробелов
        String message = messageTextField.getText().trim();
        if (message.isEmpty()) {
            return;
        }

        appendOwnMessage(message);
        String selectedUser = (String) usersList.getSelectedValue();
        if (selectedUser != null) {
            appendMessage(selectedUser, message);
        }

        messageTextField.setText(null);
    }

    private void appendMessage(String sender, String message) {
        String formattedMessage = String.format("%s: %s%n", sender, message);
        chatText.append(formattedMessage);
    }

    private void appendOwnMessage(String message) {
        appendMessage("I", message);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setContentPane(new ClientChat().mainPanel);
        frame.setTitle("MyPractice Window");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null); // окно будет открываться по середине экрана
        frame.setVisible(true);
    }
}
