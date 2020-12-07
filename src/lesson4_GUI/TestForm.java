package lesson4_GUI;

import javax.swing.*;

public class TestForm {
    private JButton button1;
    private JList list1;
    private JComboBox comboBox1;
    private JTextPane textPane1;
    private JPanel mainPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("My MyPractice");
        frame.setContentPane(new TestForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(300, 300, 800, 800);
        frame.setVisible(true);
    }
}
