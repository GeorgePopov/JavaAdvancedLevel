package lesson4_GUI.tainingManual;

import javax.swing.*;
import java.awt.*;

public class MyWindowBorder2 extends JFrame {
    public MyWindowBorder2() {
        setTitle("BorderLayoutDemo");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(700, 250, 400, 500);
        JButton button = new JButton("Button 1 (PAGE_START)");
        add(button, BorderLayout.PAGE_START);
        button = new JButton("Button 2 (CENTER)");
        button.setPreferredSize(new Dimension(200, 100));
        add(button, BorderLayout.CENTER);
        button = new JButton("Button 3 (LINE_START)");
        add(button, BorderLayout.LINE_START);
        button = new JButton("Long-named Button 4 (PAGE_END)");
        add(button, BorderLayout.PAGE_END);
        button = new JButton("5, (LINE_END)");
        add(button, BorderLayout.LINE_END);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyWindowBorder2();
    }
}
