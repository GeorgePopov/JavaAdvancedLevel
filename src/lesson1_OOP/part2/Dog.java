package lesson1_OOP.part2;

public class Dog extends Animal {
    private final String color;
    private final String type;

    public Dog(String name, String color, String type) {
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
}