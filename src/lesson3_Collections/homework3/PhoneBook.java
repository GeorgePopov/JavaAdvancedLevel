package lesson3_Collections.homework3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PhoneBook {

    Map<Surname, String> phoneBook;

    public PhoneBook(Map<Surname, String> phoneBook) {
        this.phoneBook = phoneBook;
    }

    public void addContact(Surname surname, String phoneNumber) {
        if (phoneBook.isEmpty()) {
            phoneBook.put(surname, phoneNumber);
        }
        else {
            for (Surname contact : phoneBook.keySet()) {
                if (surname.equals(contact)) {
                    System.out.println("Such contact already exists!");
                    return;
                }
                else {
                    phoneBook.put(surname, phoneNumber);
                    break;
                }
            }
        }
    }

    public String printGetNumber(String surname) {
        return  "phone numbers under the name " + surname + ": " + getPhoneNumber(surname);
    }


    public List<String> getPhoneNumber(String surname) {
        List<String> phoneNumber = new ArrayList<>();

        // этот код почему-то не работает? узнать в телеге!
//        for (Surname contact : phoneBook.keySet()) {
//            if (surname.equals(contact.getSurname())) {
//                phoneNumber.add(phoneBook.get(contact));
//            }
//        }

        phoneBook.forEach((k, v) -> {
            if (surname.equals(k.getSurname())) {
                phoneNumber.add(v);
            }
        });

        return phoneNumber;
    }
}