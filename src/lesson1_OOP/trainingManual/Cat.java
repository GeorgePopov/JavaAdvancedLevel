package lesson1_OOP.trainingManual;

public class Cat {
    private String name;
    private String color;
    private int age;

    public Cat(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public void putMeInArray(Cat[] cats, int arrayIndex) {
        cats[arrayIndex] = this; // this указывает нп объект у которого мы вызываем метод !
    }

    @Override
    public String toString() {
        return name + " " + color + " " + age;
    }
}