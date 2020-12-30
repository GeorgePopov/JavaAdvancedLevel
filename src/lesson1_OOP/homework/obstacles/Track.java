package lesson1_OOP.homework.obstacles;

import lesson1_OOP.homework.competitors.Competitor;

public class Track implements Obstacle {
    private final int trackLength;

    public Track(int lengthRun) {
        this.trackLength = lengthRun;
    }

    @Override
    public boolean passObstacle(Competitor competitor) {
        boolean result = false;
        if (competitor.run() > trackLength) {
            System.out.printf("%s overcome an obstacle \n", competitor); // можно %s
            result = true;
        }
        else {
            System.out.printf("%s did not overcome the obstacle \n", competitor);
        }
        return result;
    }

    @Override
    public void obstacleInfo() {
        System.out.println("Track " + trackLength);
    }
}
