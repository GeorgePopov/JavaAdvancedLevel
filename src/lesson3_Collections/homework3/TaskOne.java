package lesson3_collections.homework3;

import java.util.*;

/**
 * 1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
 * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
 * Посчитать, сколько раз встречается каждое слово.
 */
public class TaskOne {
    public static void main(String[] args) {

        String[] arrayMonths = {"August", "March", "June", "August", "Jule", "October", "November", "September",
                "Jule", "April", "January", "June", "October", "August", "February", "December", "Mai", "October"};

        List<String> listMonths = Arrays.asList(arrayMonths);
        Set<String> singleMonths = new LinkedHashSet<>();

        singleMonths(listMonths, singleMonths);
        countMonth(listMonths, singleMonths);

    }

    private static void singleMonths(List<String> listMonths, Set<String> singleMonths) {

        singleMonths.addAll(listMonths);

        System.out.println(listMonths);
        System.out.println(singleMonths);

        // предыдущая версия, где использовались две коллекции List, я использовал метод contains()
//        for (String month : listMonths) {
//            if (!singleMonths.contains(month)) {
//                singleMonths.add(month);
//            }
//        }
    }

    private static void countMonth(List<String> listMonths, Set<String> singleMonths) {
        int countMonth = 0;

        for (String month : singleMonths) {
            for (String anotherMonth : listMonths) {
                if (month.equals(anotherMonth)) {
                    countMonth++;
                }
            }

            System.out.println(month + " " + countMonth);
            countMonth = 0;
        }
    }
}