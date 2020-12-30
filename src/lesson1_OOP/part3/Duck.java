package lesson1_OOP.part3;

import lesson1_OOP.part2.Animal;

public class Duck extends Animal implements Pet, Waterfowl {

    public static final int SWIM_LENGTH = 150;

    public Duck(String name) {
        super(name);
    }

    @Override
    public void voice() {
        System.out.println("Cry");
    }

    @Override
    public void loveMaster() {
        System.out.println("Cry Cry Cry");
    }

    @Override
    public boolean isUseful() {
        return true;
    }

    @Override
    public int swim() {
        System.out.println("Duck swimming");
        return SWIM_LENGTH;
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
