package lesson1_OOP.teacherExample.obstacle;

import lesson1_OOP.teacherExample.participant.Participant;

public class Track implements Obstacle {
    private final int length;

    public Track(int length) {
        this.length = length;
    }

    @Override
    public boolean passObstacleBy(Participant participant) {
        if (participant.run() > length) {
            System.out.println("Participant " + participant + " successful run distance");
            return true;
        }
        else {
            System.out.println("Participant " + participant + " did not run distance " + length);
            return false;
        }
    }
}