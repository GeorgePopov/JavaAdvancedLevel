package lesson3_Collections.teacherExample;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// если класс реализации и интерфейса совпадает, можно вначале названия инрф-са поставить букву I
public class PhoneBook implements IPhoneBook {

    private final Map<String, Set<String>> phonesBySurname; // кол-цию надо называть так что бы понимать что в ней

    public PhoneBook() {
        phonesBySurname = new TreeMap<>();
    }

    @Override
    public void add(String surname, String phoneNumber) {
        Set<String> phones = getPhones(surname);
        phones.add(phoneNumber);
    }

    private Set<String> getPhones(String surname) {
//        Set<String> phones = phonesBySurname.getOrDefault(surname, new HashSet<>());
//        if (phones.isEmpty()) {
//            phonesBySurname.put(surname, phones);
//        }

//        return phones;

        // упрощйнная версия с посощью метода computeIfAbsent() данный метод возвращает значение по ключу,
        // если оно имеется, если нет новое значение будет new HashSet<>(). (функция, лямда выражение)
        // !*!*! СМОТРЕТЬ ОПИСАНИЕ МЕТОДА, ТАМ ЕСТЬ ПРИМЕР КАК ОН РАБОТАЕТ, ВООБЩЕ СМОТРЕТЬ ОПИСАНИЕ МЕТОДОВ
        return phonesBySurname.computeIfAbsent(surname, key -> new HashSet<>());
    }

    @Override
    public Set<String> get(String surname) {
        return getPhones(surname);
    }

    @Override
    public Set<String> getAllSurname() {
        return phonesBySurname.keySet();
    }
}
