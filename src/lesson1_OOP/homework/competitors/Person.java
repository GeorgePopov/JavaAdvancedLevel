package lesson1_OOP.homework.competitors;

public class Person implements Competitor {
    private final String name;
    private final int runLimit;
    private final double jumpLimit;

    public Person(String name, int runLimit, double jumpLimit) {
        this.name = name;
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
    }

    @Override
    public int run() {
        System.out.println("Person " + name + " run");
        return runLimit;
    }

    @Override
    public double jump() {
        System.out.println("Person " + name + " jump");
        return jumpLimit;
    }

    @Override
    public String toString() {
        return "Person " + name;
    }

    @Override
    public void competitorInfo() {
        System.out.printf(this + ", run " + runLimit + ", jump " + jumpLimit + "\n");
    }
}
