package lesson3_collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Коллекции List могут содержать дубликаты элементов.
 * В коллекции нельзя использовать примитивные типы, только их обёртки.
 * ArrayList создаётся на основе массива.
 */
public class ArrayListExample {
    public static void main(String[] args) {

//        arrayExample();
//        arrayListExample();
//        arrayListToArray();

    }

    private static void arrayExample() {
        Integer[] arr = new Integer[4]; //+5
//        int[] arr = {1, 2, 3, 4};
//        int[] arrNew = new int[10];
// копируем элементы из предыдущего массива в новый с помощью статического метода arraycopy()
//        System.arraycopy(arr, 0, 0, arrNew.length);
        Integer[] arrNew = Arrays.copyOf(arr, 10); // копируем старый массив и указываем длину нового
        arr = arrNew;
        arr[4] = 5;
//        arrNew = null;
        System.out.println(Arrays.toString(arrNew)); // method for outputting information about the array to the console
    }

    private static void arrayListExample() {
        // желательно использовать List, если не нужны методы ArrayList
        List<String> newDataName = new ArrayList<>(5); // можно задавать изначальный размер
        // add
        newDataName.add("B");
        newDataName.add("A");
        newDataName.add("C");
        newDataName.add("E");
        newDataName.add("D");
        newDataName.add("E");
        newDataName.add("E");
        System.out.println(newDataName);

        // remove
        newDataName.remove("E"); // remove only first element 'E'
        newDataName.remove(2); // remove element by index
        System.out.println(newDataName);

        System.out.println("contains C? " + newDataName.contains("C")); // содержится ли эл-т в кол-ции?

        // work with a cycle
        for (int i = 0; i < newDataName.size(); i++) { // size() текущее кол-во эллементов в кол-ции
            System.out.print(newDataName.get(i) + " "); // get() олучаем значение эл-та
        }
        System.out.println();
        for (String nex : newDataName) {
            System.out.print(nex + " ");
        }
        System.out.println();

        // sorting a collection
        // можно создать собственный метод сортировки
        newDataName.sort(Comparator.naturalOrder()); // отсартирует в порядке возрастания
        newDataName.sort(Comparator.reverseOrder()); // отсартирует в порядке убывания
        System.out.println(newDataName);


    }

    private static void arrayListToArray() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Integer[] arr = new Integer[list.size()]; // создаём массив на необходимое колличество эл-тов
        list.toArray(arr); // с помощмью метода toArray() переписываем значение коллекции в массив
        System.out.println(Arrays.toString(arr));

        List<Integer> arr2 = Arrays.asList(arr); // преобразовываем массив в коллекцию с помощью asList()
        System.out.println(arr2);

    }

}