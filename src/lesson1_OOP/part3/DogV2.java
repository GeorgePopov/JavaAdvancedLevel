package lesson1_OOP.part3;

import lesson1_OOP.part2.Animal;

public class DogV2 extends Animal implements Pet, Waterfowl {
    private final String color;
    private final String type;

    public DogV2(String name, String color, String type) {
        super(name); // super(); // coll constructor with no parameters
        this.color = color;
        this.type = type;
    }

    @Override
    public void animalInfo() {
        System.out.println("Dog name is " + super.getName() + "; color - " + color + "; type - " + type);
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    @Override
    public void voice() {
        System.out.println("Gav");
    }

    @Override
    public void loveMaster() {
        System.out.println("Gav Gav Gav");
    }

    @Override
    public boolean isUseful() {
        return true;
    }

    @Override
    public int swim() {
        System.out.println("swimming doggy stile");
        return Waterfowl.DEFAULT_SWIM_LENGTH;
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
