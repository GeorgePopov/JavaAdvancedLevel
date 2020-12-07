package lesson3_collections;

import java.util.*;

/**
 * В основе коллекции Set лежит Map. Set это Map, только ключ (а он уникален) без значения,
 * отсюда следует уникальность Set.
 * Те коллекции которые поддерживают интерфейс Set, хранят в себе только уникальные значения.
 * В Set элементы не будут храниться в томже порядке, что мы их и добавляли.
 */
public class SetExample {
    public static void main(String[] args) {
//        Set<String> set = new HashSet<>();
//         LinkedHashSet эл-ты храняться в хаотично, при обходе эл-тов востанавливает порядок который был при добавлении
        // данные хранятся как в HashSet, но у каждого эл-та есть обертка с сылками
        Set<String> set = new LinkedHashSet<>();
        // TreeSet поддерживает определённый порядок эл-тов (например упорядочивает по алфавиту)
//        Set<String> set = new TreeSet<>();
//        Set<String> set = new TreeSet<>(Comparator.reverseOrder()); // по возрастанию

        set.add("Альфа");
        set.add("Бетта");
        set.add("Альфа");
        set.add("Этта");
        set.add("Гамма");
        set.add("Эпсилом");
        set.add("Омега");
        set.add("Гамма");
        System.out.println(set);

        System.out.println("--------");
    }
}