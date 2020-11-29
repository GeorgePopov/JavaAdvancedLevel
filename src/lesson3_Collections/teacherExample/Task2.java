package lesson3_Collections.teacherExample;

import java.util.Set;

public class Task2 {

    public static void main(String[] args) {

        IPhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Krylov", "777-77-77");
        phoneBook.add("Ivanov", "555-55-55");
        phoneBook.add("Abrams", "987-11-69");
        phoneBook.add("Petrov", "666-66-66");
        phoneBook.add("Potter", "430-00-00");
        phoneBook.add("Abrams", "264-98-76");
        phoneBook.add("Ivanov", "888-88-88");
        phoneBook.add("Abrams", "343-23-00");

        Set<String> allSurnames = phoneBook.getAllSurname();
        for (String surname : allSurnames) {
            Set<String> phones = phoneBook.get(surname);
            System.out.printf("%s: %s%n", surname, phones);
        }
    }
}