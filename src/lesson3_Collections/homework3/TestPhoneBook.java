package lesson3_collections.homework3;

import java.util.LinkedHashMap;

/**
 * 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
 * В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать
 * номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов
 * (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
 * Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись добавлять
 * еще дополнительные поля (имя, отчество, адрес), делать взаимодействие с пользователем через консоль и т.д.).
 * Консоль желательно не использовать (в том числе Scanner), тестировать просто из метода main(),
 * прописывая add() и get().
 */
public class TestPhoneBook {
    public static void main(String[] args) {

        PhoneBook phoneBook = new PhoneBook(new LinkedHashMap<>());

        phoneBook.addContact(new Surname("Abram"), "215-46-89");
        phoneBook.addContact(new Surname("Harley"), "215-89-52");
        phoneBook.addContact(new Surname("Kelsey"), "213-30-98");
        phoneBook.addContact(new Surname("Dayton"), "215-11-45");
        phoneBook.addContact(new Surname("Kelsey"), "217-15-98");
        phoneBook.addContact(new Surname("Garfield"), "219-74-15");
        phoneBook.addContact(new Surname("Abram"), "219-30-26");
        phoneBook.addContact(new Surname("Kelsey"), "211-23-23");
        phoneBook.addContact(new Surname("Dayton"), "211-09-57");

        // этоти коды почему-то не работают? узнать в телеге!
//        for (Surname contact : phoneBook.phoneBook.keySet()) {
//            System.out.println(contact + " " + phoneBook.phoneBook.get(contact));
//        }

//        for (Surname key : phoneBook.phoneBook.keySet()) {
//            String value = phoneBook.phoneBook.get(key);
//            System.out.println(key + " - " + value);
//        }

        phoneBook.phoneBook.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });

        System.out.println(phoneBook.printGetNumber("Kelsey"));
        System.out.println(phoneBook.printGetNumber("Garfield"));
        System.out.println(phoneBook.printGetNumber("Abram"));

    }
}
