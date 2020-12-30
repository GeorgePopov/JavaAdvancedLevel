package lesson1_OOP.teacherExample.obstacle;

import lesson1_OOP.teacherExample.participant.Participant;

public class Wall implements Obstacle{
    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean passObstacleBy(Participant participant) {
        if (participant.jump() > height) {
            System.out.println("Participant " + participant + " successful jumped wall");
            return true;
        }
        else {
            System.out.println("Participant " + participant + " did not jumped wall " + height);
            return false;
        }
    }
}