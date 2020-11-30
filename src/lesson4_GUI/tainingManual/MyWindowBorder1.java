package lesson4_GUI.tainingManual;

import javax.swing.*;
import java.awt.*;

public class MyWindowBorder1 extends JFrame {
    public MyWindowBorder1() {
        setTitle("BorderLayoutDemo");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(700, 250, 400, 500);
        JButton[] buttons = new JButton[5];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("#" + i);
        }
        setLayout(new BorderLayout());      // выбор компановщика элементов
        add(buttons[0], BorderLayout.EAST); // добавление кнопки на форму
        add(buttons[1], BorderLayout.WEST);
        add(buttons[2], BorderLayout.SOUTH);
        add(buttons[3], BorderLayout.NORTH);
        add(buttons[4], BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyWindowBorder1();
    }
}
