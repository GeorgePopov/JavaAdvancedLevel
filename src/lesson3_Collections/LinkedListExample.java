package lesson3_collections;

import java.util.Collections;
import java.util.LinkedList;

/**
 * LinkedList создаётся на основе 'связанного списка'
 */
public class LinkedListExample {
    public static void main(String[] args) {

        LinkedList<String> linkedList = new LinkedList<>(); // List<String> linkedList = new LinkedList<>();
        linkedList.add("F");
        linkedList.add("B");
        linkedList.add("D");
        linkedList.add("E");
        linkedList.add("C");

        linkedList.addLast("Z");
        linkedList.addFirst("A");
        System.out.println(linkedList);

        linkedList.add(1, "A2");
        System.out.println("1. linkedList: " + linkedList);

        linkedList.remove("F");
        linkedList.remove(2);
        System.out.println("2. linkedList: " + linkedList);

        linkedList.removeFirst();
        linkedList.removeLast();
        System.out.println("3. linkedList: " + linkedList);

        String val = linkedList.get(2);
        linkedList.set(2, val + " change");
        System.out.println("4. linkedList: " + linkedList);

        Collections.shuffle(linkedList); // перемешивает элементы в хатичном порядке
        System.out.println("random: " + linkedList);

        Collections.sort(linkedList); // сортирует по порядку
        System.out.println("by order " + linkedList);

        linkedList.sort(null); // тоже сортировка, если передать null, то сортироваться будет так как есть изначально
        System.out.println(linkedList);
    }
}