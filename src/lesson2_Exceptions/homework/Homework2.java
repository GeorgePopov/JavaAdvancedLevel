package lesson2_exceptions.homework;

import java.util.Arrays;

/**
 * 1. Напишите метод, на вход которого подается двумерный строковый массив размером 4х4,
 * при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
 * 2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и
 * просуммировать. Если в каком-то элементе массива преобразование не удалось (например,
 * в ячейке лежит символ или текст вместо числа), должно быть брошено исключение
 * MyArrayDataException – с детализацией, в какой именно ячейке лежат неверные данные.
 * 3. В методе main() вызвать полученный метод, обработать возможные исключения
 * MySizeArrayException и MyArrayDataException и вывести результат расчета.
 *
 * !*!*! Здесь у меня крутой вариант работы с двумерными массивами, здесь можно изменять и
 * колличество сторок и колличество столбцов, и весь массив и работа с ним динамически меняется.
 */
public class Homework2 {

    private static int SIZE = 4;
    private static String[][] array = new String[4][4];

    public static void main(String[] args) {

        try {
            checkingArraySize(array);
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        }

        fullingArray(array);
//        array[2][2] = "A";

        try {
            changeArray(array);
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkingArraySize(String[][] array) {
        if (array.length != SIZE) {
            throw new MyArraySizeException();
        }

        for (String[] o : array) {
            if (o.length != SIZE) {
                throw new MyArraySizeException();
            }
        }
    }

    private static void fullingArray(String[][] array) {
        String variable = "1";

        for (String[] o : array) {
            // упрощённая конструкция !
            Arrays.fill(o, variable);
//            for (int i = 0; i < o.length; i++) {
//                o[i] = variable;
//            }
        }

        printArray(array);
    }

    private static void changeArray(String[][] array) throws MyArrayDataException {
        int sumArray = 0;

        for (String[] o : array) {
            // упрощённая конструкция !
            for (String s : o) {
//          for (int i = 0; i < o.length; i++) {
                sumArray += Integer.parseInt(s);
            }
        }

        System.out.println("The sum of all components of the array is " + sumArray);
    }

    private static void printArray(String[][] array) {
        for (String[] o : array) {
            // упрощённая конструкция !
            for (String s : o) {
//          for (int i = 0; i < o.length; i++) {
                System.out.print(s + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}