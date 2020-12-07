package lesson3_collections;

public class Person implements Comparable<Person> {
    private String name;
    private String surname;
    private int age;

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + age + " years old";
    }

    @Override
    public int compareTo(Person anotherPerson) {
//        return this.name.compareTo(anotherPerson.getName()); // comparison by name

        int compareBySurname = this.surname.compareTo(anotherPerson.getSurname()); // if the surnames are the same
        return compareBySurname != 0 ? compareBySurname : this.getName().compareTo(anotherPerson.getName());
    }
}
