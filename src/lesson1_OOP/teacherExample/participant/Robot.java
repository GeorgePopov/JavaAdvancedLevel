package lesson1_OOP.teacherExample.participant;

public class Robot implements Participant {
    private final String model;
    private final int jumpHeight;
    private final int maxRunLength;

    public Robot(String model, int jumpHeight, int maxRunLength) {
        this.model = model;
        this.jumpHeight = jumpHeight;
        this.maxRunLength = maxRunLength;
    }

    @Override
    public int run() {
        System.out.printf("Robot %s ran distance %d%n", model, maxRunLength);
        return maxRunLength;
    }

    @Override
    public int jump() {
        System.out.printf("Robot %s jumped %d%n", model, jumpHeight);
        return jumpHeight;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "name ='" + model + '\'' +
                ", jumpHeight=" + jumpHeight +
                ", maxRunLength=" + maxRunLength +
                '}';
    }
}