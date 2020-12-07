package lesson3_collections.teacherExample;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Task1 {
    private static final String[] INPUT_DATA = {
            "a",
            "b", "b", "b",
            "c",
            "d",
            "e", "e",
            "f",
            "g", "g", "g",
            "h",
            "g",
            "i"
    };

    // Выражение: (++переменная) эквивалентно (переменная + 1) в sout
    public static void main(String[] args) {
        Map<String, Integer> frequencyByWord = new LinkedHashMap<>();
        for (String word : INPUT_DATA) {
            // example1
//            Integer frequency = frequencyByWord.get(word);
//            if (frequency == null) {
//                frequency = 0;
//            }
//            frequencyByWord.put(word, ++frequency);

            // example2
            // метод getOrDefault() возвращает значение по ключю или дефолтное заданное при вызове метода
//            Integer frequency = frequencyByWord.getOrDefault(word, 0);
//            frequencyByWord.put(word, frequency + 1);

            // example3
            // Анонимный класс (функция), реализующий interface BiFunction
//            frequencyByWord.merge(word, 1, new BiFunction<Integer, Integer, Integer>() {
//                @Override
//                public Integer apply(Integer oldValue, Integer newValue) {
//                    return oldValue + newValue;
//                }
//            });

            // example3 лямда выражение предыдущего кода
//            frequencyByWord.merge(word, 1, (oldValue, newValue) -> oldValue + newValue);

            // example3 это конечный код lesson4 14h15m
            frequencyByWord.merge(word, 1, Integer::sum);
        }

        // example1
//        for (String word : frequencyByWord.keySet()) {
//            Integer frequency = frequencyByWord.get(word);
//            System.out.println(word + ": " + frequency);
//        }

        // example2
        frequencyByWord.forEach((word, frequency) -> {
            System.out.println(word + ": " + frequency);
        });
    }
}