package lesson4_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerExample {
    public static void main(String[] args) {
        // 1: запускаются два окна, одно обычное, второе с AnotherObject
        // 2: пример как можно обращаться и работать с компонентами приложения из других классов !
        // 3: главное передавать всё в параметрах !
        new MyWindowGrid();

        MyWindowGrid windowGrid = new MyWindowGrid();
        AnotherObject anotherObject = new AnotherObject(windowGrid.anotherLabel);
        anotherObject.setText("Wow");
    }
}

class MyWindowGrid extends JFrame {

    JLabel anotherLabel = new JLabel();

    // ! конструктор можно без public
    MyWindowGrid() {
        setBounds(500, 500, 400, 300);
        setTitle("GridLayoutDemo");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton[] jbs = new JButton[10];

        JLabel label = new JLabel();
        setLayout(new GridLayout(4, 3));
        for (int i = 0; i < jbs.length; i++) {
            jbs[i] = new JButton("#" + i);
            // навешиваем событие, их очень много после точки, можно выбирать
            jbs[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(((JButton) e.getSource()).getText()); // так можно достучаться до названия кнопки
                    double random = Math.random();
                    label.setText(String.valueOf(random)); // с помощью сетеров можно взаимодей-вать с копонентами окна
                }
            });
            add(jbs[i]);
        }
        add(label);
        add(anotherLabel);
        setVisible(true);
    }
}

class AnotherObject {
    private JLabel anotherLabel;

    public AnotherObject(JLabel anotherLabel) {
        this.anotherLabel = anotherLabel;
    }

    public void setText(String text) {
        anotherLabel.setText(text);
    }
}
