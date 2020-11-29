package lesson3_Collections;

import java.util.*;

public class workWithCollections {
    public static void main(String[] args) {

//        collectionTransformation();
//        collectionListEquals();
//        collectionSetEquals();
//        collectionTraversal();
    }

    private static void collectionTransformation() {
        // преобразовываем коллекцию типа Integer в String, проще всего создать новыую и перезаписать значения
        List<Integer> listInteger = new ArrayList<>();
        listInteger.add(1);
        listInteger.add(2);
        listInteger.add(3);

        List<String> listString = new ArrayList<>();

        for (Integer o : listInteger) {
            listString.add(String.valueOf(o));
        }

        System.out.println(listString);
    }

    private static void collectionListEquals() {
        // колекции List будут равные если и все элементы в том же порядке
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(3);
        list2.add(1);

        System.out.println("equals: " + list1.equals(list2));
    }

    private static void collectionSetEquals() {
        // колекции Set будут равны, элементы могут быть в любом порядке
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        Set<Integer> set2 = new HashSet<>();
        set2.add(2);
        set2.add(3);
        set2.add(1);

        System.out.println("equals: " + set1.equals(set2));
    }

    private static void collectionTraversal() {
        List<String> newDataName = new ArrayList<>();
        newDataName.add("B");
        newDataName.add("A");
        newDataName.add("C");
        newDataName.add("E");
        newDataName.add("D");
        newDataName.add("E");
        newDataName.add("E");

        for (String next : newDataName) {
            System.out.print(next + " ");
        }
        System.out.println();

        // аналогичная работа for-ech в развернутом виде с помощью интерфейса Iterator
        Iterator<String> iterator = newDataName.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.print(next + " ");
        }
    }
}