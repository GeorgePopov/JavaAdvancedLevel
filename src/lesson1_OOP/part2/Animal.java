package lesson1_OOP.part2;

public abstract class Animal extends Object {

    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    public Animal() {
        name = "Default name";
    }

    public void animalInfo() {
        System.out.println("AnimalEx1 name is " + name);
    }

    public void jump() {
        System.out.println("AnimalEx1 jumped");
    }

    public String getName() {
        return name;
    }

    public abstract void voice();
}