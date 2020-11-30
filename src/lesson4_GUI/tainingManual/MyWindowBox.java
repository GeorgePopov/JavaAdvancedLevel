package lesson4_GUI.tainingManual;

import javax.swing.*;

public class MyWindowBox extends JFrame {
    public MyWindowBox() {
        setTitle("BoxLayoutDemo");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(500, 500, 500, 300);
        JButton[] buttons = new JButton[10];
        setLayout(new BoxLayout(getContentPane(),  BoxLayout.X_AXIS));
//        setLayout(new BoxLayout(getContentPane(),  BoxLayout.Y_AXIS));
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("#" + i);
            buttons[i].setAlignmentX(CENTER_ALIGNMENT);
            add(buttons[i]);
        }
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyWindowBox();
    }
}
