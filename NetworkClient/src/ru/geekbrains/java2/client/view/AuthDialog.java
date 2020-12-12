package ru.geekbrains.java2.client.view;

import ru.geekbrains.java2.client.controller.ClientController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

// в этом классе нет лошики программы, если нужно отправить сообщение, используем контроллер
public class AuthDialog extends JFrame {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField loginText;
    private JPasswordField passwordText;

    private ClientController controller;

    public AuthDialog(ClientController controller) {
        this.controller = controller;
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK); // задаём default кнопку для этой корневой панели
        setSize(400, 250);
        setLocationRelativeTo(null);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AuthDialog.this.onOk(); // так можно обращаться к методу этого же класса
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AuthDialog.this.onCancel(); // почему бы просто не вызвать метод?
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
    }

    private void onOk() {
        String login = loginText.getText().trim(); // из поля логин получаем login
        String pass = new String(passwordText.getPassword()).trim(); // из поля пароль получаем password
        try {
            controller.sendAuthMessage(login, pass); // отправляем через контроллер с-ние об auth передавая login и pass
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка при попытке аутентификации");
        }
    }

    private void onCancel() { // закрываем клиентское приложение
        System.exit(0);
    }
}