package lesson1_OOP.homework.competitors;

public class Cat implements Competitor {
    private final String name;
    private final int runLimit;
    private final double jumpLimit;

    public Cat(String name, int runLimit, double jumpLimit) {
        this.name = name;
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
    }

    @Override
    public int run() {
        System.out.println("Cat " + name + " run");
        return runLimit;
    }

    @Override
    public double jump() {
        System.out.println("Cat " + name + " jump");
        return jumpLimit;
    }

    @Override
    public String toString() {
        return "Cat " + name;
    }

    @Override
    public void competitorInfo() {
        System.out.printf(this + ", run " + runLimit + ", jump " + jumpLimit + "\n");
    }
}
