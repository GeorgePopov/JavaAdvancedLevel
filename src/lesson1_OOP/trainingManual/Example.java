package lesson1_OOP.trainingManual;

import java.util.Arrays;

/**
 * Очень интересный пример с созданием метода putMeInArray !
 */
public class Example {
    public static void main(String[] args) {
        Cat cat1 = new Cat("A1", "B1", 1);
        Cat cat2 = new Cat("A2", "B2", 2);
        Cat[] cats = new Cat[2];

        cat1.putMeInArray(cats, 0);
        cat2.putMeInArray(cats, 1);

        System.out.println(Arrays.toString(cats));
    }
}