package lesson3_collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Map не экстендит Collection, в отличии о других коллекций.
 * Ключи в Map это только уникальные значения.
 * В основе коллекции Map лежит массив.
 * При работе с HashMap необходимо перегружать в нашем классе методы equals & hasCode.
 * value мы получаем по key.
 */
public class HasMapExample {

    public static void main(String[] args) {
//        Map<String, String> hm = new HashMap<>(); // в основе обычный массив
        Map<String, String> hm = new LinkedHashMap<>();
//        Map<String, String> hm = new TreeMap<>();

        hm.put("Russia", "Moscow"); // вместо метода add
//        hm.put("Russia", "Smolensk"); // Smolensk перепишет значение Moscow
        hm.put("France", "Paris");
        hm.put("Germany", "Berlin");
        hm.put("Norway", "Oslo");

        for (String key : hm.keySet()) { // keySet() получаем множество ключей
            String value = hm.get(key); // get(key) получаем значеие ключа
            System.out.println(key + " - " + value);
        }

        // новые технологии обхода HashMap
        hm.forEach((k, v) -> {
            System.out.println(k + ": " + v);
            });


    }
}