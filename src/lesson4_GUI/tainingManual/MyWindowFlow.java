package lesson4_GUI.tainingManual;

import javax.swing.*;
import java.awt.*;

public class MyWindowFlow extends JFrame {
    public MyWindowFlow() {
        setBounds(500, 500, 400, 300);
        setTitle("FlowLayoutDemo");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton[] buttons = new JButton[10];
        setLayout(new FlowLayout());
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("#" + i);
            add(buttons[i]);
        }
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyWindowFlow();
    }
}
