package lesson1_OOP.homework.competitors;

public class Robot implements Competitor {
    private final String model;
    private final int runLimit;
    private final double jumpLimit;

    public Robot(String model, int runLimit, double jumpLimit) {
        this.model = model;
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
    }

    @Override
    public int run() {
        System.out.println("Robot " + model + " run");
        return runLimit;
    }

    @Override
    public double jump() {
        System.out.println("Robot " + model + " jump");
        return jumpLimit;
    }

    @Override
    public String toString() {
        return "Robot " + model;
    }

    @Override
    public void competitorInfo() {
        System.out.printf(this + ", run " + runLimit + ", jump " + jumpLimit + "\n");
    }
}
