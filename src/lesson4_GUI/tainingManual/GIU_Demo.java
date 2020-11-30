package lesson4_GUI.tainingManual;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GIU_Demo extends JFrame {
    public GIU_Demo() {
        setBounds(500, 200, 800, 600);
        setTitle("GUI Demo");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2));
        JPanel[] jp = new JPanel[4];
        for (int i = 0; i < jp.length; i++) {
            jp[i] = new JPanel();
            add(jp[i]);
            jp[i].setBackground(new Color(100 + i * 40, 100 + i * 40, 100 + i * 40));
        }

        // first panel settings
        jp[0].setLayout(new BorderLayout());
        JTextArea jta = new JTextArea();
        JScrollPane jsp = new JScrollPane(jta);
        jp[0].add(jsp);

        // second panel settings
        jp[1].setLayout(new FlowLayout());
        JRadioButton[] jrb = new JRadioButton[4];
        ButtonGroup bgr = new ButtonGroup();
        for (int i = 0; i < jrb.length; i++) {
            jrb[i] = new JRadioButton("Option #" + i);
            bgr.add(jrb[i]);
            jp[1].add(jrb[i]);
        }
        JCheckBox[] jcb = new JCheckBox[4];
        for (int i = 0; i < jcb.length; i++) {
            jcb[i] = new JCheckBox("Check #" + i);
            jp[1].add(jcb[i]);
        }

        // third panel settings
        jp[2].setLayout(new FlowLayout());
        String[] comboStr = {"Item #1", "Item #2", "Item #3", "Item #4"};
        JComboBox<String> jComboBox1 = new JComboBox<>(comboStr);
        JComboBox<String> jComboBox2 = new JComboBox<>(comboStr);
        jp[2].add(jComboBox1);
        jp[2].add(jComboBox2);
        jComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(jComboBox1.getSelectedItem().toString());
            }
        });

        // fourth panel settings
        jp[3].setLayout(null);
        JSlider js= new JSlider();
        JLabel jLabel = new JLabel("Value: 50");
        js.setMaximum(100);
        js.setMinimum(0);
        js.setValue(50);
        jp[3].add(jLabel);
        jp[3].add(js);
        js.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                jLabel.setText("Value: " + js.getValue());
            }
        });

        jLabel.setBounds(10, 10, 100, 20);
        js.setBounds(20, 40, 300, 100);
        js.setBackground(new Color(160, 255, 160));

        // top menu settings
        JMenuBar mainMenu = new JMenuBar();
        JMenu mFile = new JMenu("File");
        JMenu mEdit = new JMenu("Edit");

        JMenuItem miFileNew = new JMenuItem("New");
        JMenuItem miFileExit = new JMenuItem("Exit");
        setJMenuBar(mainMenu);
        mainMenu.add(mFile);
        mainMenu.add(mEdit);
        mFile.add(miFileNew);
        mFile.addSeparator(); // разделительна линия в меню
        mFile.add(miFileExit);

        miFileExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("BYE");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new GIU_Demo();
    }
}