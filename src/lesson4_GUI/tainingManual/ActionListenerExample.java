package lesson4_GUI.tainingManual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ActionListenerExample extends JFrame {
    public ActionListenerExample() {
        setBounds(300, 300, 400, 300);
        setTitle("Action Listener Example");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // add listener to button
        JButton button = new JButton("Event");
        add(button, BorderLayout.LINE_START);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button press...");
            }
        });

        // add listener to text field
        JTextField field = new JTextField();
        add(field, BorderLayout.PAGE_END);
        field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Your message: " + field.getText());
            }
        });

        // tracking mouse clicks
        JPanel panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("MousePos: " + e.getX() + " " + e.getY());
            }
        });

        // tracking when a window closed
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Bye");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new ActionListenerExample();
    }
}