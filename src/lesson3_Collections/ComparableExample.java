package lesson3_Collections;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class ComparableExample {
    public static void main(String[] args) {

        // в конструкторе можно задать обратный порядок
        Set<Person> personSet = new TreeSet<>(Comparator.reverseOrder());

        // разобраться как это работает
//        Set<Person> personSet = new TreeSet<>(Comparator.comparing(Person::getName));
//        Set<Person> personSet = new TreeSet<>(Comparator.comparing(person -> person.getName()));
//        Set<Person> personSet = new TreeSet<>(Comparator.comparing(new Function<Person, String>() {
//            @Override
//            public String apply(Person person) {
//                return person.getName();
//            }
//        }));

        Person sam = new Person("Sam", "Woc", 27);
        Person zara = new Person("Zara", "Auc", 25);
        Person bob = new Person("Bob", "Roc", 29);
        Person alex = new Person("Alex", "Jec", 31);

        // if the surnames are the same
//        Person sam = new Person("Sam", "Woc", 27);
//        Person zara = new Person("Zara", "Woc", 25);
//        Person bob = new Person("Bob", "Woc", 29);
//        Person alex = new Person("Alex", "Woc", 31);

        personSet.add(sam);
        personSet.add(zara);
        personSet.add(bob);
        personSet.add(alex);

        for (Person person : personSet) {
            System.out.println(person);
        }
    }
}