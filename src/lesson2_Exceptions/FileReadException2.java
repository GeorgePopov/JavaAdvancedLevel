package lesson2_Exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * try можно использовать с finally без catch
 */
public class FileReadException2 {
    public static void main(String[] args) {
//        FileInputStream fis = null;
        try (FileInputStream fis = new FileInputStream("test.txt")) { // вместо двух закомиченных строк
//            fis = new FileInputStream("test.txt");
            // здесь часть кода не работает readAllBytes()
            byte[] bytes/* = fis.readAllBytes()*/;
            System.out.println(new String(/*bytes*/)); // !*!*! вот так можно вывести массив байтов в консоль
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Файл не был найден");
        } catch (IOException e) {
            System.out.println("Файл повреждён");
            // !*!*! это громозская конструкция ↓, которую можно заменить на 'try with resources'
            // 'трай с ресурсами' - в круглых скобках после трай помещаедся код, и он автоматически закрывается
            // !*!*! класс должен наследовать интерфейс AutoCloseable, тогда можно применять 'try with resources'
//        } finally {
//            try {
//                if (fis != null) {
//                    fis.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}
