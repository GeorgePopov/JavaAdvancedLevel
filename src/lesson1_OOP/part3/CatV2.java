package lesson1_OOP.part3;

import lesson1_OOP.part2.Animal;

public class CatV2 extends Animal implements Pet{

    private final String color;

    public CatV2(String name, String color) {
        super(name);
        this.color = color;
    }

    @Override
    public void animalInfo() {
        super.animalInfo(); // call the parent implementation animalInfo()
        System.out.println("Cat name is " + super.getName() + "; color - " + color);
        // т.к. своего поля name у класса Cat нет, мы через super и getName() обращаемся к полю name родителя класса AnimalEx1
    }

    @Override
    public void voice() {
        System.out.println("May");
    }

    @Override
    public void loveMaster() {
        System.out.println("May May May");
    }

    @Override
    public boolean isUseful() {
        return false;
    }
}
