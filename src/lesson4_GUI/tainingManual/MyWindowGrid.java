package lesson4_GUI.tainingManual;

import javax.swing.*;
import java.awt.*;

public class MyWindowGrid extends JFrame {
    public MyWindowGrid() {
        setBounds(500, 500, 400, 300);
        setTitle("GridLayoutDemo");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton[] buttons = new JButton[12];
        setLayout(new GridLayout(4, 3));
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("#" + i);
            add(buttons[i]);
        }
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyWindowGrid();
    }
}
