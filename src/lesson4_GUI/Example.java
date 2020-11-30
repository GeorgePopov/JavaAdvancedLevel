package lesson4_GUI;

import javax.swing.*;

/**
 * Хорошим тоном считается запуск приложения с помощью утилитного метода invokeLater() из класса
 * SwingUtilities где в реализации интерфейса Runnable(), необходимо описать запуск приложения.
 */
public class Example {
    // в момент создания окна создаётся новый поток, а основной поток main
    // продолжает работать паралельно выполяет свои действия и завершается
    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new MyWindowBorder1();
//            }
//        });

        // сокращенные варианты лямда выражение и еще короче метод вызова конструкора
//        SwingUtilities.invokeLater(() -> new MyWindowBorder1());
        SwingUtilities.invokeLater(MyWindow::new);
        System.out.println("By main!");
    }
}

class MyWindow extends JFrame {
    public MyWindow() {
//        super("My Window");
        setTitle("My Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setSize(400, 400); // размер
        setBounds(300, 300, 400, 400); // размещение и размер
        setVisible(true);
    }
}