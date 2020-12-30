package lesson1_OOP.homework.obstacles;

import lesson1_OOP.homework.competitors.Competitor;

public class Wall implements Obstacle {
    private final double wallHeight;

    public Wall(double wallHeight) {
        this.wallHeight = wallHeight;
    }

    @Override
    public boolean passObstacle(Competitor competitor) {
        boolean result = false;
        if (competitor.jump() > wallHeight) {
            System.out.printf("%s overcome an obstacle \n", competitor);
            result = true;
        }
        else {
            System.out.printf("%s did not overcome the obstacle \n", competitor);
        }
        return result;
    }

    @Override
    public void obstacleInfo() {
        System.out.println("Wall " + wallHeight);
    }
}
